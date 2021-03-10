<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
   	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">
        
        <title><%= title %></title>
         <!-- Bootstrap core CSS -->
        <link href="./css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="./css/styles.css" rel="stylesheet">
        <%@page import="webd4201.hinbestd.*" %>
        <%@page import="java.util.*" %>
    </head>
    <body>
        <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
            <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="index.jsp">Durham College</a>
            <ul class="navbar-nav px-3">
                <li class="nav-item text-nowrap">
                    <%
                        Student loggedInStudent = (Student)session.getAttribute("student");
                        if (loggedInStudent == null) {
                            out.println("<a class=nav-link href=\"login.jsp\" name=\"signin\">Sign in</a>");                            
                        }
                        else {                            
                            out.println("<a class=nav-link href=\"./Logout\" name=\"signout\">Sign out</a>"); 
                        }
                    %>
                </li>
            </ul>
        </nav>
        <div class="container-fluid">
      <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <div class="sidebar-sticky">
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link" href="dashboard.jsp">
                        <span data-feather="file"></span>
                        Dashboard
                    </a> 
                </li>
                <li class="nav-item">
                    <% 
                        if (loggedInStudent == null) {
                            out.println("<a class=\"nav-link\" href=\"register.jsp\">"
                                        + "<span data-feather=\"file\"></span>"
                                        + "Register"
                                        + "</a>");
                        } else {
                            out.println("<a class=\"nav-link\" href=\"update.jsp\">"
                                        + "<span data-feather=\"file\"></span>"
                                        + "Update"
                                        + "</a>");
                        }
                    %>
                </li>
            </ul>
            </div>
        </nav>

        <main class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
          <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">