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

    <table border="1" cellpadding="5">
        <caption><h2>List of conferences</h2></caption>
        <tr>
            <th>ID</th>
            <th>name</th>
            <th>registered people</th>
            <th>address</th>
            <th>date </th>
          <th><a href="/?command=<c:out value='Logout'/>">Logout</a></th>

        </tr>
<c:forEach var="conference" items="${sessionScope.conferences}">

    <tr>
    <td> <c:out value="${conference.conferenceId}"/> </td>
    <td> <c:out value="${conference.name}"/> </td>
    <td> <c:out value="${conference.location.address}"/> </td>
    <td> <c:out value="${conference.date}"/> </td>

    <td>
          <a href="/?id=<c:out value='${conference.conferenceId}&command=ShowEditConferencePage' />">Edit</a>
         <a href="/?id=<c:out value='${conference.conferenceId}&command=ShowSpeeches'/>">Show speeches</a>
    </td>

    </tr>



</c:forEach>
    </table>

</body>
</html>
