package com.kangbiao.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.kangbiao.listener.Log;

/**
 * 数据库连接类
 * 
 * @author kangbiao
 *
 */
public class ConnectDB
{
	private static Connection conn = null;
	private static Properties prop = new Properties();
	static
	{
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("jdbc.properties");
		try
		{
			prop.load(is);
		}
		catch (IOException e)
		{
			Log.errorlog("读取配置文件失败", "jdbc.ConnectDB");
		}
	}

	/**
	 * 返回数据库连接对象
	 * 
	 * @return Connection
	 */
	public static Connection GetConnectionMysql()
	{
		String driver = prop.getProperty("driver");
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String psw = prop.getProperty("password");

		try
		{
			Class.forName(driver);
		}
		catch (ClassNotFoundException e)
		{
			Log.errorlog("加载数据库驱动失败", "jdbc.ConnectDB.GetConnectionMysql");
		}
		try
		{
			conn = DriverManager.getConnection(url, user, psw);
		}
		catch (SQLException e)
		{
			Log.errorlog("数据库连接失败", "jdbc.ConnectDB.GetConnectionMysql");
		}
		return conn;
	}
}
