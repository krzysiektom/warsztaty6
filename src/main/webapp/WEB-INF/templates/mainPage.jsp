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
        New tweet: <form:input path="text"/>
        <input type="submit" value="Save"/>
    </form:form>
</div>

<div>Tweets:
    <c:forEach items="${allTweets}" var="tweet">
        <div>
            <a>Text: ${tweet.text}</a>
            <a>Author: ${tweet.user.firstName} ${tweet.user.lastName}</a>
            <a href="/tweet/${tweet.id}">Details</a>
            <br>
        </div>
    </c:forEach>
</div>
</body>
</html>
