<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sasak
  Date: 2/4/2020
  Time: 8:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>conferenceWithEdit</title>
</head>
<body>
<c:forEach var="conference" items="${sessionScope.conferences}">
    <c:out value="${conference.conferenceId}"/>
    <c:out value="${conference.registeredPeople}"/>
    <c:out value="${conference.location.address}"/>
    <c:out value="${conference.date}"/>

    <a href="conferenceEdit.jsp">edit</a>
           ${par}
</c:forEach>

<h1>conferences for ADMIN and MODERATOR</h1>
</body>
</html>
