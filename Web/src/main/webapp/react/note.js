/**
 * Created by shengcj on 2016/9/19.
 */
var NoteTag = React.createClass({
    onClickTag: function (tagId) {
        this.props.handleTagClick(tagId);
    },
    render: function () {
        return (
            <div className="noteTag">
                <button className={this.props.tag.selected ? "btn btn-default btn-success" : "btn btn-default"}
                        data-tag-id={this.props.tag.tagId}
                        onClick={this.onClickTag.bind(this, this.props.tag.tagId)}>{this.props.tag.tagName}</button>
            </div>
        )
    }
});

var NoteHeader = React.createClass({
    onAllClick: function () {
        this.props.handleAllSelect();
    },
    render: function () {
        return (
            <div className="row noteRow">
                <div className="noteTag">
                    <button className={this.props.isAll ? "btn btn-default btn-success" : "btn btn-default"}
                            onClick={this.onAllClick.bind(this)}>全部
                    </button>
                </div>
                {
                    (function () {
                        var self = this;
                        return self.props.tags.map(function (tag) {
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

var NoteBody = React.createClass({
    onTitleClick: function (noteId) {
        this.props.handleTitleClick(noteId);
    },
    render: function () {
        return (
            <div className="note-body">
                <div className="row noteRow">
                    <table className="table table-condensed table-hover">
                        <tbody>
                        <tr>
                            <th width="45%">标题</th>
                            <th width="20%">作者</th>
                            <th width="35%">时间</th>
                        </tr>
                        {
                            (function () {
                                var self = this;
                                return this.props.notes.map(function (note) {
                                    return (
                                        <tr onClick={self.onTitleClick.bind(this, note.id)}>
                                            <td>
                                                <div data-note-id={note.id}>{note.title}</div>
                                            </td>
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
            </div>
        );
    }
});

var App = React.createClass({
    getInitialState: function () {
        var tags = [];
        var notes = [];
        //console.log(tags);
        //console.log(notes);
        return {tags: tags, notes: notes, isAll: true, pageSize: 0,currentPage :0};
    },

    handleTagClick: function (tagId) {

        var newTags = this.state.tags.map(function (tag) {
            //console.log(tag);
            if (tag.tagId == tagId) {
                tag.selected = !tag.selected;
            }
            return tag;
        });
        this.setState({'tags': newTags,isAll:false});
        this.getNotesByPage(0,false);
    },
    handleAllSelect: function () {
        if (this.state.isAll) {
            this.setState({
                'isAll': false,
                'notes': []
            });
        } else {
            var newTags = this.state.tags.map(function (tag) {
                if (!this.state.isAll) {
                    tag.selected = false;
                }
                return tag;
            }.bind(this));

            this.setState({
                'tags': newTags,
                'isAll': true,
            });
            //setState是异步的,所以到下面方法，还是false
            //感觉分成2个方法 能让界面加载更友好
            this.getNotesByPage(0,true);

        }
    },
    handleTitleClick: function (noteId) {
        //ReactDOM.render(<NoteContent  noteId={noteId}/>,document.getElementById("container"));
        window.location = context + "/note/content/" + noteId;
    },
    componentDidMount: function () {
        $.get(context + "/note/noteTag/getAll", function (result) {
            if (this.isMounted()) {
                result.forEach(t=>t.selected = false);
                //console.log(result);
                this.setState({tags: result});
            }
        }.bind(this));

        this.getNotesByPage(0);
        //return  $.parseJSON('[{"tagId":1,"tagName":"标签1","selected":true},{"tagId":2,"tagName":"标签2","selected":true}]');
    },
    getSelectedTagIds: function () {
        var selectedTagIds = [];
        this.state.tags.forEach(function (tag) {
            if (tag.selected == true) {
                selectedTagIds.push(tag.tagId);
            }
        });
        return selectedTagIds.join(',');
    },
    handlePaginationClick: function (index) {
        this.getNotesByPage(index);
    },
    getNotesByPage: function (page,isAll) {
        var note_url =context + "/note/page/getAll?";
        var note_tag_url=context + "/note/page/byTag/get?";

        if ( typeof (isAll)=='undefined'?this.state.isAll:isAll ) {
            $.get(note_url+"page=" + page + "&size=10", function (data) {
                console.log(data);
                this.setState({
                    notes: this.convertToNote(data),
                    currentPage:page,
                    pageSize: data.totalPages
                });
            }.bind(this));
        }else {
            $.get(note_tag_url+"tags="+this.getSelectedTagIds()+"&page="+page+"&size=10", function (data) {
                this.setState({
                    notes: this.convertToNote(data),
                    currentPage:page,
                    pageSize: data.totalPages
                });
            }.bind(this));
        }


    },
    convertToNote:function (data) {
        var notes = [];
        data.content.forEach(function (note) {
          notes.push({
              id: note.id,
              title: note.title,
              author: note.user.nickname,
              createTime: new Date(note.createTime).toLocaleString()
          });
        }.bind(this));
        return notes;
    },
    render: function () {
        return (
            <div className="container-fluid">
                <NoteHeader tags={this.state.tags} isAll={this.state.isAll} handleTagClick={this.handleTagClick}
                            handleAllSelect={this.handleAllSelect}/>
                <NoteBody notes={this.state.notes} handleTitleClick={this.handleTitleClick}/>
                <Pagination pages={this.state.pageSize} currentPage={this.state.currentPage} handlePaginationClick={this.handlePaginationClick}/>
            </div>
        );
    }
});

var Pagination = React.createClass({
    onIndexClick: function (index, event) {
        event.preventDefault();
        if(index<0 ||index>this.props.pages-1)
        {
            console.log(index);
            return;
        }
        this.props.handlePaginationClick(index);

    },
    render: function () {
        return (
            <div className="note-pagination">
                <ul className="pagination">
                    <li className={this.props.currentPage==0?"disabled":""}><a  onClick={this.onIndexClick.bind(this,this.props.currentPage-1 , event)}>&laquo;</a></li>
                    {
                        (function () {
                            var arr = [];
                            console.log(this.props.currentPage);
                            console.log(this.props.pages);
                            for (var i = 0; i < this.props.pages; i++) {
                                arr.push(<li className={this.props.currentPage==i?"active":""}><a onClick={this.onIndexClick.bind(this, i, event)}>{i+1}</a></li>);
                            }
                            return arr;
                        }.bind(this))()
                    }
                    <li className={this.props.currentPage==this.props.pages-1?"disabled":""}><a onClick={this.onIndexClick.bind(this,this.props.currentPage+1 , event)}>&raquo;</a></li>
                </ul>
            </div>
        );
    }
});

var NoteContent = React.createClass({
    render: function () {
        return (
            <div id="noteContent"></div>
        );
    },
    componentDidMount: function () {
        console.log(this.props.noteId);
        $.get(context + "/note/content/get/" + this.props.noteId + "?" + Math.random(), function (data) {
            $('#noteContent').html(data.object);
        });
    }
});

React.render(<App />, document.getElementById("container"));