<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>统计Demo</title>
    <style type="text/css">
        #body{
            margin:15px;
            padding:25px 60px;
        }
        #body .select{
            margin-left:-8px;
            font-weight: 600;
        }
        #fileArea, #textArea{
            display: none;
        }
        #body .select .input .button1{
            position: absolute;
            margin-left: 10px;
        }
        #body .select .input .button2{
            position: absolute;
            top: 44px;
            margin-left: 10px;
        }
        .file {
            text-decoration: none;
            position: relative;
            width: 75px;
            height:25px;
            color: #ffffff;
            font-size: medium;
            line-height: 25px;
            text-align: center;
            cursor: pointer;
            box-sizing:content-box;
            border: 1px solid #7ae6ff;
            display: inline-block;
            background-color: #00cdff;
        }
        .file input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
        }
        input[type="submit"], .button1{
            width: 75px;
            height:25px;
            margin-left: 80px;
            color: #ffffff;
            font-size: medium;
            line-height: 25px;
            text-align: center;
            cursor: pointer;
            box-sizing:content-box;
            border: 1px solid #7ae6ff;
            display: inline-block;
            background-color: #00cdff;
        }
        .button2{
            width: 75px;
            height:25px;
            margin-left: 80px;
            color: #ffffff;
            font-size: medium;
            line-height: 25px;
            text-align: center;
            cursor: pointer;
            box-sizing:content-box;
            border: 1px solid #ffd595;
            display: inline-block;
            background-color: #ff9900;
        }
        td,tr{
            height: 18px;
            width: 120px;
            padding: 2px;
        }
    </style>
</head>
<body>
<script type="text/javascript">
    function test(obj) {
        if (obj.id == "file") {
            document.getElementById("fileArea").style.display = "block";
            document.getElementById("textArea").style.display = "none";
        }
        else {
            document.getElementById("fileArea").style.display = "none";
            document.getElementById("textArea").style.display = "block";
        }
    }
</script>
<p> 请选择一段文字</p>
<div id="body">
    <div class="select">
        <label>
            <input type="radio" name="method" id="file" onclick="test(this)"/>文件上传
        </label>
        <label>
            <input type="radio" name="method" id="text" onclick="test(this)"/>文本输入
        </label>
    </div>
    <div id="fileArea">
        <form action="/CharacterCounter/file" method="post" enctype="multipart/form-data">
            <a href="javascript:;" class="file">选择文件
                <input type="file" name="fileUpload">
            </a>
            <input type="submit" value="统计"/>
        </form>
    </div>
    <div id="textArea">
        <form action="/CharacterCounter/string" method="post">
            <table>
                <tr>
                    <td>
                    <textarea name="stringUpload" style="resize:none;" rows="4" cols="50"
                              placeholder="请在此输入文本"></textarea>
                    </td>
                    <td>
                        <table>
                            <tr>
                                <td><input type="submit" value="统计"
                                           style="background-color: #75fff5; text-align: center"
                                           width="20" height="5" class="button1"></td>
                            </tr>
                            <tr>
                                <td><input type="reset" value="清空内容"
                                           style="background-color: #ff8d2a; text-align: center"
                                           width="20" height="5" class="button2"></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="resultDiv">
        <p>各统计内容的个数如下：</p>
        <table border="1">
            <tr>
                <td><b>统计项</b></td>
                <td><b>个数</b></td>
            </tr>
            <tr>
                <td>英文字母</td>
                <td id="letter">${letter}</td>
            </tr>
            <tr>
                <td>数字</td>
                <td id="digit">${digit}</td>
            </tr>
            <tr>
                <td>中文汉字</td>
                <td id="chinese">${chinese}</td>
            </tr>
            <tr>
                <td>中英文标点符号</td>
                <td id="punctuation">${punctuation}</td>
            </tr>
        </table>
    </div>
    <div id="topCountDiv">
        <p>文字中出现频率最高的三个字是：</p>
        <table border="1">
            <tr>
                <td><b>名称</b></td>
                <td><b>个数</b></td>
            </tr>
            <tr>
                <td id="topChinese1">${topChinese1}</td>
                <td id="topNumber1">${topNumber1}</td>
            </tr>
            <tr>
                <td id="topChinese2">${topChinese2}</td>
                <td id="topNumber2">${topNumber2}</td>
            </tr>
            <tr>
                <td id="topChinese3">${topChinese3}</td>
                <td id="topNumber3">${topNumber3}</td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
