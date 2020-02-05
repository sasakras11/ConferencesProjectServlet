<%--
  Created by IntelliJ IDEA.
  User: sasak
  Date: 2/4/2020
  Time: 8:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>conferenceWithLook</title>
</head>
<body>

<c:forEach var="conference" items="${sessionScope.conferences}">
    <c:out value="${conference.conferenceId}"/>
    <c:out value="${conference.name}"/>
    <c:out value="${conference.date}"/>
    <c:out value="${conference.location.address}"/><br>

    <a href="/?id=<c:out value='${conference.conferenceId}&command=ShowSpeeches'/>">Show speeches</a>

</c:forEach>
  conferences for VISITOR AND SPEAKER

</body>
</html>
