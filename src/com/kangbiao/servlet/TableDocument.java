package com.kangbiao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kangbiao.beans.FilterBean;
import com.kangbiao.dao.DocumentDao;

/**
 * 获取处理成功的文档
 * @author kangbiao
 *
 */
@WebServlet("/tabledocument.do")
public class TableDocument extends HttpServlet 
{
	
	private static final long serialVersionUID = 1L;

	public void destroy()
	{
		super.destroy();

	}
	//返回文档类型的json数据,包括所有的公司组成的文档和一个公司所有的文件组成的文档
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		FilterBean filterBean=new FilterBean();
		filterBean.setLengh(Integer.parseInt(request.getParameter("length")));
		filterBean.setStart(Integer.parseInt(request.getParameter("start")));
		//filterBean.setOrder(request.getParameter(""));
		//filterBean.setOrderby(request.getParameter(""));
		filterBean.setSearchvalue(request.getParameter("search[value]"));
//		System.out.println(request.getParameter("companyid"));
		DocumentDao documentDao=new DocumentDao();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		if (request.getParameter("companyid")==""||request.getParameter("companyid")==null)
		{
			response.getWriter().print(documentDao.getCompanyResult(filterBean));
		}
		else 
		{//返回一个公司处理失败或者成功文档
			response.getWriter().print(documentDao.getFileResult(Integer.parseInt(request.getParameter("companyid")),Integer.parseInt(request.getParameter("status")),filterBean));
		}
		
//		System.out.println("dsfd");
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		//this.doGet(request, response);
	}

	public void init() throws ServletException
	{
		
	}
	
	
}
