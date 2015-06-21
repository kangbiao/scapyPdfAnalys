<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="author" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>公司文档</title>
<link rel="stylesheet" type="text/css"
	href="static/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="static/css/datatable.css">
<script src="static/js/jquery-1.11.2.min.js"></script>
<style>
td {
	text-align: center;
	vertical-align: middle;
}
</style>
</head>
<body>
	<div class="container">
		<%@ include file="public_jsp/public_nav.jsp"%>
		<div class="row"
			style="width: 100%; height: 600px; height: auto !important; min-height: 600px;">
			<c:choose>
				<c:when test="${param.companyid==null}">


					<div role="tabpanel" class="tab-pane active" id="left"
						style="margin-top: 20px">
						<table id="company_list"
							class="table table-striped table-bordered table-hover"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th class="text-center" width='10%'>公司代码</th>
									<th class="text-center" width='20%'>公司名称</th>
									<th class="text-center" width='10%'>转让类型</th>
									<th class="text-center" width='10%'>所属行业</th>
									<th class="text-center" width='5%'>详细</th>
								</tr>
							</thead>
						</table>
					</div>
					<script>
						$(function() {
							$("#faildocumenticon").click(function() {
								$(".badge").remove();
							});
							$('#myTab a:first').tab('show')
						});
						$(document).ready(function() 
						{
							$('#company_list').dataTable(
							{
								"processing" : true,
								"serverSide" : true,
								"ordering" : false,
								"ajax" : "tabledocument.do",
								"columns" : [
										{
											"data" : "code"
										},
										{
											"data" : "name"
										},
										{
											"data" : "kind"
										},
										{
											"data" : "trade"
										},
										{
											"data" : "detail"
										} ]
							});
						});
					</script>

				</c:when>
				<c:otherwise>
					<ul class="nav nav-tabs" role="tablist" id="myTab">
						<li style="width: 200px" role="presentation" class="active"><a
							href="#left" aria-controls="home" role="tab" data-toggle="tab">成功文档</a></li>
						<li style="width: 200px" id="faildocumenticon" role="presentation"><a
							href="#right" aria-controls="profile" role="tab"
							data-toggle="tab">处理失败文档<span style="background-color: red"
								class="badge">4</span></a></li>
					</ul>

					<div class="tab-content">
						<!-- 左边tab开始 -->
						<div role="tabpanel" class="tab-pane active" id="left"
							style="margin-top: 20px">
							<table id="successdocument"
								class="table table-striped table-bordered table-hover"
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th class="text-center" width='10%'>公司代码</th>
										<th class="text-center" width='20%'>公司名称</th>
										<th class="text-center" width='10%'>文件名</th>
										<th class="text-center" width='5%'>查看</th>
									</tr>
								</thead>
							</table>
						</div>
						<!-- 左边tab结束 -->

						<!-- 右边tab开始 -->
						<div role="tabpanel" class="tab-pane" id="right"
							style="margin-top: 20px">
							<table id="faildocument"
								class="table table-striped table-bordered table-hover"
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th class="text-center" width='10%'>公司代码</th>
										<th class="text-center" width='20%'>公司名称</th>
										<th class="text-center" width='10%'>文件名</th>
										<th class="text-center" width='8%'>查看</th>
									</tr>
								</thead>
							</table>
							<div class="btn-group" role="group" aria-label="..."
								style="float: right; margin-right: 20px;">
								<button type="button" class="btn btn-default" id="deal">处理</button>
								<button type="button" class="btn btn-default" id="ingore">忽略</button>
							</div>
						</div>
						<!-- 右边tab结束 -->
					</div>

					<script>
						$(function() {
							$("#faildocumenticon").click(function() {
								$(".badge").remove();
							});
							$("#deal").click(function() {
								dofile(true);
							});
							$("#ingore").click(function() {
								dofile(false);
							});
							$('#myTab a:first').tab('show')
						});
						function dofile(type) {
							var selectedfile = document
									.getElementsByName('selectedfile');
							var value = "";
							for (var i = 0; i < selectedfile.length; i++) {
								if (selectedfile[i].checked) {
									if (i == selectedfile.length - 1)
										value += selectedfile[i].value;
									else
										value += selectedfile[i].value + ",";
								}
							}
							$.ajax({
					          type : "post",
					          async : false, //同步执行
					          url : "FileDeal",
					          data : {"fileidString":value,"type":type},
					          dataType : "json", //返回数据形式为json
					          success : function(result) 
					         		{
					        	  	  //myChart.hideLoading();
					                  if (result) 
					                  {
					                	  
					                  }
					          	   },
					          error : function(errorMsg) 
					          	{
						              alert("操作失败,请重试");
					          	}
					          });
						}

						function GetQueryString(name) {
							var reg = new RegExp("(^|&)" + name
									+ "=([^&]*)(&|$)", "i");
							var r = window.location.search.substr(1).match(reg);
							if (r != null)
								return (r[2]);
							return null;
						}
						var success_url = "tabledocument.do?status=1";
						var fail_url = "tabledocument.do?status=-1";
						var companyid = GetQueryString("companyid");
						if (companyid != null || companyid != "")
							;
						{
							success_url += "&companyid=" + companyid;
							fail_url += "&companyid=" + companyid;
						}
						$(function() {
							$("#faildocument").click(function() {
								$(".badge").remove();
							});
							$('#myTab a:first').tab('show')
						});
						$(document).ready(function() {
							$('#successdocument').dataTable({
								"processing" : true,
								"serverSide" : true,
								"ordering" : false,
								"searching" : false,
								"ajax" : success_url,
								"columns" : [ {
									"data" : "company_code"
								}, {
									"data" : "company_name"
								}, {
									"data" : "filename"
								}, {
									"data" : "twopath"
								} ]
							});
							$('#faildocument').dataTable({
								"processing" : true,
								"serverSide" : true,
								"ordering" : false,
								"searching" : false,
								"ajax" : fail_url,
								"columns" : [ {
									"data" : "company_code"
								}, {
									"data" : "company_name"
								}, {
									"data" : "filename"
								}, {
									"data" : "twopath"
								} ]
							});
						});
					</script>
				</c:otherwise>
			</c:choose>
		</div>

		<%@ include file="public_jsp/public_footer.jsp" %>
	</div>

	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/datatable.min.js"></script>
	<script src="static/js/datatable.bootstrap.js"></script>
</body>
</html>