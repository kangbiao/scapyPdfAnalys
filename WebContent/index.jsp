<%@ page language="java" import="com.kangbiao.dao.PartNumDao" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="author" content="">
	<meta name="keywords" content="">
 	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>系统首页</title>
	<link rel="stylesheet" type="text/css" href="static/css/bootstrap.min.css">
	<style>
		.tip
		{
			margin-top:50px;
			border:1px solid;
			font-size:20px;
			height:200px;
			font-family:"sans-serif";
			background-color:rgb(238,238,238);
			border-color:rgb(205,205,205);
		}
	</style>
	<script src="static/js/jquery-1.11.2.min.js"></script>
</head>
<body>
	<div class="container">
	
		<%@ include file="public_jsp/public_nav.jsp" %>
		<% 
			PartNumDao partNumDao=new PartNumDao();
			String companyNum=partNumDao.getCompanyNum();
			String documentNum=partNumDao.getDocumentNum();
			String failDocumentNum=partNumDao.getFailDocumentNum();
			String reptileStatus=partNumDao.getReptileStatus();
			
		%>
	
		<div class="row" style="width: 100%; height: 600px; height: auto !important; min-height: 600px;">
			<div class="col-md-1"></div>
			<div class="col-md-3 tip">
				<p align="center">系统状态</p>
				<p>公司总数:<span><%=companyNum %></span></p>
				<p>文档总数<span><%=documentNum %></span></p>
				<p>待处理文档数量:<span><%=failDocumentNum %></span></p>
			</div>
			<div class="col-md-1"></div>
			<div class="col-md-3 tip">
				<p align="center">爬虫状态</p>
				<p>爬虫服务状态: <span id="reptilestatus"><%=reptileStatus %></span></p>
				<p>爬虫服务管理:&nbsp;<br /><input type="submit" id="start" class="btn btn-info" value="启动">&nbsp;&nbsp;<input type="submit" id="stop" class="btn btn-danger" value="停止"></p>
			</div> 
			<div class="col-md-1"></div>
			<div class="col-md-2 tip">
				<p align="center">软件信息</p>
				<p>版本号:V0.5</p>
				<p>操作系统:Centos6.5</p>
			</div>
		</div>
		<%@ include file="public_jsp/public_footer.jsp" %>
	</div>
	<script>
		$(function() {
			$("#start").click(function() {
				start();
			});
			$("#stop").click(function() {
				stop();
			});
			
			$('#myTab a:first').tab('show')
		});
		function start()
		{
			$.ajax({
		          type : "get",
		          async : false, //同步执行
		          url : "ControlReptile?status=start",
		          dataType : "json", //返回数据形式为json
		          success : function(result) 
		         		{
		         			if(result.success)
			         		{
								$("#reptilestatus").html("<font color='green'>正在运行</font>")
			         		}
		         			else
			         		{
		         				alert(result.errorMessage);
					         }
		          	   },
		          error : function(errorMsg) 
		          	{
			              alert("操作失败,请重试");
		          	}
		          });
		}
		function stop()
		{
			$.ajax({
		          type : "get",
		          async : false, //同步执行
		          url : "ControlReptile?status=stop",
		          dataType : "json", //返回数据形式为json
		          success : function(result) 
		         		{
		         			if(result.success)
			         		{
								$("#reptilestatus").html("<font color='red'>已停止</font>")
			         		}
		         			else
			         		{
		         				alert(result.errorMessage);
					         }
		          	   },
		          error : function(errorMsg) 
		          	{
			              alert("操作失败,请重试");
		          	}
		          });
		}
		
	</script>
<script src="static/js/bootstrap.min.js"></script>
</body>
</html>