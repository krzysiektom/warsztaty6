<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>messagePage</title>
    <style>
        a.normal {
            font-weight: normal;
        }

        a.thick {
            font-weight: bold;
        }
    </style>
</head>
<body>
<a href="/message/add">Send Message</a>
<div>Sent messages
    <c:forEach items="${senderMessages}" var="message">
        <div>
            <a>${message.receiver.firstName} ${message.receiver.lastName}</a><br>
            <a>${message.created}</a><br>
            <a class="${message.read?'normal':'thick'}">${message.text}</a> <a
                href="/message/${message.id}">Details</a>
        </div>
    </c:forEach>
</div>

<div>Received messages
    <c:forEach items="${receiverMessages}" var="message">
        <div>
            <a>${message.sender.firstName} ${message.sender.lastName}</a><br>
            <a>${message.created}</a><br>
            <a class="${message.read?'normal':'thick'}">${message.text}</a> <a href="/message/${message.id}">Details</a>
        </div>
    </c:forEach>
</div>
</body>
</html>
