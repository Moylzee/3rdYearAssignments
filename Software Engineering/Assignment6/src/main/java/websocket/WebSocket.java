package websocket;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/server")
public class WebSocket {
    // Vars to store the sessions, Users, Messages for each forum
    private static Set<Session> sessions = new HashSet<>();
    private static HashMap<String, Set<Session>> forumSessions = new HashMap<>();
    private static HashMap<String, ArrayList<String>> forumMessages = new HashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        System.out.printf("Session opened, id: %s%n", session.getId());
        sessions.add(session);
        try {
            session.getBasicRemote().sendText("Session " + session.getId() + " now open");
        } catch (IOException ex) {}
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        JsonObject jsonMessage = Json.createReader(new java.io.StringReader(message)).readObject();
        String action = jsonMessage.getString("action");

        if ("join".equals(action)) {
            handleJoin(jsonMessage, session);
        } else if ("post".equals(action)) {
            handlePost(jsonMessage);
        } else if ("requestMessages".equals(action)) {
            handleRequestMessages(jsonMessage, session);
        }
    }

    private void handleJoin(JsonObject jsonMessage, Session session) {
        String forum = jsonMessage.getString("forum");
        sessions.add(session);
        forumSessions.computeIfAbsent(forum, k -> new HashSet<>()).add(session);
        // Send existing messages to the new user
        sendExistingMessages(session, forum);
    }

    private void handlePost(JsonObject jsonMessage) {
        String forum = jsonMessage.getString("forum");
        String message = jsonMessage.getString("message");
        // Update the forumMessages
        forumMessages.computeIfAbsent(forum, k -> new ArrayList<>()).add(message);
        // Show the messages to everyone
        broadcastMessage(forum, jsonMessage.toString());
    }

    private void handleRequestMessages(JsonObject jsonMessage, Session session) {
        String forum = jsonMessage.getString("forum");
        // Send existing messages to the requesting user
        sendExistingMessages(session, forum);
    }

    private void sendExistingMessages(Session session, String forum) {
        ArrayList<String> messages = forumMessages.getOrDefault(forum, new ArrayList<>());
        // Send existing messages to the user
        JsonObject response = Json.createObjectBuilder()
                .add("action", "messages")
                .add("messages", Json.createArrayBuilder(messages).build())
                .build();
        sendMessage(session, response.toString());
    }

    private void broadcastMessage(String forum, String message) {
        Set<Session> forumSessionSet = forumSessions.getOrDefault(forum, new HashSet<>());
        for (Session session : forumSessionSet) {
            // Check if the session is still open
            if (session.isOpen()) {
                sendMessage(session, message);
            }
        }
    }

    private void sendMessage(Session session, String message) {
        session.getAsyncRemote().sendText(message);
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) {
        System.out.printf("Session closed with id: %s%n", session.getId());
        sessions.remove(session);
        forumSessions.values().forEach(sessionsSet -> sessionsSet.remove(session));
    }
}