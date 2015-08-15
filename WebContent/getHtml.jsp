<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <title>目录折叠DEMO</title>
  <style>
    #popmenuarea ul{
      counter-reset: li;
      list-style: none;
      *list-style: decimal;
      font: 15px 'trebuchet MS', 'lucida sans';
      padding: 0;
      margin-bottom: 4em;
      text-shadow: 0 1px 0 rgba(255,255,255,.5);
    }

    #popmenuarea ul ul{
      margin: 0 0 0 2em;
    }
    #popmenuarea ul a{
      position: relative;
      display: block;
      padding: .4em .4em .4em .8em;
      *padding: .4em;
      margin: .5em 0 .5em 2.5em;
      background: #ddd;
      color: #444;
      text-decoration: none;
      -webkit-transition: all .3s ease-out;
      -moz-transition: all .3s ease-out;
      -ms-transition: all .3s ease-out;
      -o-transition: all .3s ease-out;
      transition: all .3s ease-out;
    }

    #popmenuarea ul a:hover{
      background: #eee;
    }

    #popmenuarea ul a:before{
      counter-increment: li;
      position: absolute;
      left: -2.5em;
      top: 50%;
      margin-top: -1em;
      background: #fa8072;
      height: 2em;
      width: 2em;
      line-height: 2em;
      text-align: center;
      font-weight: bold;
    }

    #popmenuarea ul a:after{
      position: absolute;
      content: '';
      border: .5em solid transparent;
      left: -1em;
      top: 50%;
      margin-top: -.5em;
      -webkit-transition: all .3s ease-out;
      -moz-transition: all .3s ease-out;
      -ms-transition: all .3s ease-out;
      -o-transition: all .3s ease-out;
      transition: all .3s ease-out;
    }

    #popmenuarea ul a:hover:after{
      left: -.5em;
      border-left-color: #fa8072;
    }
    #menu ul,li{margin:0;padding:0;}
    #menu li{list-style:none;}
    #menu a{color:#333;text-decoration:none;}
    #menu a:hover{color:#008000;}
    #menu{width:100px;display:none;position:fixed;top:0;left:0;padding:5px;z-index:100;}
    #menu ul{background:#FFF;border-radius:5px;box-shadow:0 1px 3px rgba(0, 0, 0, 0.2)}
    #menu ul li{line-height:30px;border-bottom:1px solid #F2F2F2;text-align:center;}
    #menu ul li a{display:block;}
    #menu ul li a:hover{background:#FAFAFA;}
  </style>
  <script src="static/js/jquery-1.11.2.min.js"></script>
</head>
<body>
<div style="display: none;" id="menu">
  <ul>
    <li id="menuItem"><a id="getWord" href="javascript:void(0)">生成word</a></li>
  </ul>
</div>
<div id="popmenuarea" style="width:25%;float:left;padding-right:5px;border-right:1px solid grey;height:90%;overflow:auto;">
  <ul id="first">
    <li partid="1"><a class="menuLink" href="javascript:void(0)">1.文档信息</a></li>
    <li partid="2"><a class="menuLink" href="javascript:void(0)">2.资金信息</a></li>
    <li partid="3"><a class="menuLink" href="javascript:void(0)">2.资金信息</a></li>
    <li partid="4"><a class="menuLink" href="javascript:void(0)">3.其他信息(click)</a>
      <ul >
        <li partid="5"><a class="menuLink" href="javascript:void(0)">3.1其他信息的子类</a></li>
        <li partid="6"><a class="menuLink" href="javascript:void(0)">3.2其他信息的子类(click)</a>
          <ul >
            <li partid="7"><a class="menuLink" href="javascript:void(0)">3.2.1其他信息的子类的子类</a></li>
            <li partid="8"><a class="menuLink" href="javascript:void(0)">3.2.2其他信息的子类的子类(click)</a>
              <ul>
                <li partid="9"><a class="menuLink" href="javascript:void(0)">3.2.2.1其他信息的子类的子类的子类</a></li>
                <li partid="10"><a class="menuLink" href="javascript:void(0)">3.2.2.2其他信息的子类的子类的子类</a></li>
              </ul>
            </li>
          </ul>
        </li>
        <li partid="11"><a class="menuLink" href="javascript:void(0)">3.3其他信息的子类</a></li>
      </ul>
    </li>
    <li partid="12"><a class="menuLink" href="javascript:void(0)">4.系统信息</a></li>
    <li partid="13"><a class="menuLink" href="javascript:void(0)">5.公司信息</a></li>
  </ul>
</div>
<div style="width:73%;float:right;border:1px solid red;" id="contentArea">

</div>
<script type="text/javascript">
  $(function () {
    $("#first").find('ul').each(function(){
      $(this).css('display','none');
    });
    $(".menuLink").click(function ()
    {
      $(this).next().css('display',$(this).next().css('display')=='none'?'':'none');
      var partid=$(this).parent().attr("partid");
       console.log(partid);
       $.ajax({
               type: "post",
               url: "statis.do",
               data: {
                 fileid:<%=request.getParameter("fileid") %>,
                 partid:partid,
                 action:'getRightContent'
               },
               dataType: "text", //返回数据形式为json
               success: function (result) {
                 	$("#contentArea").html(result);
               },
               error: function (errorMsg) {
                 	alert("请求失败,请重试");
               }
             });
    });
    $("#popmenuarea").on("contextmenu","li",function(e){

      if(typeof e.preventDefault === "function"){
        e.preventDefault();
        e.stopPropagation();
      }else{
        e.returnValue = false;
        e.cancelBubble = true;
      }

      $(this).addClass("an").siblings("li").removeClass("an");
      $("#menu").css({"left":e.clientX,"top":e.clientY}).fadeIn();
      // console.log($("#menu").find("#menuItem"));
      // console.log($(e.target).parent().attr("partid"));
      $("#menu").find("#getWord").attr("partid",$(e.target).parent().attr("partid"));
    });
    $(document).bind("click",function(e){
      var target  = $(e.target);
      if(target.closest("#popmenuarea").length == 0){
        $("#menu").hide()
        $("#popmenuarea li.an").removeClass("an")
      }
    });
    $("#getWord").click(function () {
      console.log($(this).attr("partid"));
      var form=$("<form>");//定义一个form表单
      form.attr("style","display:none");
      form.attr("target","");
      form.attr("method","post");
      form.attr("action","statis.do");
      var input1=$("<input>");
      input1.attr("type","hidden");
      input1.attr("name","fileid");
      input1.attr("value",<%=request.getParameter("fileid") %>);
      var input2=$("<input>");
      input2.attr("type","hidden");
      input2.attr("name","partid");
      input2.attr("value",$(this).attr("partid"));
      var input3=$("<input>");
      input3.attr("type","hidden");
      input3.attr("name","action");
      input3.attr("value","downloadDoc");
      $("body").append(form);//将表单放置在web中
      form.append(input1);
      form.append(input2);
      form.append(input3);
      form.submit();//表单提交

    });
  });
</script>
</body>
</html>