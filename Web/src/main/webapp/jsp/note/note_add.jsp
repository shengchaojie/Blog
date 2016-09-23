<%--
  Created by IntelliJ IDEA.
  User: shengcj
  Date: 2016/9/21
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新笔记</title>
</head>
<body>
<div id="container"></div>
<script type="text/babel">
    


    var HelloWorld =React.createClass({
        render:function () {
            return (
                    <div>Hello,World</div>
            );
        }
    });
    ReactDOM.render(<HelloWorld />,document.getElementById("container"));
</script>
</body>
</html>
