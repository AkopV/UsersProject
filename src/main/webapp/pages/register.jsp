<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-COMPATIBLE" content="IE=edge">
    <meta name="viewport" content="width=device=width, initial-scale=1.0">
    <title>Registration</title>
    <link href="<c:url value="/pages/css/registration.css"/>" rel="stylesheet"/>
</head>
<body>
<div class="container" style="width: auto">
    <c:url value="/j_spring_security_check" var="loginUrl"></c:url>
    <form action="${loginUrl}" method="post">
        <h1 class="text" name="login"></h1>
        <input type="text" class="form-control" name="login" placeholder="login" required autofocus value="login"><br/>
        <input type="password" class="form-control" name="password" placeholder="****************"><br/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">sign in</button>
    </form>
</div>
</body>
</html>
