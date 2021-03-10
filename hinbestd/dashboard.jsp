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
    Hello, <% out.print(loggedInStudent.getFirstName() + " " + loggedInStudent.getLastName()); %>. This is your dashboard to the Durham College Mark Database.
</h4>


<%@include file="./footer.jsp" %>