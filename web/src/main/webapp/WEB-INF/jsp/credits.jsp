<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Credit</title>
</head>
<body>
<c:forEach var="credit" items="${requestScope.credits}">
    <p>${credit}</p>
</c:forEach>
</body>
</html>
