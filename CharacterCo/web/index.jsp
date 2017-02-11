<%--
  Created by IntelliJ IDEA.
  User: GL
  Date: 2017/1/6
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>统计</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
  </head>
  <body>
  <script type="text/javascript">
      $(document).ready( function() {
          $("#content").css("background-color","red");
          $("#statis").click(function()
              {
                  var content = $("#content").val().replace(/[\r\n]/g,"");
                  console.log(content);
            $.get("/mvc/hello", {"content": content}, function(data){
                var result = $.parseJSON(data);
                $("#zm").text(result.zmCount);
                $("#sz").text(result.szCount);
                $("#fh").text(result.fhCount);
                $("#uni").text(result.unicodeCount);
                var top1 = result.most[0].split('_');
                var top2 = result.most[1].split('_')
                var top3 = result.most[2].split('_')
                $("#top1_name").text(top1[0]);
                $("#top1_num").text(top1[1]);
                $("#top2_name").text(top2[0]);
                $("#top2_num").text(top2[1]);
                $("#top3_name").text(top3[0]);
                $("#top3_num").text(top3[1]);
            })
          });
      });

  </script>
  <script type="text/javascript">
      function doClear()
      {
          $("#content").val('');
      }
  </script>
  <div align="center" width="500">
    <h3>请输入一段文字</h3>
    <div width="500">
    <textarea rows="10" cols="65" id="content" width="500" required="required">
    </textarea>
    </div>
    <div width="300">
      <button type="button" id="statis">统计</button>
      <button type="button"　id="clear" onclick="doClear()">清空</button></div>
    <div>
      <h3>各统计内容的个数如下</h3>
      <table border="1 solid" cellpadding="0" cellspacing="0" width="500" height="100">
        <tr>
          <th>统计项</th>
          <th>个数</th>
        </tr>
        <tr>
          <td>英文字母</td>
          <td id="zm"></td>
        </tr>
        <tr>
          <td>数字</td>
          <td id="sz"></td>
        </tr>
        <tr>
          <td>中文汉字</td>
          <td id="uni"></td>
        </tr>
        <tr>
          <td>中英文标点符号</td>
          <td id="fh"></td>
        </tr>
      </table>
      <h3>文字中出现频率最高的三个字</h3>
      <table border="1 solid" cellpadding="0" cellspacing="0" width="500" height="100">
        <tr>
          <th>名称</th>
          <th>个数</th>
        </tr>
        <tr>
          <td id="top1_name"> </td>
          <td id="top1_num"> </td>
        </tr>
        <tr>
          <td id="top2_name"> </td>
          <td id="top2_num"></td>
        </tr>
        <tr>
          <td id="top3_name"> </td>
          <td id="top3_num"></td>
        </tr>
      </table>
    </div>
  </div>
  </body>
</html>
