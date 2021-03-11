<%
    /*
        Daniel Hinbest
        WEBD 4201
        March 11, 2021
    */
    String title = "WEBD4201 - Register";
%>
<%@include file="./header.jsp" %>
<%
    if (loggedInStudent != null) {
        session.setAttribute("message", "You must be signed in to access that page");
        response.sendRedirect("./dashboard.jsp");
        return;
    }
%>

<!--<div class="form">
    <form class="form" method="post" action="">
        <h1 class="h3 mb-3 font-weight-normal">Salespeople Form</h1>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input name="inputEmail" type="text" id="inputEmail" class="form-control" placeholder="Email address" value="" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input name="inputPassword" type="password" id="inputPassword" class="form-control" placeholder="Password" value="" required>
    </form>
</div>-->

<%@include file="./footer.jsp" %>