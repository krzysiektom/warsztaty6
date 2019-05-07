<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>TweetPage</title>
</head>
<body>
<%@include file="fragments/header.jsp" %>
<div>Tweet:
    <p>Text: ${tweet.text}</p>
    <p>Author: ${tweet.user.firstName} ${tweet.user.lastName}</p>
</div>

<div>
    <form:form method="post" modelAttribute="comment">
        <form:errors path="text"/><br>
        New comment: <form:input path="text"/>
        <input type="submit" value="Save"/>
    </form:form>
</div>

<div>Comments:
    <c:forEach items="${allComments}" var="comment">
        <div>
            <a>Text: ${comment.text}</a><br>
            <a>Author: ${comment.user.firstName} ${comment.user.lastName}</a>
        </div>
    </c:forEach>
</div>

</body>
</html>
