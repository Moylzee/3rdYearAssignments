package controller;
import entity.Artwork;
import entity.Artist;
import java.io.IOException;
import java.util.Collection;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import session.ArtistFacade;

@WebServlet(name = "Controller",
        loadOnStartup = 1,
        urlPatterns =
        {
            "/artist",
            "/chooseLanguage"
        })
public class ControllerServlet extends HttpServlet
{
    Artist selectedArtist;
    Collection<Artwork> artistArtwork;
    Collection<Artist> artists;

    @EJB
    private ArtistFacade ArtistFacade;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException
    {
        super.init(servletConfig);

        artists = ArtistFacade.findAll();
        // while loop
        for (Artist a : artists)
        {
            System.out.println(a.getArtistid());
            System.out.println(a.getSurname());
            System.out.println(a.getFirstname());
            System.out.println(a.getYob());
            System.out.println(a.getNationality());
            System.out.println(a.getBiography());
        }
        // store artist list in servlet context
        getServletContext().setAttribute("artists", artists);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String userPath = request.getServletPath();
        HttpSession session = request.getSession();

        // if artist is selected
        if (userPath.equals("/artist"))
        {
            // get artistid from request
            String artistid = request.getQueryString();
            System.out.println("artist id = " + artistid);

            if (artistid != null)
            {

                // get selected category
                selectedArtist = ArtistFacade.find(Integer.valueOf(artistid));

                // place selected category in session scope
                session.setAttribute("selectedArtist", selectedArtist);

                // get all products for selected category
                artistArtwork = selectedArtist.getArtworkCollection();

                // place category products in session scope
                session.setAttribute("artistArtwork", artistArtwork);

               request.getRequestDispatcher("/ArtistArtwork.jsp").forward(request, response);
               
               return;
            }
        } else if (userPath.equals("/chooseLanguage"))
        {
            // get language choice
            String language = request.getParameter("language");

            // place in request scope
            request.setAttribute("language", language);
        }

        String userView = (String) session.getAttribute("view");
        
        // use RequestDispatcher to forward request internally
        String url = "/" + userView + ".jsp";

        try
        {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }
}