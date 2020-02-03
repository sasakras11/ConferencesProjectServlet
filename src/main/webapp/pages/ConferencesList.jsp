<%@ page import="com.entity.Conference" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sasak
  Date: 2/3/2020
  Time: 1:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <c:forEach items="${conferences}" var="item">
        <tr>
            <td><c:out value="${item}" /></td>
        </tr>
    </c:forEach>
</table>
      <h1>you logged in successfully </h1>
<fmt:message key="label.welcome" />

    </body>
</html>
