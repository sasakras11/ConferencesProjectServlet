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

<form action="/?command=SpeechEdit">
    <input type="hidden" name="speechId" id="speechId" value="${requestScope.speech.id}">
    <input type="hidden" name="command" id = "command" value="SpeechEdit">
    <label for="topic" class="text-info" ><fmt:message key="label.topic"/></label>
    <input type="text" id="topic" name="topic">

    <label for="suggestedTopic" class="text-info"><fmt:message key="label.suggestTopic"/></label>
    <input type="text" id="suggestedTopic" name="suggestedTopic">

    <label for="startHour" class="text-info"><fmt:message key="label.start"/></label>
    <input type="text" id="startHour" name="startHour">

    <label for="endHour" class="text-info"><fmt:message key="label.end"/></label>
    <input type="text" name="endHour" id="endHour">

    <button type="submit" class="btn btn-info btn-md" ><fmt:message key="label.confirm"/></button>
</form>
</body>
</html>
