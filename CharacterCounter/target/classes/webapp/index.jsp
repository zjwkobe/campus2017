<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>CharacterCounter</title>
    <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
    <style type="text/css">
        body {
            font-family : Microsoft YaHei;
        }

        #content {
            padding: 10px 0px 30px 50px;
        }

        #selection {
            width: 1200px;
            height: 40px;
        }

        #section {
            width: 1200px;
            height: 100px;
        }

        .file {
            position: relative;
            display: inline-block;
            background: #D0EEFF;
            border: 1px solid #99D3F5;
            border-radius: 4px;
            padding: 4px 12px;
            overflow: hidden;
            color: #1E88C7;
            text-decoration: none;
            text-indent: 0;
            line-height: 20px;
            top: 10px;
        }

        .file input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
        }

        .file:hover {
            background: #AADFFD;
            border-color: #78C3F3;
            color: #004974;
            text-decoration: none;
        }

        .button1 {
            position: absolute;
            display: inline-block;
            background: #D0EEFF;
            border: 1px solid #99D3F5;
            border-radius: 4px;
            color: #1E88C7;
            text-decoration: none;
            text-indent: 0;
            line-height: 20px;
            width: 90px;
            height: 30px;
            font-size: 15px;
            line-height: 30px;
            left: 400px;
            top: 120px;
        }

        .button2 {
            position: absolute;
            display: inline-block;
            background: #D0EEFF;
            border: 1px solid #99D3F5;
            border-radius: 4px;
            color: #1E88C7;
            text-decoration: none;
            text-indent: 0;
            line-height: 20px;
            width: 90px;
            height: 30px;
            font-size: 15px;
            line-height: 30px;
            left: 400px;
            top: 160px;
        }

        table th,tr {
            height: 40px;
            line-height: 20px;
            text-align: center;
        }
    </style>
</head>
<body>
<h3>请选择一段文字</h3>
<div id="content">
    <div id="selection">
        <input type="radio" name="type" id="file" class="radio" value="1" placeholder="" checked="checked">文件上传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="radio" name="type" id="text" class="radio" value="2" placeholder="">文本输入
    </div>
    <div id="section">
        <div id="Info1">
            <form method="post" action="/upload_file.do" enctype="multipart/form-data">
                <a href="javascript:;" class="file">选择文件<input type="file" name="file" id="upload_file" onchange="upload(this);"></a>&nbsp;&nbsp;<font id="filename"></font>
                <button type="submit" class="btn btn-primary">Count</button>
                <%--<input type="submit" name="count" id="count_file" value="统计" class="button1" onclick="count()">--%>
            </form>

        </div>
        <div id="Info2">

            <form method="post" action="/input_text.do">
                <textarea name="text" id="area" style="width: 300px;font-size: 16px " rows="5" placeholder="请输入内容"></textarea>
                <button type="submit" class="btn btn-primary" >GO</button>
                <input type="button" name="reset" id="reset" value="清空" class="button2" onclick="reset()">
            </form>
            <%-- --%>
        </div>
    </div>
    <div id="result">
        <h4>各统计内容的个数如下</h4>
        <table border="1" cellspacing="0" cellpadding="0" width="400">
            <thead>
            <tr>
                <th>统计项</th>
                <th>个数</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>英文字母</td>
                <td>${countCharacterModel.englishLetterCount}</td>
            </tr>
            <tr>
                <td>数字</td>
                <td>${countCharacterModel.numberCount}</td>
            </tr>
            <tr>
                <td>中文汉字</td>
                <td>${countCharacterModel.chineseCharacterCount}</td>
            </tr>
            <tr>
                <td>标点符号</td>
                <td>${countCharacterModel.punctuationCount}</td>
            </tr>
            </tbody>
        </table>
        <br>
        <h4>文字中出现频率最高的3个字是：</h4>
        <table border="1" cellspacing="0" cellpadding="0" width="400">
            <thead>
            <tr>
                <th>名称</th>
                <th>个数</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${countCharacterModel.topList.get(0).getKey()}</td>
                <td>${countCharacterModel.topList.get(0).getValue()}</td>
            </tr>
            <tr>
                <td>${countCharacterModel.topList.get(1).getKey()}</td>
                <td>${countCharacterModel.topList.get(1).getValue()}</td>
            </tr>
            <tr>
                <td>${countCharacterModel.topList.get(2).getKey()}</td>
                <td>${countCharacterModel.topList.get(2).getValue()}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
<script type="text/javascript">
    function upload(src) {
        var filename = document.getElementById('filename');
        filename.innerHTML = src.value;
    }

    function count() {
        alert('123');
    }

    function reset() {
        $('#area').val('');
    }

    $(function(){
        showCont();
        $("input[name=type]").click(function(){
            showCont();
        });

        function showCont() {
            switch($("input[name=type]:checked").attr("id")){
                case "file":
                    //alert("file");
                    $("#Info2").hide();
                    $("#Info1").show();
                    break;
                case "text":
                    //alert("text");
                    $("#Info1").hide();
                    $("#Info2").show();
                    break;
                default:
                    break;
            }
        }
    });
</script>
</html>