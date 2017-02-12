<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 

<%@ taglib prefix="JSTL" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@  include file="head.jsp"%>
	<script>
	$(function() {
		$("#collect").click(function() {
			if($("#loginid").val()==null||$("#loginid").val()==''){
				alert("请先登录");
				return ;
			}
			$.ajax({
				url: '<%=serverPath%>/Record/FA/add?userId=' +$("#loginid").val()+'&topicId='+$("#topicId").val(),
				success: function(data){
					var reslut= '';
						if(data==1){
							reslut='收藏成功';
						}else{
							reslut='您已经收藏过了';
						}
						alert(reslut);
				},
				error: function(){}
			});
		});
		
		
		
		
		
		$("#submit").click(function() {
			if ($("#content").val() == "") {
				alert("内容不能为空");
				return false;
			}  else {
				$("#commentform").submit();
			}
		});
	})
</script>
	
</head>

	<body>
		<%@  include file="top.jsp"%>
		<!--header-web-->
		<div id="main">
	
	
			<div id="container">
				<nav id="mbx">
					当前位置：
					<a href="<%=serverPath%>/topic/list">首页</a> &gt;
					<a href="<%=serverPath%>/topic/list?categoryId=${topic.categoryId}">${topic.categoryName}</a> &gt; 正文
				</nav>
				<!--mbx-->
				<article class="content">
					<header class="contenttitle">
						<a href="" class="counte">☆</a>
						<div class="mscc">
							<h1 class="mscctitle">
								<a href="">${topic.title}</a>
							</h1>
							<address class="msccaddress "> 
								<em>已有${topic.browsingNumber} 人阅读此文 -</em>

								<time>更新${topic.updateTime}</time>
								<time>创建${topic.createTime}</time>
								-
         					 	<a href="${topic.authorId}">${topic.authorName}</a>
						
							</address>
						</div>
					</header>
					<div class="content-text">
						${topic.description}
					</div>
					
					<div class="post-like">
						<a id="collect" href="javascript:;" data-action="ding" data-id="3580" class="favorite">
							<span class="count">收藏</span>
						</a>
					</div>
					
				</article>
				<!--content-->

				
				<div class="comment" id="comments">

					<!-- You can start editing here. -->

					<ol id="comment">
						<JSTL:forEach items="${replies}" var="reply">
						<li id="comment-13248">
							<span>
							<a href="" class="avatar" target="_blank">
								<img src="<JSTL:if test="${reply.authorHeadImage==null}">/uploadImages/default.jpg</JSTL:if>${reply.authorHeadImage}" class="avatar avatar-86" height="86" width="86"></a>
							</span>

							<div class="mhcc">
								<address> 
									<font color="">${reply.authorName}</font>
									-
									${reply.replyTime}
								</address>
								<p>${reply.content}</p>

							</div>
						</li>
						</JSTL:forEach>
						
						<!-- #comment-## -->
					</ol>
					<div id="respond">
						<form action="<%=serverPath%>/reply/add" method="post" id="commentform">
							<input type="hidden" name="topicId" value="${topic.id}" id="topicId">
							<textarea name="content" id="content" rows="3" tabindex="5" placeholder="你有什么要说的 ..."></textarea>
							<input name="submit" type="submit" id="submit" tabindex="2" value="提交评论">
						</form>
					</div>
				</div>
				<!-- .nav-single -->
			</div>
		<form method="GET"  action="<%=serverPath%>/topic/list">
			<%@  include file="aside.jsp"%>
		</form>	
		</div>
		<%@  include file="scroll.jsp"%>
		
			
	</body>


</html>