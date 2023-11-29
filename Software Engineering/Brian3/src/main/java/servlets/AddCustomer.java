package servlets;
import Entity.Customer;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.NotSupportedException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.SystemException;
import jakarta.transaction.UserTransaction;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "AddCustomer", urlPatterns = {"/AddCustomer"})
public class AddCustomer extends HttpServlet {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;
    @Resource
    private UserTransaction userTransaction;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException, NotSupportedException {
        
        // Initialize Variables         
        String customerNumber = request.getParameter("cNum");
        String cName = request.getParameter("cName");
        String pNum = request.getParameter("pNum");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        String pCode = request.getParameter("pCode");
        String cLimit = request.getParameter("cLimit");

        // Parse the variables that need to be integers and doubles        
        int cNum = Integer.parseInt(customerNumber);
        double creditLimit = Double.parseDouble(cLimit);
        // Create the customer and persist it to database        
        Customer c = new Customer(cNum, cName, pNum, email, country, pCode, creditLimit);
        userTransaction.begin();
        em.persist(c);
        em.flush();
        userTransaction.commit();
        // Send user back to main page when created        
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (RollbackException ex) {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotSupportedException ex) {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (RollbackException ex) {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotSupportedException ex) {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}