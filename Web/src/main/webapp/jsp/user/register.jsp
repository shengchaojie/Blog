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
            max-width: 100%;
            margin-left:20%;
        }
        div.error{
            color: red;
            padding-left:15px;
            background:url("${pageContext.request.contextPath}/pics/unchecked.gif") no-repeat 0% 50%;
            font-weight: bold;
            margin-top: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <form:form cssClass="register-form form-horizontal" id="registerForm"
               action="${pageContext.request.contextPath}/user/register"
               method="post">
        <div class="form-group">
            <label for="username" class="col-sm-2">用户名:</label>
            <div class="col-sm-6">
                <input type="text" name="username" id="username" placeholder="请输入用户名" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2">密码:</label>
            <div class="col-sm-6">
                <input type="password" name="password" id="password" placeholder="请输入密码" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label for="password_check" class="col-sm-2">密码确认:</label>
            <div class="col-sm-6">
                <input type="password" name="password_check" id="password_check" placeholder="请再次输入密码" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label for="nickname" class="col-sm-2">昵称:</label>
            <div class="col-sm-6">
                <input type="text" name="nickname" id="nickname" placeholder="请输入昵称" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label for="age" class="col-sm-2">年龄:</label>
            <div class="col-sm-6">
                <input type="text" name="age" id="age" placeholder="请输入年龄" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label for="birth" class="col-sm-2">出生日期:</label>
            <div class="col-sm-6">
                <div class=" input-group date form_date " data-date="" data-date-format="yyyy-mm-dd"
                     data-link-field="birth" data-link-format="yyyy-mm-dd">
                    <input class="form-control" size="14" type="text" name="birth" id="birthDisplay" value="" placeholder="点击选择生日">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
            </div>
            <input type="hidden" id="birth" name="birth" value=""/>
        </div>
        <div class="form-group">
            <label for="birth" class="col-sm-2">性别:</label>
            <div class=" col-sm-6">
                <label class="radio-inline">
                    <input type="radio" name="gender" id="gender1" value="0" checked>
                    男
                </label>
                <label class="radio-inline">
                    <input type="radio" name="gender" id="gender2" value="1">
                    女
                </label>
            </div>
        </div>
        <div class="form-group" style="margin-top: 20px">
            <div class="col-sm-2 col-sm-offset-2">
                <button class="btn btn-block btn-primary" type="submit">注册</button>
            </div>
            <div class="col-sm-2">
                <button class="btn btn-block btn-success" type="button" id="reset">重置</button>
            </div>
        </div>

    </form:form>
</div>
<script>
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
</script>
<script>
    $.validator.setDefaults({
        /*submitHandler:function () {
            alert("test");
        }*/
        //debug:true
    });

    $().ready(function () {
        var validator=
            $("#registerForm").validate({
            errorPlacement:function (error,element) {
                if(element.is("#birthDisplay"))
                {
                    error.insertAfter(element.parent().parent());
                }else {
                    error.insertAfter(element.parent());
                }
            },
            errorElement:"div",
            errorClass:"error col-sm-2",
            rules: {
                username: {
                    required: true,
                    minlength: 3,
                    remote:{
                        url:"${pageContext.request.contextPath}/user/isUserNameExisted",
                        type:"get",
                        dataType: "json",
                        data:{
                            username:function(){
                                return $("#username").val();
                            }
                        }
                    }
                },
                password:{required:true,minlength:6},
                nickname:{required:true,minlength:2},
                password_check:{required:true,minlength:6,equalTo:"#password"},
                age:{required:true},
                birth:{required:true}
            },
            messages: {
                username:{
                    required:"请输入用户名",
                    minlength:"用户名必须超过3位",
                    remote:"用户名已经被注册"
                },
                password:{required:"请输入密码",minlength:"密码必须超过6位"},
                nickname:{required:"请输入昵称",minlength:"昵称必须超过2位"},
                password_check:{required:"请确认密码",minlength:"密码必须超过6位",equalTo:"两次输入密码需要相同"},
                age:{required:"请选择年龄"},
                birth:{required:"请选择生日"}
            }
        });

        $("#reset").click(function () {
            validator.resetForm();
        })
    });

</script>
</body>
</html>
