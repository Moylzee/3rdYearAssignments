package servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // Takes our inputs and stores them in variables        
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");

        // Check the harcoded username and password is correct
        // If Login Details are correct then the user is directed to the ToDoList page     
        // If either input is wrong then it stays on the Login page and resets the form         
        if (username.equals("Moylzee") && pwd.equals("123")) {
            response.sendRedirect("getToDoList");
        } else {
            request.getRequestDispatcher("index.html").forward(request, response);
        } 
   }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}