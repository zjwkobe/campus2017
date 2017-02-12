<%--
  Created by IntelliJ IDEA.
  User: RONIN
  Date: 2017/2/3
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <style type="text/css">
        .box{
            height: 550px;
            width: 500px;
        }
        .button-container {
            display: inline-block;
            height: 80px;
            vertical-align: middle;
        }
        .submit, .clear {
            height: 25px;
            width: 80px;
            margin: 5px auto;
            line-height: 25px;
            font-size: 14px;
            color: #fff;
            font-weight: bolder;
            text-align: center;
        }
        .submit {
            background-color: #4CACF0;
        }
        .clear {
            background-color: #99E83B;
        }
        td{
            text-align:center;
        }
    </style>
</head>
<body>
    <div class="box">
        <h3>${message}</h3>
        <%--<form>--%>
            <%--文本上传：--%>
            <%--<input type="radio"  name="mod" value="upload" onclick="load()"/>--%>
            <%--<br />--%>
            <%--文本输入：--%>
            <%--<input type="radio" name="mod" checked="checked" value="typein" onclick="typein()"/>--%>
        <%--</form>--%>
        <form accept-charset="UTF-8">
            <textarea name="input" id="input-text" class="input-text" cols="50" rows="10" placeholder="请输入文本">${content}</textarea>
            <div class="button-container">
                <div class="submit" onclick="show()">统计</div>
                <div class="clear" onclick="clean()">清除</div>
            </div>
        </form>
        <table border="1" id="total-count" style="width: 334px" >
            <h4>各统计内容个数如下</h4>
            <tr>
                <td>统计项</td>
                <td>个数</td>
            </tr>
            <tr>
                <td>英文字母</td>
                <td>${english}</td>
            </tr>
            <tr>
                <td>数字</td>
                <td>${number}</td>
            </tr>
            <tr>
                <td>中文汉字</td>
                <td>${chinese}</td>
            </tr>
            <tr>
                <td>中英文标点符号</td>
                <td>${punctuation}</td>
            </tr>
        </table>
        <table border="1" id="high-rate" style="width: 334px">
            <h4>文本中频率最高的三个字符</h4>
            <tr>
                <td>名称</td>
                <td>个数</td>
            </tr>
            <tr>
                <td>${first_name}</td>
                <td>${first_times}</td>
            </tr>
            <tr>
                <td>${second_name}</td>
                <td>${second_times}</td>
            </tr>
            <tr>
                <td>${third_name}</td>
                <td>${third_times}</td>
            </tr>
        </table>
    </div>
</body>
<script>
    var text = document.getElementById("input-text");
    function show(){
        var form = document.forms[0];
        form.action = "${pageContext.request.contextPath}/type/show";
        form.method = "post";
        form.submit();
    }
    function clean(){
        var form = document.forms[1];
        form.action = "${pageContext.request.contextPath}/type";
        form.method = "post";
        form.submit();
        text.value="";
    }
    function load(){
        var form = document.forms[0];
        form.action = "${pageContext.request.contextPath}/";
        form.method = "post";
        form.submit();
    }
    function typein(){
        var form = document.forms[0];
        form.action = "${pageContext.request.contextPath}/type";
        form.method = "post";
        form.submit();
    }
</script>
</html>
