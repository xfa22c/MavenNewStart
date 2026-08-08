<jsp:useBean id="userName" scope="request" type="java.lang.String"/>
<jsp:useBean id="currentDate" scope="request" type="java.util.Date"/>
<%--
  Created by IntelliJ IDEA.
  User: xfa22c
  Date: 8/4/26
  Time: 8:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Min första JSP</title>
</head>
<body>
    <h1>Hej, världen!</h1>
    <p>Dagens datum: ${currentDate}</p>

    <h1>Hej, ${userName}!</h1>
</body>
</html>
