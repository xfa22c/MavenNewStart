<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Edit Manufacturer</title>
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
        input {
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
    </style>
</head>
<body>

<h1>Edit Manufacturer</h1>

<form action="${pageContext.request.contextPath}/Manufacturer/editManufacturer?pas=blankEnd" method="post">
    <input type="hidden" name="id" value="${manufacturer.id}">

    <label for="name">Name</label>
    <input type="text" id="name" name="name" value="${manufacturer.name}" required minlength="2" maxlength="50">

    <label for="yearOfCreation">Year of Creation</label>
    <input type="number" id="yearOfCreation" name="yearOfCreation" value="${manufacturer.yearOfCreation}" required min="1800" max="2100">

    <button type="submit">Update Manufacturer</button>
</form>

<a href="${pageContext.request.contextPath}/Manufacturer/listManufacturers?pas=blankEnd" class="back">← Back to list</a>

</body>
</html>