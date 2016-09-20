/**
 * Created by shengcj on 2016/9/19.
 */
var NoteTag =React.createClass({
    onClickTag:function(tagId)
    {
        this.props.handleTagClick(tagId);
    },
    render:function(){
        return (
            <div className="noteTag">
                <button className= {this.props.tag.selected?"btn btn-default btn-success":"btn btn-default"}  data-tag-id={this.props.tag.tagId} onClick={this.onClickTag.bind(this,this.props.tag.tagId)}>{this.props.tag.tagName}</button>
            </div>
        )
    }
});

var NoteHeader =React.createClass({
    onAllClick:function () {
      this.props.handleAllSelect();
    },
    render:function(){
        return (
            <div className="row noteRow">
                <div className="noteTag">
                    <button className={this.props.isAll?"btn btn-default btn-success":"btn btn-default"} onClick={this.onAllClick.bind(this)}>全部</button>
                </div>
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
        </div>
        );
    }
});

var NoteBody =React.createClass({
    onTitleClick:function () {
      this.props.handleTitleClick();
    },
    render:function(){
        return (
            <div className="row noteRow">
            <table className="table table-condensed table-hover" >
                <tbody>
            <tr>
            <th>标题</th>
            <th>作者</th>
            <th>时间</th>
            </tr>
            {
                (function(){
                    var self =this;
                    return this.props.notes.map(function(note){
                        return (
                            <tr>
                                <td><div onClick={self.onTitleClick}>{note.title}</div></td>
                                <td>{note.author}</td>
                                <td>{note.createTime}</td>
                            </tr>
                        );
                    })
                }.bind(this))()

            }
                </tbody>
        </table>
        </div>
        );
    }
});

var App= React.createClass({
    getInitialState: function () {
        var tags = [];
        var notes = [];
        //console.log(tags);
        //console.log(notes);
        return {tags: tags, notes: notes,isAll:true};
    },

    handleTagClick: function (tagId) {

        var newTags = this.state.tags.map(function (tag) {
            //console.log(tag);
            if (tag.tagId == tagId) {
                tag.selected = !tag.selected;
            }
            return tag;
        });

        $.post(context+"/note/getByTagIds",{tags:this.getSelectedTagIds()},function(result){
            this.setState({
                'tags': newTags,
                'notes': result,
                isAll:false
            });
        }.bind(this));
    },
    handleAllSelect:function () {
        var newTags = this.state.tags.map(function (tag) {
            if(!this.state.isAll) {
                tag.selected = false;
            }
            return tag;
        }.bind(this));

        if(this.state.isAll)
        {
            this.setState({
                'tags': newTags,
                'isAll':!this.state.isAll,
                'notes': []
            });
        }else {
            $.get(context + "/note/getAll", function (result) {
                this.setState({
                    'tags': newTags,
                    'isAll': !this.state.isAll,
                    'notes': result
                });
            }.bind(this));
        }
    },
    handleTitleClick:function () {
        ReactDOM.render(<NoteContent content="12345" />,document.getElementById("container"));
    },
    componentDidMount:function(){
        $.get(context+"/note/noteTag/getAll",function(result){
            if(this.isMounted())
            {
                result.forEach(t=>t.selected =false);
                //console.log(result);
                this.setState({tags:result});
            }
        }.bind(this));

        $.get(context+"/note/getAll",function(result){
            this.setState({
                'notes': result
            });
        }.bind(this));
       //return  $.parseJSON('[{"tagId":1,"tagName":"标签1","selected":true},{"tagId":2,"tagName":"标签2","selected":true}]');
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
    render: function () {
        return (
            <div className="container-fluid">
                <NoteHeader tags={this.state.tags} isAll={this.state.isAll} handleTagClick={this.handleTagClick} handleAllSelect={this.handleAllSelect}/>
                <NoteBody notes={this.state.notes} handleTitleClick={this.handleTitleClick}/>
            </div>
        );
    }
});
var HelloWorld =React.createClass({
    render:function () {
        return (
            <div>Hello,World</div>
        );
    }
});

var NoteContent =React.createClass({
    render:function () {
        return (
        <div>13</div>
        );
    }
});

ReactDOM.render(<App />,document.getElementById("container"));