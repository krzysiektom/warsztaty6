<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>messagePage</title>
</head>
<body>
<%@include file="fragments/header.jsp" %>
<div>
    <a>Sender: ${message.sender.firstName} ${message.sender.lastName}</a><br>
    <a>Receiver: ${message.receiver.firstName} ${message.receiver.lastName}</a><br>
    <a>Text: ${message.text}</a>
</div>
</body>
</html>
