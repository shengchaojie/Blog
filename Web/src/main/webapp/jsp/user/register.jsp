<%--
  User: shengchaojie
  Date: 2016/7/18
  Time: 23:47
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Register</title>
    <form:form action="${pageContext.request.contextPath}/user/register" method="post">
        username:<input type="text" name="username"/><br/>
        password:<input type="text" name="password"/><br/>
        nickname:<input type="text" name="nickname"/><br/>
        <input type="submit" value="register"/>
    </form:form>
</head>
<body>

</body>
</html>
