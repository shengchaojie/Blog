<%--
  Created by IntelliJ IDEA.
  User: shengcj
  Date: 2016/9/18
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>笔记</title>
    <style>
        .noteRow{
            padding :10px
        }
        .noteTag
        {
            margin: 0 5px;
            float:left;
        }
        #container{
            width:70%;
            margin-left:auto;
            margin-right: auto;
            border:1px solid #ccc;
            height:100%;
        }
        .note-content{
            margin-right: 0;
            margin-left: 0;
            background-color: #fff;
            border-color: #ddd;
            border-width: 1px;
            border-radius: 4px 4px 0 0;
        }
    </style>
</head>
<body>
<div id="container" ></div>
<script type="text/babel" src="${pageContext.request.contextPath}/scripts/note.js"></script>
</body>

    </html>
