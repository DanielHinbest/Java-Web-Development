package webd4201.hinbestd;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import javax.servlet.http.*;
import webd4201.hinbestd.Exceptions.*;

/**
 *
 * @author Daniel
 */
public class RegisterServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        try {
            Connection c = DatabaseConnect.initialize();
            User.initialize(c);
            Student.initialize(c);
            Long id = null;
            String password = new String();
            String firstName = new String();
            String lastName = new String();
            String emailAddress = new String();
            Date lastAccess = new Date();
            Date enrolDate = new Date();
            String programCode = new String();
            String programDescription = new String();
            Integer year = null;
            
            try {
                id = Long.parseLong(request.getParameter("ID"));
                password = request.getParameter("Password");
                firstName = request.getParameter("First_Name");
                lastName = request.getParameter("Last_Name");
                emailAddress = request.getParameter("Email_Address");
                programCode = request.getParameter("Program_Code");
                programDescription = request.getParameter("Program_Description");
                year = Integer.parseInt(request.getParameter("Year"));
                
                Student aStudent = new Student(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, true, 's', programCode, programDescription, year);
                
                aStudent.create();
                
                session.setAttribute("student", aStudent);
                session.setAttribute("errors", "");
                
                response.sendRedirect("./dashboard.jsp");
            } catch (DuplicateException e) {
                StringBuffer errorBuffer = new StringBuffer();
                errorBuffer.append("<strong>A user with your details already exists<br/>");
                errorBuffer.append("Please try again</strong>");   
                
                session.setAttribute("errors", errorBuffer.toString());
                response.sendRedirect("./register.jsp");            
            } catch (InvalidUserDataException e){
                StringBuffer errorBuffer = new StringBuffer();
                errorBuffer.append("<strong>Your registration details are invalid<br/>");
                errorBuffer.append("Please try again</strong>");   
                
                session.setAttribute("errors", errorBuffer.toString());
                response.sendRedirect("./register.jsp");
            }
            
        } catch (NumberFormatException e){
            StringBuffer errorBuffer = new StringBuffer();
            errorBuffer.append("<strong>Your registration details are invalid");
            errorBuffer.append("Please try again</strong>");  
        } catch (Exception e){
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
    
    /**
     * Generates the error page formatting
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
