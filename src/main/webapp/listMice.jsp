<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dgf.xfa22c.miniJakartaProject.entities.MouseEntity" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Mice Management</title>
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
    .container {
      width: 100%;
      max-width: 900px;
      background-color: #1e1e1e;
      padding: 30px;
      border-radius: 12px;
      box-shadow: 0 8px 24px rgba(0,0,0,0.5);
    }
    .header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 24px;
    }
    h1 {
      margin: 0;
      font-size: 24px;
      color: #ffffff;
    }
    .btn-add {
      background-color: #3b82f6;
      color: white;
      padding: 10px 18px;
      text-decoration: none;
      border-radius: 6px;
      font-weight: 600;
      transition: background 0.2s;
    }
    .btn-add:hover { background-color: #2563eb; }
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 10px;
    }
    th, td {
      padding: 14px 16px;
      text-align: left;
      border-bottom: 1px solid #2a2a2a;
    }
    th {
      background-color: #252525;
      color: #a0a0a0;
      font-size: 14px;
      text-transform: uppercase;
      letter-spacing: 0.5px;
    }
    tr:hover { background-color: #262626; }
    .actions {
      display: flex;
      gap: 8px;
    }
    .btn-edit {
      background-color: #10b981;
      color: white;
      padding: 6px 12px;
      text-decoration: none;
      border-radius: 4px;
      font-size: 13px;
    }
    .btn-edit:hover { background-color: #059669; }
    .btn-delete {
      background-color: #ef4444;
      color: white;
      border: none;
      padding: 6px 12px;
      border-radius: 4px;
      font-size: 13px;
      cursor: pointer;
    }
    .btn-delete:hover { background-color: #dc2626; }
    .empty-msg {
      text-align: center;
      padding: 30px;
      color: #888;
    }
  </style>
</head>
<body>
<div class="container">
  <div class="header">
    <h1>Каталог мышек</h1>
    <a href="${pageContext.request.contextPath}/Mouse/addMouse?pas=blankEnd" class="btn-add">+ Добавить мышку</a>
  </div>

  <table>
    <thead>
    <tr>
      <th>ID</th>
      <th>Модель</th>
      <th>Сенсор</th>
      <th>Ускорение (G)</th>
      <th>Частота опроса (Hz)</th>
      <th>Действия</th>
    </tr>
    </thead>
    <tbody>
    <%
      @SuppressWarnings("unchecked") List<MouseEntity> mice = (List<MouseEntity>) request.getAttribute("mice");
      if (mice != null && !mice.isEmpty()) {
        for (MouseEntity mouse : mice) {
    %>
    <tr>
      <td><%= mouse.getId() %></td>
      <td><strong><%= mouse.getName() %></strong></td>
      <td><%= mouse.getSensor() %></td>
      <td><%= mouse.getMaxAcceleration() %> G</td>
      <td><%= mouse.getPollingRate() %> Hz</td>
      <td class="actions">
        <a href="${pageContext.request.contextPath}/Mouse/editMouse?id=<%= mouse.getId() %>&pas=blankEnd" class="btn-edit">Редактировать</a>
        <form action="${pageContext.request.contextPath}/Mouse/delete?pas=blankEnd" method="post" style="display:inline;">
          <input type="hidden" name="id" value="<%= mouse.getId() %>"/>
          <button type="submit" class="btn-delete" onclick="return confirm('Удалить эту мышку?');">Удалить</button>
        </form>
      </td>
    </tr>
    <%
      }
    } else {
    %>
    <tr>
      <td colspan="6" class="empty-msg">Список мышек пуст. Добавьте первую!</td>
    </tr>
    <% } %>
    </tbody>
  </table>
</div>
</body>
</html>