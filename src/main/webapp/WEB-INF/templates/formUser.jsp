<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>userForm</title>
</head>
<body>
<div>
    <c:if test="${error != false}">
        <p>${errorMsg}</p>
    </c:if>

    <form:form method="post" modelAttribute="user">
        <form:errors path="firstName"/><br>
        firstName: <form:input path="firstName"/><br>
        <form:errors path="lastName"/><br>
        lastName: <form:input path="lastName"/><br>
        <form:errors path="email"/><br>
        email: <form:input path="email"/><br>
        <form:errors path="password"/><br>
        password: <form:input path="password"/><br>
        <input type="submit" value="Save"/>
    </form:form>
</div>
</body>
</html>
