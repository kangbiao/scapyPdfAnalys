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
					<label for="userid" class="control-label">条件过滤:&nbsp;&nbsp;&nbsp;</label>
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="输入过滤条件">
								<span class="input-group-btn"><button id="filter" class="btn btn-default" type="button">确定</button></span>
						</div>
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
          url : "pie.do",
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