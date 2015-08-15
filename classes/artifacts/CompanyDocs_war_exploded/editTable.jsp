  <%@ page language="java" contentType="text/html; charset=UTF-8"
           pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="static/css/bootstrap.min.css">
    <script src="static/js/jquery-1.11.2.min.js"></script>
    <style>
      input {
        width: 50%;
      }
    </style>
  </head>
  <body>
  <div class="container-fluid" style="height:800px;">

    <div class="row" style="width: 100%;height:800px">
      <div class="col-md-8" style="height:800px;">
        <iframe src="static/showPdf/web/viewer.html?filepath=<%=request.getParameter("filepath") %>" frameborder="0"
                height="750"
                width="100%">
          <p>您的浏览器不支持iframe</p>
        </iframe>
      </div>
      <div class="col-md-4" style="height:800px;overflow:auto">
        <ul class="nav nav-tabs" role="tablist">
          <li role="presentation" class="active"><a id="zcfztable" href="#zcfz" aria-controls="zcfz" role="tab"
                                                    data-toggle="tab">资产负债表</a></li>
          <li
                  role="presentation"><a id="lrtable" href="#lr" aria-controls="lr" role="tab" data-toggle="tab">利润表
          </a></li>
          <li
                  role="presentation"><a id="xjlltable" href="#xjll" aria-controls="xjll" role="tab" data-toggle="tab">
            现金流量表</a></li>
        </ul>
        <div class="tab-content" style="margin-top: 20px">
          <div role="tabpanel" class="tab-pane fade in active" id="zcfz"><!-- 资产负债tab开始 -->
            <form action="">
              <p class="bg-info">提示:请依次输入期末金额和年初金额&nbsp;&nbsp;单位:￥</p>
              <table class="table table-bordered table-condensed">
                <tr>
                  <td>货币资金:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>交易性金融资产:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>应收票据:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>应收账款:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>预付款项:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>应收利息:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>应收股利:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>其他应收款:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>存货:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>一年内到期的非流动资产:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>其他流动资产:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>流动资产合计:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>可供出售金融资产:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>持有至到期投资:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>长期应收款:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>长期股权投资:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>投资性房地产:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>固定资产:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>在建工程:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>工程物资:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>固定资产清理:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>生产性生物资产:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>油气资产:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>无形资产:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>开发支出:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>商誉:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>长期待摊费用:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>递延所得税资产:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>其他非流动资产:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>非流动资产合计:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>短期借款:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>交易性金融负债:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>应付票据:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>应付账款:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>预收款项:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>应付职工薪酬:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>应交税费:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>应付利息:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>应付股利:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>其他应付款:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>一年内到期的非流动负债:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>其他流动负债:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>流动负债合计:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>长期借款:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>应付债券:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>长期应付款:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>专项应付款:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>预计负债:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>递延所得税负债:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>其他非流动负债:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>非流动负债合计:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>实收资本(或股本):</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>资本公积:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>减:库存股:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>其他综合收益:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>专项储备:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>盈余公积:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>未分配利润:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>外币报表折算差额:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>归属于母公司所有者权益合计:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>少数股东权益:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>所有者权益合计:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
              </table>
              <p align="center">
                <button type="button" class="btn btn-info btn-sm" id="table1">保存</button>
              </p>
            </form>
          </div>
          <!-- 资产负债tab结束 -->

          <div role="tabpanel" class="tab-pane fade" id="lr"><!-- 利润tab开始 -->
            <form action="">
              <p class="bg-info">提示:请依次输入期末金额和年初金额&nbsp;&nbsp;单位:￥</p>
              <table id="form2" class="table table-bordered table-condensed">
                <tr>
                  <td>其中:营业收入:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>其中:营业成本:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>营业税金及附加:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>销售费用:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>管理费用:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>财务费用:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>资产减值损失:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>加:公允价值变动收益:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>投资收益:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>其中:对联营企业和合营企业的投资收益:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>汇总收益:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>营业外收入:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>非流动资产处置利得:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>营业外支出:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>非流动资产处置损失:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>利润总额:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>减:所得税费用:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>净利润:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>归属于母公司所有者的净利润:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>少数股东损益:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>每股收益:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>基本每股收益:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>稀释每股收益:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>其他综合收益:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>综合收益总额:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>归属于母公司所有者的综合收益:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>归属于少数股东的综合收益:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
              </table>
              <p align="center">
                <button type="button" class="btn btn-info btn-sm" id="table2">保存</button>
              </p>
            </form>
          </div>
          <!-- 利润表tab结束 -->

          <div role="tabpanel" class="tab-pane fade" id="xjll"><!-- 现金流量tab开始 -->
            <form action="">
              <p class="bg-info">提示:请依次输入期末金额和年初金额&nbsp;&nbsp;单位:￥</p>
              <table id="form2" class="table table-bordered table-condensed">
                <tr>
                  <td>销售商品、提供劳务收到的现金:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>收到的税费返还:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>收到其他与经营活动有关的现金:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>经营活动现金流入小计:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>购买商品，接受劳务支付的现金:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>支付给职工以及为职工支付的现金:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>支付的各项税费:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>支付其他与经营活动有关的现金:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>经营活动现金流出小计:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>经营活动产生的现金流量净额:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>投资活动产生的现金流量:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>收回投资收到的现金:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>取得投资收益收到的现金:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>处置固定资产…收回的现金净额:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>处置子公司及其他营业单位收到的现金净额:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>收到其他与投资活动有关的现金:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>投资活动现金流入小计:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>购建固定资产，无形资产和其他长期资产支付的现金:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>投资支付的现金:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>取得子公司及其他营业单位支付的现金净额:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>支付其他与投资活动有关的现金:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>投资活动现金流出小计:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>投资活动产生的现金流量净额:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>筹资活动产生的现金流量净额:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>汇率变动对现金及现金等价物的影响:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>现金及现金等价物净增加额:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>加:期初现金及现金等价物余额:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
                <tr>
                  <td>期末现金及现金等价物余额:</td>
                  <td><input type="text"><input type="text"></td>
                </tr>
              </table>
              <p align="center">
                <button type="button" class="btn btn-info btn-sm" id="table3">保存</button>
              </p>
            </form>
          </div>
          <!-- 现金流量tab结束 -->

        </div>
        <!-- tabContent结束 -->
      </div>
    </div>
    <!-- row结束 -->
  </div>
  <script src="static/js/jquery.cookie.js"></script>
  <script type="text/javascript">
    $(function () {
      var companyType='<%=request.getParameter("companyType") %>';
      var fileid=<%=request.getParameter("fileid") %>;
      $.ajax({
        type: "post",
        async: true,
        url: "statis.do",
        data:
        {'fileid':fileid,'action':'getFromMsg','companyTtype':companyType},
        dataType: "json", //返回数据形式为json
        success: function (result) {
          for (var table in result)
          {
            console.log(result[table]);
            if(table=="table1")
            {
              if(result[table]['status']==0)
                $("#zcfztable").append("&nbsp;<span class='badge' style='background-color: red'>!</span>");
              var allInput=$("#zcfz").find("tr");
              for (var dataindex in result[table]['data'])
              {
                result[table]['data'][dataindex]=result[table]['data'][dataindex].split(",");
                var i=0;
                $(allInput[dataindex]).find("input").each( function()
                {
                  $(this).val(result[table]['data'][dataindex][i++]);
                })
              }
            }
            else if(table=="table2")
            {
              if(result[table]['status']==0)
                $("#lrtable").append("&nbsp;<span class='badge' style='background-color: red'>!</span>");
              var allInput=$("#lr").find("tr");
              for (var dataindex in result[table]['data'])
              {
                result[table]['data'][dataindex]=result[table]['data'][dataindex].split(",");
                var i=0;
                $(allInput[dataindex]).find("input").each( function()
                {
                  $(this).val(result[table]['data'][dataindex][i++]);
                })
              }
            }
            else if(table=="table3")
            {
              if(result[table]['status']==0)
                $("#xjlltable").append("&nbsp;<span class='badge' style='background-color: red'>!</span>");
              var allInput=$("#xjll").find("tr");
              for (var dataindex in result[table]['data'])
              {
                result[table]['data'][dataindex]=result[table]['data'][dataindex].split(",");
                var i=0;
                $(allInput[dataindex]).find("input").each( function()
                {
                  $(this).val(result[table]['data'][dataindex][i++]);
                })
              }
            }
          }
        },
        error: function (errorMsg) {
          alert("网络连接错误!");
        }
      });
    });
    $(":input").click(function () {
      if($(this).val()=="") {
        $(this).val($.cookie("clipBoard"));
        $.cookie('clipBoard', "", { expires: 7, path: '/' });
      }
    });

    $("#table1").click(function () {
      submitEdit(this, 1);
    });
    $("#table2").click(function () {
      submitEdit(this, 2);
    });
    $("#table3").click(function () {
      submitEdit(this, 3);
    });
    function submitEdit(object, tableid) {
      var counter = 1;
      var data = {};
      var i = 1;
      $(object).parent().parent().find("input").each(function () {

        if (counter % 2 == 1) {
          data[i] = $(this).val();
          counter = counter + 1;
        }
        else {
          if(data[i]==""&&$(this).val()=="")
            delete data[i];
          else
            data[i] = data[i] + "," + $(this).val();
          i = i + 1;
          counter = 1;
        }
      });
      console.log(data);
      data['table'] = tableid;
      data['fileid']=<%=request.getParameter("fileid") %>;
      data['action']='submitModify';
      data['companyType']='<%=request.getParameter("companyType") %>';
      $.ajax({
        type: "post",
        async: false, //同步执行
        url: "statis.do",
        data: data,
        dataType: "json", //返回数据形式为json
        success: function (result) {
          if (result.success) {
              alert("处理成功")
          } else {
            alert(result.errorMsg);
          }
        },
        error: function (errorMsg) {
          alert("网络连接错误!");
        }
      });
    }
  </script>
  <script src="static/js/bootstrap.min.js"></script>
  </body>
  </html>