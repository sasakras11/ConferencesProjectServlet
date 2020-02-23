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
        body{
            background-color:#f2f2f2;
        }
        .table{
            background-color:#ffff;
            box-shadow:0px 2px 2px #aaa;
            margin-top:50px;
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
                           role="tab" aria-controls="nav-home" aria-selected="true" href="/?command=ShowConferencesComing"
                        ><fmt:message key="label.coming"/></a>
                        <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab"
                           role="tab" aria-controls="nav-profile" aria-selected="false" href="/?command=ShowConferencesFinished"
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
                                <th><fmt:message key="label.id"/></th>
                                <th><fmt:message key="label.name"/></th>
                                <th> <fmt:message key="label.date"/></th>
                                <th><fmt:message key="label.address"/></th>
                                <th></th>
                                <th></th>
                                <th></th>

                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="conference" items="${requestScope.conferences}">

                                <tr>
                                    <td><c:out value="${conference.conferenceId}"/></td>
                                    <td><c:out value="${conference.name}"/></td>
                                    <td><c:out value="${conference.location.address}"/></td>
                                    <td><c:out value="${conference.date}"/></td>

                                    <td>
                                        <a href="/?conferenceId=<c:out value='${conference.conferenceId}&command=ShowConferenceEditPage'/>"><fmt:message
                                                key="label.edit"/></a></td>
                                    <td>
                                        <a href="/?type=coming&conferenceId=<c:out value='${conference.conferenceId}&command=ShowSpeeches'/>"><fmt:message
                                                key="label.speeches"/></a></td>

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

<div class="text-center fixed-bottom" style="margin-bottom: 220px">
    <form action="/?command=FindPage" method="post">
        <div class="form-group">
            <input type="hidden" id="command" value="FindPage">
            <input type="text" id="pageComing" name="pageComing">
        </div>
        <input type="submit" class="btn btn-info btn-md" value="<fmt:message key="label.page"/>">
    </form>
</div>
</body>
</html>
