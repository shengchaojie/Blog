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
            {
            (function(){
                //console.log(this.props.tag.selected);
                if(this.props.tag.selected ==true)
                {
                    return (
                        <button className="btn btn-default btn-success" data-tag-id={this.props.tag.tagId} onClick={this.onClickTag.bind(this,this.props.tag.tagId)}>{this.props.tag.tagName}</button>
                );
                }else
                {
                    return (
                        <button className="btn btn-default" data-tag-id={this.props.tag.tagId} onClick={this.onClickTag.bind(this,this.props.tag.tagId)}>{this.props.tag.tagName}</button>
                );
                }
            }.bind(this))()
    }
        </div>
        )
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
        </div>
        );
    }
});

var NoteBody =React.createClass({
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
                this.props.notes.map(function(note){
                return (
                    <tr>
                    <td>{note.title}</td>
                    <td>{note.author}</td>
                    <td>{note.createTime}</td>
                    </tr>
                );
            })
    }
                </tbody>
        </table>
        </div>
        );
    }
});

var App =React.createClass({
    getInitialState:function(){
        var tags =$.parseJSON('[{"tagId":1,"tagName":"标签1","selected":true},{"tagId":2,"tagName":"标签2","selected":true}]');
        var notes =$.parseJSON('[{"title":"这是一个标题","author":"盛超杰","createTime":"2016年9月19日","tagId":2},{"title":"这是一个标题","author":"盛超杰","createTime":"2016年9月19日","tagId":1}]');

        console.log(tags);
        console.log(notes);
        return {tags:tags,notes:notes};
    },

    handleTagClick:function(tagId){
        var selectedTagIds =[];

        var newTags =this.state.tags.map(function(tag){
            console.log(tag);
            if(tag.tagId==tagId)
            {
                tag.selected =!tag.selected ;
            }
            if(tag.selected==true)
            {
                selectedTagIds.push(tag.tagId);
            }
            return tag;
        });
        var newNotes=[];
        this.state.notes.map(function(note){
            if(selectedTagIds.indexOf(note.tagId)>-1)
            {
                newNotes.push(note);
            }
        });
        console.log(newNotes);
        this.setState({
            'tags':newTags,
            'notes':this.state.notes.concat(newNotes)
        });
    },
    render:function(){
        return (
            <div className="container-fluid">
            <NoteHeader tags={this.state.tags} handleTagClick={this.handleTagClick}/>
        <NoteBody notes={this.state.notes}/>
        </div>
        );
    }
});

var HelloWorld =React.createClass({
    render:function(){
        return (
        <div>Hello,World</div>
        );
    }
});

ReactDOM.render(<App />,document.getElementById("container"));