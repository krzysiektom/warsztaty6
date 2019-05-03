<%--
  Created by IntelliJ IDEA.
  User: krzysztof
  Date: 02.05.19
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>mainPage</title>
</head>
<body>
<a>Witaj ${userName}</a>
<a href="/tweet/user">User Tweets</a>
<a href="/user/page">User Page</a>
<a href="/user/logout">Logout</a>
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
            <a>${tweet.text}</a><a href="/tweet/${tweet.id}"> Tweet Details</a>
        </div>
    </c:forEach>
</div>
</body>
</html>
