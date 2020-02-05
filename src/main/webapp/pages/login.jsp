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
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="message" />
<html>
<head>
    <title>Title</title>
</head>


<body>
<table border="1" cellpadding="5">
    <form action="${pageContext.request.contextPath}/" method="post">
        <input type="hidden" id="command" name="command" value="Authorization">
        <tr>
            <td><input type="text" id="username" placeholder=" <fmt:message key="label.login" />" name="username"><td>
            <td><input type="text" id="password" placeholder=" <fmt:message key="label.login" />" name="password"></td>
        </tr>
        <tr>
            <td><input type="submit" name="login" value="log in"/></td>
            <td><input type="submit" name="register" value="register"/></td>
        </tr>
    </form>
</table>

</body>
</html>
