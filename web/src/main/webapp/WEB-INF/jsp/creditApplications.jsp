<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Credit applications</title>
    <style>
        <%@include file="/WEB-INF/resources/css/search.css"%>
    </style>
    <script src="/webjars/jquery/3.2.1/jquery.js"></script>
    <script src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        <%@include file="/WEB-INF/resources/js/search.js"%>
    </script>
</head>
<body>
<%@ include file="header.jsp"%>

<form action="${pageContext.request.contextPath}/credit_applications" class="reg-form" method="post">
    <fieldset class="form-row">
        <legend><fmt:message key="parameters.clients"/></legend>

        <div class='form-row'>
            <label for='dateFrom'><fmt:message key="period"/></label>
            <input id="dateTo" type='date' step="1" value="2018-03-01" required>
            <input id="dateFrom" type="date" step="1" value="2018-01-01" required>
        </div>

        <div class='form-row'>
            <label for='ageFrom'><fmt:message key="age"/></label>
            <input id="ageTo" type='number' step="1" min="18"  max="60" value="60" required>
            <input id="ageFrom" type="number" step="1" min="18"  max="60" value="18" required>
        </div>

        <div class='form-row'>
            <label for='childrenFrom'><fmt:message key="children"/></label>
            <input type='number' id="childrenTo" step="1" min="0" value="3" required>
            <input type='number' id="childrenFrom" step="1" min="0" value="0" required>
        </div>

        <div style="text-align:right; margin:0 auto;">
            <select id="gender">
                <c:forEach var="gender" items="${requestScope.gender}">
                    <option style="width:125px" value="${gender}">${gender}</option>
                </c:forEach>
            </select>
            <select id="maritalStatus">
                <c:forEach var="maritalStatus" items="${requestScope.maritalStatus}">
                    <option style="width:125px" value="${maritalStatus}">${maritalStatus}</option>
                </c:forEach>
            </select>
            <select id="clientRating">
                <c:forEach var="clientRating" items="${requestScope.clientRatings}">
                    <option style="width:125px" value="${clientRating}">${clientRating}</option>
                </c:forEach>
            </select>
        </div>

    </fieldset>

    <fieldset class="form-row">
        <legend><fmt:message key="parameters.applications"/></legend>

        <div style="text-align:right; margin:0 auto;">
            <select id="creditId">
                <c:forEach var="credit" items="${requestScope.credits}">
                    <option style="width:280px" value="${credit.id}">${credit.title}</option>
                </c:forEach>
            </select>
        </div>
        <br>

        <div class='form-row'>
            <label for='incomeFrom'><fmt:message key="income"/></label>
            <input type='number' id="incomeTo" step="10" min="100" value="5000" required>
            <input type='number' id="incomeFrom" step="10" min="100" value="100" required>
        </div>

        <div class='form-row'>
            <label for='pledgeFrom'><fmt:message key="pledge"/></label>
            <input type='number' id="pledgeTo" step="10" min="0" value="50000" required>
            <input type='number' id="pledgeFrom" step="10" min="0" value="100" required>
        </div>

        <div class='form-row'>
            <label for='sumFrom'><fmt:message key="sum"/></label>
            <input type='number' id="sumTo" step="10" min="100" value="30000" required>
            <input type='number' id="sumFrom" step="10" min="100" value="100" required>
        </div>


        <div class='form-row'>
            <label for='loanPeriodFrom'><fmt:message key="loan.period"/></label>
            <input type='number' id="loanPeriodTo" min="3" value="36" required>
            <input type='number' id="loanPeriodFrom" min="3" value="12" required>
        </div>

        <div style="text-align:left; margin:0 auto;">
            <label for="applicationQuality">applicationQuality</label>
            <select id="applicationQuality">
                <c:forEach var="applicationQuality" items="${requestScope.applicationQuality}">
                    <c:if test="${applicationQuality == 'UNKNOWN'}">
                        <option style="width:125px" value="${applicationQuality}" selected="selected">${applicationQuality}</option>
                    </c:if>
                    <c:if test="${applicationQuality != 'UNKNOWN'}">
                        <option style="width:125px" value="${applicationQuality}">${applicationQuality}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>

        <div style="text-align:left; margin:0 auto;">
            <label for="scoringSystemResolution">scoringSystemResolution</label>
            <select id="scoringSystemResolution" >
                <c:forEach var="applicationQuality" items="${requestScope.applicationQuality}">
                    <c:if test="${applicationQuality == 'GOOD'}">
                        <option style="width:125px" value="${applicationQuality}" selected="selected">${applicationQuality}</option>
                    </c:if>
                    <c:if test="${applicationQuality != 'GOOD'}">
                        <option style="width:125px" value="${applicationQuality}">${applicationQuality}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>

        <div class='form-row'>
            <label for='applicationsPerPage'>Количество заявок на странице</label>
            <input type='number' id="applicationsPerPage" min="1" value="10" required>
        </div>

        <div class='form-row'>
            <label for='applicationsPerPage'>Номер страницы</label>
            <input type='number' id="page" min="1" value="1" required>
        </div>
    </fieldset>
    <button type="button" onclick="sendInputDataToServer()"><fmt:message key="submit"/></button>
</form>
<p id="search-result"></p>
</body>
</html>
