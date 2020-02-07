
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lang"/>

<html>
<head>
    <title>Title</title>
</head>


<body>
<table border="1" cellpadding="5">
    <form action="/?command=Authorization" method="post">
        <tr>
            <td><input type="text" id="username" placeholder="enter username" name="username"></td>

            <td><input type="text" id="password" placeholder="enter password" name="password"></td>

            <td><input type="submit" name="login" value="<fmt:message key="label.login" />"/></td>
            <td><input type="submit" name="register" value="<fmt:message key="label.register" />"/></td>
        </tr>
    </form>
</table>


<h1>${requestScope.error}</h1>

</body>
</html>
