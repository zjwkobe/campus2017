<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="css/main.css" rel="stylesheet">
    <script type="text/javascript" src="/js/jquery-3.1.1.min.js"></script>
</head>
<body>
<div class="main">
    <div class="title">
        <p>请选择一段文字</p>
    </div>
    <div class="body">
        <input type="radio" id="contentRadio" value="contentDiv" name="switch" checked="checked"><label>文件上传</label>
        <input type="radio" id="fileRadio" value="fileDiv" name="switch"><label>文本输入</label>
        <!--<input type="radio" id="radio" value="1" checked="checked"/><label for="re">文件上传</label>
        <input type="radio" id="radio" value="2"/><label for="ct">文本输入</label>-->

        <div class="box" style="display: none" id="contentDiv">
            <!--文本输入统计-->
            <form id="formContent" enctype="multipart/form-data">
                <textarea class="textarea" name="content" id="content" placeholder="请在此输入文本内容" value=""></textarea>
                <input type="button" value="统计" id="btn_content">
                <input type="reset" value="清空内容" class="btn_reset">
            </form>
        </div>
        <div class="box" id="fileDiv">
            <!--文件上传统计-->
            <form id="formFile" enctype="multipart/form-data">
                <input type="file" name="file" id="uploadFile">
                <input type="button" value="统计" class="btn_file" id="btn_file">

            </form>

        </div>
    </div>
    <div class="table">
        <p>各统计内容的个数如下：</p>
        <table class="result" id="ClassifyList">

        </table>

        <p>文中出现频率最高的三个字是：</p>
        <table class="result" name="TopList" id="TopList">

        </table>
    </div>

</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/template.js"></script>
<script type="text/html" id="ClassifyListTpl">
    <tr>
        <td class="one">统计项</td>
        <td>个数</td>
    </tr>
    {{each list}}
    <tr>
        <td class="one">{{$index}}</td>
        <td><span >{{$value}}</span></td>
    </tr>
    {{/each}}
</script>

<script type="text/html" id="TopListTpl">
    {{each list as object index}}
    <tr>
        {{each object as value index}}
        <td class="one">{{index}}</td>
        <td><span >{{value}}</span></td>
        {{/each}}
    </tr>
    {{/each}}
</script>
</html>