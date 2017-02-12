<%@page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>统计字符</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/text.count.js"></script>
    <script>
        function show(){
            if(document.getElementById("textButton").checked){
                document.getElementById("textShow").style.display="block";
                document.getElementById("fileShow").style.display="none";
            }else if(document.getElementById("fileButton").checked){
                document.getElementById("fileShow").style.display="block";
                document.getElementById("textShow").style.display="none";
            };
        };

        function doUpload() {
            //var formData = new FormData($( "#upLoadForm" )[0]);
            //var formData = new FormData(document.getElementById("upLoadForm"));
            var formdata = new FormData( );
            formdata.append("fileField" , $("#file")[0].files[0]);
            $.ajax({
                url: 'countChar/file/upload',
                type: 'POST',
                data: formdata,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) {
                    finalData(data);
                },
                error: function (data) {
                    alert("上传失败");
                }
            });
        }


    </script>
</head>
<body>
<br>
<strong >请选择一段文字:</strong>
</br>
<div class="btn-group">
    <input name="radio" id="fileButton" type="radio" checked="checked" onclick="show()">文件上传
    <input name="radio" id="textButton" type="radio" onclick="show()">文本输入
</div>

<div id="textShow" style="display: none;">
    <form name="textForm" method="post">
        <textarea id="textValue" name="textValue" rows="3" maxlength="1000" placeholder="请在此输入文本内容，限制在1000字符以内"/></textarea>
        <div class="form-actions">
            <input id="save" type="button" value="提交" onclick="doTextCount()">
            <input type="reset" class="res" id="reset" >
        </div>
    </form>
</div>

<div id="fileShow" style="display: block;">
    <form name="upLoadForm" enctype="multipart/form-data">
        选择一个文件:
        <input type="file" name="file" id="file"/>
        <input type="button" value="上传" onclick="doUpload()" />
    </form>
</div>
<div id="result"></div>
</body>
</html>
