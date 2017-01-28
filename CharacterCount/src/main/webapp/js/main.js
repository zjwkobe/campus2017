$(function(){
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
                if(re.status){
                    var top3 = result.top3;
                    var s ="";
                    for(var i=0;i<top3.length;i++){
                        s = s + "<p>" + top3[i].key +"------" + top3[i].value +"</p>";
                    }
                    $("#co").children().remove();
                    $("#co").append(s);
                }else{
                    alert("服务器错误！请稍后再试");
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
            if(re.status){
                var top3 = result.top3;
                var s ="";
                for(var i=0;i<top3.length;i++){
                    s = s + "<p>" + top3[i].key +"------" + top3[i].value +"</p>";
                }
                $("#co").children().remove();
                $("#co").append(s);
            }else{
                alert("服务器错误！请稍后再试");
            }
        }).fail(function(res) {
            alert("请检查你的网络环境");
        });


    });

    
});