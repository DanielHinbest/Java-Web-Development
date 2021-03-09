<%
    String title = "WEBD4201 - Dashboard";
%>
<%@include file="./header.jsp" %>
<%
    if (loggedInStudent == null) {
        session.setAttribute("message", "You must be signed in to access that page");
        response.sendRedirect("./login.jsp");
        return;
    }
%>

<h1 class="h2">Dashboard</h1>

<p>
    Welcome, <%= loggedInStudent.getFirstName() %>
</p>

<%@include file="./footer.jsp" %>