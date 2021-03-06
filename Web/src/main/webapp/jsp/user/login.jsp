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
    <style>
        .form-singin{
            max-width: 330px;
            margin: 10% auto;
        }

        .login-container{
            height: 100%;
        }
    </style>
</head>
<body>
    <div class="container login-container">
        <form:form cssClass="form-singin"  id="loginForm" action="${pageContext.request.contextPath}/user/login" method="post">
            <h2><strong>请登录</strong></h2>
            <input type="text" name="username" placeholder="用户名" class="form-control"/><br/>
            <input type="password" name="password" placeholder="密码" class="form-control"/><br/>
            <button class="btn btn-lg btn-primary btn-block" id="login" type="submit">登陆</button>
            <a class="btn btn-lg btn-block btn-success" href="${pageContext.request.contextPath}/user/view/register" role="button">注册</a>
        </form:form>
    </div>
</body>
</html>
