<%--
  Created by IntelliJ IDEA.
  User: xfa22c
  Date: 8/6/26
  Time: 8:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--@elvariable id="name" type="java.lang.String"--%>
<%--@elvariable id="numbers" type="java.util.List"--%>

<html>
<head>
    <title>JSTL Test</title>
</head>
<body>

    <p>Hej,  <c:out value="${name}"/></p>

    <c:if test="${name == 'Angelina'}">
        <p>Du är Angelina</p>
    </c:if>

    <ul>

        <c:forEach var="i" items="${numbers}">
            <li>${i}</li>
        </c:forEach>
    </ul>

</body>
</html>
