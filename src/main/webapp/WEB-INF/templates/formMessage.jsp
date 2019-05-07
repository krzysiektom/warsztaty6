<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>formMessage</title>
</head>
<body>
<%@include file="fragments/header.jsp" %>

<form:form method="post" modelAttribute="message">
    <form:errors path="receiver" /><br>
    receiver: <form:select path="receiver" items="${receiverUsers}" itemLabel="lastName" itemValue="id"/><br>
    <form:errors path="text"/><br>
    message: <form:input path="text"/><br>
    <input type="submit" value="Save"/>
</form:form>
</body>
</html>
