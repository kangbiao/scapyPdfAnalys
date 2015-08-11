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
  <link rel="stylesheet" type="text/css"
        href="static/css/bootstrap.min.css">
  <script src="static/js/jquery-1.11.2.min.js"></script>
</head>
<body>
<%@ include file="public_jsp/public_nav.jsp" %>

<div class="row"
     style="width: 100%; height: 600px; height: auto !important; min-height: 600px;">
  <div class="col-md-2" style="margin-top: 70px;">
    <ul class="nav nav-pills nav-stacked">
      <li class="text-center"><a href="user.jsp">个人设置</a></li>
      <li class="active text-center"><a href="accountmanage.jsp">账号管理</a></li>
      <li class="text-center"><a href="reptilemanage.jsp">爬虫管理</a></li>
    </ul>
  </div>
  <div class="col-md-1"></div>
  <div class="col-md-6" style="margin-top: 70px">
    <form class="form-horizontal" role="form">
      <div class="form-group">
        <label for="name" class="col-sm-3 control-label">姓名</label>

        <div class="col-sm-9">
          <input type="text" class="form-control" id="name">
        </div>
      </div>
      <div class="form-group">
        <label for="email" class="col-sm-3 control-label">邮箱</label>

        <div class="col-sm-9">
          <input type="text" class="form-control" id="email">
        </div>
      </div>
      <div class="form-group">
        <label for="userid" class="col-sm-3 control-label">输入登录名</label>

        <div class="col-sm-9">
          <input type="text" class="form-control" id="userid">
        </div>
      </div>
      <div class="form-group">
        <label for="password" class="col-sm-3 control-label">输入密码</label>

        <div class="col-sm-9">
          <input type="password" class="form-control" id="password">
        </div>
      </div>
      <div class="form-group">
        <label for="role" class="col-sm-3 control-label">选择用户组</label>

        <div class="col-sm-9">
          <select class="form-control" id="role">
            <option value="0">--请选择--</option>
            <option value="1">普通用户</option>
            <option value="2">管理员</option>
          </select>
        </div>
      </div>
      <div class="form-group"></div>
      <div class="form-group">
        <div class="col-sm-offset-3 col-sm-9">
          <input type="button" class="btn btn-primary" id="confirm"
                 value="确定添加">
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
      var name = $("#name").val();
      var email = $("#email").val();
      var userid = $("#userid").val();
      var password = $("#password").val();
      var role = $("#role option:selected").val();
      if(name=="")
        alert("请输入用户名!");
      else if(email=="")
        alert("请输入邮箱!");
      else if(userid=="")
        alert("请输入登录名!");
      else if(password=="")
        alert("请输入密码!");
      else if(role=="")
        alert("请选择用户角色!");
      else
        alert("添加成功!");
//
//      $.ajax({
//        type: "post",
//        async: false, //同步执行
//        url: "get_company_list.do",
//        data: {'name': name, "email": email, "userid": userid, "password": password, "role": role},
//        dataType: "json", //返回数据形式为json
//        success: function (result) {
//          //myChart.hideLoading();
//        },
//        error: function (errorMsg) {
//          alert("数据加载失败，请重试");
//        }
//      });

    });
  });
</script>
<script src="static/js/bootstrap.min.js"></script>
</body>
</html>