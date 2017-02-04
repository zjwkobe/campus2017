<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Character Counter</title>
    <style>
        .title {
            font-weight: bold;
            font-size: 20px;
        }

        .btn1 {
            background: #00CCFF
        }

        .btn2 {
            background: #FF9900
        }

        .pos1 {
            position: absolute;
            left: 320px;
            top: 80px;
        }

        .pos2 {
            position: absolute;
            left: 320px;
            top: 120px;
        }

        table {
            border-collapse: collapse;
            border: 1px solid black;
        }

        td {
            text-align: center;
            height: 30px;
            width: 150px;
            border: 1px solid black;
        }

        progress {
            border-radius: 0px;
            border-left: 1px #ccc solid;
            border-right: 1px #ccc solid;
            border-top: 1px #aaa solid;
            width: 300px;
            height: 5px;
            background-color: #eee;
        }
    </style>
    <script>
        function changeType() {
            var div1 = document.getElementById("byUpload");
            var div2 = document.getElementById("byInput");
            if (document.getElementsByName("textSrc").item(0).checked) {
                div1.style.display = "inline";
                div2.style.display = "none";
            } else {
                div1.style.display = "none";
                div2.style.display = "inline";
            }
        }

        function ajax_submit1() {
            var formData = new FormData();
            formData.append("file", document.getElementById("file").files[0]);
            var request = new XMLHttpRequest();
            request.open("POST", "submit1.form", true);
            request.onload = function () {
                if (request.status === 200) {
                    console.log('Info：上传成功！');
                } else {
                    console.log('Error：上传失败！');
                }
            };
            request.onreadystatechange = function () {
                if (request.readyState == 4 && request.status == 200) {
                    var result = request.responseText.split("###");
                    analyzeResult(result);
                }
            };
            request.send(formData);
            request.upload.onprogress = function (event) {
                if (event.lengthComputable) {
                    var complete = (event.loaded / event.total * 100 | 0);
                    var progress = document.getElementById('uploadprogress');
                    progress.value = progress.innerHTML = complete;
                }
            };
        }

        function ajax_submit2() {
            var request = new XMLHttpRequest();
            var data = "inputText=" + document.getElementById("inputText").value;
            request.open("POST", "submit2.form", true);
            request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            request.send(data);
            request.onreadystatechange = function () {
                if (request.readyState == 4 && request.status == 200) {
                    var result = request.responseText.split("###");
                    analyzeResult(result);
                }
            }
        }

        function analyzeResult(result) {
            var typeCount = eval(result[0]);
            var charCount = eval(result[1]);
            for (var i = 0; i < typeCount.length; i++) {
                var item = typeCount[i];
                if (item.type == 'english') {
                    document.getElementById("english").innerHTML = item.count;
                }
                if (item.type == 'number') {
                    document.getElementById("number").innerHTML = item.count;
                }
                if (item.type == 'chinese') {
                    document.getElementById("chinese").innerHTML = item.count;
                }
                if (item.type == 'punctuation') {
                    document.getElementById("punctuation").innerHTML = item.count;
                }
            }
            for (var i = 0; i < charCount.length; i++) {
                var item = charCount[i];
                if (i == 0) {
                    document.getElementById("top1_ch").innerHTML = item.ch;
                    document.getElementById("top1_count").innerHTML = item.count;
                }
                if (i == 1) {
                    document.getElementById("top2_ch").innerHTML = item.ch;
                    document.getElementById("top2_count").innerHTML = item.count;
                }
                if (i == 2) {
                    document.getElementById("top3_ch").innerHTML = item.ch;
                    document.getElementById("top3_count").innerHTML = item.count;
                }
            }
        }
    </script>
</head>
<body>
<div>
    <div class="title">请选择一段文字</div>
    <br>
    <div>
        <input type="radio" name="textSrc" value="" checked="checked" onclick="changeType()">文件上传</input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="radio" name="textSrc" value="" onclick="changeType()">文本输入</input>
    </div>
    <div id="byUpload" style="display: inline;">
        <form>
            <input type="file" name="file" id="file" value="上传文件" accept=".txt, .html, .json, .c, .cpp, .java"/>
            <input class="btn1" type="button" id="count1" value="统计" onclick="ajax_submit1()">
        </form>
        <progress id="uploadprogress" min="0" max="100" value="0">0</progress>
    </div>
    <div id="byInput" style="display: none;">
        <form>
            <textarea id="inputText" value="" style="width: 300px; height:100px"></textarea>
            <input class="btn1 pos1" type="button" id="count2" value="统计" onclick="ajax_submit2()">
            <input class="btn2 pos2" type="reset" id="clearText" value="清空内容">
        </form>
    </div>
    <br>
    <div>
        各统计内容的个数如下：<br>
        <table>
            <thead>
            <tr>
                <td><b>统计项</b></td>
                <td><b>个数</b></td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>英文字母</td>
                <td id="english"></td>
            </tr>
            <tr>
                <td>数字</td>
                <td id="number"></td>
            </tr>
            <tr>
                <td>中文汉字</td>
                <td id="chinese"></td>
            </tr>
            <tr>
                <td>中英文标点符号</td>
                <td id="punctuation"></td>
            </tr>
            </tbody>
        </table>
        <br>
        文字中出现频率最高的三个字是：<br>
        <table>
            <thead>
            <tr>
                <td><b>名称</b></td>
                <td><b>个数</b></td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td id="top1_ch"></td>
                <td id="top1_count"></td>
            </tr>
            <tr>
                <td id="top2_ch"></td>
                <td id="top2_count"></td>
            </tr>
            <tr>
                <td id="top3_ch"></td>
                <td id="top3_count"></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
