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
        .register-form {
            max-width: 40%;
            margin: auto;
        }
    </style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container">
    <form:form cssClass="register-form form-horizontal" action="${pageContext.request.contextPath}/user/register"
               method="post">
        <div class="form-group">
            <label for="username" class="col-sm-2">username:</label>
            <div class="col-sm-10">
                <input type="text" name="username" id="username" placeholder="请输入用户名" class="form-control"/>
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
                <input type="text" name="nickname" id="nickname" placeholder="请输入昵称" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label for="age" class="col-sm-2">age:</label>
            <div class="col-sm-10">
                <input type="text" name="age" id="age" placeholder="请输入年龄" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label for="birth" class="col-sm-2">birth:</label>
            <div class="col-sm-10">
                <div class=" input-group date form_date " data-date="" data-date-format="dd MM yyyy"
                     data-link-field="birth" data-link-format="yyyy-mm-dd">
                    <input class="form-control" size="14" type="text" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
            </div>
            <input type="hidden" id="birth" name="birth" value=""/>
        </div>
        <div class="form-group">
            <label for="birth" class="col-sm-2">gender:</label>
            <div class=" col-sm-5">
                <label class="radio-inline">
                    <input type="radio" name="gender" id="gender1" value="0">
                    male
                </label>
                <label class="radio-inline">
                    <input type="radio" name="gender" id="gender2" value="1">
                    female
                </label>
            </div>
        </div>
        <button class="btn btn-block btn-primary" type="submit">注册</button>
    </form:form>
</div>
<script type="text/javascript">
    $('.form_date').datetimepicker({
        language: 'fr',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
</script>
</body>
</html>
