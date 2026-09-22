<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Edit Mouse</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 20px;
        }
        h1 {
            color: #333;
        }
        form {
            background-color: #fff;
            padding: 20px;
            max-width: 500px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            border-radius: 4px;
        }
        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
            color: #333;
        }
        input, select {
            width: 100%;
            padding: 8px;
            margin-top: 4px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            margin-top: 15px;
            padding: 10px 16px;
            background-color: #2196F3;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            opacity: 0.85;
        }
        .back {
            display: inline-block;
            margin-top: 15px;
            color: #2196F3;
            text-decoration: none;
        }
        .error {
            color: red;
            border: 1px solid red;
            padding: 10px;
            margin-bottom: 15px;
            background-color: #fff0f0;
        }
    </style>
</head>
<body>

<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>

<h1>Edit Mouse</h1>

<form action="${pageContext.request.contextPath}/Mouse/editMouse?pas=blankEnd" method="post">
    <input type="hidden" name="id" value="${empty param.id ? mouse.id : param.id}">

    <label for="name">Name</label>
    <input type="text" id="name" name="name"
           value="${empty param.name ? mouse.name : param.name}"
           required minlength="3" maxlength="50">

    <label for="sensor">Sensor</label>
    <input type="text" id="sensor" name="sensor"
           value="${empty param.sensor ? mouse.sensor : param.sensor}"
           required>

    <label for="maxAccel">Max Acceleration</label>
    <input type="number" id="maxAccel" name="maxAccel"
           value="${empty param.maxAccel ? mouse.maxAcceleration : param.maxAccel}"
           required min="5" max="150">

    <label for="pollingRate">Polling Rate</label>
    <input type="number" id="pollingRate" name="pollingRate"
           value="${empty param.pollingRate ? mouse.pollingRate : param.pollingRate}"
           required min="125" max="16000">

    <label for="price">Price</label>
    <input type="number" id="price" name="price"
           value="${empty param.price ? mouse.price : param.price}"
           required min="1">

    <button type="submit">Update Mouse</button>
</form>

<a href="${pageContext.request.contextPath}/Mouse/mouseList?pas=blankEnd" class="back">← Back to list</a>

</body>
</html>