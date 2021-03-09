<%
    String title = "WEBD4201 - Login";
%>
<%@ include file="./header.jsp" %>
<% 
    String errorMessage = (String)session.getAttribute("errors");
    String message = (String)session.getAttribute("message");
    Long id = (Long)session.getAttribute("ID");
    String password = (String)session.getAttribute("Password");
    String idAsString = "";
    
    if (errorMessage == null)
        errorMessage = "";
    if (message == null) {
        message = "";
    }
    if (id != null)
        idAsString = id.toString();
    if (password == null)
        password = "";
%>

<div class="form-signin">
<p><% out.println(errorMessage); %></p>
<p><% out.println(message); %></p>
<form class="form-signin" method="post" action="./Login">
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <label for="inputId" class="sr-only">ID</label>
    <input name="inputId" type="text" id="inputId" class="form-control" placeholder="ID" value="<%= idAsString %>" required autofocus>
    <label for="inputPassword" class="sr-only">Password</label>
    <input name="inputPassword" type="password" id="inputPassword" class="form-control" placeholder="Password" value="<%= password %>" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
</form>
</div>
    


<%@include file="./footer.jsp" %>