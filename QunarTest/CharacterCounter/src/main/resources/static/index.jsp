<html>
  <head>
    <title>字符统计</title>
  </head>

  <script type="text/javascript">
      var xmlhttp;
      function locaAjax() {
          if (window.XMLHttpRequest){
              xmlhttp=new XMLHttpRequest();
          }
          else{
              xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
          }

          var text = document.getElementById("textarea").value;
          var _json = jQuery.param({ "text": text });
          $.ajax({
              type:"POST",
              url:"ServletInput",
              data:_json,
              success:resultOk
          });
      }
      function createXmlhttp() {

      }
    function loadXml() {
        if (window.XMLHttpRequest){
            xmlhttp=new XMLHttpRequest();
        }

        else{
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        var text = document.getElementById("textarea").value;
        xmlhttp.onreadystatechange = resultOk;

        xmlhttp.open("POST","ServletInput?text"+text,true);
//        xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
        xmlhttp.send();
    }
    function resultOk() {
        var text = xmlhttp.responseText;
//        document.getElementById("textarea").value="";
//        document.getElementById("textarea").value=text;
        document.getElementById("lableEnglish").innerHTML=text;
    }


    function selectValue() {
        var radio = document.getElementsByName("select");
        for(i =0 ;i< radio.length;i++){
            if(radio[i].checked && radio[i].id == "text" ){
                document.getElementById("selectfile").style.display="none";
                document.getElementById("selecttext").style.display="inline";
            }
            else if(radio[i].checked && radio[i].id == "file"){
                document.getElementById("selectfile").style.display="inline";
                document.getElementById("selecttext").style.display="none";
            }

        }
    }
    function cleanStr() {
        document.getElementById("textarea").value="";
    }

  </script>
  <body>
  <span id="title"></span>
  <br>
  <label><input type="radio" name="select" onclick="selectValue()" checked="checked" id="file"/>文件上传</label>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <label><input type="radio" name="select" onclick="selectValue()" id="text"/>文本输入</label>
  <br>
  <br>
  <div>

    <form action="wordCount/" method="post" id="selectfile">
      <table id="filetable">
        <tr>
          <td align="right"><input type="file" name="file" value="上传文件"></td>
          <td align="left"><input type="submit" value="统计" /></td>
        </tr>
      </table>
    </form>

    <form method="post" id="selecttext" style="display: none" action="wordCount/">
      <textarea cols="30" rows="7" id="textarea" name="textarray"></textarea>
      <input type="button" value="统计" onclick="loadXml()" id="bt_submit"/>
      <input type="button" value="清空内容" onclick="cleanStr()" id="clearText"/>
    </form>

  </div>

  <br><br>
  个统计内容如下
  <form>
    <table id="resulttable" border="1" width="300" >
      <tr>
        <td><label>统计项</label></td>
        <td><label>个数</label></td>
      </tr>
      <tr>
        <td><label>英文字母</label></td>
        <td><label id="lableEnglish"></label>${1+1}</td>
      </tr>
      <tr>
        <td><label>数字</label></td>
        <td><label id="lableNumber"></label></td>
      </tr>
      <tr>
        <td><label>中文汉字</label></td>
        <td><label id="lableChinese"></label></td>
      </tr>
      <tr>
        <td><label>英文标点符号</label></td>
        <td><label id="lableMark"></label></td>
      </tr>
    </table>
    <br>
    文本中出现频率最高的三个字是：
    <table id="top3" border="1" width="300">
      <tr>
        <td><label>名称</label></td>
        <td>个数</td>
      </tr>
      <tr>
        <td><label id="one">.  </label></td>
        <td><label id="onenum"></label></td>
      </tr>
      <tr>
        <td><label id="two">.  </label></td>
        <td><label id="twonum"></label></td>
      </tr>
      <tr>
        <td><label id="three">.  </label></td>
        <td><label id="threenum"></label></td>
      </tr>

    </table>
  </form>

<script>
   function submit(){

   }
</script>
  </body>
</html>
