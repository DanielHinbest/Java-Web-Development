package webd4201.hinbestd;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import webd4201.hinbestd.Exceptions.*;

/**
 *
 * @author Daniel
 */
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = DatabaseConnect.initialize();
            Student.initialize(c);
            HttpSession session = request.getSession(true);
            long id = 0;
            String password = new String();
            
            try {
                String string_id = Long.toString(id);
                string_id = request.getParameter("ID");
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
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
    
    public void formatErrorPage(String first, String second, HttpServletResponse response) throws IOException {
        PrintWriter output = response.getWriter();
        response.setContentType("text/html");
        output.println(first);
        output.println(second);
        output.close();
    }
}
