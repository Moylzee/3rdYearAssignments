package servlets;

import Entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetCustomers", urlPatterns = {"/GetCustomers"})
public class GetCustomers extends HttpServlet {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // Create list for the customers        
        List<Customer> customers = new ArrayList<>();
        Query q = em.createQuery("select c from Customer c");
        // Get the customers that are stored         
        customers = q.getResultList();
        HttpSession session = request.getSession();
        session.setAttribute("customers", customers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayCustomers.jsp");
        dispatcher.forward(request, response);
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
    }
// </editor-fold>
}