<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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

<%@ include file="public_jsp/public_nav.jsp" %>

<div class="row" style="width: 100%; height: 600px; height: auto !important; min-height: 600px;">
  <div class="col-md-2" style="margin-top: 70px;">
    <ul class="nav nav-pills nav-stacked">
      <li class="active text-center"><a href="user.jsp">个人设置</a></li>
      <li class="text-center"><a href="accountmanage.jsp">账号管理</a></li>
      <li class="text-center"><a href="reptilemanage.jsp">爬虫管理</a></li>
    </ul>

  </div>
  <div class="col-md-1"></div>
  <div class="col-md-6" style="margin-top:70px">
    <form class="form-horizontal" role="form">
      <div class="form-group">
        <label for="oldpwd" class="col-sm-3 control-label">输入原密码</label>

        <div class="col-sm-9">
          <input type="text" class="form-control" id="oldpwd">
        </div>
      </div>
      <div class="form-group">
        <label for="newpwd" class="col-sm-3 control-label">输入新密码</label>

        <div class="col-sm-9">
          <input type="text" class="form-control" id="newpwd"
                  >
        </div>
      </div>
      <div class="form-group">
        <label for="checkpwd" class="col-sm-3 control-label">确认新密码</label>

        <div class="col-sm-9">
          <input type="text" class="form-control" id="checkpwd"
                  >
        </div>
      </div>
      <div class="form-group"></div>
      <div class="form-group">
        <div class="col-sm-offset-3 col-sm-9">
          <input type="button" class="btn btn-primary" id="confirm" value="确定修改">
        </div>
      </div>
    </form>
  </div>
  <div class="col-md-3"></div>
</div>
<%@ include file="public_jsp/public_footer.jsp" %>
<script type="text/javascript">
  $(function () {
    $("#confirm").click(function () {
      changepwd();
    });
  });

  function changepwd() {
    var oldpwd = $("#oldpwd").val();
    var newpwd = $("#newpwd").val();
    var checkpwd = $("#checkpwd").val();
    if (oldpwd == "")
      alert('请输入原密码');
    else if (newpwd == "")
      alert("请输入新密码");
    else if (checkpwd == "")
      alert("请再次输入新密码");
    else if (checkpwd != newpwd)
      alert("两次密码输入不一致");
    else {
      var url = "user.do";
      $.post(url, {
        action: "edit",
        oldpwd: oldpwd,
        newpwd: newpwd,
        checkpwd: checkpwd
      }, function (msg) {
        if (msg.info == "ok") {
          alert("修改成功,请重新登陆");
        } else {
          alert(msg.info);
        }
      }, 'json').error(function () {
        alert("网络连接错误，请稍后再试");
      });
    }
  }
</script>
<script src="static/js/bootstrap.min.js"></script>
</body>
</html>