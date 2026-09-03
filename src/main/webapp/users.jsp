<%--
  Created by IntelliJ IDEA.
  User: xfa22c
  Date: 9/3/26
  Time: 8:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="errors" type="java.util.Set"--%>
<%--@elvariable id="users" type="java.util.List"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h2>Add user</h2>
<form action="usersweb?pas=blankEnd" method="post">
    Name: <input type="text" name="name" value="${param.name}"><br>
    Email: <input type="email" name="email" value="${param.email}"><br>
    Age: <input type="number" name="age" value="${param.age}"><br>
    <button type="submit">Add</button>
</form>

<c:if test="${not empty errors}">
    <ul style="color:red;">
        <c:forEach var="err" items="${errors}">
            <li>${err.message}</li>
        </c:forEach>
    </ul>
</c:if>

<h2>User list</h2>
<ul>
    <c:forEach var="user" items="${users}">
        <li>${user.name} (${user.email}, ${user.age})</li>
    </c:forEach>
</ul>
</body>
</html>
