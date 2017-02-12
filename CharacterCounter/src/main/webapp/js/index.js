$(function() {
	// 奇偶行变色
	$(".list tr:even").addClass("even");
	// 按钮点击
	$("#submitBtn").click(function() {
		$(".error").removeClass("error");
		var userName = $("#userName").val();
		var gender = $("#gender").val();
		if (userName.length == 0 && gender == '') {
			$("#userName").addClass("error");
			$("#gender").addClass("error");
			return;
		}
		$("#listForm").submit();
	});
	// 点击删除
	$(".deleteBtn").click(function() {
		$(this).parent().parent().remove();
		$(".list .even").removeClass("even");
		$(".list tr:even").addClass("even");
	});
	
	// 输入框鼠标移入移出
	$(".ipt").focus(function() {
		$(this).addClass("ipt-focus");
	});
	$(".ipt").blur(function() {
		$(this).removeClass("ipt-focus");
	});
	// $(".ipt").mouseover(function() {
	// $(this).addClass("ipt-hover");
	// });
	// $(".ipt").mouseout(function() {
	// $(this).removeClass("ipt-hover");
	// });
	// $(".ipt").hover(function() {
	// $(this).addClass("ipt-hover");
	// }, function() {
	// $(this).removeClass("ipt-hover");
	// });
	$("#delects").click(function(){
		$(".add").each(function(i){
			if($(this).val()==null){
				if(i==1)
					alert("用户名不能为空")
			}
			
		})
		
		var deletId="";
		$("input:checked").each(function(i){
			if(i==0)
				deletId=deletId+$(this).val();
			else
				deletId=deletId+","+$(this).val();
		})
		
		$("#delIds").val(deletId);
		//$("input:checked").parent().parent().remove();
		$("#delForm").submit();
		//location.href="DelectServlet?deletIds="+deletId;
		
	});
	$(".update").click(function() {
		var id=$(this).parent().siblings(".id").html();
		var username=$(this).parent().siblings(".username").html();
		var userage=$(this).parent().siblings(".userage").html();
		var usergender=$(this).parent().siblings(".usergender").html();
		var useraddr=$(this).parent().siblings(".useraddr").html();
		var userphone=$(this).parent().siblings(".userphone").html();
		//var updateline="<tr>"+$(".updateline").html()+"</tr>";
		var updateline=$(".updateline").html();
		//alert(updateline);
		//$(this).parent().parent().remove();
		$(this).parent().parent().html(updateline);
		$(".updateoption1").val(id);
		$(".updateoption2").val(username);
		$(".updateoption3").val(userage);
		$(".updateoption4").val(usergender);
		$(".updateoption5").val(useraddr);
		$(".updateoption6").val(userphone);
		
	});
	
	
	
	var nowpage=$("#nowpage").val();
	var Tpage=$("#Tpage").val();



	
	if(nowpage<=2){	
		$(".page").each(function(i){
			if(i!=Tpage-1){
			if(i>=3){
				$(this).hide();
			}
			}else{
				$(this).prepend(".......");
			}
		})			
	}else if(Tpage-nowpage<=1){
		$(".page").each(function(i){	
			if(i!=0){
			if(i<nowpage-3){
				$(this).hide();
			}}else{
				$(this).append(".......");
			}
		})
	}else{
		$(".page").each(function(i){
			
			if(i!=0){
				if(i!=Tpage-1){
				if((i<nowpage-2||i>nowpage)){
				$(this).hide();
				}
				if(i==nowpage-2&&i!=1)
					$(this).prepend(".......");
				if(i==nowpage&&i!=Tpage-2)
					$(this).append(".......");
				}
			}						
		})
	
	}
	
	$(".examine").click(function() {
		alert($(this).parent().siblings(".username").html()+"  正在审核中");
	});
	
	
	
	
	
	
	
});
/*
 * function submitForm() { // 校验 var userName =
 * document.getElementById('userName').value; if (userName.length == 0) {
 * alert("用户姓名不能为空"); return; } var gender =
 * document.getElementById('gender').value; if (gender == '') { alert("请选择性别");
 * return; } document.forms[0].submit(); };
 */