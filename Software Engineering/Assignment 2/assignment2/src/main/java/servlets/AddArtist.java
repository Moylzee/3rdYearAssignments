package servlets;

import bean.artist;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddArtist", urlPatterns = {"/AddArtist"})
public class AddArtist extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Initialize Variables         
        String fName = request.getParameter("fName");
        String sName = request.getParameter("sName");
        String nationality = request.getParameter("nationality");
        String bYear = request.getParameter("bYear");
        String dYear = request.getParameter("dYear");
        String bio = request.getParameter("bio");
        String pURL = request.getParameter("pURL");
        
        HttpSession session = request.getSession();

        List<artist> artistList = (List<artist>)session.getAttribute("artistList");
        
        if (artistList == null) {
            artistList = new ArrayList<>();
        }
        artistList.add(new artist(fName, sName, nationality, bYear, dYear, bio, pURL));
        
        session.setAttribute("artistList", artistList);
        request.getRequestDispatcher("/DisplayArtists.jsp").forward(request, response);
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
