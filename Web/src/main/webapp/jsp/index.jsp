<%--
  Created by IntelliJ IDEA.
  User: shengcj
  Date: 2016/7/20
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页2</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<script>
    var count =0;
    reset();
    $(function(){
        $("img").click(function(){
            if(count <1)
            {
                $(this).fadeToggle("slow",function(){
                    $(this).attr("src","pics/qq.jpg");
                    $(this).fadeIn("normal");
                });

                count ++;
            }else
            {
                alert('no');
            }

        });
    });

    function reset()
    {
        count =0;
        $("img").attr("src","pics/1.jpg");
        $("img").attr("display","block");
    }
</script>
<div class="container-fluid" style="width:600px;height:600px">
    <div class="row">
        <div class="col-md-4">
            <img src="pics/1.jpg" alt="..." class="img-rounded pocker">
        </div>
        <div class="col-md-4">
            <img src="pics/1.jpg" alt="..." class="img-rounded pocker">
        </div>
        <div class="col-md-4">
            <img src="pics/1.jpg" alt="..." class="img-rounded pocker">
        </div>
    </div>
    <div class="row">
        <div class="col-md-4">
            <img src="pics/1.jpg" alt="..." class="img-rounded pocker">
        </div>
        <div class="col-md-4">
            <img src="pics/1.jpg" alt="..." class="img-rounded pocker">
        </div>
        <div class="col-md-4">
            <img src="pics/1.jpg" alt="..." class="img-rounded pocker">
        </div>
    </div>
    <div class="row">
        <div class="col-md-4">
            <img src="pics/1.jpg" alt="..." class="img-rounded pocker">
        </div>
        <div class="col-md-4">
            <img src="pics/1.jpg" alt="..." class="img-rounded pocker">
        </div>
        <div class="col-md-4">
            <img src="pics/1.jpg" alt="..." class="img-rounded pocker">
        </div>
    </div>
    <div class="row" style="padding:10px">
        <div class="col-md-4 col-md-offset-3">
            <button class="btn btn-default" type="button" onclick="reset();">reset</button>
        </div>
    </div>
</div>
</body>
</html>
