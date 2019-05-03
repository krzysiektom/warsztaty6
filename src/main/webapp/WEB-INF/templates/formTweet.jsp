<%--
  Created by IntelliJ IDEA.
  User: krzysztof
  Date: 30.04.19
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>formTweet</title>
</head>
<body>
<form:form method="post" modelAttribute="tweet">
    <form:errors path="text"/><br>
    firstName: <form:input path="text"/><br>
    <input type="submit" value="Save"/>
</form:form>
</body>
</html>
