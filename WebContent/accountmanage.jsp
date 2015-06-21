<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="author" content="">
	<meta name="keywords" content="">
 	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>用户管理</title>
	<link rel="stylesheet" type="text/css" href="static/css/bootstrap.min.css">
	<script src="static/js/jquery-1.11.2.min.js"></script>
</head>
<body>
	<div class="container">
	
		<%@ include file="public_jsp/public_nav.jsp" %>
	
		<div class="row" style="width: 100%; height: 600px; height: auto !important; min-height: 600px;">
			<div class="col-md-2" style="margin-top: 70px;">
				<ul class="nav nav-pills nav-stacked">
					<li class="text-center"><a href="user.jsp">个人设置</a></li>
					<li class="active text-center"><a href="accountmanage.jsp">账号管理</a></li>
				</ul>
			</div>
			<div class="col-md-10">
				账号管理
			</div>
			
		</div>
		
		<%@ include file="public_jsp/public_footer.jsp" %>
	</div>
	
<script type="text/javascript">

</script>
<script src="static/js/bootstrap.min.js"></script>
</body>
</html>