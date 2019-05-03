<%--
  Created by IntelliJ IDEA.
  User: krzysztof
  Date: 02.05.19
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>UserTweets</title>
</head>
<body>
<div>
    <c:forEach items="${userAllTweets}" var="tweet">
        <div>
            <a>${tweet.created}</a><br>
            <a>${tweet.text}</a> <a href="/tweet/${tweet.id}">Details</a>
        </div>
    </c:forEach>
</div>
</body>
</html>
