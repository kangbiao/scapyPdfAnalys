<%--
  Created by IntelliJ IDEA.
  User: kangbiao
  Date: 15-7-17
  Time: 下午10:24.
--%>
<%@page import="DynamicIndex.Main.HtmlPartControl"%>
<%@ page contentType="text/html;charset=UTF-8" import="com.kangbiao.dao.PartNumDao" language="java" %>


<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title></title>
  <script src="static/js/jquery-1.11.2.min.js"></script>
  <style>
    ul {
      counter-reset: li;
      list-style: none;
      *list-style: decimal;
      font: 15px 'trebuchet MS', 'lucida sans';
      padding: 0;
      margin-bottom: 4em;
      text-shadow: 0 1px 0 rgba(255, 255, 255, .5);
    }

    ul ul {
      margin: 0 0 0 2em;
    }

    ul a {
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

    ul a:hover {
      background: #eee;
    }

    ul a:before {
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

    ul a:after {
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

    ul a:hover:after {
      left: -.5em;
      border-left-color: #fa8072;
    }
  </style>
</head>
<body>
<div style="width:25%;float:left;padding-right:5px;border-right:1px solid grey;height:90%;overflow:auto;">
  <ul id="first">
       <%
         String str=HtmlPartControl.getControl().getHtmlFullPart(Integer.valueOf(request.getParameter("fileid")));
       %>
       <%=str %>
  </ul>
</div>
<div style="width:73%;float:right;border:1px solid red;" id="contentArea">

</div>

<script type="text/javascript">
  $(function () {
    $("#first").find('ul').each(function () {
      $(this).css('display', 'none');
    });
    $("a").click(function () {
      $(this).next().css('display', $(this).next().css('display') == 'none' ? '' : 'none');
      var partid = $(this).parent().attr("partid");
      console.log(partid);
      var Request = new Object();
      Request = GetRequest();
      var fileid=Request['fileid']; 
      $.ajax({
        type: "post",
        url: "HtmlPartFile",
        data: {
          fileid: fileid,
          partid: partid
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
  });
</script>
	<Script language="javascript">
		function GetRequest() {
			var url = location.search; //获取url中"?"符后的字串
			var theRequest = new Object();
			if (url.indexOf("?") != -1) {
				var str = url.substr(1);
				strs = str.split("&");
				for (var i = 0; i < strs.length; i++) {
					theRequest[strs[i].split("=")[0]] = unescape(strs[i]
							.split("=")[1]);
				}
			}
			return theRequest;
		}
	</Script>
</body>
</html>

