package com.kangbiao.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kangbiao.beans.UserBean;
import com.kangbiao.dao.LoginDao;
import com.kangbiao.listener.LoginListener;

@WebServlet("/login.do")
public class Login extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public void destroy()
	{
		super.destroy();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String message = "错误原因：";
		UserBean user = new UserBean();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));


		LoginDao loginDao = new LoginDao();
		// 判断验证码是否过期
		// 验证登录
		System.out.println(request.getParameter("username")+request.getParameter("password"));
		if (loginDao.login(user))
		{
			// 防止重复登录
//			if (!getRepeat(request, response))
//			{
				session.setAttribute("user", user);
				session.setAttribute("username", user.getUsername());
				response.sendRedirect("index.jsp");
//			}
//			else
//			{
//				session.setAttribute("message", "请勿重复登录！");
//				response.sendRedirect("errors.jsp");
//			}
		}
		else
		{// 验证了错后的操作
			message = message + "用户名或密码错误.\n";
			session.setAttribute("message", message);
			response.sendRedirect("errors.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		this.doGet(request, response);
	}

	// 是否重复登录的封闭方法
	public static boolean getRepeat(HttpServletRequest request, HttpServletResponse response)
	{
		boolean falg = false;
		List<UserBean> list = LoginListener.list;
		for (int i = 0; i < list.size(); i++)
		{
			UserBean user = (UserBean) (list.get(i));
			if (request.getParameter("username").equals(user.getUsername()))
			{
				falg = true;
			}
		}
		return falg;
	}

	public void init() throws ServletException
	{

	}

}
