$(function(){

    $("#cf").click(function () {
        chooseOne("#cf");
    });
    $("#ct").click(function () {
        chooseOne("#ct");
    });
    function chooseOne(id) {
        if($(id).val()==1){
            $("#textdiv").css('display','none');
            $("#filediv").css('display','block');
        }else{
            $("#textdiv").css('display','block');
            $("#filediv").css('display','none');
        }
    }


    function processData(result){
        $("#enCount").text(result.enCount);
        $("#chCount").text(result.chCount);
        $("#numCount").text(result.numCount);
        $("#chOtherCount").text(result.chOtherCount);
        for(var i = 0;i<3;i++){
            if(i >= result.top3.length){
                return ;
            }
            var top = result.top3[i];
            var keyid = "#top"+(i+1)+"Key";
            var valueid = "#top"+(i+1)+"Value";
            $(keyid).text("\""+top.key+"\"");
            $(valueid).text(top.value);
        }
    }
    $("#btn_submit_text").click(function () {
        var text = $("#text").val();
        if(text.trim() == "请在此输入文本内容" || text.trim() == ""){
            alert("请输入内容在进行统计！");
            return ;
        }
        var data ={"text":text};
        //发送异步请求
        $.ajax({
            method:"post", 
            url:"/count/text",
            dataType:"json",
            data:data,
            success:function (result) {
                var re = eval(result);
                if(re.status==0){
                   processData(re);
                }else{
                    alert(re.msg);
                }
            },
            error:function (e) {
                alert("请检查你的网络环境");
            }
        });


    });

    $("#btn_submit_file").click(function () {
        $.ajax({
            url: '/count/file',
            type: 'POST',
            cache: false,
            data: new FormData($('#fileform')[0]),
            processData: false,
            contentType: false
        }).done(function(result) {
            var re = eval(result);
            if(re.status==0){
                processData(re);
            }else{
                alert(re.msg);
            }
        }).fail(function(res) {
            alert("请检查你的网络环境");
        });
    });

});

