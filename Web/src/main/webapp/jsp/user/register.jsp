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
    <style>
        .register-form{
            max-width: 40%;
            margin: auto;
        }
    </style>
</head>
<body>
    <jsp:include page="../header.jsp"/>
    <div class="container">
        <form:form cssClass="register-form form-horizontal" action="${pageContext.request.contextPath}/user/register" method="post">
            <div class="form-group">
                <label for="username" class="col-sm-2">username:</label>
                <div class="col-sm-10">
                    <input type="text"  name="username" id="username" placeholder="请输入用户名" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2">password:</label>
                <div class="col-sm-10">
                    <input type="text" name="password" id="password" placeholder="请输入密码" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label for="nickname" class="col-sm-2">nickname:</label>
                <div class="col-sm-10">
                    <input type="text" name="nickname" id="nickname" placeholder="请输入昵称"  class="form-control"/>
                </div>
            </div>
            <button class="btn btn-block btn-primary" type="submit" >注册</button>
        </form:form>
    </div>

</body>
</html>
