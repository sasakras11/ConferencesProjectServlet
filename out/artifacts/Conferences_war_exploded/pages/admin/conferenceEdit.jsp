<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>"<fmt:message key="label.coming"/>"</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <style>
        body {background-color: #f2f2f2;}
    </style>
</head>
<body>
        <form action="/?command=ConferenceEdit" method="POST">

            <input type="hidden" name="conferenceId"  id = "conferenceId" value="${requestScope.conference.conferenceId}">


            <label for="name" class="text-info" ><fmt:message key="label.name"/></label>
            <input type="text" name="name" id="name" placeholder="${requestScope.conference.name}">

            <label for="date" class="text-info" ><fmt:message key="label.date"/></label>
            <input type="date" name="date" id="date" placeholder="${requestScope.conference.date}">

            <button type="submit" class="btn btn-info btn-md" ><fmt:message key="label.confirm"/></button>
        </form>

</body>
</html>
