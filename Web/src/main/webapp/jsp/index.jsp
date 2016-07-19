<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>弹幕测试</title>
    <script src="${pageContext.request.contextPath}/scripts/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/jquery.danmu.js"></script>
    <style>
        #danmuarea {
            position: relative;
            background: black;
            width: 800px;
            height: 445px;
            margin-left: auto;
            margin-right: auto;
        }
        .ctr {
            font-size: 1em;
            line-height: 2em;
        }
        .center {
            text-align: center;
        }
    </style>
</head>
<body class="center">
<%--<h2>Hello World!${sessionScope.username}</h2>
<a href="${pageContext.request.contextPath}/user/view/login">login</a><br/>
<a href="${pageContext.request.contextPath}/user/view/register">register</a>--%>
<div id="danmuarea">
    <div id="danmu"></div>
</div>
<div class="ctr">
    <select name="color" id="color">
        <option value="white">白色</option>
        <option value="red">红色</option>
        <option value="green">绿色</option>
        <option value="blue">蓝色</option>
        <option value="yellow">黄色</option>
    </select>
    <select name="text_size" id="text_size">
        <option value="1">大文字</option>
        <option value="0">小文字</option>
    </select>
    <select name="position" id="position">
        <option value="0">滚动</option>
        <option value="1">顶端</option>
        <option value="2">底端</option>
    </select>
    <input type="textarea" id="text" max=300/>
    <button onclick="send_danmu()">发送弹幕</button>
    <br/>
    <button onclick="add()">固定测试弹幕</button>
</div>

<script src="${pageContext.request.contextPath}/scripts/scj.danmu.js"></script>

</body>
</html>
