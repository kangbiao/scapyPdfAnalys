package com.kangbiao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kangbiao.beans.UserBean;
import com.kangbiao.jdbc.ConnectDB;
import com.kangbiao.listener.Log;

public class UserDao
{
	Connection conn = null;
	
	/**
	 * 修改用户密码
	 * @param user
	 * @return
	 */
	public boolean edit(UserBean user)
	{
		boolean flag=false;
		conn=ConnectDB.GetConnectionMysql();
		String sql="UPDATE User SET passwd=?";
		PreparedStatement pst=null;
		int rs=0;
		
		try
		{
			pst=conn.prepareStatement(sql);
			pst.setString(1, user.getPassword());
			rs=pst.executeUpdate();
			if(rs>0)
			{
				flag=true;
			}
			pst.close();
		}
		catch (SQLException e)
		{
			Log.errorlog("执行SQL："+sql+" 失败", "dao.UserDao.edit");
		}
		
		return flag;
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public boolean add(UserBean user)
	{
		boolean flag=false;
		return flag;
	}
	
	/**
	 * 删除用户
	 * @param user
	 * @return
	 */
	public boolean del(UserBean user)
	{
		boolean flag=false;
		return flag;
	}
}
