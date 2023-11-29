<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Information</title>
    </head>
    <body>
        <table>
            <!-- Header For Table -->
            <tr>
                <th>Customer ID</th>
                <th>Customer Name</th>
                <th>Phone Number</th>
                <th>Email</th>
                <th>Country</th>
                <th>Post Code</th>
                <th>Credit Limit</th>
            </tr>
            <!-- Gets the Customer Details and inserts it into a table row -->
            <tr>
                <td>${Customer.cNum}</td>
                <td>${Customer.cName}</td>
                <td>${Customer.pNum}</td>
                <td>${Customer.email}</td>
                <td>${Customer.country}</td>
                <td>${Customer.pCode}</td>
                <td>${Customer.cLimit}</td>
            </tr>
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