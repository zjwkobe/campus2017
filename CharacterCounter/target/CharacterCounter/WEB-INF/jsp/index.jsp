<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
    <head>
        <title>字符统计工具</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/index_style.css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript">

            function radio() {
                var radios = document.getElementsByName("uploadType");

                if (radios[0].checked&&radios[0].value==1) {
                    var target=   document.getElementById("tab1");
                    target.style.display="block";
                    target=   document.getElementById("tab2");
                    target.style.display="none";
                }else {
                    var target=   document.getElementById("tab1");
                    target.style.display="none";
                    target=   document.getElementById("tab2");
                    target.style.display="block";
                }
            }

            function reset() {
                var text=document.getElementById("text_input");
                text.clear();
            }


            function change(e){
                var src=e.target || window.event.srcElement;
                var filename=src.value;
                filename= filename.substring( filename.lastIndexOf('\\')+1 );

                var label=document.getElementById("file_label");
                label.innerText=filename;
            }

            function uploadFormFile(){
                var label=document.getElementById("file_label");
                var filename= label.innerText;
                var suffix= filename.substring( filename.lastIndexOf('.')+1);
                if(suffix!="txt"){
                    alert("请上传txt文件！");
                    label.innerText="";
                    return;
                }
                var formobj =  document.getElementById("form1");
                var formdata = new FormData(formobj);
                $.ajax({
                    url: "<%=request.getContextPath()%>/uploadFile",
                    data: formdata,
                    dataType: 'json',
                    processData: false,
                    contentType: false,
                    type: 'POST',
                    success: function(data){
                        $("#td1").html(data.letter);
                        $("#td2").html(data.num);
                        $("#td3").html(data.chinese);
                        $("#td4").html(data.mark);

                        for(var i =0;i<3;i++){
                            $("#td"+(5+2*i)).html("&nbsp;");
                            $("#td"+(6+2*i)).html("&nbsp;");
                        }

                        for(var i =0;i<parseInt(data.topNum);i++){
                            $("#td"+(5+2*i)).html(data.chars[i]);
                            $("#td"+(6+2*i)).html(data.charsNum[i]);
                        }
                    },
                    error:function () {
                        alert("error");
                    }
                });
            }
            function uploadFormText(){
                var txt = document.getElementById("text_input").value;
                if(txt==""||txt==null){
                    alert("文本为空！");
                    return;
                }
                var formobj =  document.getElementById("form2");
                var formdata = new FormData(formobj);
                $.ajax({
                    url: "<%=request.getContextPath()%>/uploadText",
                    data: formdata,
                    dataType: 'json',
                    processData: false,
                    contentType: false,
                    type: 'POST',
                    success: function(data){
                        $("#td1").html(data.letter);
                        $("#td2").html(data.num);
                        $("#td3").html(data.chinese);
                        $("#td4").html(data.mark);

                        for(var i =0;i<3;i++){
                            $("#td"+(5+2*i)).html("&nbsp;");
                            $("#td"+(6+2*i)).html("&nbsp;");
                        }

                        for(var i =0;i<parseInt(data.topNum);i++){
                            $("#td"+(5+2*i)).html(data.chars[i]);
                            $("#td"+(6+2*i)).html(data.charsNum[i]);
                        }
                    },
                    error:function () {
                        alert("error");
                    }
                });
            }

        </script>
    </head>


    <body>
     <div>
         <div id="container">
               <h3>请选择一段文字</h3>
                <%--切换选项卡--%>
                <form action="" method="">
                    <input style="margin-left: 40px" type="radio"  name="uploadType" value="1" checked="checked" onclick="radio()"/>文件上传
                    <input style="margin-left: 80px" type="radio"  name="uploadType" value="2" onclick="radio()"/>文本输入
                </form>

                <%--选项卡--%>
                <div id="tab_panel">

                    <%--文件上传方式--%>
                    <div   id="tab1" >
                        <form id="form1" action="<%=request.getContextPath()%>/uploadFile" method="post" enctype="multipart/form-data">
                            <div style="float: left;padding-top: 12px" >
                                <div class="btn" ><a href="#" class="file"> 上传文件 <input type="file"  name="file" onchange="change(event)"/></a></div>
                            </div>
                            <label id="file_label" style="position:relative;top:15px;left:10px"></label>
                            <div style="float: right;padding-right: 50px;padding-top: 12px">
                                <input  id="upload_file" type="button"  class="btn" value="统计" onclick="uploadFormFile()">
                            </div>
                        </form>
                    </div>


                    <%--文本输入方式--%>
                    <div id="tab2" style="display:none;">
                        <form id="form2" action="<%=request.getContextPath()%>/uploadTextext" method="post" enctype="text/plain">
                            <textarea  rows="5"  style="width: 370px" name="text" id="text_input"></textarea>
                            <div style="float: right;padding-top: 12px;padding-right: 50px">
                                <input type="button" class="btn" value="统计" onclick="uploadFormText()">
                                <div style="position:relative;top:5px">
                                    <input type="button" class="btn1" value="清空内容" onclick="reset()">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <%--数据显示的位置--%>
                <div id="main">
                    <p>各统计内容的个数如下：</p>
                    <table border="1" cellspacing="0" bordercolor="#000000" class="data_table">
                        <tr >
                            <th>统计项</th>
                            <th>个数</th>
                        </tr>
                        <tr>
                            <td>英文字母</td>
                            <td id="td1"></td>
                        </tr>
                        <tr>
                            <td>数字</td>
                            <td id="td2"></td>
                        </tr>
                        <tr>
                            <td>中文汉字</td>
                            <td id="td3"></td>
                        </tr>
                        <tr>
                            <td>中文标点符号</td>
                            <td id="td4"></td>
                        </tr>
                    </table>
                    <br><br>
                    <p>文字中出现频率最高的几个字是：</p>
                    <table border="1" cellspacing="0" bordercolor="#000000" class="data_table">
                        <tr >
                            <th>名称</th>
                            <th>个数</th>
                        </tr>
                        <tr>
                            <td id="td5">&nbsp; </td>
                            <td id="td6"> </td>
                        </tr>
                        <tr>
                            <td id="td7">&nbsp; </td>
                            <td id="td8"> </td>
                        </tr>
                        <tr>
                            <td id="td9">&nbsp;</td>
                            <td id="td10"> </td>
                        </tr>
                    </table>
                </div>
         </div>
     </div>
    </body>
</html>
