<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Clients</title>
</head>
<body>
<c:forEach var="client" items="${requestScope.clients}">
    <p>${client.firstName} ${client.lastName}, ${client.birthday} г.р., ${client.rating}</p>
</c:forEach>
</body>
</html>
