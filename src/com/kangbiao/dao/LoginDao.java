package com.kangbiao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kangbiao.beans.UserBean;
import com.kangbiao.jdbc.ConnectDB;
import com.kangbiao.listener.Log;

public class LoginDao
{
	Connection conn = null;
	
	/**
	 * 检查用户信息是否合法
	 * @param user
	 * @return
	 */
	public boolean login(UserBean user)
	{
		boolean flag=false;
		conn=ConnectDB.GetConnectionMysql();
		String sql="SELECT * FROM User AS u WHERE u.account=? AND u.passwd=?";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try
		{
			pst=conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			rs=pst.executeQuery();
			while (rs.next())
			{
				flag=true;
			}
			pst.close();
			rs.close();
		}
		catch (SQLException e)
		{
			Log.errorlog("执行SQL："+sql+" 失败", "dao.LoginDao.login");
		}
		
		return flag;
	}

}
