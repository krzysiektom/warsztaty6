<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>header</title>
    <style>
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
        }

        li {
            display: inline;
        }
    </style>
</head>
<body>
<ul>
    <li><a href="/tweet/main">Main Page</a></li>
    <li><a href="/tweet/user">User Tweets</a></li>
    <li><a href="/message/">User Messages</a></li>
    <li><a href="/user/page">User Page</a></li>
    <li><a href="/user/logout">Logout</a></li>
</ul>
</body>
</html>
