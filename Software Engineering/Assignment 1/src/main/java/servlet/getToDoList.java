package servlet;
import bean.Todo;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "getToDoList", urlPatterns = {"/getToDoList"})
public class getToDoList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Todo> toDoList = (List<Todo>)session.getAttribute("toDoList");
        
        try (PrintWriter out = response.getWriter()) {   
            out.println("<html>");
            out.println("<head>");
            out.println("<body>");
        // Check if the ToDo list is empty and Link option to add Tasks
        // Display the tasks in a table if there are any          
        if (toDoList == null || toDoList.isEmpty()) {
            out.println("ToDo List is Empty");
            out.println("</br>");
            out.println("<a href='addTaskList.html'>Add Task</a>");
        } else {
            out.println("<table border='1'>");
            out.println("<tr><th>Subject</th><th>Task</th></tr>");
            // iterate through array to display them
            for (Todo todo : toDoList) {
                out.println("<tr>");
                out.println("<td>" + todo.getSubject() + "</td>");
                out.println("<td>" + todo.getTask() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<a href='addTaskList.html'>Add Task</a>");
        }
            out.println("</html>");
            out.println("</head>");
            out.println("</body>");
    }
           
    }  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
