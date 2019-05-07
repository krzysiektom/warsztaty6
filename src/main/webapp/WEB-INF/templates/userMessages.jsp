<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>messagePage</title>
    <style>
        table {
            border-collapse: collapse;
        }

        table, td, th {
            border: 1px solid black;
        }

        td.normal {
            font-weight: normal;
        }

        td.thick {
            font-weight: bold;
        }
    </style>
</head>
<body>
<%@include file="fragments/header.jsp" %>
<div>
    <a href="/message/add">Send new message</a>
</div>
<table><b>Sent messages:</b>
    <tr>
        <th>To</th>
        <th>When</th>
        <th>Text</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${senderMessages}" var="message">
        <tr>
            <td>${message.receiver.firstName} ${message.receiver.lastName}</td>
            <td>${localDateTimeFormat.format(message.created)}</td>
            <td>${message.text}</td>
            <td><a href="/message/${message.id}">Details</a></td>
        </tr>
    </c:forEach>
</table>

<table><b>Received messages:</b>
    <tr>
        <th>From</th>
        <th>When</th>
        <th>Text</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${receiverMessages}" var="message">
        <tr>
            <td>${message.sender.firstName} ${message.sender.lastName}</td>
            <td>${localDateTimeFormat.format(message.created)}</td>
            <td class="${message.read?'normal':'thick'}">${message.text}</td>
            <td><a href="/message/${message.id}">Details</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
