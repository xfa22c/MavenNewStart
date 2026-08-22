<%--
  Created by IntelliJ IDEA.
  User: xfa22c
  Date: 8/6/26
  Time: 9:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%--@elvariable id="logs" type="java.util.List"--%>
<html>
<head>
    <title>Logs</title>
</head>
<body>
    <h2>Добавить новый лог</h2>
    <form action="logs?pas=blankEnd" method="post">
      <p>
        <label>Тема урока: </label><br>
        <label>
          <input type="text" name="topic" required>
        </label>
      </p>
      <p>
        <label>Время в минутах: </label><br>
        <label>
          <input type="number" name="timeSpent" required>
        </label>
      </p>
      <button type="submit">Записать</button>
    </form>

    <hr>
    <h2>История занятий</h2>
    <ul><c:forEach var="log" items="${logs}">
      <li><c:out value="${log}"/></li>
    </c:forEach></ul>
</body>
</html>
