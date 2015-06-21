package com.kangbiao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取扇形图数据的servlet
 * @author kangbiao
 *
 */
@WebServlet("/pie.do")
public class Pie extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void destroy()
	{
		super.destroy();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String string = "{\"上市公司\":\"320\",\"一般公司\":\"215\",\"石油公司\":\"455\",\"电信公司\":\"578\"}";
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(string);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		this.doGet(request, response);
	}

	public void init() throws ServletException
	{

	}
}
