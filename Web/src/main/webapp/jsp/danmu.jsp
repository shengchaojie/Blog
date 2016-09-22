<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>弹弹弹幕</title>
    <script>
        var contextPath = "${pageContext.request.contextPath}";
        var port = "${pageContext.request.serverPort}";
    </script>
    <style>
        #danmuarea {
            position: relative;
            background: black;
            width: 70%;
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

        #danmu {
            width: 70%;
            height: 445px;
        }

        .danmu-sender {
            width: 70%;
            padding-top: 10px;
            border: 1px solid #cccccc;
            margin-top: 10px;
            border-radius: 4px 4px 0 0;
        }

        .commenter {
            width: 70%;
            padding: 10px;
            border: 1px solid #cccccc;
            margin: 10px auto 10px auto;

            border-radius: 4px;
        }

        .commenter-item {
            margin: 15px 0 15px;
            border: 1px solid #cccccc;
        / / border-radius: 4 px 4 px 0 0;
        }

        .commenter-item-header {
            border-bottom: 1px solid #cccccc;
        }

        .commenter-item-content {
            padding: 10px;
            min-height: 80px;
        }

        .commenter-item-header-datetime {
            margin-right: 10px;
            float: right;
        }
    </style>
</head>
<body class="center">
<div id="danmuarea">
    <div id="danmu"></div>
</div>
<%--<div class="container-fluid danmu-sender">
    <div class="row">
        <div class="form-group col-md-4">
            <select name="color" id="color" class="form-control">
                <option value="white">白色</option>
                <option value="red">红色</option>
                <option value="green">绿色</option>
                <option value="blue">蓝色</option>
                <option value="yellow">黄色</option>
            </select>
        </div>
        <div class="form-group col-md-4">
            <select name="text_size" id="text_size" class="form-control">
                <option value="1">大文字</option>
                <option value="0">小文字</option>
            </select>
        </div>
        <div class="form-group col-md-4">
        <select name="position" id="position" class="form-control">
            <option value="0">滚动</option>
            <option value="1">顶端</option>
            <option value="2">底端</option>
        </select>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-2">
            <select name="name" id="name" class="form-control">
                <option value="小红帽">小红帽</option>
                <option value="大灰狼">大灰狼</option>
            </select>
        </div>
        <div class="form-group col-md-8">
            <input type="textarea" class="form-control" id="text" max=300/>
        </div>
        <div class="form-group col-md-2">
            <button class="btn btn-default" onclick="send_danmu()">发送弹幕</button>
        </div>
    </div>
   &lt;%&ndash; <button onclick="add()">固定测试弹幕</button>
    <button onclick="test_insert()">插入策划</button>&ndash;%&gt;
</div>--%>
<div id="commenter"></div>
<script src="${pageContext.request.contextPath}/scripts/scj.danmu.js"></script>
<script type="text/babel">
    var Sender = React.createClass({
        onSendClick:function () {
            alert('test');
        },
        render: function () {
            return (
                    <div className="container-fluid danmu-sender">
                        <div className="row">
                            <div className="form-group col-md-4">
                                <select name="color" id="color" className="form-control">
                                    <option value="white">白色</option>
                                    <option value="red">红色</option>
                                    <option value="green">绿色</option>
                                    <option value="blue">蓝色</option>
                                    <option value="yellow">黄色</option>
                                </select>
                            </div>
                            <div className="form-group col-md-4">
                                <select name="text_size" id="text_size" className="form-control">
                                    <option value="1">大文字</option>
                                    <option value="0">小文字</option>
                                </select>
                            </div>
                            <div className="form-group col-md-4">
                                <select name="position" id="position" className="form-control">
                                    <option value="0">滚动</option>
                                    <option value="1">顶端</option>
                                    <option value="2">底端</option>
                                </select>
                            </div>
                        </div>
                        <div className="row">
                            <div className="form-group col-md-2">
                                <select name="name" id="name" className="form-control">
                                    <option value="小红帽">小红帽</option>
                                    <option value="大灰狼">大灰狼</option>
                                </select>
                            </div>
                            <div className="form-group col-md-8">
                                <input type="textarea" className="form-control" id="text" max="300"/>
                        </div>
                        <div className="form-group col-md-2">
                            <button className="btn btn-default" onClick={this.onSendClick}>发送弹幕</button>
                        </div>
                    </div>
            </div>
            )
            ;
        }
    });

    var CommentItem = React.createClass({
        render: function () {
            return (
                    <div className="commenter-item">
                        <div className="commenter-item-header">
                            <div>{this.props.comment.name}:
                                <div className="commenter-item-header-datetime">{ new Date(this.props.comment.createTime).toGMTString()}</div>
                            </div>
                        </div>
                        <div className="commenter-item-content">
                            {this.props.comment.text}
                        </div>
                    </div>
            );
        }
    });

    var Commenter = React.createClass({
        getInitialState: function () {
            var comments = [{name: 'ss', date: '2016年9月21日, PM 05:36:41', content: "今晚月色真美"}, {
                name: 'ss',
                date: '2016年9月21日, PM 05:36:41',
                content: "今晚月色真美"
            }];
            console.log(comments);
            return {
                comments: []
            };
        },
        componentDidMount: function () {

            $.get(context + "/barrage/comment/getAll", function (result) {
                this.setState({comments: result});
            }.bind(this));
        },
        render: function () {
            return (
                    <div>
                        <Sender />
                        <div className="commenter">
                            {
                                this.state.comments.map(function (comment) {
                                    return (
                                            <CommentItem comment={comment}/>
                                    );
                                })
                            }
                        </div>
                    </div>
            );
        }
    });
    ReactDOM.render(<Commenter />, document.getElementById('commenter'));
</script>
</body>
</html>
