<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>allUsers</title>
</head>
<body>
<table>
    <tr>
        <th>Imię</th>
        <th>Nazwisko</th>
        <th>Emial</th>
        <th>Hasło</th>
        <th>Akcje</th>
    </tr>

    <c:forEach items="${allUsers}" var="user">
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td><a href="edit/${user.id}">Edit</a> <a href="delete/${user.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<a href="/user/add">Add User</a><br>

</body>
</html>
