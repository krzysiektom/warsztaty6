<%--
  Created by IntelliJ IDEA.
  User: krzysztof
  Date: 03.05.19
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>TweetPage</title>
</head>
<body>
<div>
    <a>${tweet.user.firstName} ${tweet.user.lastName}</a><br>
    <a>${tweet.created}</a><br>
    <a>${tweet.text}</a>
</div>
</body>
</html>