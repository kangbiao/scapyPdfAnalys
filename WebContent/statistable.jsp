<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="author" content="">
	<meta name="keywords" content="">
 	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>数据统计-表格统计</title>
	<link rel="stylesheet" type="text/css" href="static/css/bootstrap.min.css">
	<script src="static/js/jquery-1.11.2.min.js"></script>
	<style type="text/css">
        .search
        {
            left: 0;
            position: relative;
        }
        #auto_div
        {
            display: none;
            width: 185px;
            border: 1px #74c0f9 solid;
            background: #FFF;
            position: absolute;
            top: 35px;
            left: 0;
            color: #323232;
            z-index:999;
            height:300px;
            overflow:auto;
        }
    </style>
</head>
<body>
	<div class="container">
	
		<%@ include file="public_jsp/public_nav.jsp" %>
		
		<div class="row" style="width: 100%; height: 600px; height: auto !important; min-height: 600px;">
			<div class="col-md-2" style="margin-top: 40px;">
				<ul class="nav nav-pills nav-stacked">
					<li class="active text-center"><a href="statistable.jsp">表格数据</a></li>
				</ul>
				
				<ul class="nav nav-pills nav-stacked">
					<hr />
					 <li class="text-center" style="font-size:1.2em">图形统计</li>
					<li class="text-center"><a href="statislinebar.jsp">坐标图</a></li>
					<li class="text-center"><a href="statispie.jsp">扇形图</a></li>
				</ul>
			</div>
			<div class="col-md-10">
				<form class="form-inline">
					<div class="form-group">
					<label for="company_name" class="control-label">筛选条件:&nbsp;&nbsp;&nbsp;</label>
						<div class="input-group">
							<input type="text" class="form-control" id="company_name"
								placeholder="输入公司名">
								 <div id="auto_div">
       	 						</div>
						</div>
					</div>
					<div class="form-group">
						<select id="tableType" class="form-control">
						  <option>--表格类型--</option>
						  <option>资产负载表</option>
						  <option>利润表</option>
						  <option>现金流量表</option>
						  <option>所有者权益变动表</option>
						</select>
					</div>
					<div class="form-group">
					<select id="tablePart" class="form-control">
						<option value="">------</option>
					</select>
					<span class="select-group-btn"><button id="filter" class="btn btn-default" type="button">确定</button></span>
					</div>
				</form>
				<br />
				<div>
					<table id="tableArea"
							class="table table-striped table-bordered table-hover"
							cellspacing="0" width="100%">
							
					</table>
				</div>
			</div>
		</div>
		<!-- 中间容器结束 -->
		
		<%@ include file="public_jsp/public_footer.jsp" %>
	</div>
	
