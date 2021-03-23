package webd4201.hinbestd;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.util.Objects;
import javax.servlet.http.*;
import webd4201.hinbestd.Exceptions.*;
import static webd4201.hinbestd.User.isValidEmailAddress;

/**
 * This class performs the processes to register a user
 *
 * @author Daniel Hinbest
 * @version 1.0 (April 1, 2021)
 * @since 4.0
 */
public class RegisterServlet extends HttpServlet {

    /**
     * When the form is submitted, register the user if valid and redirect to
     * the dashboard
     *
     * @param request The request to the server
     * @param response The response from the server
     * @throws IOException Thrown when the input and output fails
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);

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
            password = request.getParameter("Password").trim();
            firstName = request.getParameter("First_Name").trim();
            lastName = request.getParameter("Last_Name").trim();
            emailAddress = request.getParameter("Email_Address").trim();
            programCode = request.getParameter("Program_Code").trim();
            programDescription = request.getParameter("Program_Description").trim();
            year = Integer.parseInt(request.getParameter("Year"));

            Student aStudent = new Student(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, true, 's', programCode, programDescription, year);

            aStudent.create();

            session.setAttribute("student", aStudent);
            session.setAttribute("errors", "");
            session.setAttribute("message", "You are now registered in the database.");

            response.sendRedirect("./dashboard.jsp");

        } catch (DuplicateException e) {
            StringBuffer errorBuffer = new StringBuffer();
            boolean isValid = true;
            errorBuffer.append("<strong>A user with your details already exists<br/>");
            errorBuffer.append("Please try again</strong>");

            session.setAttribute("errors", errorBuffer.toString());
            response.sendRedirect("./register.jsp");
        } catch (NumberFormatException | InvalidUserDataException e) {
            StringBuffer errorBuffer = new StringBuffer();
            boolean isValid = true;
            errorBuffer = new StringBuffer();          
                        
            errorBuffer.append("<strong>Your registration details are invalid<br/>");
            
            if (id == null) {
                errorBuffer.append("The ID cannot be blank<br/>");
                isValid = false;
                session.setAttribute("errors", errorBuffer.toString());
            } else if (id < User.MINIMUM_ID_NUMBER || id > User.MAXIMUM_ID_NUMBER) {
                errorBuffer.append("The ID must be between " + User.MINIMUM_ID_NUMBER + " and " + User.MAXIMUM_ID_NUMBER + "<br/>");
                isValid = false;
                session.setAttribute("ID", "");
            }

            if (password.length() < User.MINIMUM_PASSWORD_LENGTH) {
                errorBuffer.append("The password must be at least " + User.MINIMUM_PASSWORD_LENGTH + " characters<br/>");
                isValid = false;
                session.setAttribute("Password", "");
            } else if (password == null || password == "") {
                errorBuffer.append("The password cannot be blank<br/>");
                isValid = false;
                session.setAttribute("Password", "");
            }

            if (firstName.length() < 1 || firstName.length() > 35) {
                errorBuffer.append("The first name must be between 1 and 35 characters<br/>");
                isValid = false;
                session.setAttribute("First_Name", "");
            } else if (firstName == null || firstName == "") {
                errorBuffer.append("The first name cannot be blank<br/>");
                isValid = false;
                session.setAttribute("First_Name", "");
            }

            if (lastName.length() < 1 || lastName.length() > 50) {
                errorBuffer.append("The last name must be between 1 and 50 characters<br/>");
                isValid = false;
                session.setAttribute("Last_Name", "");
            } else if (lastName == null || lastName == "") {
                errorBuffer.append("The first name cannot be blank<br/>");
                isValid = false;
                session.setAttribute("Last_Name", "");
            }

            if (emailAddress == null || emailAddress == "") {
                isValid = false;
                errorBuffer.append("You must enter an email address<br/>");
                session.setAttribute("Email_Address", "");
            } else if (!isValidEmailAddress(emailAddress)) {
                isValid = false;
                errorBuffer.append(emailAddress + " is not a valid email address<br/>");
                session.setAttribute("Email_Address", "");
            }

            if (programCode.length() < 1 || programCode.length() > 4) {
                errorBuffer.append("The program code must be between 1 and 4 characters<br/>");
                isValid = false;
                session.setAttribute("Program_Code", "");
            } else if (programCode == null || programCode == "") {
                errorBuffer.append("The program code cannot be blank<br/>");
                isValid = false;
                session.setAttribute("Program_Code", "");
            }

            if (programDescription.length() < 1 || programDescription.length() > 70) {
                errorBuffer.append("The program description must be between 1 and 70 characters<br/>");
                isValid = false;
                session.setAttribute("Program_Description", "");
            } else if (programDescription == null || programDescription == "") {
                errorBuffer.append("The program description cannot be blank<br/>");
                isValid = false;
                session.setAttribute("Program_Description", "");
            }
                       
            if (year == null) {
                errorBuffer.append("The year of study cannot be blank<br/>");
                isValid = false;
                session.setAttribute("errors", errorBuffer.toString());
            } else if (year < 1 || year > 4) {
                errorBuffer.append("The year of study must be between 1 and 4 years<br/>");
                isValid = false;
                session.setAttribute("Year", "");
            } 
            
            errorBuffer.append("Please try again</strong>");
            session.setAttribute("errors", errorBuffer.toString());
            response.sendRedirect("./register.jsp");
        } catch (Exception e) {
            System.out.println(e);
            String line1 = "<h2>A network error has occurred!</h2>";
            String line2 = "<p>Please notify your system "
                    + "administrator and check log. " + e.toString() + "</p>";
            formatErrorPage(line1, line2, response);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    /**
     * Generates the error page formatting
     *
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
