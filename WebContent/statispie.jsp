<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="author" content="">
	<meta name="keywords" content="">
 	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>数据统计-扇形图</title>
	<link rel="stylesheet" type="text/css" href="static/css/bootstrap.min.css">
	<style type="text/css">
		.search
		{
			left: 0;
			position: relative;
		}
		#auto_div {
			display: none;
			width: 195px;
			border: 1px #74c0f9 solid;
			background: #FFF;
			color: #323232;
			z-index: 999;
			height: 300px;
			overflow: auto;
		}
		.glyphicon:hover {
			cursor: pointer;
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
					<li class="text-center"><a href="statislinebar.jsp">坐标图</a></li>
					<li class="active text-center"><a href="statispie.jsp">扇形图</a></li>
				</ul>
			
			</div>
			<div class="col-md-10">
				<form class="form-inline">
					<div class="form-group">
						<label for="company_name" class="control-label">条件过滤:&nbsp;&nbsp;&nbsp;</label>
						<div class="input-group">
							<input type="text" class="form-control" id="company_name"
								placeholder="输入公司名">
							<div id="auto_div"></div>
						</div>
					</div>
					<div class="form-group">
						<select id="tableType" class="form-control">
							<option value="">--表格类型--</option>
							<option value="1">资产负载表</option>
							<option value="2">利润表</option>
							<option value="3">现金流量表</option>
							<!--<option value="4">所有者权益变动表</option> -->
						</select>
					</div>
					<div class="form-group">
						<select id="year" class="form-control">
							<option value="">--请选择--</option>
						</select>
					</div>
					<div class="form-group">
						<span class="input-group-btn"><button id="filter" class="btn btn-default" type="button">确定</button></span>
					</div>
				</form>
				<br />
				<div id="piepanel" style="height:500px;"></div>
			</div>
		</div>
		<!-- 中间容器结束 -->
		
		<%@ include file="public_jsp/public_footer.jsp" %>
	</div>
	
	
<script src="static/js/echarts.js"></script>
<script type="text/javascript">
//测试用的数据
var company_list = new Array();
var old_value = "";
var highlightindex = -1; //高亮 
//自动完成
function AutoComplete(obj, auto, search, mylist) {
	if ($(obj).val() != old_value || old_value == "") {
		var autoNode = $("#" + auto); //缓存对象（弹出框）
		var carlist = new Array();
		var n = 0;
		old_value = $(obj).val();
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
			var wordNode = carlist[i]; //弹出框里的每一条内容 
			var newDivNode = $("<div>").attr("id", i); //设置每个节点的id值
			newDivNode.attr("style","font:14px/25px;height:25px;padding:0 8px;cursor: pointer;");
			newDivNode.html(wordNode).appendTo(autoNode); //追加到弹出框 
			//鼠标移入高亮，移开不高亮
			newDivNode.mouseover(function() {
				if (highlightindex != -1) { //原来高亮的节点要取消高亮（是-1就不需要了）
					autoNode.children("div").eq(highlightindex).css(
							"background-color", "white");
				}
				//记录新的高亮节点索引
				highlightindex = $(this).attr("id");
				$(this).css("background-color", "#ebebeb");
			});
			newDivNode.mouseout(function() {
				$(this).css("background-color", "white");
			});
			//鼠标点击文字上屏
			newDivNode.click(function() {
				//取出高亮节点的文本内容
				var comText = autoNode.hide().children("div").eq(highlightindex).text();
				highlightindex = -1;
				//文本框中的内容变成高亮节点的内容
				$(obj).val(comText);
			})
			if (carlist.length > 0) { //如果返回值有内容就显示出来
				var offsettop = $(obj).offset().top;
				var offsetleft = $(obj).offset().left;
				autoNode.css({position : "fixed",'top' : offsettop + 34,'left' : offsetleft});
				autoNode.show();
			} else { //服务器端无内容返回 那么隐藏弹出框
				autoNode.hide();
				//弹出框隐藏的同时，高亮节点索引值也变成-1
				highlightindex = -1;
			}
		}
	}
	//点击页面隐藏自动补全提示框
	document.onclick = function(e) {
		var e = e ? e : window.event;
		var tar = e.srcElement || e.target;
		if (tar.id != search) {
			if ($("#" + auto).is(":visible")) {
				$("#" + auto).css("display", "none")
			}
		}
	}
}
$(function() {
	//获取公司列表
	$.ajax({
		type : "post",
		async : false, //同步执行
		url : "statis.do",
		data:{"action":"getCompanyList"},
		dataType : "json", //返回数据形式为json
		success : function(result) {
			//myChart.hideLoading();
			if (result) {
				for ( var temp in result) {
					company_list.push(result[temp]['name']);
				}
			}
		},
		error : function(errorMsg) {
			alert("数据加载失败，请重试");
		}
	});

	old_value = $("#company_name").val();
	$("[id=company_name]").focus(function() {
		AutoComplete(this, "auto_div", "company_name", company_list);
	});
	$("[id=company_name]").keyup(function() {
		AutoComplete(this, "auto_div", "company_name", company_list);
	});
	
	//生成年份下拉框
	var myDate= new Date();
	for (var i=myDate.getFullYear();i>=2005;i--){
		$("#year").append("<option value='"+i+".1'>"+i+"--上半年</option>");
		$("#year").append("<option value='"+i+".2'>"+i+"--下半年</option>");
	}
	
	//获取扇形图数据
	$("#filter").click(function() {
		var companyName = "";
		var tableType = "";
		var year="";
		var status="";
			// console.log($(this).children());
		companyName=$("#company_name").val();
		tableType = $("#tableType option:selected").val();
		year = $("#year option:selected").val();
		year_status=year.split(".");
		year=year_status[0];
		status=year_status[1];
		if (companyName == "") {
			alert("请选择公司!");
		} else if (tableType == "") {
			alert("请选择表格类型");
		} else if(year==""){
			alert("请选择年份");
		}else {
			var data={};
			data['companyName']=companyName;
			data['tableType']=tableType;
			data['status']=status;
			data['year']=year;
			data['action']="getPie";
			loadechart(data);
		}
	});
	
	
});
require.config({
    paths: {
        echarts: 'static/js'
    }
});

