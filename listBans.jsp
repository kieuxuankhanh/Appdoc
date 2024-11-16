<%@page import="java.util.List"%>
<%@ page import="DAO.DatBanDAO" %>
<%@ page import="model.DatBan" %>
<%
    // L?y danh s�ch b�n t? request
    List<DatBan> bans = (List<DatBan>) request.getAttribute("bans");
%>
<html>
<head>
    <title>Danh s�ch B�n</title>
</head>
<body>
    <h1>Danh s�ch b�n trong qu�n</h1>

    <table border="1">
        <tr>
            <th>M� B�n</th>
            <th>T�nh Tr?ng</th>
            <th>H�nh ??ng</th>
        </tr>
        <% 
            // Duy?t qua danh s�ch b�n v� hi?n th? trong b?ng
            for (DatBan ban : bans) {
        %>
        <tr>
            <td><%= ban.getMaBan() %></td>
            <td><%= ban.getTinhTrang() %></td>
            <td>
                <%
                    if (ban.getTinhTrang().equals("C�n tr?ng")) {
                %>
                    <a href="datban?maBan=<%= ban.getMaBan() %>">??t b�n</a>
                <%
                    } else {
                %>
                    <a href="huydatban?maBan=<%= ban.getMaBan() %>">H?y ??t</a>
                <%
                    }
                %>
            </td>
        </tr>
        <% 
            }
        %>
    </table>
</body>
</html>
