package com.kangbiao.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kangbiao.beans.AjaxReturnDocumentBean;
import com.kangbiao.beans.CompanyBean;
import com.kangbiao.beans.FileBean;
import com.kangbiao.beans.FilterBean;
import com.kangbiao.jdbc.ConnectDB;
import com.kangbiao.listener.Log;

/**
 * 文档数据访问对象，用来提供Ajax返回公司文档数据和直接返回待处理文档数据
 * 
 * @author kangbiao
 *
 */
public class DocumentDao
{
	Connection conn = null;
	String temp = null;// 存储json数据的临时变量

	// 获取所有的公司的名称和id
	public String getAllCompany()
	{
		String sql = "SELECT DISTINCT name,id FROM Company";
		List<CompanyBean> list = new ArrayList<CompanyBean>();
		conn = ConnectDB.GetConnectionMysql();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try
		{
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next())
			{
				CompanyBean companyBean = new CompanyBean();
				companyBean.setId(rs.getInt("id"));
				companyBean.setName(rs.getString("name"));
				list.add(companyBean);
			}
			pst.close();
			rs.close();
		}
		catch (SQLException e)
		{
			Log.errorlog("执行SQL：" + sql + " 失败", "dao.DocumentDao.getAllCompany");
		}
		ObjectMapper objectMapper = null;
		objectMapper = new ObjectMapper();
		try
		{
			temp = objectMapper.writeValueAsString(list);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return temp;
	}

	/**
	 * 返回表格文档公司数据
	 * 
	 * @param filter
	 * @return
	 */
	public String getCompanyResult(FilterBean filter)
	{
		String sql = null;
		String countsql = null;
		List<CompanyBean> list = new ArrayList<CompanyBean>();
		conn = ConnectDB.GetConnectionMysql();
		AjaxReturnDocumentBean ajaxReturnDocumentBean = new AjaxReturnDocumentBean();
		if (filter.getSearchvalue() == null || filter.getSearchvalue().equals(""))
		{
			sql = "SELECT * FROM Company LIMIT ?,?";
			countsql = "SELECT COUNT(*) AS rows FROM Company";
		}
		else
		{
			sql = "SELECT * FROM Company WHERE num LIKE(?) OR name LIKE(?) LIMIT ?,?";
			countsql = "SELECT COUNT(*) AS rows FROM Company WHERE num LIKE(?) OR name LIKE(?)";
		}
		PreparedStatement pst = null;
		ResultSet rs = null;
		try
		{
			pst = conn.prepareStatement(sql);
			if (filter.getSearchvalue() == null || filter.getSearchvalue().equals(""))
			{
				pst.setInt(1, filter.getStart());
				pst.setInt(2, filter.getLengh());
			}
			else
			{
				pst.setString(1, "%" + filter.getSearchvalue() + "%");
				pst.setString(2, "%" + filter.getSearchvalue() + "%");
				pst.setInt(3, filter.getStart());
				pst.setInt(4, filter.getLengh());
			}
			rs = pst.executeQuery();
			while (rs.next())
			{
				CompanyBean companyBean = new CompanyBean();
				companyBean.setCode(rs.getString("num"));
				companyBean.setName(rs.getString("name"));
				companyBean.setKind(rs.getString("kind"));
				companyBean.setTrade(rs.getString("trade"));
				companyBean.setDetail("<a href='list.jsp?companyid=" + rs.getInt("id")
						+ "'>详细信息</a>");
				list.add(companyBean);
			}
			pst = conn.prepareStatement(countsql);
			if (filter.getSearchvalue() == null || filter.getSearchvalue().equals(""))
			{

			}
			else
			{
				pst.setString(1, "%" + filter.getSearchvalue() + "%");
				pst.setString(2, "%" + filter.getSearchvalue() + "%");
			}
			rs = pst.executeQuery();
			while (rs.next())
			{
				ajaxReturnDocumentBean.setRecordsFiltered(rs.getString("rows"));
				ajaxReturnDocumentBean.setRecordsTotal(rs.getString("rows"));
			}

			pst.close();
			rs.close();

		}
		catch (SQLException e)
		{
			Log.errorlog("执行SQL：" + sql + " 失败", "dao.DocumentDao.getCompanyResult");
		}
		ajaxReturnDocumentBean.setData(list);
		ObjectMapper objectMapper = null;
		objectMapper = new ObjectMapper();
		try
		{
			temp = objectMapper.writeValueAsString(ajaxReturnDocumentBean);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return temp;
	}

	// 获取文件
	public String getFileResult(int companyid, int status, FilterBean filterBean)
	{
		String sql = "SELECT File.id AS fileid,filename,pdfpath,htmlpath,time,num,name,Company.id AS companyid FROM File,Company WHERE File.id IN (SELECT fileid FROM FileCompany WHERE FileCompany.companyid=?) AND Company.id=? AND File.status=? LIMIT ?,?";
		String countsql = "SELECT COUNT(File.id) AS rows FROM File,Company WHERE File.id IN (SELECT fileid FROM FileCompany WHERE FileCompany.companyid=?) AND Company.id=? AND File.status=?";
		List<FileBean> list = new ArrayList<FileBean>();
		conn = ConnectDB.GetConnectionMysql();
		AjaxReturnDocumentBean ajaxReturnDocumentBean = new AjaxReturnDocumentBean();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try
		{
			pst = conn.prepareStatement(sql);
			pst.setInt(1, companyid);
			pst.setInt(2, companyid);
			pst.setInt(3, status);
			pst.setInt(4, filterBean.getStart());
			pst.setInt(5, filterBean.getLengh());
			rs = pst.executeQuery();
			while (rs.next())
			{
				FileBean fileBean = new FileBean();
				fileBean.setFilename(rs.getString("filename"));
				if (status == 1)
				{
					fileBean.setTwopath("<a class='btn btn-primary btn-xs' href='file/"
							+ rs.getString("pdfpath")
							+ "'>PDF</a>&nbsp;&nbsp;&nbsp;<a class='btn btn-primary btn-xs' href='file/"
							+ rs.getString("htmlpath") + "'>HTML</a>");
				}
				else
				{
					fileBean.setTwopath("<a class='btn btn-primary btn-xs' href='file/"
							+ rs.getString("pdfpath")
							+ "'>PDF</a>&nbsp;&nbsp;&nbsp;<a class='btn btn-primary btn-xs' href='javascript:void(0);' title='点击修改' id='outside'>处理</a>&nbsp;&nbsp;<input name='selectedfile' value='"+rs.getString("fileid")+"' type='checkbox'>");
				}
				fileBean.setTime(rs.getString("time"));
				fileBean.setCompany_name(rs.getString("name"));
				fileBean.setCompany_code(rs.getString("num"));
				list.add(fileBean);
			}
			pst = conn.prepareStatement(countsql);
			pst.setInt(1, companyid);
			pst.setInt(2, companyid);
			pst.setInt(3, status);
			rs = pst.executeQuery();
			while (rs.next())
			{
				ajaxReturnDocumentBean.setRecordsFiltered(rs.getString("rows"));
				ajaxReturnDocumentBean.setRecordsTotal(rs.getString("rows"));
			}
			pst.close();
			rs.close();
		}
		catch (SQLException e)
		{
			Log.errorlog("执行SQL：" + sql + " 失败", "dao.DocumentDao.getResult");
		}
		ajaxReturnDocumentBean.setData(list);
		ObjectMapper objectMapper = null;
		objectMapper = new ObjectMapper();
		try
		{
			temp = objectMapper.writeValueAsString(ajaxReturnDocumentBean);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return temp;
	}

}
