<%
    /*
        Daniel Hinbest
        WEBD 4201
        March 11, 2021
    */
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

</div>

<h4>
    Hello, <% out.print(loggedInStudent.getFirstName() + " " + loggedInStudent.getLastName()); %>. This is your dashboard to the Durham College Mark Database.
</h4>


<%@include file="./footer.jsp" %>