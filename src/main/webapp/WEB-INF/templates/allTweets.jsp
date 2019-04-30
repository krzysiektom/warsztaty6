<%--
  Created by IntelliJ IDEA.
  User: krzysztof
  Date: 30.04.19
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>allTweets</title>
</head>
<body>

<div>
    <c:forEach items="${allTweets}" var="tweet">
        <div>
            <h5>${tweet.user.firstName} ${tweet.user.lastName}</h5>
            <h6>${tweet.created}</h6>
            <p>${tweet.text}</p>
        </div>
    </c:forEach>
</div>
</body>
</html>
