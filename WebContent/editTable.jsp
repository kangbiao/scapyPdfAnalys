<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
 	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="static/css/bootstrap.min.css">
	<script src="static/js/jquery-1.11.2.min.js"></script>
	<style>
		input
		{
			width: 50%;
		}
	</style>
</head>
<body>
	<div class="container">
	
		
		<div class="row" style="width: 100%; height: 600px; height: auto !important; min-height: 600px;">
			<div class="col-md-12">
				<ul class="nav nav-tabs" role="tablist">
				  <li role="presentation" class="active"><a href="#zcfz" aria-controls="zcfz" role="tab" data-toggle="tab">资产负债表</a></li>
				  <li role="presentation"><a href="#lr" aria-controls="lr" role="tab" data-toggle="tab">利润表</a></li>
				  <li role="presentation"><a href="#xjll" aria-controls="xjll" role="tab" data-toggle="tab">现金流量表</a></li>
				  <li role="presentation"><a href="#syzqybd" aria-controls="syzqybd" role="tab" data-toggle="tab">所有者权益变动表</a></li>
				</ul>
				<div class="tab-content" style="margin-top: 20px">
					<div role="tabpanel" class="tab-pane fade in active" id="zcfz"><!-- 资产负债tab开始 -->
						<form action="">
							<p class="bg-info">提示:请依次输入期末金额和年初金额&nbsp;&nbsp;单位:￥</p>
							<table class="table table-bordered table-condensed">
								<tr>
									<td>流动资产:</td><td><input type="text"><input type="text"></td>
									<td>货币资金:</td><td><input type="text"><input type="text"></td>
									<td>交易性金融资产:</td><td><input type="text"><input type="text"></td>
									<td>应收票据:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>应收账款:</td><td><input type="text"><input type="text"></td>
									<td>预付款项:</td><td><input type="text"><input type="text"></td>
									<td>应收利息:</td><td><input type="text"><input type="text"></td>
									<td>应收股利:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>其他应收款:</td><td><input type="text"><input type="text"></td>
									<td>存货:</td><td><input type="text"><input type="text"></td>
									<td>一年内到期的非流动资产:</td><td><input type="text"><input type="text"></td>
									<td>其他流动资产:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>流动资产合计:</td><td><input type="text"><input type="text"></td>
									<td>非流动资产:</td><td><input type="text"><input type="text"></td>
									<td>可供出售金融资产:</td><td><input type="text"><input type="text"></td>
									<td>持有至到期投资:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>长期应收款:</td><td><input type="text"><input type="text"></td>
									<td>长期股权投资:</td><td><input type="text"><input type="text"></td>
									<td>投资性房地产:</td><td><input type="text"><input type="text"></td>
									<td>固定资产:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>在建工程:</td><td><input type="text"><input type="text"></td>
									<td>工程物资:</td><td><input type="text"><input type="text"></td>
									<td>固定资产清理:</td><td><input type="text"><input type="text"></td>
									<td>生产性生物资产:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>油气资产:</td><td><input type="text"><input type="text"></td>
									<td>无形资产:</td><td><input type="text"><input type="text"></td>
									<td>开发支出:</td><td><input type="text"><input type="text"></td>
									<td>商誉:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>长期待摊费用:</td><td><input type="text"><input type="text"></td>
									<td>递延所得税资产:</td><td><input type="text"><input type="text"></td>
									<td>其他非流动资产:</td><td><input type="text"><input type="text"></td>
									<td>非流动资产合计:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>资产总计:</td><td><input type="text"><input type="text"></td>
									<td>流动负债:</td><td><input type="text"><input type="text"></td>
									<td>短期借款:</td><td><input type="text"><input type="text"></td>
									<td>交易性金融负债:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>应付票据:</td><td><input type="text"><input type="text"></td>
									<td>应付账款:</td><td><input type="text"><input type="text"></td>
									<td>预收款项:</td><td><input type="text"><input type="text"></td>
									<td>应付职工薪酬:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>应交税费:</td><td><input type="text"><input type="text"></td>
									<td>应付利息:</td><td><input type="text"><input type="text"></td>
									<td>应付股利:</td><td><input type="text"><input type="text"></td>
									<td>其他应付款:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>一年内到期的非流动负债:</td><td><input type="text"><input type="text"></td>
									<td>其他流动负债:</td><td><input type="text"><input type="text"></td>
									<td>流动负债合计:</td><td><input type="text"><input type="text"></td>
									<td>非流动负债:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>长期借款:</td><td><input type="text"><input type="text"></td>
									<td>应付债券:</td><td><input type="text"><input type="text"></td>
									<td>长期应付款:</td><td><input type="text"><input type="text"></td>
									<td>专项应付款:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>预计负债:</td><td><input type="text"><input type="text"></td>
									<td>递延所得税负债:</td><td><input type="text"><input type="text"></td>
									<td>其他非流动负债:</td><td><input type="text"><input type="text"></td>
									<td>非流动负债合计:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>负债合计:</td><td><input type="text"><input type="text"></td>
									<td>所有者权益(或股东权益):</td><td><input type="text"><input type="text"></td>
									<td>实收资本(或股本):</td><td><input type="text"><input type="text"></td>
									<td>资本公积:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>减:库存股:</td><td><input type="text"><input type="text"></td>
									<td>其他综合收益:</td><td><input type="text"><input type="text"></td>
									<td>专项储备:</td><td><input type="text"><input type="text"></td>
									<td>盈余公积:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>未分配利润:</td><td><input type="text"><input type="text"></td>
									<td>外币报表折算差额:</td><td><input type="text"><input type="text"></td>
									<td>归属于母公司所有者权益合计:</td><td><input type="text"><input type="text"></td>
									<td>少数股东权益:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>所有者权益合计:</td><td><input type="text"><input type="text"></td>
									<td>负载和所有者权益总计:</td><td><input type="text"><input type="text"></td>
								</tr>
							</table>
							<p align="center"><button type="button" class="btn btn-info btn-sm" id="table1">保存</button></p>
						</form>
					</div>	<!-- 资产负债tab结束 -->

					<div role="tabpanel" class="tab-pane fade" id="lr"><!-- 利润tab开始 -->
						<form action="">
							<p class="bg-info">提示:请依次输入期末金额和年初金额&nbsp;&nbsp;单位:￥</p>
							<table id="form2" class="table table-bordered table-condensed">
								<tr>
									<td>营业总收入:</td><td><input type="text"><input type="text"></td>
									<td>其中:营业收入:</td><td><input type="text"><input type="text"></td>
									<td>营业总成本:</td><td><input type="text"><input type="text"></td>
									<td>其中:营业成本:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>营业税金及附加:</td><td><input type="text"><input type="text"></td>
									<td>销售费用:</td><td><input type="text"><input type="text"></td>
									<td>管理费用:</td><td><input type="text"><input type="text"></td>
									<td>财务费用:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>资产减值损失:</td><td><input type="text"><input type="text"></td>
									<td>加:公允价值变动收益:</td><td><input type="text"><input type="text"></td>
									<td>投资收益:</td><td><input type="text"><input type="text"></td>
									<td>其中:对联营企业和合营企业的投资收益:</td><td><input type="text"><input type="text"></td>
									
								</tr>
								<tr>
									<td>汇总收益:</td><td><input type="text"><input type="text"></td>
									<td>营业利润:</td><td><input type="text"><input type="text"></td>
									<td>营业外收入:</td><td><input type="text"><input type="text"></td>
									<td>非流动资产处置利得:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>营业外支出:</td><td><input type="text"><input type="text"></td>
									<td>非流动资产处置损失:</td><td><input type="text"><input type="text"></td>
									<td>利润总额:</td><td><input type="text"><input type="text"></td>
									<td>减:所得税费用:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>净利润:</td><td><input type="text"><input type="text"></td>
									<td>归属于母公司所有者的净利润:</td><td><input type="text"><input type="text"></td>
									<td>少数股东损益:</td><td><input type="text"><input type="text"></td>
									<td>每股收益:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>基本每股收益:</td><td><input type="text"><input type="text"></td>
									<td>稀释每股收益:</td><td><input type="text"><input type="text"></td>
									<td>其他综合收益:</td><td><input type="text"><input type="text"></td>
									<td>综合收益总额:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>归属于母公司所有者的综合收益:</td><td><input type="text"><input type="text"></td>
									<td>归属于少数股东的综合收益:</td><td><input type="text"><input type="text"></td>
								</tr>
							</table>
							<p align="center"><button type="button" class="btn btn-info btn-sm" id="table2">保存</button></p>
						</form>
					</div><!-- 利润表tab结束 -->

					<div role="tabpanel" class="tab-pane fade" id="xjll"><!-- 现金流量tab开始 -->
						<form action="">
							<p class="bg-info">提示:请依次输入期末金额和年初金额&nbsp;&nbsp;单位:￥</p>
							<table id="form2" class="table table-bordered table-condensed">
								<tr>
									<td>经营活动产生的现金流量:</td><td><input type="text"><input type="text"></td>
									<td>销售商品、提供劳务收到的现金:</td><td><input type="text"><input type="text"></td>
									<td>收到的税费返还:</td><td><input type="text"><input type="text"></td>
									<td>收到其他与经营活动有关的现金:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>经营活动现金流入小计:</td><td><input type="text"><input type="text"></td>
									<td>购买商品，接受劳务支付的现金:</td><td><input type="text"><input type="text"></td>
									<td>支付给职工以及为职工支付的现金:</td><td><input type="text"><input type="text"></td>
									<td>支付的各项税费:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>支付其他与经营活动有关的现金:</td><td><input type="text"><input type="text"></td>
									<td>经营活动现金流出小计:</td><td><input type="text"><input type="text"></td>
									<td>经营活动产生的现金流量净额:</td><td><input type="text"><input type="text"></td>
									<td>投资活动产生的现金流量:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>收回投资收到的现金:</td><td><input type="text"><input type="text"></td>
									<td>取得投资收益收到的现金:</td><td><input type="text"><input type="text"></td>
									<td>处置固定资产…收回的现金净额:</td><td><input type="text"><input type="text"></td>
									<td>处置子公司及其他营业单位收到的现金净额:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>收到其他与投资活动有关的现金:</td><td><input type="text"><input type="text"></td>
									<td>投资活动现金流入小计:</td><td><input type="text"><input type="text"></td>
									<td>购建固定资产，无形资产和其他长期资产支付的现金:</td><td><input type="text"><input type="text"></td>
									<td>投资支付的现金:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>取得子公司及其他营业单位支付的现金净额:</td><td><input type="text"><input type="text"></td>
									<td>支付其他与投资活动有关的现金:</td><td><input type="text"><input type="text"></td>
									<td>投资活动现金流出小计:</td><td><input type="text"><input type="text"></td>
									<td>投资活动产生的现金流量净额:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>筹资活动产生的现金流量净额:</td><td><input type="text"><input type="text"></td>
									<td>汇率变动对现金及现金等价物的影响:</td><td><input type="text"><input type="text"></td>
									<td>现金及现金等价物净增加额:</td><td><input type="text"><input type="text"></td>
									<td>加:期初现金及现金等价物余额:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>期末现金及现金等价物余额:</td><td><input type="text"><input type="text"></td>
								</tr>
							</table>
							<p align="center"><button type="button" class="btn btn-info btn-sm" id="table3">保存</button></p>
						</form>
					</div><!-- 现金流量tab结束 -->
					
					<div role="tabpanel" class="tab-pane fade" id="syzqybd"><!-- 所有者权益tab开始 -->
						<form action="">
							<p class="bg-info">提示:请依次输入实收资本,资本公积,减库存股,其他综合收益,专项储备,盈余公积,未分配利润&nbsp;&nbsp;单位:￥</p>
							<table id="form2" class="table table-bordered table-condensed">
								<tr>
									<td>上年年末余额:</td><td><input type="text"><input type="text"></td>
									<td>加:会计政策变更:</td><td><input type="text"><input type="text"></td>
									<td>前期差错更正:</td><td><input type="text"><input type="text"></td>
									<td>其他:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>本年年初余额:</td><td><input type="text"><input type="text"></td>
									<td>本期增减变动金额:</td><td><input type="text"><input type="text"></td>
									<td>综合收益总额:</td><td><input type="text"><input type="text"></td>
									<td>股东投入和减少资本:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>所有者投入资本:</td><td><input type="text"><input type="text"></td>
									<td>股份支付计入所有者权益的金额:</td><td><input type="text"><input type="text"></td>
									<td>其他:</td><td><input type="text"><input type="text"></td>
									<td>利润分配:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>提取盈余公积:</td><td><input type="text"><input type="text"></td>
									<td>对所有者(或股东)的分配:</td><td><input type="text"><input type="text"></td>
									<td>其他:</td><td><input type="text"><input type="text"></td>
									<td>所有者权益内部结转:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>资本公积转增资本(或股本):</td><td><input type="text"><input type="text"></td>
									<td>盈余公积转增资本(或股本):</td><td><input type="text"><input type="text"></td>
									<td>盈余公积弥补亏损:</td><td><input type="text"><input type="text"></td>
									<td>其他:</td><td><input type="text"><input type="text"></td>
								</tr>
								<tr>
									<td>专项储备:</td><td><input type="text"><input type="text"></td>
									<td>本期提取:</td><td><input type="text"><input type="text"></td>
									<td>本期使用:</td><td><input type="text"><input type="text"></td>
									<td>本期期末余额:</td><td><input type="text"><input type="text"></td>
								</tr>
							</table>
							<p align="center"><button type="button" class="btn btn-info btn-sm" id="table4">保存</button></p>
						</form>
					</div><!-- 利润表tab结束 -->
				</div><!-- tabContent结束 -->
			</div>
		</div><!-- row结束 -->
	</div>
<script type="text/javascript">
$("#table1").click(function(){
	submitEdit(this,1);
});
$("#table2").click(function(){
	submitEdit(this,2);
});
$("#table3").click(function(){
	submitEdit(this,3);
});
$("#table4").click(function(){
	submitEdit(this,4);
});
function submitEdit(object,tableid)
{
	var counter=1;
	var data={};
	var i=1;
	$(object).parent().parent().find("input").each(function(){

		if (counter%2==1) 
		{
			data["'"+i+"'"]=$(this).val();
			counter=counter+1;
			//console.log($(this).val());
		}
		else
		{
			data["'"+i+"'"]=data["'"+i+"'"]+","+$(this).val();
			//console.log($(this).val());
			i=i+1;
			counter=1;
		}
		//console.log(i);
	});
	data['table']=tableid;
	console.log(data);	
}
</script>
<script src="static/js/bootstrap.min.js"></script>
</body>
</html>