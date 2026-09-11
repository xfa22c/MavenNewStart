<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="dgf.xfa22c.miniJakartaProject.entities.MouseEntity" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Редактировать мышку</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #121212;
            color: #e0e0e0;
            margin: 0;
            padding: 40px;
            display: flex;
            justify-content: center;
        }
        .form-card {
            width: 100%;
            max-width: 480px;
            background-color: #1e1e1e;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 8px 24px rgba(0,0,0,0.5);
        }
        h2 {
            margin-top: 0;
            margin-bottom: 20px;
            color: #fff;
        }
        .form-group {
            margin-bottom: 16px;
        }
        label {
            display: block;
            margin-bottom: 6px;
            color: #a0a0a0;
            font-size: 14px;
        }
        input {
            width: 100%;
            padding: 10px 12px;
            border-radius: 6px;
            border: 1px solid #333;
            background-color: #2a2a2a;
            color: #fff;
            box-sizing: border-box;
            font-size: 14px;
        }
        input:focus {
            outline: none;
            border-color: #10b981;
        }
        .btn-submit {
            width: 100%;
            background-color: #10b981;
            color: white;
            border: none;
            padding: 12px;
            border-radius: 6px;
            font-weight: 600;
            font-size: 15px;
            cursor: pointer;
            margin-top: 10px;
        }
        .btn-submit:hover { background-color: #059669; }
        .btn-cancel {
            display: block;
            text-align: center;
            margin-top: 12px;
            color: #888;
            text-decoration: none;
            font-size: 14px;
        }
        .btn-cancel:hover { color: #bbb; }
    </style>
</head>
<body>
<%
    MouseEntity mouse = (MouseEntity) request.getAttribute("mouse");
    if (mouse != null) {
%>
<div class="form-card">
    <h2>Редактирование мышки #<%= mouse.getId() %></h2>
    <form action="${pageContext.request.contextPath}/Mouse/editMouse?pas=blankEnd" method="post">
        <input type="hidden" name="id" value="<%= mouse.getId() %>"/>

        <div class="form-group">
            <label for="name">Название модели</label>
            <input type="text" id="name" name="name" value="<%= mouse.getName() %>" required />
        </div>
        <div class="form-group">
            <label for="sensor">Модель сенсора</label>
            <input type="text" id="sensor" name="sensor" value="<%= mouse.getSensor() %>" required />
        </div>
        <div class="form-group">
            <label for="maxAccel">Макс. ускорение (G)</label>
            <input type="number" id="maxAccel" name="maxAccel" value="<%= mouse.getMaxAcceleration() %>" min="5" max="150" required />
        </div>
        <div class="form-group">
            <label for="pollingRate">Частота опроса (Hz)</label>
            <input type="number" id="pollingRate" name="pollingRate" value="<%= mouse.getPollingRate() %>" min="125" max="16000" required />
        </div>
        <button type="submit" class="btn-submit">Обновить</button>
        <a href="${pageContext.request.contextPath}/Mouse/mouseList?pas=blankEnd" class="btn-cancel">Отмена</a>
    </form>
</div>
<% } else { %>
<div class="form-card">
    <h2>Ошибка</h2>
    <p>Мышка не найдена.</p>
    <a href="${pageContext.request.contextPath}/Mouse/mouseList?pas=blankEnd" class="btn-cancel">Вернуться к списку</a>
</div>
<% } %>
</body>
</html>