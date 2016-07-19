/**
 * Created by shengcj on 2016/7/19.
 */

$("danmu").danmu({
    height: "100%",  //弹幕区高度
    width: "100%" ,   //弹幕区宽度
    //zindex :100,   //弹幕区域z-index属性
    speed:7000,      //滚动弹幕的默认速度，这是数值值得是弹幕滚过每672像素所需要的时间（毫秒）
    sumTime:65535,   //弹幕流的总时间
    danmuLoop:false,   //是否循环播放弹幕
    defaultFontColor:"#FFFFFF",   //弹幕的默认颜色
    fontSizeSmall:16,     //小弹幕的字号大小
    FontSizeBig:24,       //大弹幕的字号大小
    opacity:"0.9",          //默认弹幕透明度
    topBottonDanmuTime:6000,   // 顶部底部弹幕持续时间（毫秒）
    SubtitleProtection:false,     //是否字幕保护
    positionOptimize:false,         //是否位置优化，位置优化是指像AB站那样弹幕主要漂浮于区域上半部分

    maxCountInScreen: 40,   //屏幕上的最大的显示弹幕数目,弹幕数量过多时,优先加载最新的。
    maxCountPerSec: 100      //每分秒钟最多的弹幕数目,弹幕数量过多时,优先加载最新的。
});

$("#danmu").danmu("addDanmu",[
    { text:"这是滚动弹幕" ,color:"white",size:1,position:0,time:2}
    ,{ text:"这是顶部弹幕" ,color:"yellow" ,size:1,position:1,time:3}
    ,{ text:"这是底部弹幕" , color:"red" ,size:1,position:2,time:3}
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
    var aDanmu={ text:text ,color:color,size:text_size,position:position,time:time,isnew:1};
    $('#danmu').danmu("addDanmu",aDanmu);
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

function query()
{
    $.get(contextPath+"/barrage/getAll",function(data,status){
        var danmu_from_sql=eval(data);
        for (var i=0;i<danmu_from_sql.length;i++){
            var danmu_ls=eval('('+danmu_from_sql[i]+')');
            $('#danmu').danmu("addDanmu",danmu_ls);
        }
    });
}