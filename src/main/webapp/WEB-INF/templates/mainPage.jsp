<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>mainPage</title>
</head>
<body>
<%@include file="fragments/header.jsp" %>
<div>
    <form:form method="post" modelAttribute="tweet">
        <form:errors path="text"/><br>
        tweet: <form:input path="text"/><br>
        <input type="submit" value="Save"/>
    </form:form>
</div>

<div>
    <c:forEach items="${allTweets}" var="tweet">
        <div>
            <a>${tweet.user.firstName} ${tweet.user.lastName}</a><br>
            <a>${tweet.created}</a><br>
            <a>${tweet.text}</a> <a href="/tweet/${tweet.id}">Details</a>
        </div>
    </c:forEach>
</div>
</body>
</html>
