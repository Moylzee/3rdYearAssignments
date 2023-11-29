package servlets;

import Entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "FindCustomer", urlPatterns = {"/FindCustomer"})
public class FindCustomer extends HttpServlet {

    // Reference to my persistence file    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        // Read in the customer number and store it as an integer        
        String customerNumber = request.getParameter("cNum");
        int cNum = Integer.parseInt(customerNumber);
        // Find the Customer details for the corresponding Customer Number       
        Customer c = em.find(Customer.class, cNum);
        
        // If the Customer does not exist it keeps them on the find customer page         
        if (c == null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("FindCustomer.html");
            dispatcher.forward(request, response);
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("Customer", c);
            RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayCustomer.jsp");
            dispatcher.forward(request, response);
        } 
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
    }// </editor-fold>
}