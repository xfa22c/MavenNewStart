<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <title>Mice List</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 20px;
    }
    h1 {
      color: #333;
    }
    table {
      border-collapse: collapse;
      width: 100%;
      background-color: #fff;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }
    th, td {
      border: 1px solid #ddd;
      padding: 10px;
      text-align: left;
    }
    th {
      background-color: #4CAF50;
      color: white;
    }
    tr:nth-child(even) {
      background-color: #f9f9f9;
    }
    .btn {
      display: inline-block;
      padding: 8px 14px;
      margin: 5px 2px;
      background-color: #4CAF50;
      color: white;
      text-decoration: none;
      border-radius: 4px;
    }
    .btn-edit {
      background-color: #2196F3;
    }
    .btn-delete {
      background-color: #f44336;
    }
    .btn:hover {
      opacity: 0.85;
    }
  </style>
</head>
<body>

<h1>Mice List</h1>

<a href="${pageContext.request.contextPath}/Mouse/addMouse?pas=blankEnd" class="btn">Add Mouse</a>
<a href="${pageContext.request.contextPath}/Manufacturer/listManufacturers?pas=blankEnd" class="btn">Manufacturers</a>
<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Sensor</th>
    <th>Max Acceleration</th>
    <th>Polling Rate</th>
    <th>Price</th>
    <th>Tier</th>
    <th>Manufacturer</th>
    <th>Actions</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${mice}" var="mouse">
    <tr>
      <td>${mouse.id}</td>
      <td>${mouse.name}</td>
      <td>${mouse.sensor}</td>
      <td>${mouse.maxAcceleration}</td>
      <td>${mouse.pollingRate}</td>
      <td>${mouse.price}</td>
      <td>${mouse.tier}</td>
      <td>${mouse.manufacturer.name}</td>
      <td>
        <a href="${pageContext.request.contextPath}/Mouse/editMouse?id=${mouse.id}&pas=blankEnd" class="btn btn-edit">Edit</a>
        <form action="${pageContext.request.contextPath}/Mouse/delete?pas=blankEnd" method="post" style="display:inline;">
          <input type="hidden" name="id" value="${mouse.id}">
          <button type="submit" class="btn btn-delete" onclick="return confirm('Delete this mouse?');">Delete</button>
        </form>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

</body>
</html>