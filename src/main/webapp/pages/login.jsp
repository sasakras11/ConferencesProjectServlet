<%--
  Created by IntelliJ IDEA.
  User: sasak
  Date: 2/1/2020
  Time: 7:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${not empty param.language}">
    <c:set var="language" value="${param.language}" scope="session"/>
</c:if>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="translate" />
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/" method="post">
    <input type="hidden" id="command" name="command" value="Authorization">
    <input type="text" id="username" placeholder="Your username" name="username">
    <input type="text" id="password" placeholder="Your password" name="password"><br>
    <input  type="submit" name="login" value="log in" /><br>
    <input  type="submit" name="register" value="register" />

</form>

</body>
</html>
