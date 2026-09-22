<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Add Mouse</title>
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
            background-color: #4CAF50;
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

<h1>Add Mouse</h1>

<form action="${pageContext.request.contextPath}/Mouse/addMouse?pas=blankEnd" method="post">
    <label for="name">Name</label>
    <input type="text" id="name" name="name" value="${param.name}" required minlength="3" maxlength="50">

    <label for="sensor">Sensor</label>
    <input type="text" id="sensor" name="sensor" value="${param.sensor}" required>

    <label for="maxAccel">Max Acceleration</label>
    <input type="number" id="maxAccel" name="maxAccel" value="${param.maxAccel}" required min="5" max="150">

    <label for="pollingRate">Polling Rate</label>
    <input type="number" id="pollingRate" name="pollingRate" value="${param.pollingRate}" required min="125" max="16000">

    <label for="price">Price</label>
    <input type="number" id="price" name="price" value="${param.price}" required min="1">

    <label for="manufacturerId">Manufacturer</label>
    <select id="manufacturerId" name="manufacturerId" required>
        <option value="">-- Select manufacturer --</option>
        <c:forEach items="${manufacturers}" var="m">
            <option value="${m.id}" ${m.id == param.manufacturerId ? 'selected' : ''}>${m.name}</option>
        </c:forEach>
    </select>

    <button type="submit">Add Mouse</button>
</form>

<a href="${pageContext.request.contextPath}/Mouse/mouseList?pas=blankEnd" class="back">← Back to list</a>

</body>
</html>