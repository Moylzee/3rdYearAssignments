<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="Entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Display Customers</title>
    </head>
    <body>
        <table>
            <!-- Table Headers -->
            <tr>
                <th>Customer ID</th>
                <th>Customer Name</th>
                <th>Phone Number</th>
                <th>Email</th>
                <th>Country</th>
                <th>Post Code</th>
                <th>Credit Limit</th>
            </tr>
            <!-- For every customer that is stored, a table row is created with the customer information -->
            <c:forEach var="c" items="${customers}">
                <tr>
                    <td>${c.cNum}</td>
                    <td>${c.cName}</td>
                    <td>${c.pNum}</td>
                    <td>${c.email}</td>
                    <td>${c.country}</td>
                    <td>${c.pCode}</td>
                    <td>${c.cLimit}</td>
                </tr>  
            </c:forEach>
        </table>
    </body>
    <style>
        body {
            background-color: yellowgreen;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 50vh;
            margin: 0;
        }
        table {
            border: 2px solid black;
            background-color: lightgray;
        }
        td, th {
            border: 1px solid black;
            width: fit-content;
            text-align: center;
        }
    </style>
</html>