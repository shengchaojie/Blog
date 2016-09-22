var Shower =React.createClass({
    componentDidMount:function () {
        $("#danmu").danmu({
            left: 0,    //区域的起始位置x坐标
            top: 0 ,  //区域的起始位置y坐标
            height: "100%", //区域的高度
            width: "100%", //区域的宽度
            zindex :100, //div的css样式zindex
            speed:10000, //弹幕速度，飞过区域的毫秒数
            sumtime:900 , //弹幕运行总时间
            danmuss:{}, //danmuss对象，运行时的弹幕内容
            default_font_color:"#FFFFFF", //弹幕默认字体颜色
            font_size_small:16, //小号弹幕的字体大小,注意此属性值只能是整数
            font_size_big:24, //大号弹幕的字体大小
            opacity:"0.9", //弹幕默认透明度
            top_botton_danmu_time:6000 //顶端底端弹幕持续时间
        });

        /*$("#danmu").danmu("addDanmu",[
            { text:"闲着也是闲着,那就做个弹幕留言板吧" ,color:"white",size:1,position:0,time:15}
            ,{ text:"今天上班无聊" ,color:"yellow" ,size:1,position:1,time:5}
            ,{ text:"发现了一个弹幕插件" , color:"red" ,size:1,position:2,time:10}
        ]);*/

        this.getDanmu();

        $('#danmu').danmu('danmuStart');

    },
    getDanmu:function () {
        $.get(context+"/barrage/getAll",function(data,status){
            var danmu_from_sql=eval(data);
            for (var i=0;i<danmu_from_sql.length;i++){
                //var danmu_ls=eval('('+danmu_from_sql[i]+')');
                $('#danmu').danmu("addDanmu",danmu_from_sql[i]);
            }
        });
    },
    render:function () {
        return (
            <div id="danmuarea">
                <div id="danmu"></div>
            </div>
        );
    }
});

var Sender = React.createClass({
    onSendClick:function () {
        this.props.handleSendClick();
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
                        <button className="btn btn-default" id="buttonSend" onClick={this.onSendClick}>发送弹幕</button>
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
    handleSendClick:function () {
        var text =$('#text').val();

        if(text==null||text.length==0)
        {
            alert('至少输入点内容吧！');
            return;
        }

        var time =$('#danmu').data("nowTime")+1;
        var color =$('#color').val();
        var text_size =$('#text_size').val();
        var position =$('#position').val();
        var name =$('#name').val();
        var date =new Date();

        var danmuDisplay={ text:text ,color:color,size:text_size,position:position,time:time,isnew:1};
        var danmuSave={ text:text ,color:color,size:text_size,position:position,time:time,name:name,createTime:date};

        this.saveDanmu(danmuSave);

        $('#danmu').danmu("addDanmu",danmuDisplay);

        //按钮禁用
        $('#buttonSend').attr({"disabled":"disabled"});
        this.buttonControl(5);

    },
    buttonControl:function (i) {
        var self =this;
        if(i==0)
        {
            $('#buttonSend').removeAttr("disabled");
            $('#buttonSend').text("发送弹幕");
        }else {
            $('#buttonSend').text(i);
            i--;
            setTimeout(function () {
                self.buttonControl(i);
            },1000);
        }
    },
    saveDanmu:function (danmu) {
        $.post(context+"/barrage/save",danmu,function(data){
            var object =eval(data);
            console.log(data.message);

            //保存之后 回调刷新commenter
            this.refreshCommenter();
        }.bind(this));
    },
    getInitialState: function () {
        return {
            comments: []
        };
    },
    componentDidMount: function () {
        this.refreshCommenter();
    },
    refreshCommenter:function () {
        $.get(context + "/barrage/comment/getAll", function (result) {
            this.setState({comments: result});
        }.bind(this));
    },
    render: function () {
        return (
            <div>
                <Sender handleSendClick={this.handleSendClick}/>
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

var BarrageApp =React.createClass({
    render:function () {
        return (
            <div>
                <Shower />
                <Commenter />
            </div>
        );
    }
});
ReactDOM.render(<BarrageApp />, document.getElementById('container'));
