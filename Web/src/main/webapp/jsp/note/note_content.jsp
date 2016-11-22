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
            padding: 10px 5px;
        }

        .note-comment {
            /*border:1px solid #cccccc;
            padding: 10px;*/
        }

        .note-comment-item {
            border: 1px solid #cccccc;
            padding: 10px;
            margin-bottom: 10px;
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
        <hr/>
        <div class="note-content-body">${note.content}</div>
    </div>
    <hr/>
    <div id="comment-container"></div>
</div>
<script type="text/babel">
    var CommentItem = React.createClass({
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
                    </div>
            );
        }
    });

    var CommentAdder = React.createClass({
        handleAddButtonClick:function () {
            event.preventDefault();

            var comment ={};
            comment.noteId =this.props.noteId;
            comment.content =this.refs.comment.value;

            this.props.onAddButtonClick(comment);
        },
        render: function () {
            return (
                    <div className="note-comment-adder">
                        <form role="form" action="#">
                            <div className="form-group">
                                <input type="text" name="comment" placeholder="评论" ref="comment" className="form-control"/>
                            </div>
                            <button className="btn  btn-primary " id="publish" onClick={this.handleAddButtonClick} >提交</button>
                        </form>
                    </div>
            );
        }
    });

    var Commenter = React.createClass({
        getInitialState: function () {
            var comments = [];

            return {comments: comments};
        },
        onAddButtonClick:function (comment) {
            $.post(context+"/noteComment/add/"+this.props.noteId,comment,function (result) {
                console.log(result);
            });

            var newComments =this.state.comments.map(function (comment) {
                return $.extend(true,{},comment);
            });
            newComments.push(comment);
            this.setState({comments:newComments});
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

                    <div className="note-comment">
                        <CommentAdder noteId="${note.id}"/>
                        {
                            this.state.comments.map(function (comment) {
                                return <CommentItem comment={comment} onAddButtonClick={this.onAddButtonClick}/>
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