<script type="text/javascript">  
     //测试用的数据
    var company_list = new Array();
    var old_value = "";
    var highlightindex = -1;   //高亮 
     //自动完成
    function AutoComplete(auto, search, mylist) {
        if ($("#" + search).val() != old_value || old_value == "") {
            var autoNode = $("#" + auto);   //缓存对象（弹出框）
            var carlist = new Array();
            var n = 0;
            old_value = $("#" + search).val(); 
             for (i in mylist) {
                if (mylist[i].indexOf(old_value) >= 0) {
                    carlist[n++] = mylist[i];
                }
            }
            if (carlist.length == 0) {
                autoNode.hide();
                return;
            }
            autoNode.empty(); 
            for (i in carlist) {
                var wordNode = carlist[i];   //弹出框里的每一条内容 
                 var newDivNode = $("<div>").attr("id", i);    //设置每个节点的id值
                newDivNode.attr("style", "font:14px/25px;height:25px;padding:0 8px;cursor: pointer;"); 
                 newDivNode.html(wordNode).appendTo(autoNode);  //追加到弹出框 
                 //鼠标移入高亮，移开不高亮
                newDivNode.mouseover(function () {
                    if (highlightindex != -1) {        //原来高亮的节点要取消高亮（是-1就不需要了）
                        autoNode.children("div").eq(highlightindex).css("background-color", "white");
                    }
                    //记录新的高亮节点索引
                    highlightindex = $(this).attr("id");
                    $(this).css("background-color", "#ebebeb");
                });
                newDivNode.mouseout(function () {
                    $(this).css("background-color", "white");
                }); 
                 //鼠标点击文字上屏
                newDivNode.click(function () {
                    //取出高亮节点的文本内容
                    var comText = autoNode.hide().children("div").eq(highlightindex).text();
                    highlightindex = -1;
                    //文本框中的内容变成高亮节点的内容
                    $("#" + search).val(comText);
                })
                if (carlist.length > 0) {    //如果返回值有内容就显示出来
                    autoNode.show();
                } else {               //服务器端无内容返回 那么隐藏弹出框
                    autoNode.hide();
                    //弹出框隐藏的同时，高亮节点索引值也变成-1
                    highlightindex = -1;
                }
            }
        } 
         //点击页面隐藏自动补全提示框
        document.onclick = function (e) {
            var e = e ? e : window.event;
            var tar = e.srcElement || e.target;
            if (tar.id != search) {
                if ($("#" + auto).is(":visible")) {
                    $("#" + auto).css("display", "none")
                }
            }
        } 
     } 
     $(function () {
    	 $.ajax({
             type : "post",
             async : false, //同步执行
             url : "get_company_list.do",
             dataType : "json", //返回数据形式为json
             success : function(result) 
            		{
           	  	  //myChart.hideLoading();
                     if (result) 
                     {
                    	for(var temp in result)
                    	{
                    		company_list.push(result[temp]['name']);
                    	}
                     }
             	   },
             error : function(errorMsg) 
             	{
   	              alert("数据加载失败，请重试");
             	}
             });
    	 
    	 
        old_value = $("#company_name").val();
        $("#company_name").focus(function () {
            AutoComplete("auto_div", "company_name", company_list);
        }); 
         $("#company_name").keyup(function () {
            AutoComplete("auto_div", "company_name", company_list);
        });
    });
     
     
     $(function () 
    		 {
    		 	$("#tableType").change(function () 
    		 	{
    		 		var company_name=$("#company_name").val();
    		 		var tableType=$("#tableType option:selected").attr('value');
    		 		$.ajax({
    		              type : "post",
    		              async : false, //同步执行
    		              url : "statistable.do",
    		              data:{"action":"getTablePart","company_name":company_name,"tableType":tableType},
    		              dataType : "json", //返回数据形式为json
    		              success : function(result) 
    		             		{
    		                      if (result.success) 
    		                      {
    		 	       		$("#tablePart").empty();
    		 	       		$(result.jsonString).each(function(){
    		 	       			$("#tablePart").append("<option value='"+this.id+"'>"+this.name+"</option>");
    		 	       		});
    		                      }
    		                      else
    		                      {
    		                      	alert(result.errorMsg);
    		                      }
    		              	   },
    		              error : function(errorMsg) 
    		              	{
    		    	              alert("服务器连接失败,请重试!");
    		              	}
    		              });
    		 	});
    		 	$("#filter").click(function()
    		 	{
    		 		var company_name=$("#company_name").val();
    		 		var tableType=$("#tableType option:selected").val();
    		 		var tablePart=$("#tablePart option:selected").val();
    		 		if (company_name==""||tableType==""||tablePart==""){
    		 			alert("相关选项不合法!");
    		 		}
    		 		else
    		 		{
    		 			$.ajax({
    		 	             type : "post",
    		 	             async : false, //同步执行
    		 	             url : "statistable.do",
    		 	             data:{"action":"getTableData","company_name":company_name,"tableType":tableType,"tablePart":tablePart},
    		 	             dataType : "json", //返回数据形式为json
    		 	             success : function(result) 
    		 	            		{
    		 	                     if (result.success) 
    		 	                     {
		    		 		       		$("#tableArea").empty();
		    		 		       		var lineData="<tr>";
		    		 		       		for(var index in result.jsonString.tableheadArr){
		    		 		       			lineData+="<th>"+result.jsonString.tableheadArr[index]+"</th>";
		    		 		       		}
		    		 		       		lineData+="</tr>";
		    		 		       		$("#tableArea").html(lineData);
			    		 		       	for (var index1 in result.jsonString.data){
			    			       			lineData="<tr>";
			    			       			for(var index2 in result.jsonString.data[index1]){
			    			       				lineData+="<td>"+result.jsonString.data[index1][index2]+"</td>";
			    			       			}
			    			       			lineData+="</tr>";
			    			       			$("#tableArea").append(lineData);
			    			       		}
    		 	                     }
    		 	                     else
    		 	                     {
    		 	                     	alert(result.errorMsg);
    		 	                     }
    		 	             	   },
    		 	             error : function(errorMsg) 
    		 	             	{
    		 	   	              alert("暂没有相关数据!");
    		 	             	}
    		 	             });
    		 		}
    		 	});
    		 })
</script>	
<script src="static/js/bootstrap.min.js"></script>
</body>
</html>