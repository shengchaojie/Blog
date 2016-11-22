<%--
  Created by IntelliJ IDEA.
  User: shengcj
  Date: 2016/9/21
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>新笔记</title>
    <link href="${pageContext.request.contextPath}/react/css/note.css" rel="stylesheet">
</head>
<body>
<div id="container"></div>

<script type="text/babel">
    var Editor = React.createClass({
        // 编辑器样式
        style: {
            width: '100%',
            height: '400px'},
        getInitialState:function () {
            var tags = [];
            return {tags: tags};
        },
        handleSubmit:function (event) {
            var title =this.refs.title.value;
            var content =this.editor.$txt.html();
            var selectTags =this.getSelectedTagIds();
            console.log(title);
            console.log(content);
            console.log(selectTags);

            $.post(context+"/note/add",{title:title,content:content,tagId:selectTags},function(data){
                window.location=context+data;
            });

            event.preventDefault();
        },
        getSelectedTagIds:function () {
            var selectedTagIds = [];
            this.state.tags.forEach(function (tag) {
                if (tag.selected == true) {
                    selectedTagIds.push(tag.tagId);
                }
            });
            return selectedTagIds.join(',');
        },
        handleTagClick: function (tagId) {

            var newTags = this.state.tags.map(function (tag) {
                //console.log(tag);
                if (tag.tagId == tagId) {
                    tag.selected = !tag.selected;
                }
                return tag;
            });
            this.setState({tags:newTags});
        },
        handleNoteTagAdd:function (value) {
            //console.log(value);
            $.post(context+"/note/noteTag/add",{name:value},function (result) {
                //console.log(result);
                $('#myModal').modal('hide');
               /* console.log(this.state.tags);
                var newTags = $.extend(true,{},this.state.tags);//这边生成的是jquery对象并不是数组
                console.log(newTags);*/

               //深拷贝
                var newTags =this.state.tags.map(function (tag) {
                   return $.extend(true,{},tag)
                });
                newTags.push({tagId:result.object,tagName:value,selected:false });
                this.setState({tags:newTags});
            }.bind(this))
        },
        componentDidMount: function () {
            var id = this.props.id;
            this.editor = new window.wangEditor(id);
            this.editor.config.uploadImgFileName = 'myFileName';
            var uploadUrl =context+"/note/upload";
            this.editor.config.uploadImgUrl =uploadUrl;
            this.editor.create();

            // 初始化内容
            this.editor.$txt.html(this.props.content);

            $.get(context+"/note/noteTag/get",function(result){
                if(this.isMounted())
                {
                    result.forEach(t=>t.selected =false);
                    //console.log(result);
                    this.setState({tags:result});
                }
            }.bind(this));
        },
        // 获取内容
        getContent: function () {
            var content = this.editor.$txt.html();
            console.log(content);
        },
        render: function() {
            return (
                    <div>
                        <form role="form" action="#">
                            <NoteTagAdder handleNoteTagAdd={this.handleNoteTagAdd}/>
                            <div className="form-group">
                               <input type="text" name="title" placeholder="标题" ref="title" className="form-control"/>
                            </div>
                            <div className="form-group">

                                <div id={this.props.id} style={this.style} contentEditable="true"></div>
                             </div>
                             <div className="form-group">
                                <NoteHeader tags={this.state.tags} handleTagClick={this.handleTagClick}/>
                             </div>
                             <button className="btn  btn-primary " id="publish" onClick={this.handleSubmit} >发表</button>
                        </form>
                        <%--<button onClick={this.getContent}>get content</button>--%>
                    </div>
            );
        }
    });

    var NoteTagAdder =React.createClass({
        onClick:function () {
            this.props.handleNoteTagAdd(this.refs.tag.value);
        },
        render:function () {
            return (
                    <div className="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div className="modal-dialog">
                            <div className="modal-content">
                                <div className="modal-header">
                                    <button type="button" className="close" data-dismiss="modal"
                                            aria-hidden="true">×
                                    </button>
                                    <h4 className="modal-title" id="myModalLabel">
                                        操作
                                    </h4>
                                </div>
                                <div className="modal-body">
                                    <div className="form-group">
                                        <input type="text" name="title" placeholder="新增标签" ref="tag" className="form-control"/>
                                    </div>
                                </div>
                                <div className="modal-footer">
                                    <button type="button" className="btn btn-default"
                                            data-dismiss="modal">关闭
                                    </button>
                                    <button type="button" className="btn btn-primary" onClick={this.onClick}>
                                        提交
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
            );
        }
    });

    var NoteHeader =React.createClass({
        render:function(){
            return (
                    <div className="row noteRow">
                        {
                            (function(){
                                var self =this;
                                return self.props.tags.map(function(tag){
                                    return (
                                            <NoteTag tag={tag} handleTagClick={self.props.handleTagClick}/>
                                    );
                                })
                            }.bind(this))()
                        }
                        <div className="noteTag">
                                <span className="glyphicon glyphicon-plus header-add" data-toggle="modal" data-target="#myModal" ></span>
                        </div>
                    </div>
            );
        }
    });

    var NoteTag =React.createClass({
        onClickTag:function(tagId)
        {
            console.log(1);
            this.props.handleTagClick(tagId);
        },
        render:function(){
            return (
                    <div className="noteTag">
                        <button  type="button" className= {this.props.tag.selected?"btn btn-default btn-success":"btn btn-default"}  data-tag-id={this.props.tag.tagId} onClick={this.onClickTag.bind(this,this.props.tag.tagId)}>{this.props.tag.tagName}</button>
                    </div>
            )
        }
    });

    ReactDOM.render(<Editor id="editor1"/>,document.getElementById("container"));
</script>
</body>
</html>
