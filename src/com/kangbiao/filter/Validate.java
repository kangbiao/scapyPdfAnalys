package com.kangbiao.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 检查是否登陆的过滤器
 * @author kangbiao
 *
 */
public class Validate implements Filter
{

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest reqeust = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = reqeust.getSession();
		Object username = session.getAttribute("username");
		String path = ((HttpServletRequest) req).getServletPath();
		if (!"/login.jsp".equals(path))
		{
			if (username == null || username == "")
			{
				response.sendRedirect("login.jsp");
			}
			else
			{
				chain.doFilter(req, res);
			}
		}
		else
		{
			chain.doFilter(req, res);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException
	{

	}

	@Override
	public void destroy()
	{

	}
}
