<%--
  Created by IntelliJ IDEA.
  User: krzysztof
  Date: 28.04.19
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>loginPage</title>
</head>
<body>

<c:if test="${error != false}">
    <p>${errorMsg}</p>
</c:if>

<form:form method="post" modelAttribute="user">
    <form:errors path="email"/><br>
    email: <form:input path="email"/><br>
    <form:errors path="password"/><br>
    password: <form:input path="password"/><br>
    <input type="submit" value="Save"/>
</form:form>
<a href="/user/add">Zarejestruj się</a>
</body>
</html>
