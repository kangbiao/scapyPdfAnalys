package com.kangbiao.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kangbiao.dao.DocumentDao;

@WebServlet("/statis.do")
public class Statis extends HttpServlet
{

    public void destroy()
    {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String tablePart1 = "{\"msg\":{\"info\":\"参数错误\"}}";
        String tablePart2 = "{\"msg\":{\"info\":\"参数错误\"}}";

        String data1 = "{\"msg\":{\"info\":\"参数错误\"}}";
        String data2 = "{\"msg\":{\"info\":\"参数错误\"}}";
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (request.getParameter("action").equals("getTablePart"))
        {
            if (request.getParameter("company_name").equals("1"))
            {
                response.setContentType("application/json");
                response.getWriter().print(tablePart1);
            }
            else if (request.getParameter("company_name").equals("2"))
            {
                response.setContentType("application/json");
                response.getWriter().print(tablePart2);
            }
        }
        else if (request.getParameter("action").equals("getTableData"))
        {
            if (request.getParameter("company_name").equals("1") && request.getParameter("tablePart").equals("1"))
            {
                response.setContentType("application/json");
                response.getWriter().print(data1);
            }
            else if (request.getParameter("company_name").equals("1") && request.getParameter("tablePart").equals("4"))
            {
                response.setContentType("application/json");
                response.getWriter().print(data2);
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //测试数据
        String tablePart = "{\"success\":true,\"jsonString\":[{\"id\":\"1\",\"name\":\"流动资产\"},{\"id\":\"2\"," +
                "\"name\":\"非流动资产\"},{\"id\":\"3\",\"name\":\"流动负债\"},{\"id\":\"4\",\"name\":\"非流动负债\"}]}";
        String table = "{\"success\":true,\"jsonString\":{\"tableheadArr\":[\"项目\",\"附注\",\"期末余额\",\"年初余额\"]," +
                "\"data\":" + "[[\"货币资金\",\"\",\"962,596,420,20\"],[\"交易性金融资产\",\"\",\"\",\"\"],[\"应收票据\",\"\",\"2," +
                "000,000,00\",\"5,002,710,96\"]," + "[\"应收款项\",\"十四\",\"608,181,608,52\",\"403,969,198,29\"]," +
                "[\"预付款项\",\"\",\"362,249,833,23\",\"172,312,146,56\"]," + "[\"应收利息\",\"\",\"\",\"\"]," + "[\"应收股利\"," +
                "\"\",\"\",\"22,400,000.00\"]," + "[\"其他应收款\",\"十四,2\",\"191,623,336,32\",\"144,879,117,80\"]," +
                "[\"存货\",\"\",\"512,146,314.13\",\"573,939,250.64\"]," + "[\"流动资产合计\",\"\",\"2,665,063,775.92\",\"2," +
                "077,929,007.62\"]]}}";
        String barline = "{\"公司一\":{\"五月\":\"21\",\"五1月\":\"21\",\"五2月\":\"21\",\"六月\":\"34\",\"七月\":\"23\"}," +
                "\"公司二\":{\"五月\":\"12\",\"五1月\":\"21\",\"五2月\":\"21\",\"六月\":\"16\",\"七月\":\"16\"}}";
        String pie = "{\"上市公司\":\"320\",\"一般公司\":\"215\",\"石油公司\":\"455\",\"电信公司\":\"578\"}";
        String action = request.getParameter("action");
        String tableColumns = "{\"errorMsg\":\"\",\"success\":true,\"jsonString\":[{\"id\":\"1\",\"name\":\"货币资金\"}," +
                "{\"id\":\"2\",\"name\":\"非货币资金\"}]}";
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        switch (action)
        {
            //1.获取所有的公司列表
            case "getCompanyList":
                DocumentDao documentDao = new DocumentDao();
                response.getWriter().print(documentDao.getAllCompany(request.getParameter("value")));
                break;

            //2.获取表格的part列表
            case "getTablePartList":
//                response.getWriter().print(tablePart);
            	RequestDispatcher rd=null;
            	ServletContext context=getServletContext();
            	rd=context.getRequestDispatcher("/TableTypeList");
            	rd.forward(request, response);
                break;

            //3.获取折线图显示的数据
            case "getLineBar":
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                response.getWriter().print(barline);
//            	RequestDispatcher lrd=null;
//            	ServletContext lcontext=getServletContext();
//            	lrd=lcontext.getRequestDispatcher("/LineCount");
//            	lrd.forward(request, response);
                break;

            //4.获取扇形图显示的数据
            case "getPie":
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                response.getWriter().print(pie);
//            	RequestDispatcher prd=null;
//            	ServletContext pcontext=getServletContext();
//            	prd=pcontext.getRequestDispatcher("/PieCount");
//            	prd.forward(request, response);
                break;

            //5.获取表格显示的数据
            case "getTable":
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                response.getWriter().print(table);
//            	RequestDispatcher trd=null;
//            	ServletContext tcontext=getServletContext();
//            	trd=tcontext.getRequestDispatcher("/TableGet");
//            	trd.forward(request, response);
                break;

            //6.获取复选框的所有值
            case "getTableColumns":
//                response.getWriter().print(tableColumns);
            	RequestDispatcher crd=null;
            	ServletContext ccontext=getServletContext();
            	crd=ccontext.getRequestDispatcher("/TableTypeList");
            	crd.forward(request, response);
                break;

            //7.提交对公司文档数据的修改
            case "submitModify":
//                response.getWriter().print("{\"success\":true}");
            	RequestDispatcher srerd=null;
            	ServletContext srecontext=getServletContext();
            	srerd=srecontext.getRequestDispatcher("/RedealTable");
            	srerd.forward(request, response);
                break;

            //8.处理或者忽略失败的文档
            case "dealIgnore":
            	RequestDispatcher frerd=null;
            	ServletContext frecontext=getServletContext();
            	frerd=frecontext.getRequestDispatcher("/FileDeal");
            	frerd.forward(request, response);
                break;

            //9.设置爬虫进程启动的时间
            case "reptileControl":
//                response.getWriter().print("{\"success\":true}");
            	RequestDispatcher rerd=null;
            	ServletContext recontext=getServletContext();
            	rerd=recontext.getRequestDispatcher("/ReptileTime");
            	rerd.forward(request, response);
                break;
            default:
                break;
        }
    }

    public void init() throws ServletException
    {

    }
}
