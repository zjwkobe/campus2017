<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="styles/file.css" rel="stylesheet">
</head>
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/jquery-form.js"></script>
<script type="text/javascript">
	$(function(){
		$("#file").change(function(){
		
			$("#clear_text").click(function(){
				$("#string").val("");
			});
			$("#filename").text($("#file").val());
			$("#stat").click(function(){
				$("#file_form").ajaxSubmit({
					url:"handleAction/handleFile.do",
					success:function(data){
						$("#word").text(data[1]);
						$("#number").text(data[2]);
						$("#chinese").text(data[0]);
						$("#char").text(data[3]);
						
						$.ajax({
							url:"handleAction/handlefre_File.do",
							type:"post",
							success:function(data){
								if(data.length>=1){
									$("#str1").text(data[0].str);
									$("#sum1").text(data[0].sum);
								}
								
								if(data.length>=2){
									$("#str2").text(data[1].str);
									$("#sum2").text(data[1].sum);
								}
								
								if(data.length>=3){
									$("#str3").text(data[2].str);
									$("#sum3").text(data[2].sum);
								}
							}
						});
					}
				});
			});
		});
		
		
		$("#stat_string").click(function(){
			$.ajax({
				url:"handleAction/handleString.do",
				datatype:"json",
				type:"post",
				data:{
					value:$("#string").val()
				},
				success:function(data){
					$("#word").text(data[1]);
					$("#number").text(data[2]);
					$("#chinese").text(data[0]);
					$("#char").text(data[3]);
					
					
					$.ajax({
							url:"handleAction/handlefre_String.do",
							type:"post",
							success:function(data){
									if(data.length>=1){
									$("#str1").text(data[0].str);
									$("#sum1").text(data[0].sum);
								}
								
								if(data.length>=2){
									$("#str2").text(data[1].str);
									$("#sum2").text(data[1].sum);
								}
								
								if(data.length>=3){
									$("#str3").text(data[2].str);
									$("#sum3").text(data[2].sum);
								}
							}
						});
				}
			});
		});
	});
</script>
<body>
    <div class="wrap">
        <header>
            <p>请选择一段文字</p>
            <input type="radio" value="文件上传" id="upload" name="file" checked><label for="upload">文件上传</label>
            <input type="radio" value="文本输入" id="entry" name="file"><label for="entry">文本输入</label><br>
            <div class="input input_file" style="position: relative">
                <form enctype="multipart/form-data" id="file_form" method="post">
                	  <a href="#" class="file">  上传文件
                   	  <input type="file" value="上传文件" name="file" id="file">
                </a>
                </form>
               
                	 <span id="filename"></span>
                 
                <input type="button" value="统计" class="button" id="stat">
            </div>
            <div class="input input_text">
                <textarea rows="4" cols="45" class="text_area" id="string"></textarea>
                <input type="button" value="统计" class="button" id="stat_string">
                <input type="button" value="清空内容" class="button clear" id="clear_text">
            </div>
        </header>
        <div class="main">
            <div class="table table_1">
                <p>各统计内容的个数如下：</p>
                <table cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                            <td class="td1">统计项</td>
                            <td class="td2">个数</td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                           <td>英文字母</td>
                           <td id="word"></td>
                        </tr>
                        <tr>
                            <td>数字</td>
                            <td id="number"></td>
                        </tr>
                        <tr>
                            <td>中文汉字</td>
                            <td id="chinese"></td>
                        </tr>
                        <tr>
                            <td>中英文标点符号</td>
                            <td id="char"></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="table table_2">
                <p>文字中出现频率最高的三个字是：</p>
                <table cellspacing="0" cellpadding="0">
                    <thead>
                    <tr>
                        <td class="td1">名称</td>
                        <td class="td2">个数</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td id="str1"></td>
                        <td id="sum1"></td>
                    </tr>
                    <tr>
                        <td id="str2"></td>
                        <td id="sum2"></td>
                    </tr>
                    <tr>
                        <td id="str3"></td>
                        <td id="sum3"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script src="scripts/file.js"></script>
</body>
</html>