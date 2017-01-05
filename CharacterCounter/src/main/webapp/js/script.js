/**
 * Created by ryan.hao on 16-12-15.
 */

alert("注意：此程序前端只在chrome上进行过测试。。");

function showInputControl() {
    console.log("显示输入控件");
    $("#inputcontrol").show();
    $("#fileupload").hide();
}

function showFileUpload() {
    console.log("显示上传控件");
    $("#inputcontrol").hide();
    $("#fileupload").show();
}

function ajaxfileupload() {
    $.ajaxFileUpload
    (
        {
            url: '/countbyfile',
            secureuri: false,
            fileElementId: 'file',
            dataType: 'json',
            success: function (data, status)
            {
                console.log("文件上传，服务器响应状态：" + status)
                console.log("返回数据：" + data);
                if (status == 'success') {
                    displayresult(data.result);
                } else {
                    alert("服务器出现错误");
                }
            },
            error: function (data, status, e)//服务器响应失败处理函数
            {
                console.log("文件上传，服务器响应状态：" + status)
                console.log("返回数据：" + data);
                console.log("错误信息：" + e);
            }
        }
    )
}

function displayresult(result) {
    $("#en").html(result.en);
    $("#num").html(result.num);
    $("#ch").html(result.ch);
    $("#sim").html(result.sim);
    $("#top1").html(result.tops[0]);
    $("#top2").html(result.tops[1]);
    $("#top3").html(result.tops[2]);
    $("#topValue1").html(result.topsValue[0]);
    $("#topValue2").html(result.topsValue[1]);
    $("#topValue3").html(result.topsValue[2]);
}

function getfilename() {
    var filepath = $("#file").val();
    var filename = filepath.substring(filepath.lastIndexOf("\\") + 1, filepath.length);
    return filename
}

function onfilechanged() {
    var filename = getfilename();
    console.log("文件发生改变：" + filename);
    $("#filename").val(filename);
}

function clearinput() {
    $("#input_text").val("");
}

function ajaxformsubmit() {
    $("#input_form").ajaxSubmit({
        success: function(data) {
            displayresult(data.result);
        }
    });
}