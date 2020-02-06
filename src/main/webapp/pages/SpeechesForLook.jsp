<%--
  Created by IntelliJ IDEA.
  User: sasak
  Date: 2/5/2020
  Time: 12:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" cellpadding="5">
    <caption><h2>List of conferences</h2></caption>
    <tr>
        <th>ID</th>
        <th>topic</th>
        <th>start hour</th>
        <th>end hour</th>
        <th>speaker</th>
        <th><a href="/?id=<c:out value='command=Logout'/>">Logout</a></th>

    </tr>
    <c:forEach var="speech" items="${sessionScope.speeches}">
        <tr>
            <td>
                    <c:out value="${speech.id}"/>
            <td><c:out value="${speech.topic}"/></td>
            <td><c:out value="${speech.startHour}"/></td>
            <td><c:out value="${speech.endHour}"/></td>
            <td><c:out value="${speech.speaker.username}"/></td>
            <td><a href="/?id=<c:out value='${speech.id}'/>">visit conference</a></td>

        </tr>
    </c:forEach>

</table>
</body>
</html>
