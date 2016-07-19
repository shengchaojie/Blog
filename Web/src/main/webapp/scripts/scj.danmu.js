/**
 * Created by shengcj on 2016/7/19.
 */
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

$("#danmu").danmu("addDanmu",[
    { text:"闲着也是闲着,那就做个弹幕留言板吧" ,color:"white",size:1,position:0,time:15}
    ,{ text:"今天上班无聊" ,color:"yellow" ,size:1,position:1,time:5}
    ,{ text:"发现了一个弹幕插件" , color:"red" ,size:1,position:2,time:10}
]);

query();

$('#danmu').danmu('danmuStart');

function send_danmu()
{
    var text =$('#text').val();
    var time =$('#danmu').data("nowTime")+1;
    var color =$('#color').val();
    var text_size =$('#text_size').val();
    var position =$('#position').val();
    //var adanmu ={"text":"123","color":"green","size":"1","position":"0","time":2};
    var aDanmu_display={ text:text ,color:color,size:text_size,position:position,time:time,isnew:1};
    var aDanmu_insert={ text:text ,color:color,size:text_size,position:position,time:time};

    insert_db(aDanmu_insert);

    $('#danmu').danmu("addDanmu",aDanmu_display);
}

function add() {
    var time =$('#danmu').data("nowTime")+1;
    var newd =
    {"text": "new2", "color": "green", "size": "1", "position": "0", "time": time};
    $('#danmu').danmu("addDanmu", newd);
}

function test_insert()
{
    var time =$('#danmu').data("nowTime")+1;
    var newd =
    {"text": "new2", "color": "green", "size": "1", "position": "0", "time": time};
    $.post(contextPath+"/barrage/save",newd,function(data){
        var object =eval(data);
        alert(object.code);
    });
}

function insert_db(danmu)
{
    $.post(contextPath+"/barrage/save",danmu,function(data){
        var object =eval(data);
        console.log(data.message);
    });
}

function query()
{
    $.get(contextPath+"/barrage/getAll",function(data,status){
        var danmu_from_sql=eval(data);
        for (var i=0;i<danmu_from_sql.length;i++){
            //var danmu_ls=eval('('+danmu_from_sql[i]+')');
            $('#danmu').danmu("addDanmu",danmu_from_sql[i]);
        }
    });
}