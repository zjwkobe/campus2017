<%--
  Created by IntelliJ IDEA.
  User: shenhaojie
  Date: 2017/1/10
  Time: 下午4:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="JSTL" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String serverPath = "http://" + request.getServerName() + ":" + request.getServerPort()
            + request.getContextPath();
%>
<html>
<head>
    <title>CharacterCounter</title>
    <script src="./js/jquery.js"></script>
    <script>
      $(function(){
          $(":radio").click(function(){
              if($(this).val()=="file"){
                  $("#input").hide();
                  $("#file").show();
              }else {
                  $("#input").show();
                  $("#file").hide();
              }
          });

          $("#clear").click(function () {
              $("textarea").val("");
          })
      });
    </script>
</head>

<body>



<p>请输入一段</p>



    <input type="radio" name="uploadway" value="file" checked="checked">文件上传
    &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
    <input type="radio" name="uploadway" value="input">文本输入

    <div id="content">
        <div id="file">
            <form action="<%=serverPath%>/file" method="post" enctype="multipart/form-data">
                <input type="file" name="fileUpload" />
                <input type="submit" value="统计" />
            </form>
        </div>

        <div id="input" hidden>
            <form action="<%=serverPath%>/input" method="post">
                <textarea name="content"></textarea>
                <input type="submit" value="统计" />
                <input id="clear" type="button" value="清空内容" />
            </form>
        </div>
    </div>




</form>


</br>



<p>各统计的内容个数如下<p>
<table border="1">
    <tr>
        <th >统计数</th>
        <th>个数</td>
    </tr>
    <tr>
        <td>英文字母</td>
        <td>${result.englishCount}</td>
    </tr>
    <tr>
        <td>数字</td>
        <td>${result.figureCount}</td>
    </tr>
    <tr>
        <td>中文汉字</td>
        <td>${result.chineseCount}</td>
    </tr>
    <tr>
        <td>中英文标点符号</td>
        <td>${result.punctuationCount}</td>
    </tr>
</table>


<p>文字中出现频率最高的三个字是：<p>
<table border="1">
    <tr>
        <th>统名称</th>
        <th>个数</td>
    </tr>
    <JSTL:forEach items="${result.list}" var="entry">
    <tr>
        <td>${entry.key}</td>
        <td>${entry.value}</td>
    </tr>
    </JSTL:forEach>

</table>


</body>
</html>
