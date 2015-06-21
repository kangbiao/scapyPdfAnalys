package com.kangbiao.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;

import com.kangbiao.beans.UserBean;

public class LoginListener
{
	public static List<UserBean> list = new ArrayList<UserBean>();

	public void attributeAdded(HttpSessionBindingEvent arg0)
	{
		if (arg0.getName().equals("user"))
		{
			UserBean user = (UserBean) arg0.getValue();
			list.add(user);
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent arg0)
	{
		try
		{
			int n = 0;
			UserBean user = (UserBean) arg0.getValue();
			for (int i = 0; i < list.size(); i++)
			{
				UserBean user2 = (UserBean) list.get(i);
				if (user.getUsername().equals(user2.getUsername()))
				{
					n = i;
					break;
				}
			}
			list.remove(n);
		}
		catch (Exception e)
		{
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent arg0)
	{

	}
}
