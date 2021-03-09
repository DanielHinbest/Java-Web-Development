<%
    String title = "WEBD4201 - Update";
%>
<%@include file="./header.jsp" %>
<%
    if (loggedInStudent == null) {
        session.setAttribute("message", "You must be signed in to access that page");
        response.sendRedirect("./login.jsp");
        return;
    }
%>



<%@include file="./footer.jsp" %>