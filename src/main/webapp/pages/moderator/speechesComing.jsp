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
        body {
            background-color: #f2f2f2;
        }

        .table {
            background-color: #ffff;
            box-shadow: 0px 2px 2px #aaa;
            margin-top: 50px;
        }
    </style>
</head>
<body>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ua" ${language == 'ua' ? 'selected' : ''}>Ukrainian</option>
    </select>
</form>
<section id="tabs" class="project-tab">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <nav>
                    <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                        <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab"
                           role="tab" aria-controls="nav-home" aria-selected="true"
                           href="/?command=ShowConferencesComing"
                        ><fmt:message key="label.coming"/></a>
                        <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab"
                           role="tab" aria-controls="nav-profile" aria-selected="false"
                           href="/?command=ShowConferencesFinished"
                        > <fmt:message key="label.finished"/></a>
                        <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" role="tab"
                           aria-controls="nav-contact" aria-selected="false" href="/?command=ShowUserSpeeches"
                        ><fmt:message key="label.your"/></a>
                    </div>
                </nav>
                <div class="tab-content" id="nav-tabContent">
                    <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                        <table class="table" cellspacing="0">
                            <thead class="thead-dark">
                            <tr>
                                <th>ID</th>
                                <th>topic</th>
                                <th>startHour</th>
                                <th>endHour</th>
                                <th>speaker</th>
                                <th>registered people</th>
                                <th></th>
                                <th></th>
                            </tr>
                            <tbody>
                            <c:forEach var="speech" items="${requestScope.speeches}">

                                <tr>
                                    <td><c:out value="${speech.id}"/></td>
                                    <td><c:out value="${speech.topic}"/></td>
                                    <td><c:out value="${speech.startHour}"/></td>
                                    <td><c:out value="${speech.endHour}"/></td>
                                    <td><c:out value="${speech.speaker.username}"/></td>
                                    <td><c:out value="${speech.registeredPeople}"/></td>

                                    <td>
                                        <a href="/?speechId=<c:out value='${speech.id}&command=ShowSpeechEditPage' />">Edit</a>
                                    </td>

                                    <td><c:if test="${!requestScope.userSpeechesIds.contains(speech.id)}">
                                        <a href="/?speechId=<c:out value='${speech.id}&command=ReservePlace'/>">reserve
                                            place</a>
                                    </c:if>
                                    </td>

                                </tr>
                             </c:forEach>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
