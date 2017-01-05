<html>
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<body>
<h3>请选择一段文字</h3>

<form name="input" action="html_form_action.php" method="get">
    <input type="radio" name="type" value="send">文本上传
    <input type="radio" name="type" value="input">文本输入
</form>
<div>
    <table>
        <form name="input" action="/getMessageInfo" method="get">
            <tr>
                <td rowspan="2">
                    <textarea id="str" name="str" rows="3" cols="50" placeholder="请在此输入文本内容">${map.str}</textarea>
                </td>
                <td>
                    <button type="submit"> 统  计 </button>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="button" onclick="cleantext()">清空内容</button>
                </td>
            </tr>
        </form>
    </table>
    <p>各统计内容的个数如下：</p>
    <table border="1">
        <div>
            <tr>
                <th height="20" width="150">统计项</th>
                <th height="20" width="150">个数</th>
            </tr>
            <tr>
                <td height="20" width="150">英文字母</td>
                <td height="20" width="150">${map.enCount}</td>
            </tr>
            <tr>
                <td height="20" width="150">数字</td>
                <td height="20" width="150">${map.numCount}</td>
            </tr>
            <tr>
                <td height="20" width="150">中文汉字</td>
                <td height="20" width="150">${map.chCount}</td>
            </tr>
            <tr>
                <td height="20" width="150">中英文标点符号</td>
                <td height="20" width="150">${map.charCount}</td>
            </tr>
        </div>
    </table>
    <p>文字中出现频率最高的三个字是：</p>
    <table border="1">
        <div>
            <tr>
                <th height="20" width="150">名称</th>
                <th height="20" width="150">个数</th>
            </tr>
            <tr>
                <td height="20" width="150">${map.chMaxes.get(0).chvalue}</td>
                <td height="20" width="150">${map.chMaxes.get(0).count}</td>
            </tr>
            <tr>
                <td height="20" width="150">${map.chMaxes.get(1).chvalue}</td>
                <td height="20" width="150">${map.chMaxes.get(1).count}</td>
            </tr>
            <tr>
                <td height="20" width="150">${map.chMaxes.get(2).chvalue}</td>
                <td height="20" width="150">${map.chMaxes.get(2).count}</td>
            </tr>
        </div>
    </table>
</div>

<script>
    function cleantext() {
        
        document.getElementById("str").value="";
    }

</script>
</body>
</html>
