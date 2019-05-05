<%--
  Created by IntelliJ IDEA.
  User: krzysztof
  Date: 05.05.19
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>formMessage</title>
</head>
<body>
<form:form method="post" modelAttribute="message">
    <form:errors path="receiver" /><br>
    receiver: <form:select path="receiver" items="${receiverUsers}" itemLabel="lastName" itemValue="id"/><br>
    <form:errors path="text"/><br>
    message: <form:input path="text"/><br>
    <input type="submit" value="Save"/>
</form:form>
</body>
</html>
