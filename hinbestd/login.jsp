<%
    /*
        Daniel Hinbest
        WEBD 4201
        March 11, 2021
    */
    String title = "WEBD4201 - Login";
%>
<%@ include file="./header.jsp" %>
<% 
    if (loggedInStudent != null) {
        session.setAttribute("message", "You must be signed in to access that page");
        response.sendRedirect("./dashboard.jsp");
        return;
    }
    
    String errorMessage = (String)session.getAttribute("errors");
    String message = (String)session.getAttribute("message");
    String id = (String)session.getAttribute("ID");
    String password = (String)session.getAttribute("Password");
    
    if (errorMessage == null)
        errorMessage = "";
    else
        session.removeAttribute("errors");
    
    if (message == null) 
        message = "";
    else 
        session.removeAttribute("message");
    
    if (id == null)
        id = "";
        
    if (password == null)
        password = "";
%>

<div class="form-signin">
<p><% 
        out.println(errorMessage); 
    
    %></p>
<p><% out.println(message); %></p>
<form class="form-signin" method="post" action="./Login">
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <label for="inputId" class="sr-only">ID</label>
    <input name="ID" type="text" id="inputId" class="form-control" placeholder="ID" value="<%= id.trim() %>" required autofocus />
    <label for="inputPassword" class="sr-only">Password</label>
    <input name="Password" type="password" id="inputPassword" class="form-control" placeholder="Password" value="<%= password %>" required />
    <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
</form>
</div>

<%@include file="./footer.jsp" %>