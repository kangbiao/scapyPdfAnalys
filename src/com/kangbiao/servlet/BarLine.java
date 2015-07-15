package com.kangbiao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取坐标图数据的servlet
 * @author kangbiao
 *
 */
@WebServlet("/barline.do")
public class BarLine extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void destroy()
	{
		super.destroy();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String string="{\"公s司一\":{\"五月\":\"21\",\"六月\":\"34\",\"七月\":\"23\"},\"公司二\":{\"五月\":\"12\",\"六月\":\"16\",\"七月\":\"16\"}}";
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
