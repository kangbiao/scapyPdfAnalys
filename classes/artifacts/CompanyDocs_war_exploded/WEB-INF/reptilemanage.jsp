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
      <li class="text-center"><a href="user.jsp">个人设置</a></li>
      <li class="text-center"><a href="accountmanage.jsp">账号管理</a></li>
      <li class="active text-center"><a href="reptilemanage.jsp">爬虫管理</a></li>
    </ul>

  </div>
  <div class="col-md-1"></div>
  <div class="col-md-6" style="margin-top:70px">
    <form class="form-inline" role="form">
      <div class="form-group">
        <label for="startTime" class="col-sm-5 control-label">选择自动启动时间</label>

        <div class="col-sm-7">
          <input type="text" class="form-control" id="startTime">
        </div>
      </div>
      <div class="form-group"></div>
      <div class="form-group">
        <span class="input-group-btn"><button id="confirm" class="btn btn-default" type="button">确定</button></span>
      </div>
    </form>
  </div>
  <div class="col-md-3"></div>
</div>
<%@ include file="public_jsp/public_footer.jsp" %>
<script type="text/javascript">
  $("#confirm").click(function () {
    var startTime = $("#startTime").val();
    $.ajax({
      type: "post",
      async: false, //同步执行
      url: "statis.do",
      data: {
        "action": "reptileControl",
        "startTime": startTime
      },
      dataType: "json", //返回数据形式为json
      success: function (result) {
        if (result.success) {
          alert("设置成功");
        } else {
          alert(result.errorMsg);
        }
      },
      error: function (errorMsg) {
        alert("网络连接错误!");
      }
    });
  });
</script>
<script src="static/js/bootstrap.min.js"></script>
</body>
</html>