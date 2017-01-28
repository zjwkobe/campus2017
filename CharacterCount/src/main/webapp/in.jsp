<%--
  Created by IntelliJ IDEA.
  User: qiaoy.yang
  Date: 2016/12/29
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="css/main.css" rel="stylesheet">
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
</head>
<body>
<div class="main">
    <div class="choose">
        <h1>请选择一段文字</h1>
        <br/>
        <input type="radio" name="method" value="0" id="m1"> 文件上传
        <input type="radio" name="method" value="1" id="m2"> 文本输入
    </div>
    <br/>
    <div class="fileUploadDiv" id="f1" style="display:none;">
        <form name="submitfile" action="/count/file" method="post">

            <input type="file" value="上传文件" id="file">
            <input type="button" value="统计" id="btn_submit">
        </form>
    </div>

    <div class="textUploadDiv" id="t1">
        <form name="submittext" action="/count/text" method="post">
            <table class="a1">
                <tr>
                    <td rowspan="2">
                        <textarea class="textarea" name="text">请在此输入文本内容</textarea>
                    </td>
                    <td>
                        <input type="submit" value="统计" class="btn_submit" id="btn_submit_text">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="reset" value="清空内容" class="btn_reset">
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <div class="result">
        <span class="title_2">各统计内容的统计如下：</span><br/>
        <table class="tab1" border="1">
            <tr>
                <th>统计项</th>
                <th>个数</th>
            </tr>
            <tr>
                <td>英文字母</td>
                <td></td>
            </tr>
            <tr>
                <td>数字</td>
                <td></td>
            </tr>
            <tr>
                <td>中文汉字</td>
                <td></td>
            </tr>
            <tr>
                <td>中文标点符号</td>
                <td></td>
            </tr>
        </table>
        <span class="title_2">文中出现频率最高的三个词是：</span><br/>

        <table class="tab2" border="1">

            <tr>
                <th>名称</th>
                <th>个数</th>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>

        </table>
    </div>


</div>
</body>
</html>
