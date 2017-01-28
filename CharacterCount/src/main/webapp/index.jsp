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


    <div class="textUploadDiv" id="t1">
        <form name="submittext" action="/count/text" method="post">
            <table class="a1">
                <tr>
                    <td rowspan="2">
                        <textarea class="textarea" name="text" id="text">请在此输入文本内容</textarea>
                    </td>
                    <td>
                        <input type="button" value="统计" class="btn_submit" id="btn_submit_text">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="reset" value="清空内容" class="btn_reset">
                    </td>
                </tr>
            </table>
        </form>

        <form id="fileform" action="/count/file" method="post" enctype="multipart/form-data">
            <table class="a1">
                <tr>
                    <td rowspan="2">
                       <input type="file" name="file">
                    </td>
                    <td>
                        <input type="button" value="统计" class="btn_submit" id="btn_submit_file">
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
    <div>
        出现最多的词：
        <p id="co"><p>


    </div>

</div>
</body>
</html>
