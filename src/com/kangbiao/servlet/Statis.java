package com.kangbiao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/statistable.do")
public class Statis extends HttpServlet
{

	public void destroy()
	{
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String tablePart1="{\"msg\":{\"info\":\"参数错误\"}}";
		String tablePart2="{\"msg\":{\"info\":\"参数错误\"}}";
		
		String data1="{\"msg\":{\"info\":\"参数错误\"}}";
		String data2="{\"msg\":{\"info\":\"参数错误\"}}";
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		if (request.getParameter("action").equals("getTablePart"))
		{
			if (request.getParameter("company_name").equals("1"))
			{
				response.setContentType("application/json");
				response.getWriter().print(tablePart1);
			}
			else if(request.getParameter("company_name").equals("2")){
				response.setContentType("application/json");
				response.getWriter().print(tablePart2);
			}
		}
		else if (request.getParameter("action").equals("getTableData"))
		{
			if (request.getParameter("company_name").equals("1")&&request.getParameter("tablePart").equals("1"))
			{
				response.setContentType("application/json");
				response.getWriter().print(data1);
			}
			else if(request.getParameter("company_name").equals("1")&&request.getParameter("tablePart").equals("4"))
			{
				response.setContentType("application/json");
				response.getWriter().print(data2);
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String tablePart1="{\"success\":true,\"jsonString\":[{\"id\":\"1\",\"name\":\"流动资产\"},{\"id\":\"2\",\"name\":\"非流动资产\"},{\"id\":\"3\",\"name\":\"流动负债\"},{\"id\":\"4\",\"name\":\"非流动负债\"}]}";
		
		String tablePart2="{\"success\":true,\"jsonString\":[{\"id\":\"5\",\"name\":\"营业\"},{\"id\":\"6\",\"name\":\"利润\"}]}";
		
		
		
		String data1="{\"success\":true,\"jsonString\":{\"tableheadArr\":[\"项目\",\"附注\",\"期末余额\",\"年初余额\"],\"data\":"
				+ "[[\"货币资金\",\"\",\"962,596,420,20\"],[\"交易性金融资产\",\"\",\"\",\"\"],[\"应收票据\",\"\",\"2,000,000,00\",\"5,002,710,96\"],"
				+ "[\"应收款项\",\"十四\",\"608,181,608,52\",\"403,969,198,29\"],"
				+ "[\"预付款项\",\"\",\"362,249,833,23\",\"172,312,146,56\"],"
				+ "[\"应收利息\",\"\",\"\",\"\"],"
				+ "[\"应收股利\",\"\",\"\",\"22,400,000.00\"],"
				+ "[\"其他应收款\",\"十四,2\",\"191,623,336,32\",\"144,879,117,80\"],"
				+ "[\"存货\",\"\",\"512,146,314.13\",\"573,939,250.64\"],"
				+ "[\"流动资产合计\",\"\",\"2,665,063,775.92\",\"2,077,929,007.62\"]]}}";
		
		
		
		String data2="{\"success\":true,\"jsonString\":{\"tableheadArr\":[\"表头11\",\"表头22\",\"表头33\"],\"data\":[[\"第一行11\",\"第一行22\",\"第一行33\"],[\"第二行11\",\"第二行22\",\"第二行33\"]]}}";
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		if (request.getParameter("action").equals("getTablePart"))
		{
			if (request.getParameter("company_name").equals("中科软"))
			{
				response.getWriter().print(tablePart1);
			}
			else if(request.getParameter("company_name").equals("2")){
				response.getWriter().print(tablePart2);
			}
		}
		else if (request.getParameter("action").equals("getTableData"))
		{
			if (request.getParameter("company_name").equals("中科软")&&request.getParameter("tablePart").equals("1"))
			{
				response.getWriter().print(data1);
			}
			else if(request.getParameter("company_name").equals("1")&&request.getParameter("tablePart").equals("4")){
				response.getWriter().print(data2);
			}
		}
	}

	public void init() throws ServletException
	{
		
	}
}
