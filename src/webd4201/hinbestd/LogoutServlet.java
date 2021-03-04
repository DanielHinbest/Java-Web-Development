package webd4201.hinbestd;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * This class logs the user out of the system
 * @author Daniel Hinbest
 * @version 1.0
 * @since 3.0 (March 3, 2021)
 */
public class LogoutServlet extends HttpServlet {
    /**
     * Signs the user out of the system
     * @param request The request from the server
     * @param response The server's response
     * @throws ServletException Thrown when the servlet fails
     * @throws IOException Thrown when the input and output fails
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        request.getRequestDispatcher("link.html").include(request, response);
        
        HttpSession session = request.getSession();
        session.invalidate();
        
        out.println("You have successfully logged out");
        
        out.close();
    }
}
