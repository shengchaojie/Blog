<%--
  User: shengchaojie
  Date: 2016/7/13
  Time: 0:01
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login</title>
    <form:form action="${pageContext.request.contextPath}/user/login" method="post">
        username:<input type="text" name="username"/><br/>
        password:<input type="text" name="password"/><br/>
        <input type="submit" value="login"/>
    </form:form>
</head>
<body>

</body>
</html>
