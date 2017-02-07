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
        .note-content-title {
            text-align: center;
        }

        .note-content-info-dt {
            float: right;
            margin: 0 5px;
        }

        .note-content-info-author {
            float: right;
            margin: 0 5px;
        }

        .clear {
            clear: both;
        }

        .note-content-body {
            border: 1px solid #cccccc;
            padding: 10px 5px;
            min-height:600px;
        }

        .note-comment {
            border: 1px solid #cccccc;
            margin-top: 10px;
            margin-bottom: 10px;
        }

        .note-comment-item {
            border: 1px solid #cccccc;
            border-bottom: 1px solid #cccccc;
            /* padding: 10px;*/
            margin-left: -1px;
            margin-right: -1px;
            margin-top: -1px;
            /*margin-bottom: 10px;*/
        }

        .note-comment-head {
            border-bottom: 1px solid #cccccc;
            padding-bottom: 5px;
        }

        .note-comment-head-name {
            float: left;
        }

        .note-comment-head-time {
            float: right;
        }

        .note-comment-body {
            padding: 10px 5px;
            min-height: 60px;
        }

        .note-comment-reply {
            text-align: center;
        }

        .note-comment-margin {
            margin: 10px;
        }

        .note-comment-adder {
            margin: 10px;
        }

        .note-comment-reply-adder {
            display: none;
        }
    </style>
</head>
<body>
<div id="container">
    <div>
        <div class="note-content-title"><h1>${note.title}</h1></div>
        <hr/>
        <div class="note-content-info">
            <div class="note-content-info-dt"><i class="icon-calendar"></i>${note.createTime}</div>
            <div class="note-content-info-author"><i class="icon-user-md"></i> ${note.author}</div>
            <div class="clear"></div>
        </div>
        <div class="note-content-body">${note.content}</div>
    </div>
    <hr/>
    <div id="comment-container"></div>
</div>
<script type="text/babel">
    var CommentItemContent = React.createClass({
        render: function () {
            return (
                    <div className="note-comment-item">
                        <div className="note-comment-head">
                            <div className="note-comment-head-name">{this.props.comment.user.nickname}</div>
                            <div className="note-comment-head-time">{new Date(this.props.comment.createTime).toLocaleString()}</div>
                            <div className="clear"></div>
                        </div>
                        <div className="note-comment-body">
                            {this.props.comment.content}
                        </div>
                        {
                            (function () {
                                if (this.props.comment.targetComment != null) {
                                    return (
                                            <div className="note-comment-margin">
                                                <CommentItemContent comment={this.props.comment.targetComment}/>
                                            </div>
                                    );
                                }
                            }.bind(this))()
                        }
                    </div>
            );
        }
    });

    var CommentItemReplyBar = React.createClass({
        onReplyClick: function () {
            console.log(this.props.commentId);
            $('#note-comment-reply-adder' + this.props.commentId).show();
            $('#note-comment-reply-display' + this.props.commentId).hide();
        },
        render: function () {
            return (
                    <div className="note-comment-reply">
                        <div id={"note-comment-reply-display" + this.props.commentId}
                             className="note-comment-reply-display" onClick={this.onReplyClick}>点击回复
                        </div>
                        <div className="note-comment-reply-adder"
                             id={"note-comment-reply-adder" + this.props.commentId}>
                            <CommentAdder commentId={this.props.commentId} noteId={this.props.noteId} onAddButtonClick={this.props.onReplyButtonClick}/>
                        </div>
                    </div>
            );
        }
    });

    var CommentItem = React.createClass({
        render: function () {
            return (
                    <div className="note-comment">
                        <CommentItemContent comment={this.props.comment}/>
                        <CommentItemReplyBar commentId={this.props.comment.id} noteId={this.props.noteId} onReplyButtonClick={this.props.onReplyButtonClick}/>
                    </div>
            );
        }
    });

    var CommentAdder = React.createClass({
        handleAddButtonClick: function () {
            event.preventDefault();

            var param = {};
            param.noteId = Number(this.props.noteId);
            param.content = this.refs.comment.value;
            param.username = username;
            param.commentId = Number(this.props.commentId);
            console.log(param);

            this.props.onAddButtonClick(param);
        },
        render: function () {
            return (
                    <div className="note-comment-adder">
                        <form role="form" action="#">
                            <div className="form-group col-md-10">
                                <input type="text" id="comment" name="comment" placeholder="评论" ref="comment"
                                       className="form-control"/>
                            </div>
                            <div className="form-group col-md-2">
                                <button className="btn  btn-primary " id="publish" onClick={this.handleAddButtonClick}>
                                    提交
                                </button>
                            </div>
                        </form>
                        <div className="clear"></div>
                    </div>
            );
        }
    });

    var Commenter = React.createClass({
        getInitialState: function () {
            var comments = [];

            return {comments: comments};
        },
        onAddButtonClick: function (param) {
            $.post(context + "/noteComment/add/" + param.noteId, {content: param.content}, function (result) {
                //判断是否成功
                if (result.code != 200) {
                    if (result.code == 5)//如果是因为未登录
                    {
                        $('#login-modal').modal('show');
                        return;
                    }
                    alert(result.message);//打印出其他错误
                    return;
                }

                //清空发送
                $('#comment').val('');

                var newComments = this.state.comments.map(function (comment) {
                    return $.extend(true, {}, comment);
                });
                newComments.push(result.object);
                this.setState({comments: newComments});
            }.bind(this));
        },
        onReplyButtonClick:function (param) {
            console.log(param);

            $.post(context + "/noteComment/reply/" + param.noteId, {
                targetCommentId: param.commentId,
                content: param.content
            }, function (result) {
                //判断是否成功
                if (result.code != 200) {
                    if (result.code == 5)//如果是因为未登录
                    {
                        $('#login-modal').modal('show');
                        return;
                    }
                    alert(result.message);//打印出其他错误
                    return;
                }

                $('#note-comment-reply-adder' + param.commentId).hide();
                $('#note-comment-reply-display' + param.commentId).show();
                this.setState({comments:result.object});
            }.bind(this));
        },
        componentDidMount: function () {
            $.get(context + "/noteComment/getAll/${note.id}", function (result) {
                if (this.isMounted()) {
                    console.log(result.object);
                    this.setState({comments: result.object});
                }
            }.bind(this))
        },
        render: function () {
            return (

                    <div className="">
                        <CommentAdder noteId="${note.id}" onAddButtonClick={this.onAddButtonClick}/>
                        {
                            this.state.comments.map(function (comment) {
                                return (
                                        <CommentItem comment={comment} noteId="${note.id}" onReplyButtonClick={this.onReplyButtonClick}/>
                                );
                            }.bind(this))
                        }
                    </div>
            );
        }
    });
    React.render(<Commenter />, document.getElementById("comment-container"));
</script>
</body>
</html>
