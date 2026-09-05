<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Список пользователей</title></head>
<body>
<h2>Пользователи</h2>

<a href="${pageContext.request.contextPath}/usersweb/add?pas=blankEnd">Добавить пользователя</a>
<br><br>

<table>
    <tr>
        <th>ID</th>
        <th>Имя</th>
        <th>Email</th>
        <th>Возраст</th>
        <th>Действия</th>
    </tr>
    <%--@elvariable id="users" type="java.util.List"--%>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td>${user.age}</td>
            <td>
                <!-- Кнопка перехода на форму редактирования -->
                <a href="${pageContext.request.contextPath}/usersweb/edit?id=${user.id}&pas=blankEnd">Редактировать</a>
                |
                <!-- Форма удаления -->
                <form action="${pageContext.request.contextPath}/usersweb/delete?pas=blankEnd" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="${user.id}">
                    <button type="submit" onclick="return confirm('Удалить?')">Удалить</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>