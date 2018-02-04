<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Children</title>
</head>
<body>
<c:forEach var="child" items="${requestScope.children}">
    <p>${child.firstName} ${child.lastName}, ${child.birthday} г.р.</p>
</c:forEach>
</body>
</html>
