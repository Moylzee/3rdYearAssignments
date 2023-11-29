<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Artist Information</title>
    </head>
    <body>
        <h1>Artist Information</h1>
        <!-- Link to the page to add an artist -->
        <h2><a href="AddArtist.html">Add Artist</a></h2>
        <table>
            <tr>
                <th>Surname</th>
                <th>First Name</th>
                <th>Nationality</th>
                <th>Birth Year</th>
                <th>Death Year</th>
                <th>Details</th>
            </tr>
            
            <!-- creates a list of all artists and displays them on the table -->
            <% List<bean.artist> artistList = (List<bean.artist>)session.getAttribute("artistList");    
                if (artistList != null && !artistList.isEmpty()) {
                    for (bean.artist artist : artistList) {
            %>
            <tr id="artists">
                <td><%= artist.getsName() %></td>
                <td><%= artist.getfName() %></td>
                <td><%= artist.getNationality() %></td>
                <td><%= artist.getbYear() %></td>
                <td><%= artist.getdYear() %></td>
                <!-- Link to the page with the artist details. Passes the artist details through the URL -->
                <td><a href='artistDetails.jsp?fName=<%=artist.getfName()%>
                       &sName=<%=artist.getsName()%> 
                       &nationality=<%=artist.getNationality()%>
                       &bYear=<%=artist.getbYear()%>
                       &dYear=<%=artist.getdYear()%>
                       &pURL=<%=artist.getpURL()%>
                       &bio=<%=artist.getBio()%>'>Details</a></td>
            </tr>
            <!-- If no artists are submitted, it shows alternative text -->
        <%
                }
            } else {
        %>
        <tr id="noArtist">
            <td colspan="6">No artists to display</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
<style>
    body {
        background-color: lightgray;
    }
    h1 {
        color: blue; 
        text-align: center;
    }
    h2 {
        text-align: center;
    }
    #artists {
        background-color: royalblue;
        text-align: center;
    }
    #noArtist {
        background-color: royalblue;
        text-align: center;
    }
    tr {
        background-color: royalblue; 
        color: white; 
        height: 60px;
    }
    a {
        color: black;
    }
</style>
</html>
