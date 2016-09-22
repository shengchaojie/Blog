<%--
  Created by IntelliJ IDEA.
  User: shengcj
  Date: 2016/7/20
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
<div id ="container" style="width:70%;margin: 0 auto 0 auto;height: 100%">
</div>
<script type="text/babel">
    var Editor = React.createClass({
        // 编辑器样式
        style: {
            width: '70%',
            height: '400px'
        },
        render: function() {
            return (
                    <div >
                        <div id={this.props.id} style={this.style} contentEditable="true"></div>
                        <%--<button onClick={this.getContent}>get content</button>--%>
                    </div>
            );
        },
        componentDidMount: function () {
            var id = this.props.id;
            this.editor = new window.wangEditor(id);
            this.editor.config.uploadImgUrl = '/upload';
            this.editor.create();

            // 初始化内容
            this.editor.$txt.html(this.props.content);
        },
        // 获取内容
        getContent: function () {
            var content = this.editor.$txt.html();
            console.log(content);
        }
    });

    React.render(
            <Editor id="editor1" content="<p>Hello,World...<img src='http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/0b/tootha_thumb.gif' style='line-height: 1;'></p><p><br></p>"/>,
            document.getElementById('container')
    );
</script>
<%--
<script type="text/babel" src="${pageContext.request.contextPath}/scripts/note.js"/>
--%>
<%--<script>
    var count =0;
    $(function(){
        reset();
        $("img").click(function(){
            if(count <1)
            {
                $(this).fadeToggle("slow",function(){
                    $(this).attr("src",context+"/pics/qq.jpg");
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
        $("img").attr("src",context+"/pics/1.jpg");
        //$("img").attr("display","block");
    }
</script>
<div class="container-fluid poker-container">
    <div class="row">
        <div class="col-md-4">
            <img src="" alt="..." class="img-rounded pocker">
        </div>
        <div class="col-md-4">
            <img src="" alt="..." class="img-rounded pocker">
        </div>
        <div class="col-md-4">
            <img src="" alt="..." class="img-rounded pocker">
        </div>
    </div>
    <div class="row">
        <div class="col-md-4">
            <img src="" alt="..." class="img-rounded pocker">
        </div>
        <div class="col-md-4">
            <img src="" alt="..." class="img-rounded pocker">
        </div>
        <div class="col-md-4">
            <img src="" alt="..." class="img-rounded pocker">
        </div>
    </div>
    <div class="row">
        <div class="col-md-4">
            <img src="" alt="..." class="img-rounded pocker">
        </div>
        <div class="col-md-4">
            <img src="" alt="..." class="img-rounded pocker">
        </div>
        <div class="col-md-4">
            <img src="" alt="..." class="img-rounded pocker">
        </div>
    </div>
    <div class="row" style="padding:10px">
        <div class="col-md-4 col-md-offset-3">
            <button class="btn btn-default" type="button" onclick="reset();">reset</button>
        </div>
    </div>
</div>--%>
</body>
</html>
