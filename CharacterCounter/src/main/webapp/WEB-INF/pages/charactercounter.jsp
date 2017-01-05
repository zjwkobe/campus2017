<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Character Counter</title>
        <link rel="stylesheet" type="text/css" href="/css/style.css"/>
        <script type="text/javascript" src="/js/jquery-1.4.1.js"></script>
        <script type="text/javascript" src="/js/ajaxfileupload.js"></script>
        <script type="text/javascript" src="/js/script.js"></script>
        <script type="text/javascript" src="/js/jquery-form.js"></script>
    </head>
    <body>
        <div class="border">
            <div class="title">请选择一段文字</div>
            <div class="content-border content-text">
                <div class="radios">
                    <input id="radioupload" name="type" type="radio" checked="checked" onclick="showFileUpload()"/>
                    <label for="radioupload">上传文件</label>
                    <input id="radioinput" name="type" type="radio" onclick="showInputControl()"/>
                    <label for="radioinput">文本输入</label>
                </div>
                <div id="fileupload" class="buttons">
                    <input id="file" name="file" type="file" class="button" style="width: 80px; opacity: 0; position: absolute" onchange="onfilechanged()" />
                    <button style="background-color: #00CCFF">上传文件</button>
                    <input id="filename" type="text" style="width:250px; border: none; color: #999999" placeholder="文件名" readonly="readonly"/>
                    <button style="background-color: #00CCFF" onclick="ajaxfileupload()">统计</button>
                </div>
                <div id="inputcontrol" class="buttons" hidden="hidden">
                    <div style="float: left">
                        <form id="input_form" action="/countbyinput" method="post">
                            <textarea id="input_text" name="input_text" style="width: 307px; height: 55px; resize: none" placeholder="请在此输入文本内容"></textarea>
                        </form>
                    </div>
                    <div style="float: left; width: 100px; height: 60px">
                        <button style="background-color: #00CCFF; margin: 2px 10px 3px 10px" onclick="ajaxformsubmit()">统计</button>
                        <button style="background-color: #FF9900; margin: 3px 10px 2px 10px" onclick="clearinput()">清空内容</button>
                    </div>
                    <div style="clear: both"></div>
                </div>
                <div class="sub-title">各统计内容的个数如下：</div>
                <table border="1" bordercolor="#999999" style="border-collapse: collapse">
                    <tr>
                        <th>统计项</th>
                        <th>个数</th>
                    </tr>
                    <tr>
                        <td>英文字母</td>
                        <td id="en"></td>
                    </tr>
                    <tr>
                        <td>数字</td>
                        <td id="num"></td>
                    </tr>
                    <tr>
                        <td>中文汉字</td>
                        <td id="ch"></td>
                    </tr>
                    <tr>
                        <td>中英文标点符号</td>
                        <td id="sim"></td>
                    </tr>
                </table>
                <div class="sub-title">文字中出现频率最高的三个字是：</div>
                <table border="1" bordercolor="#999999" style="border-collapse: collapse">
                    <tr>
                        <th>名称</th>
                        <th>个数</th>
                    </tr>
                    <tr>
                        <td id="top1"></td>
                        <td id="topValue1"></td>
                    </tr>
                    <tr>
                        <td id="top2"></td>
                        <td id="topValue2"></td>
                    </tr>
                    <tr>
                        <td id="top3"></td>
                        <td id="topValue3"></td>
                    </tr>
                </table>
            </div>
        </div>
    </body>
</html>