<%--
  Created by IntelliJ IDEA.
  User: sasak
  Date: 2/4/2020
  Time: 3:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>editPageConference</title>
</head>
<body>


<form action="edit" method="POST">
        <input type="hidden" name="conferenceId"  id = "conferenceId" value="${sessionScope.conferenceToEdit.conferenceId}">
    <input type="hidden" name="visitedPeople" id = "visitedPeople" value="${sessionScope.conferenceToEdit.visitedPeople}">
    <input type="hidden" name="registeredPeople" id = "registeredPeople" value="${sessionScope.conferenceToEdit.registeredPeople}">
    <label for="name">name</label>
    <input type="text" name="name" id="name">
    <label for="date">date</label>
    <input type="date" name="date" id="date">

        <input type="submit" value="Edit film">
</form>

</body>
</html>
