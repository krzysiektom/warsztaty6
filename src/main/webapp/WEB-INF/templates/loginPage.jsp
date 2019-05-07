<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>loginPage</title>
</head>
<body>
<div>
    <c:if test="${error != false}">
        <p>${errorMsg}</p>
    </c:if>
    <form method="post">
        email: <input type="text" name="email"/><br>
        password: <input type="password" name="password"/><br>
        <input type="submit" value="Login"/>
    </form>
</div>
<a href="/user/add">Register</a>
</body>
</html>
