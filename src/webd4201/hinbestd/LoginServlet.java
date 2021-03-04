package webd4201.hinbestd;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.http.*;
import webd4201.hinbestd.Exceptions.*;

/**
 * This class performs the processes to log in a user
 * @author Daniel
 * @version 1.0
 * @since 3.0 (March 4, 2021)
 */
public class LoginServlet extends HttpServlet {
    /**
     * Called when the form is submitted and the user login is being processed
     * @param request The request from the server
     * @param response The server's response
     * @throws IOException Thrown when the input and output fails
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = DatabaseConnect.initialize();
            Student.initialize(c);
            HttpSession session = request.getSession(true);
            Long id = null;
            String password = new String();
            
            try {
                id = Long.parseLong(request.getParameter("ID"));
                password = request.getParameter("Password");
                Student aStudent = Student.authenticate(id, password);
                
                session.setAttribute("student", aStudent);
                session.setAttribute("errors", "");
                
                response.sendRedirect("./dashboard.jsp");
            } catch (NotFoundException e) {
                StringBuffer errorBuffer = new StringBuffer();
                errorBuffer.append("<strong>Your login information is incorrect.<br/>");
                errorBuffer.append("Please try again</strong>");
                
                if (Student.isExistingLogin(id, password)) {
                    session.setAttribute("ID", id);
                    session.setAttribute("Password", password);
                } else {
                    errorBuffer.append("Invalid login id/password.</strong>");
                    session.setAttribute("ID", "");
                    session.setAttribute("Password", "");
                }
                session.setAttribute("errors", errorBuffer.toString());
                response.sendRedirect("./login.jsp");
            }
        } catch (Exception e) {
            System.out.println(e);
            String line1="<h2>A network error has occurred!</h2>";
            String line2="<p>Please notify your system " +
                                                    "administrator and check log. "+e.toString()+"</p>";
            formatErrorPage(line1, line2,response);
        }
    }
    
    /**
     * Processes when the page first loads, calls doPost
     * @param request The server's request
     * @param response The server's response
     * @throws IOException Thrown when input or output fail
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
    
    /**
     * Generates the error page
     * @param first The first line of the output
     * @param second The second line of the output
     * @param response The servlet response
     * @throws IOException Thrown when input or output fails
     */
    public void formatErrorPage(String first, String second, HttpServletResponse response) throws IOException {
        PrintWriter output = response.getWriter();
        response.setContentType("text/html");
        output.println(first);
        output.println(second);
        output.close();
    }
}
