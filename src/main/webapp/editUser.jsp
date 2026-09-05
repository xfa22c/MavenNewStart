<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Редактировать пользователя</title></head>
<body>
<h2>Редактирование пользователя</h2>

<%--@elvariable id="errors" type="java"--%>
<c:if test="${not empty errors}">
    <ul style="color:red;">
        <c:forEach var="err" items="${errors}">
            <li><c:out value="${err.message}"/></li>
        </c:forEach>
    </ul>
</c:if>

<form action="${pageContext.request.contextPath}/usersweb/edit?pas=blankEnd" method="post">
    <!-- Передаем ID скрытым полем -->
    <input type="hidden" name="id" value="${user.id}">

    Имя: <input type="text" name="name" value="${not empty param.name ? param.name : user.name}"><br><br>
    Email: <input type="email" name="email" value="${not empty param.email ? param.email : user.email}"><br><br>
    Возраст: <input type="number" name="age" value="${not empty param.age ? param.age : user.age}"><br><br>

    <button type="submit">Обновить</button>
    <a href="${pageContext.request.contextPath}/usersweb/userslist?pas=blankEnd">Отмена</a>
</form>
</body>
</html>