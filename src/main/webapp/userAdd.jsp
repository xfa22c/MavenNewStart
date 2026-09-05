<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Добавить пользователя</title></head>
<body>
<h2>Новый пользователь</h2>

<%--@elvariable id="errors" type="java"--%>
<c:if test="${not empty errors}">
    <ul style="color:red;">
        <c:forEach var="err" items="${errors}">
            <li><c:out value="${err.message}"/></li>
        </c:forEach>
    </ul>
</c:if>

<form action="${pageContext.request.contextPath}/usersweb/add?pas=blankEnd" method="post">
    Имя: <input type="text" name="name" value="${param.name}"><br><br>
    Email: <input type="email" name="email" value="${param.email}"><br><br>
    Возраст: <input type="number" name="age" value="${param.age}"><br><br>
    <button type="submit">Сохранить</button>
    <a href="${pageContext.request.contextPath}/usersweb/userslist?pas=blankEnd">Отмена</a>
</form>
</body>
</html>