<%--
  Created by IntelliJ IDEA.
  User: NULL
  Date: 2017/1/20
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CharacterCounter</title>
    <script src="<%=request.getContextPath()%> /resources/js/jquery.js"></script>
</head>

<body>

    <h3>请选择一段文字</h3>
    <input id="upload" type="button" name="upload" value="文件上传">
    <input id="input" type="button" name="input" value="文本输入">

    <br><br><br>

    <div id="upload_holder">
        <form method="post" action="/counter/upload.do" enctype="multipart/form-data">
            <input type="file" name="file"/>
            <input type="submit"/>
        </form>
    </div>

    <div id="input_holder" style="display: none;">
        <form method="post" action="/counter/input.do">
            <textarea name="text"></textarea>
            <br><br>
            <input type="submit"/>
        </form>
    </div>

    <script>

        $('#upload').click(function(){
            $('#upload_holder').css("display","block");
            $('#input_holder').css("display","none");
        });

        $('#input').click(function(){
            $('#input_holder').css("display","block");
            $('#upload_holder').css("display","none");
        });

    </script>

</body>

</html>
