$(function () {
    var radio = $("input:radio");
    var oldRadio = $(radio[0]).attr("value");

    console.log(oldRadio);
    for (var index = 0; index < radio.length; index++) {
        $(radio[index]).change(function () {
            oldRadio = $(this).attr("value");
            console.log($(this).attr("checked"));
            console.log(oldRadio);
            for (index = 0; index < radio.length; index++) {
                var value = $(radio[index]).attr("value")
                if (value == oldRadio) {
                    $("#" + value).css("display", "none");
                } else {
                    $("#" + value).css("display", "block");
                }
            }
        });
    }


    $("#btn_file").click(function () {
        var data = $(this).prev()[0].files[0];
        if (data){
            hAjax(data,"uploadContent");
        }else{
            alert("请选择文件");
        }
    });


    $("#btn_content").click(function () {
        var data = $(this).prev().val();
        if (data.trim()){
            hAjax(data,"content");
        }else{
            alert("请输入");
        }
    });

    function hAjax(data,name) {
        var formData = new FormData();
        //formData.append("uploadContent",$("#uploadFile")[0].files[0])
        //formData.append(name,$("#uploadFile")[0].files[0]);
        formData.append(name,data);
        //var formData = new FormData($("#uploadFile")[0]);
        console.log(formData);
        console.log(data);
        $.ajax({
            url: "/count",
            type: 'POST',
            cache: false,
            data: formData,
            processData: false,
            contentType: false,
            dataType: "json",
            success: function (data) {
                var data = data["success"]
                var html = template("ClassifyListTpl",{list:data["classify"]});
                $("#ClassifyList").html(html);
                html = template("TopListTpl",{list:data["rank"]});
                $("#TopList").html(html);
            }
        });
    }


});
