<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" /><html>
<head>
    <title>Header</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.6/css/bootstrap.css" />
    <script src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <fmt:setLocale value="${language}"/>
    <fmt:setBundle basename="translations"/>
</head>
<body data-spy="scroll" data-target="#navbar-main" data-offset="70" style="padding-top:70px;">

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">

        <div class="navbar-header" style="margin-right: 30px">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/"><fmt:message key="Home"/></a>
        </div>
        <div class="collapse navbar-collapse">
            <form class="nav navbar-nav navbar-form navbar-right">
                <select id="language" name="language" onchange="submit()">
                    <option value='ru_RU' ${language == 'ru_RU' ? 'selected' : ''}>Русский</option>
                    <option value='en_US' ${language == 'en_US' ? 'selected' : ''}>English</option>
                </select>
            </form>
        </div>
    </div>
    </div>
</nav>
</body>
</html>