function loadechart(data)
{
require(
    [
        'echarts',
        'echarts/chart/pie',
        'echarts/chart/funnel'
    ],
    function load(ec) {
        //--- 折柱 ---
        var myChart = ec.init(document.getElementById('piepanel'));
        var jsonObj = new Object();
        var legendArr=[];//返回文档数据的一级键值
        $.ajax({
          type : "post",
          async : false, //同步执行
          url : "statis.do",
          data : data,
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
                  }
          	   },
          error : function(errorMsg) 
          	{
        	  	  myChart.hideLoading();
	              alert("数据加载失败，请重试");
          	}
          });
        myChart.setOption({
        	title : {
                text: '本月文档情况',
                subtext: 'balabala',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient : 'vertical',
                x : 'left',
                data:legendArr
            },
            toolbox: {
                show : true,
                feature : {
                    dataView : {show: true, readOnly: false},
                    magicType : {
                        show: true, 
                        type: ['pie', 'funnel'],
                        option: {
                            funnel: {
                                x: '25%',
                                width: '50%',
                                funnelAlign: 'left',
                                max: 1548
                            }
                        }
                    },
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            series : [
                {
                    name:'文档来源',
                    type:'pie',
                    radius : '65%',
                    center: ['50%', '60%'],
                    data:(function(){
                    	var arr=[];
                    	
                    	for(var temp2 in jsonObj)
                    	{
                    		var objtemp=new Object();
                    		objtemp.value=jsonObj[temp2];
                    		objtemp.name=temp2;
                    		//console.log(objtemp);
                    		arr.push(objtemp);
                    	}
                    	return arr;
                    })()
                }
            ]
        });
    }
);
}
</script>
<script src="static/js/bootstrap.min.js"></script>
</body>
</html>