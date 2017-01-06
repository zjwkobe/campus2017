<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/1/6
  Time: 8:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <title>CharacterCounter 首页</title>
</head>
<body>

<form  action="/countchar">
    <p>请输入一段文字</p><br>
    <input type="text" id="flag" name="identify" value="co" style="display: none">
    <table width="300">
        <tr>
            <td rowspan="2">
                <textarea rows="5" cols="25" name="text">${text}</textarea>
            </td>
            <td>
                <input type="submit" name="count" value="统计" onclick="function1() ">
            </td>
        </tr>
        <tr>
            <td>
                <input type="reset" name="clear" value="清空内容" onclick="function2()">
            </td>
        </tr>

    </table>
    <br>

    <p>各统计内容如下</p><br>
    <table  border="1" width="200" cellpadding="0" cellspacing="0">
        <tr>
            <th>统计项</th>
            <th>个数</th>
        </tr>
        <tr>
            <td>英文字母</td>
            <td>${count.characters}</td>
        </tr>
        <tr>
            <td>数字</td>
            <td>${count.numbers}</td>
        </tr>
        <tr>
            <td>汉字</td>
            <td>${count.chineses}</td>
        </tr>
        <tr>
            <td>中英文标点</td>
            <td>${count.others}</td>
        </tr>
    </table>

    <p>文字中出现频率最高的三个字是：</p><br>
    <table  border="1" width="200" cellpadding="0" cellspacing="0">
        <tr>
            <th>名称</th>
            <th>个数</th>
        </tr>
        <tr>
            <td height="20">${strings[0]}</td>
            <td>${integers[0]}</td>
        </tr>
        <tr>
            <td  height="20">${strings[1]}</td>
            <td>${integers[1]}</td>
        </tr>
        <tr>
            <td  height="20">${strings[2]}</td>
            <td>${integers[2]}</td>
        </tr>

    </table>

</form>

</body>
</html>
<script>
    function1=function () {
        document.getElementById("flag").value="co";
        document.forms[0].submit();
    }
    function2=function () {
        document.getElementById("flag").value="cl";
        document.forms[0].submit();
    }
</script>
