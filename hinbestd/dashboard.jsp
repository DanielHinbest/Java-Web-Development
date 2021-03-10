<%
    String title = "WEBD4201 - Dashboard";
%>
<%@ include file="./header.jsp" %>
<%
    if (loggedInStudent == null) {
        session.setAttribute("message", "You must be signed in to access that page");
        response.sendRedirect("./login.jsp");
        return;
    }
%>

<h1 class="h2">Dashboard</h1>
<!--<h2><?php echo $message;?></h2>-->

</div>

<h4>
    Hello, <% out.print(loggedInStudent.getFirstName() + " " + loggedInStudent.getLastName()); %>
</h4>

<table>
    <tr><th colspan="2">Student Information</th></tr>
    <tr>
        <td>Email Address</td><td><% out.print(loggedInStudent.getEmailAddress()); %></td>
    </tr>
    <tr>
        <td>Enroll Date</td><td><% out.print(loggedInStudent.getEnrolDate()); %></td>
    </tr>
    <tr>
        <td>Program Code</td><td><% out.print(loggedInStudent.getProgramCode()); %></td>
    </tr>
    <tr>
        <td>Program Name</td><td><% out.print(loggedInStudent.getProgramDescription()); %></td>
    </tr>
    <tr>
        <td>Year of Study</td><td><% out.print(loggedInStudent.getYear()); %></td>
    </tr>
</table>


<%@include file="./footer.jsp" %>