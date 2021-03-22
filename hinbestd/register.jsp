<%
    /*
        Daniel Hinbest
        WEBD 4201
        April 1, 2021
    */
    String title = "WEBD4201 - Register";
%>
<%@ include file="./header.jsp" %>
<%
    if (loggedInStudent != null) {
        session.setAttribute("message", "You are already signed in");
        response.sendRedirect("./dashboard.jsp");
        return;
    }
    
    String errorMessage = (String)session.getAttribute("errors");
    String message = (String)session.getAttribute("message");     // Not sure if I really need this line
    String id = (String)session.getAttribute("ID");
    String password = (String)session.getAttribute("Password");
    String firstName = (String)session.getAttribute("First_Name");
    String lastName = (String)session.getAttribute("Last_Name");
    String emailAddress = (String)session.getAttribute("Email_Address");
    String programCode = (String)session.getAttribute("Program_Code");
    String programDescription = (String)session.getAttribute("Program_Description");
    String year = (String)session.getAttribute("Year");
    
    if (errorMessage == null)
        errorMessage = "";
    else
        session.removeAttribute("errors");
    
    if (id == null)
        id = "";
    if (password == null)
        password = "";
    if (firstName == null)
        firstName = "";
    if (lastName == null)
        lastName = "";
    if (emailAddress == null)
        emailAddress = "";
    if (programCode == null)
        programCode = "";
    if (programDescription == null)
        programDescription = "";
    if (year == null)
        year = "";
%>
<div class="form-signin">
    <p>
        <%
            out.println(errorMessage);
        %>
    </p>
<form class="form-signin" method="post" action="./Register">
    <h1 class="h3 mb-3 font-weight-normal">Register</h1>
    <label for="inputId" class="sr-only">ID</label>
    <input name="ID" type="text" id="inputId" class="form-control" placeholder="ID" value="<%= id.trim() %>" required />
    
    <label for="inputPassword" class="sr-only">Password</label>
    <input name="Password" type="password" id="inputPassword" class="form-control" placeholder="Password" value="<%= password %>" required />
    
    <label for="inputFirstName" class="sr-only">First Name</label>
    <input name="First_Name" type="text" id="inputFirstName" class="form-control" placeholder="First Name" value="<%= firstName.trim() %>" required />
    
    <label for="inputLastName" class="sr-only">Last Name</label>
    <input name="Last_Name" type="text" id="inputLastName" class="form-control" placeholder="Last Name" value="<%= lastName.trim() %>" required />
    
    <label for="inputEmailAddress" class="sr-only">Email Address</label>
    <input name="Email_Address" type="text" id="inputEmailAddress" class="form-control" placeholder="Email Address" value="<%= emailAddress.trim() %>" required />
    
    <label for="inputProgramCode" class="sr-only">Program Code</label>
    <input name="Program_Code" type="text" id="inputProgramCode" class="form-control" placeholder="Program Code" value="<%= programCode.trim() %>" required />
    
    <label for="inputProgramDescription" class="sr-only">Program Description</label>
    <input name="Program_Description" type="text" id="inputProgramDescription" class="form-control" placeholder="Program Description" value="<%= programDescription.trim() %>" required />
    
    <label for="inputYear" class="sr-only">Year of Study</label>
    <input name="Year" type="text" id="inputYear" class="form-control" placeholder="Year of Study" value="<%= year.trim() %>" required autofocus />
    <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
</form>
</div>

<%@include file="./footer.jsp" %>