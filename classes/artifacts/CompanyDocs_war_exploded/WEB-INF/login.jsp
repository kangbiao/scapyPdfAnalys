<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="author" content="">
	<meta name="keywords" content="">
 	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>用户登陆</title>
	<link rel="stylesheet" type="text/css" href="static/css/bootstrap.min.css">
	<script src="static/js/jquery-1.11.2.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
		<div style="margin-left: 20%; margin-top: 200px; width: 60%">
			<form class="form-horizontal" action="login.do" method="POST">
				<div class="form-group">
					<label for="username" class="col-sm-4 control-label">用户名</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="username" name="username"
							placeholder="Userid">
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-4 control-label">密码</label>
					<div class="col-sm-5">
						<input type="password" class="form-control" id="password" name="password"
							placeholder="Password">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-8">
						<div class="checkbox">
							<label> <input type="checkbox"> 记住我
							</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-1">
						<input type="submit" class="btn btn-info" id="nsubmit"
							value="登录">
					</div>
				</div>
			</form>
		</div>
		</div>
	</div>
</body>
</html>