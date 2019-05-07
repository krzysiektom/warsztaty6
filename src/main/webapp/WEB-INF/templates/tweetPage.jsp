<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>TweetPage</title>
</head>
<body>
<%@include file="fragments/header.jsp" %>
<div>
    <a>${tweet.user.firstName} ${tweet.user.lastName}</a><br>
    <a>${tweet.created}</a><br>
    <a>${tweet.text}</a>
</div>

<div>
    <form:form method="post" modelAttribute="comment">
        <form:errors path="text"/><br>
        comment: <form:input path="text"/><br>
        <input type="submit" value="Save"/>
    </form:form>
</div>

<div>
    <c:forEach items="${allComments}" var="comment">
        <div>
            <a>${comment.user.firstName} ${comment.user.lastName}</a><br>
            <a>${comment.text}</a>
        </div>
    </c:forEach>
</div>

</body>
</html>
