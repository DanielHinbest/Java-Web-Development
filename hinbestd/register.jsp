<%
    /*
        Daniel Hinbest
        WEBD 4201
        March 11, 2021
    */
    String title = "WEBD4201 - Register";
%>
<%@ include file="./header.jsp" %>
<%
    if (loggedInStudent != null) {
        session.setAttribute("message", "You must be signed in to access that page");
        response.sendRedirect("./dashboard.jsp");
        return;
    }
    
    String errorMessage = (String)session.getAttribute("errors");
    //String message = (String)session.getAttribute("message");     // Not sure if I really need this line
    String id = (String)session.getAttribute("ID");
    String password = (String)session.getAttribute("Password");
    String firstName = (String)session.getAttribute("First_Name");
    String lastName = (String)session.getAttribute("Last_Name");
    String emailAddress = (String)session.getAttribute("Email_Address");
    String programCode = (String)session.getAttribute("Program_Code");
    String programDescription = (String)session.getAttribute("Program_Description");
    String year = (String)session.getAttribute("Year");
%>

<div class="form-signin">
    <form class="form" method="post" action="./Register">
        <h1 class="h3 mb-3 font-weight-normal">Register</h1>
        <label for="ID" class="sr-only">ID</label>
        <input name="ID" type="text" id="ID" class="form-control" placeholder="ID" value="" required />
        
        <label for="Password" class="sr-only">Password</label>
        <input name="Password" type="password" id="Password" class="form-control" placeholder="Password" value="" required />
        
        <!--<label for="confirm_password" class="sr-only">Confirm Password</label>
        <input name="confirm_password" type="password" id="confirm_password" class="form-control" placeholder="Confirm Password" value="" required />-->
        
        <label for="First_Name" class="sr-only">First Name</label>
        <input name="First_Name" type="text" id="First_Name" class="form-control" placeholder="First Name" value="" required />
        
        <label for="Last_Name" class="sr-only">Last Name</label>
        <input name="Last_Name" type="text" id="Last_Name" class="form-control" placeholder="Last Name" value="" required />
        
        <label for="Email_Address" class="sr-only">Email address</label>
        <input name="Email_Address" type="text" id="Email_Address" class="form-control" placeholder="Email address" value="" required />
        
        <label for="Program_Code" class="sr-only">Program Code</label>
        <input name="Program_Code" type="text" id="Program_Code" class="form-control" placeholder="Program Code" value="" required />
        
        <label for="Program_Description" class="sr-only">Program Description</label>
        <input name="Program_Description" type="text" id="Program_Description" class="form-control" placeholder="Program Description" value="" required />
        
        <label for="Year" class="sr-only">Year of Study</label>
        <input name="Year" type="text" id="Year" class="form-control" placeholder="Year of Study" value="" required />
        
        <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
    </form>
</div>

<%@include file="./footer.jsp" %>