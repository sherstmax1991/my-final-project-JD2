<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Credit applications</title>
</head>
<body>
<c:forEach var="creditApplication" items="${requestScope.creditApplications}">
    <p>${creditApplication}</p>
</c:forEach>
</body>
</html>
