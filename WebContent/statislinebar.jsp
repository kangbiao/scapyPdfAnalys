<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="author" content="">
	<meta name="keywords" content="">
 	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>数据统计-折线图&柱状图</title>
	<link rel="stylesheet" type="text/css" href="static/css/bootstrap.min.css">
	    <style type="text/css">
        .search
        {
            left: 0;
            position: relative;
        }
        #auto_div
        {
            display: none;
            width: 200px;
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
        #auto_div2
        {
            display: none;
            width: 200px;
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
	<script src="static/js/jquery-1.11.2.min.js"></script>
</head>
<body>
	<div class="container">
	
		<%@ include file="public_jsp/public_nav.jsp" %>
		
		<div class="row" style="width: 100%; height: 600px; height: auto !important; min-height: 600px;">
			<div class="col-md-2" style="margin-top: 40px;">
				<ul class="nav nav-pills nav-stacked">
					<li class="text-center"><a href="statistable.jsp">表格数据</a></li>
				</ul>
				
				<ul class="nav nav-pills nav-stacked">
					<hr />
					 <li class="text-center" style="font-size:1.2em">图形统计</li>
					<li class="active text-center"><a href="statislinebar.jsp">坐标图</a></li>
					<li class="text-center"><a href="statispie.jsp">扇形图</a></li>
				</ul>
			</div>
			<div class="col-md-10">
				<form class="form-inline">
					<div class="form-group">
					<label for="userid" class="control-label">公司比较:&nbsp;&nbsp;&nbsp;</label>
						<div class="input-group">
							<input type="text" class="form-control" id="search_text"
								placeholder="输入第一个公司名">
								 <div id="auto_div">
       	 						</div>
						</div>
						<div class="input-group">
       	 					<input type="text" class="form-control" id="search_text2"
								placeholder="输入第二个公司名">
								 <div id="auto_div2">
       	 						</div>
						</div>
					</div>
					<div class="form-group">
					<select class="form-control">
						<option value="in">收入</option>
						<option value="out">支出</option>
					</select>
					<span class="select-group-btn"><button id="filter" class="btn btn-default" type="button">确定</button></span>
					</div>
				</form>
				<br />
				<div id="barlinepanel" style="height:500px;"></div>
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
                    		console.log(result[temp]);
                    	}
                     }
             	   },
             error : function(errorMsg) 
             	{
   	              alert("数据加载失败，请重试");
             	}
             });
    	 
    	 
        old_value = $("#search_text").val();
        $("#search_text").focus(function () {
            AutoComplete("auto_div", "search_text", company_list);
        }); 
         $("#search_text").keyup(function () {
            AutoComplete("auto_div", "search_text", company_list);
        });
         
         $("#search_text2").focus(function () {
                 AutoComplete("auto_div2", "search_text2", company_list);
         }); 
          $("#search_text2").keyup(function () {
             AutoComplete("auto_div2", "search_text2", company_list);
         });
    });
</script>
<script src="static/js/echarts.js"></script>	    
<script type="text/javascript">
$(function()
{
	loadechart();
	$("#filter").click(function(){
		loadechart();
	});
});
require.config({
    paths: {
        echarts: 'static/js'
    }
});
function loadechart()
{
require.config({
    paths: {
        echarts: 'static/js'
    }
});
require(
    [
        'echarts',
        'echarts/chart/bar',
        'echarts/chart/line'
    ],
    function (ec) {
        var myChart = ec.init(document.getElementById('barlinepanel'));
        myChart.showLoading(
        		{
        		    text : "正在努力加载数据。。。",
        		    textStyle : {
        		        fontSize : 20,
        		        color:"white"
        		    }
        		}		
        );
        var legendArr=[];//返回文档数据的一级键值
        var xAxisArr=[];//返回文档数据的二级键值（取样来自第一条记录）
        var jsonObj = new Object();
        $.ajax({
          type : "post",
          async : false, //同步执行
          url : "barline.do",
          data : {"filter":"这是过滤条件"},
          dataType : "json", //返回数据形式为json
          success : function(result) 
         		{
        	  	  //myChart.hideLoading();
                  if (result) 
                  {
                	 jsonObj=result;
                	 for(var temp in result)
                	 {
                		 //console.log(temp);
                		 legendArr.push(temp); 
                	 }
                	 //console.log(legendArr);
                	 for(var temp1 in result[legendArr[0]])
                	 {
                		 //console.log(temp1);
                		 xAxisArr.push(temp1);
                	 }
                	 //console.log(xAxisArr);
                  }
          	   },
          error : function(errorMsg) 
          	{
        	  	  myChart.hideLoading();
	              alert("数据加载失败，请重试");
          	}
          });
        option={
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:legendArr
            },
            toolbox: {
                show : true,
                feature : {
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : xAxisArr
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    splitArea : {show : true}
                }
            ],
            series :(function(){
            	var seriesArr=[];
            	//遍历生成数据项数组，将对象放入seriesArr数组中
            	for(var temp2 in legendArr)
            	{
            		//console.log(temp2);
            		var chartObj = new Object();
            		var datatemp=[];
            		//从数据对象中遍历循环取出数据值
            		for(var temp3 in jsonObj[legendArr[temp2]])
            		{
            			//console.log(temp3);
            			datatemp.push(jsonObj[legendArr[temp2]][temp3]);
            		}
            		//console.log(datatemp);
            		chartObj.name=legendArr[temp2];
            		chartObj.type='bar';
            		chartObj.data=datatemp;
            		seriesArr.push(chartObj);
            	}
            	//console.log(seriesArr);
            	return seriesArr;
            })()
        };
        myChart.hideLoading();
        myChart.setOption(option);
    }
);
}
</script>
<script src="static/js/bootstrap.min.js"></script>
</body>
</html>