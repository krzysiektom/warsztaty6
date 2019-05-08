<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>UserTweets</title>
</head>
<body>
<%@include file="fragments/header.jsp" %>

<div>User tweets:
    <c:forEach items="${userTweets}" var="tweet">
        <div>
            <a>Text: ${tweet.text}</a>
            <a>Comments: ${commentRepository.countByTweet(tweet)}</a> <a href="/tweet/${tweet.id}">Details</a>
        </div>
    </c:forEach>
</div>
</body>
</html>
