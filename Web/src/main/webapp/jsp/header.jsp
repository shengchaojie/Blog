<%--
  Created by IntelliJ IDEA.
  User: shengcj
  Date: 2016/7/20
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>
        <sitemesh:write property="title"/>
    </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fontAwesome/css/font-awesome.min.css">
    <script src="${pageContext.request.contextPath}/scripts/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/jquery.danmu.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/locale/bootstrap-datetimepicker.zh-CN.js"
            charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/scripts/jquery.validate.min.js"></script>
    <script src="http://static.runoob.com/assets/react/react-0.14.7/build/react.min.js"></script>
    <script src="http://static.runoob.com/assets/react/react-0.14.7/build/react-dom.min.js"></script>
    <script src="http://static.runoob.com/assets/react/browser.min.js"></script>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

    <!-- wang editor-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/wang/css/wangEditor.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/wang/js/wangEditor.min.js"></script>

    <script>
        //公共定义上下文
        var context = "${pageContext.request.contextPath}";
        var username = "${sessionScope.user_name}"
        //var serverName ="${pageContext.request.serverName}";
        //var port = "${pageContext.request.serverPort}";
        //var server ="http://"+serverName+":"+port+"/"+context;
        //console.log(server);
    </script>
    <c:set var="username" value="${sessionScope.user_name}"></c:set>
    <style>
        #container {
            width: 70%;
            margin-left: auto;
            margin-right: auto;
            border: 1px solid #ccc;
            min-height: 100%;
            padding: 10px;
        }

        .login-error-display{
            float: left;
            color:red;
        }
    </style>
    <sitemesh:write property="head"/>
</head>
<body style="padding-top: 60px;padding-bottom: 20px">
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"><B>猿超杰</B></a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-left">
                <li><a href="${pageContext.request.contextPath}/hello">首页</a></li>
                <li><a href="${pageContext.request.contextPath}/user/view/register">注册</a></li>
                <li><a href="${pageContext.request.contextPath}/danmu">弹幕留言</a></li>
                <%--<li><a href="${pageContext.request.contextPath}/note">笔记</a></li>--%>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">笔记 <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="${pageContext.request.contextPath}/note">所有笔记</a></li>
                        <li><a href="#">我的笔记</a></li>
                        <li><a href="${pageContext.request.contextPath}/noteAdd">新笔记</a></li>
                    </ul>
                </li>
                <li><p class="navbar-text" data-toggle="tooltip" data-placement="bottom" title="花钱花的好厉害，我要做个记钱的工具">
                    记账本</p></li>
                <li><a href="https://github.com/shengchaojie/Blog">源码</a></li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><p class="navbar-text" id="userinfo">
                    <c:choose>
                        <c:when test="${empty sessionScope.user_name}">
                            你可以<a data-toggle="modal" data-target="#login-modal" href="#login-modal">登录</a>，但是也没鸟用，嘻嘻
                        </c:when>
                        <c:otherwise>
                            ${sessionScope.user_name}，别<a href="${pageContext.request.contextPath}/user/logout">离我而去</a>
                        </c:otherwise>
                    </c:choose>
                </p></li>
            </ul>
        </div>
    </div>
</nav>

<!-- 模态登录框-->
<div class="modal fade" id="login-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <a class="close" data-dismiss="modal">×</a>
                <h3><strong>请登录</strong></h3>
            </div>
            <div class="modal-body">
                <form:form cssClass="form-singin" id="loginForm" action="${pageContext.request.contextPath}/user/login"
                           method="post">
                    <input type="text" id="username" name="username" placeholder="用户名" class="form-control"/><br/>
                    <input type="password" id="password" name="password" placeholder="密码" class="form-control"/><br/>
                    <button class="btn btn-lg btn-primary btn-block" id="login" type="submit">登陆</button>
                    <a class="btn btn-lg btn-block btn-success"
                       href="${pageContext.request.contextPath}/user/view/register" role="button">注册</a>
                </form:form>
            </div>
            <div class="modal-footer">
                <div class="login-error-display"></div>
                <a href="#" class="btn" data-dismiss="modal">关闭</a>
            </div>
        </div>
    </div>
</div>

<sitemesh:write property="body"/>
<div class="footer ">
    <hr/>
    <div id="footer">
        <center><p>shengchaojie@163.com</p></center>
    </div>
</div>

<script>
    //tooltip需要手动用js激活 才能使用
    $(function () {
        $("[data-toggle='tooltip']").tooltip();

        $('#login').click(function () {

            var data = {username: $('#username').val(), password: $('#password').val()};
            $.post(context + '/user/loginModal', data, function (result) {
                if (result.code == 200) {
                    $('#login-modal').modal('hide');
                    //刷新头信息展示
                    $('#userinfo').html(result.object+'，别<a href="${pageContext.request.contextPath}/user/logout">离我而去</a>');
                } else {
                    $('.login-error-display').html(result.message);
                }
            });

            return false;//禁用默认submit事件
        })
    });
</script>

</body>
</html>
