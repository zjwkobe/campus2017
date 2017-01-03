<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<style type="text/css">
<!--
.STYLE2 {font-size: large}
-->
</style>
<body>
	<h2>请输入一段文字:</h2>
	<form method="post" action="#">
		<table>
			<tr>
				<th>
					<textarea name="content" id="content" cols="30" rows="5">${content}
					</textarea>
				</th>
				<th>
					<table border="0">
						<tr>
							<th>
								<input type="submit" value="统计"/>
							</th>
						</tr>
						<tr>
							<th>
								<input type="reset" value="清空内容"/>
							</th>
						</tr>
					</table>
				</th>
			</tr>
		</table>
	</form>
	
	<p><span class="STYLE2">各统计内容的个数如下</span>:</p>
	<table border="1">
		<tr>
			<th width="104">
				<p>统计项</p>
		  </th>
			<th width="104">
				<p>个数</p>
		  </th>
		</tr>
		<tr>
			<th>
				英文字母
			</th>
			<th>${english_count}</th>
		</tr>
		<tr>
			<th>
				数字
			</th>
			<th>${number_count}</th>
		</tr>
		<tr>
			<th>
				<p>中文汉字</p>
			</th>
			<th>${chinese_count}</th>
		</tr>
		<tr>
			<th>
				<p>标点符号</p>
			</th>
			<th>${punctuation_count}</th>
		</tr>
</table>
	
	<p class="STYLE2">文字中出现频率最高的三个字是:</p>
	<table border="1">
		<tr>
			<th width="102">
				<p>名称</p>
		  </th>
			<th width="102">
				<p>个数</p>
		  </th>
		</tr>
		<tr>
			<th>${first_character}</th>
			<th>${times1}</th>
		</tr>
		<tr>
			<th>${second_character}</th>
			<th>${times2}</th>
		</tr>
		<tr>
			<th>${third_character}</th>
			<th>${times3}</th>
		</tr>
	</table>

</body>
</html>
