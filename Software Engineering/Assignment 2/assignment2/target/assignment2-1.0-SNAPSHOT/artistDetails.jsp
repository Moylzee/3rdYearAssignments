<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="bean.artist"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Artist Details</title>
</head>
<body>
    <h1>Artist Details</h1>
    
    <%-- Initialize variables and take the specified artist details from URL--%>
    <% String fName = request.getParameter("fName");
       String sName = request.getParameter("sName");
       String nationality = request.getParameter("nationality");
       String bYear = request.getParameter("bYear");
       String dYear = request.getParameter("dYear");
       String pURL = request.getParameter("pURL");
       String bio = request.getParameter("bio");
    %>
    <!-- Display the Details of the Artist -->
    <h2><%= fName %> <%= sName %></h2>
    <img src="<%= pURL %>" alt="No Image Available"/>
    <br/>
    <div style="margin: 0 auto; width: 50%;">
        <table>
            <tr>
                <td>Nationality:</td>
                <td> <%= nationality %> </td>
            </tr>
            <tr>
                <td>Year of Birth:</td>
                <td> <%= bYear %> </td>
            </tr>
            <tr>
                <td>Year of Death</td>
                <td> <%= dYear %> </td>
            </tr>
            <tr>
                <td>Biography:</td>
                <td><%= bio %></td>
            </tr>
        </table>
    </div>
</body>
<style>
    body {
        background-color: lightgray;
    }
    img {
        height: 200px;
        border: 2px solid black;
        max-height: 400px;
        max-width: 100%;
        display: block;
        margin: 0 auto;
    }
    h1 {
        color: blue; 
        text-align: center;    
    }
    h2 {
        text-align: center;
    }
    table,
    td{
        border: 2px solid black;
    }
    tr  {
        background-color: lightblue;
        width: fit-content;
    }
</style>
</html>
