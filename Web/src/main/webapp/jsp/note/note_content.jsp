<%--
  Created by IntelliJ IDEA.
  User: shengcj
  Date: 2016/9/29
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>${note.title}</title>
    <style>
        .note-content-title{
            text-align: center;
        }
        .note-content-info-dt{
            float:right;
            margin:0 5px;
        }
        .note-content-info-author{
            float:right;
            margin:0 5px;
        }
        .clear{
            clear:both;
        }
        .note-content-body{
            padding: 10px 5px;
        }
    </style>
</head>
<body>
<div id =container>
    <div>
        <div class="note-content-title"><h1>${note.title}</h1></div>
        <hr/>
        <div class="note-content-info">
            <div class="note-content-info-dt"><i class="icon-calendar"></i>${note.createTime}</div>
            <div class="note-content-info-author"><i class="icon-user-md"></i>  ${note.author}</div>
            <div class="clear"></div>
        </div>
        <hr/>
        <div id="note-content-body">${note.content}</div>
    </div>
</div>
</body>
</html>
