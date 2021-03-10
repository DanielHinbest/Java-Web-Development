package webd4201.hinbestd;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * This class logs the user out of the system
 * @author Daniel Hinbest
 * @version 1.0 (March 10, 2021)
 * @since 3.0 
 */
public class LogoutServlet extends HttpServlet {
    /**
     * Signs the user out of the system and redirects to the login screen
     * @param request The request from the server
     * @param response The server's response
     * @throws ServletException Thrown when the servlet fails
     * @throws IOException Thrown when the input and output fails
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.removeAttribute("student");
        session.setAttribute("message", "You have successfully logged out");
        response.sendRedirect("./login.jsp");
    }
}
