<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 12/01/2020
  Time: 01:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/login">
    <label ><b>Username</b></label> <input type="text" placeholder="Enter username" name="username"><br>
    <label ><b>Password</b></label> <input type="text" placeholder="Enter password" name="password"><br>
    <input type="submit" value="login">
</form>
</body>
</html>
