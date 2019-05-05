<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>allTweets</title>
</head>
<body>

<a href="/user/login">Login</a>
<div>
    <c:forEach items="${allTweets}" var="tweet">
        <div>
            <a>${tweet.user.firstName} ${tweet.user.lastName}</a><br>
            <a>${tweet.created}</a><br>
            <a>${tweet.text}</a>
        </div>
    </c:forEach>
</div>
</body>
</html>
