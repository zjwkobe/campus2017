<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>CharacterCounter</title>
    <script type="text/javascript" src="/js/jquery-3.1.1.min.js"></script>
    <style>
        .divborder {
            border: 1px solid #ccc;
            width: 650px;
            height: 650px;
            padding-left: 50px;
            margin: auto;
        }

        .tb {
            padding: 2px;
            border-collapse: collapse;
            border: 1px solid #ccc;
        }

        .thead {
            font-weight: bold;/*表头粗体*/
        }

        td{
            border: 1px solid #ccc; /*边框*/
            width:200px;             /*单元格宽度*/
            height:35px;             /*单元格高度*/
            text-align:center;       /*单元格文字居中对齐*/
        }

        .btn {
            height: 40px;
            width: 100px;
        }
    </style>
    <script type="application/javascript">
        window.onload = function () {
            var radiolist = $(".radioItem");
            radiolist[0].onclick = function () {
                $("#form_file").show();
                $("#form_text").hide();
                $("#btn_clear").hide();
            }

            radiolist[1].onclick = function () {
                $("#form_file").hide();
                $("#form_text").show();
                $("#btn_clear").show();
            }

            $("#btn_clear").click(function () {
                $("textarea").val("");
            });

            $("#btn_calc").click(function () {
                var url;
                var formData = new FormData();
                if (radiolist[0].checked) {
                    var ele=$("#form_file");
                    if(ele.val()=="") return;
                    url = "http://localhost:8080/qunar/file";
                    formData.append("file", ele[0].files[0]);
                } else {
                    var text=$("#form_text").val();
                    if($.trim(text)=='') return;
                    url = "http://localhost:8080/qunar/text";
                    formData.append("text", text);
                }
                $.ajax({
                    url: url,
                    type: 'POST',
                    data: formData,
                    processData: false,// 告诉jQuery不要去处理发送的数据
                    contentType: false,// 告诉jQuery不要去设置Content-Type请求头
                    success: function (data) {
                        if (data.success) {
                            var ceils = $(".data");
                            ceils[0].innerHTML=data.english;
                            ceils[1].innerHTML=data.number;
                            ceils[2].innerHTML=data.chinese;
                            ceils[3].innerHTML=data.punctuation;
                            var k=4;
                            for(var i=0;i<3;i++){
                                for(var j=0;j<2;j++){
                                    ceils[k++].innerHTML=data.top[i][j];
                                }
                            }
                        } else alert("文件太大");
                    }
                });
            });
        }
    </script>
</head>
<body>
<div class="divborder">
    <h3>请选择一段文字</h3>
    <input class="radioItem" type="radio" value="file" name="type" checked>文件上传
    <input class="radioItem" type="radio" value="text" name="type">文本输入<br><br>


    <div>
        <div style="float: left;height: 100px;width: 500px;display: inline">
            <input id="form_file" type="file" value="上传文件" name="file">
            <textarea style="height: 100%;width: 100%;display: none" id="form_text" name="text"></textarea>
        </div>
        <div style="height: 100px;width: 50px;display: inline">
            <input class="btn" type="button" id="btn_calc" value="统计"><br><br>
            <input class="btn" type="button" id="btn_clear" value="清空内容" style="display: none">
        </div>
    </div>
    <div style="clear: both">
        <h3>各统计内容的个数如下：</h3>
        <table class="tb">
            <tr class="thead">
                <td>统计项</td>
                <td>个数</td>
            </tr>
            <tr>
                <td>英文字母</td>
                <td class="data"></td>
            </tr>
            <tr>
                <td>数字</td>
                <td class="data"></td>
            </tr>
            <tr>
                <td>中文汉字</td>
                <td class="data"></td>
            </tr>
            <tr>
                <td>标点符号</td>
                <td class="data"></td>
            </tr>

        </table>
    </div>
    <div style="clear: both">
        <h3>文字中出现频率最高的三个字是：</h3>
        <table class="tb">
            <tr class="thead">
                <td>名称</td>
                <td>个数</td>
            </tr>
            <tr>
                <td class="data"></td>
                <td class="data"></td>
            </tr>
            <tr>
                <td class="data"></td>
                <td class="data"></td>
            </tr>
            <tr>
                <td class="data"></td>
                <td class="data"></td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
