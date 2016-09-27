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
    <title>
        <sitemesh:write property="title"/>
    </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/scripts/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/jquery.danmu.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/locale/bootstrap-datetimepicker.zh-CN.js" charset="utf-8"></script>
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
        var context ="${pageContext.request.contextPath}";
        var port = "${pageContext.request.serverPort}";
    </script>

    <style>
        #container{
            width:70%;
            margin-left:auto;
            margin-right: auto;
            border:1px solid #ccc;
            min-height:100%;
            padding :10px;
        }
    </style>
    <sitemesh:write property="head"/>
</head>
<body style="padding-top: 60px;padding-bottom: 20px">
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">ShengChaoJie</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/hello">首页</a></li>
                <%--<li><a href="${pageContext.request.contextPath}/user/view/login">登陆</a></li>--%>
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
                <li><a href="https://github.com/shengchaojie/Blog">源码</a></li>
            </ul>
        </div>
    </div>
</nav>
<div id="container"></div>
<sitemesh:write property="body"/>
<div class="footer ">
    <hr/>
   <div id="footer">
       <center><p>shengchaojie@163.com</p></center>
   </div>
</div>
</body>
</html>
