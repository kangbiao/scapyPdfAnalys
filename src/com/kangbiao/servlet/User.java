package com.kangbiao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kangbiao.beans.UserBean;
import com.kangbiao.dao.LoginDao;
import com.kangbiao.dao.UserDao;

@WebServlet("/user.do")
public class User extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// Filter filter=new Filter();
		// filter.setLengh(request.getParameter("length"));
		// filter.setStart(request.getParameter("start"));
		//
		//
		String action = request.getParameter("action");
		if (action.equals("changeinfo"))
		{
			response.getWriter().print("changeinfo");
		}
		else if (action.equals("adduser"))
		{

		}
		else if (action.equals("deluser"))
		{

		}
		else if (action.equals("edituser")) {

		}
//		DocumentDao documentDao = new DocumentDao();
//		AjaxReturnDocInfo ajaxReturnDocInfo = new AjaxReturnDocInfo();
//		response.setContentType("application/json");
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().print(documentDao.test(ajaxReturnDocInfo));
		// System.out.println("dsfd");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		LoginDao loginDao = new LoginDao();
		UserDao userDao=new UserDao();
		String responsemsg="{\"msg\":{\"info\":\"参数错误\"}}";
		String action = request.getParameter("action");
		if (action.equals("edit"))
		{
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			UserBean userBean = new UserBean();
			userBean.setUsername((String)session.getAttribute("username"));
			userBean.setPassword(request.getParameter("oldpwd"));
			//response.getWriter().print(userBean.getUsername()+userBean.getPassword());
			if (loginDao.login(userBean))
			{//如果用户原密码验证成功
				userBean.setPassword(request.getParameter("newpwd"));
				if(userDao.edit(userBean))
				{
					responsemsg="{\"info\":\"ok\"}";
				}
				else {
					responsemsg="{\"info\":\"字段更新错误!\"}";
				}
			}
			else {
				responsemsg="{\"info\":\"原密码错误!\"}";
			}
			
			response.getWriter().print(responsemsg);
		}
		else if (action.equals("adduser"))
		{
			
		}
		else if (action.equals("deluser"))
		{
			
		}
		else if (action.equals("edituser")) {
			
		}
	}
}
