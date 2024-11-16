<%@page import="java.util.List"%>
<%@ page import="DAO.DatBanDAO" %>
<%@ page import="model.DatBan" %>
<%
    // L?y danh sách bàn t? request
    List<DatBan> bans = (List<DatBan>) request.getAttribute("bans");
%>
<html>
<head>
    <title>Danh sách Bàn</title>
</head>
<body>
    <h1>Danh sách bàn trong quán</h1>

    <table border="1">
        <tr>
            <th>Mã Bàn</th>
            <th>Tình Tr?ng</th>
            <th>Hành ??ng</th>
        </tr>
        <% 
            // Duy?t qua danh sách bàn và hi?n th? trong b?ng
            for (DatBan ban : bans) {
        %>
        <tr>
            <td><%= ban.getMaBan() %></td>
            <td><%= ban.getTinhTrang() %></td>
            <td>
                <%
                    if (ban.getTinhTrang().equals("Còn tr?ng")) {
                %>
                    <a href="datban?maBan=<%= ban.getMaBan() %>">??t bàn</a>
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
