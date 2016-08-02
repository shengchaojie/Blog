<%--
  Created by IntelliJ IDEA.
  User: shengcj
  Date: 2016/7/20
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/scripts/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/jquery.danmu.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/locale/bootstrap-datetimepicker.zh-CN.js" charset="utf-8"></script>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <script>
        //公共定义上下文
        var context ="${pageContext.request.contextPath}";
    </script>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">ShengChaoJie</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/hello">首页</a></li>
                <li><a href="${pageContext.request.contextPath}/user/view/login">登陆</a></li>
                <li><a href="${pageContext.request.contextPath}/danmu">弹幕留言</a></li>
            </ul>
        </div>
    </div>

</nav>
</body>
</html>
