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
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * 文档数据访问对象，用来提供Ajax返回公司文档数据和直接返回待处理文档数据
 *
 * @author kangbiao
 */
public class DocumentDao
{
    Connection conn = null;
    String temp = null;// 存储json数据的临时变量

    // 获取所有的公司的名称和id
    public String getAllCompany(String value)
    {
        String sql;
        if (value.equals(""))
            sql="SELECT DISTINCT name,id,num FROM Company LIMIT 100";
        else
            sql = "SELECT DISTINCT name,id,num FROM Company WHERE num LIKE(?) OR name LIKE(?) LIMIT 100";
        List<CompanyBean> list = new ArrayList<>();
        conn = ConnectDB.GetConnectionMysql();
        PreparedStatement pst ;
        ResultSet rs ;
        try
        {
            pst = conn.prepareStatement(sql);
            if (!value.equals(""))
            {
                pst.setString(1,"%"+value+"%");
                pst.setString(2,"%"+value+"%");
            }
            rs = pst.executeQuery();
            while (rs.next())
            {
                CompanyBean companyBean = new CompanyBean();
                companyBean.setId(rs.getInt("id"));
                companyBean.setName(rs.getString("name"));
                companyBean.setCode(rs.getString("num"));
                list.add(companyBean);
            }
            pst.close();
            rs.close();
        }
        catch (SQLException e)
        {
            Log.errorlog("执行SQL：" + sql + " 失败", "dao.DocumentDao.getAllCompany");
        }
        ObjectMapper objectMapper;
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
     * 根据公司id返回公司的名称和代码
     *
     * @param companyID 公司的id
     * @return 公司名称和代码组成的字符串
     */
    public String getCompanyInfoByID(Integer companyID)
    {
        String sql = "SELECT num,name FROM Company WHERE id=?";
        conn = ConnectDB.GetConnectionMysql();
        PreparedStatement pst;
        ResultSet rs;
        String companyInfo = null;
        try
        {

            pst = conn.prepareStatement(sql);
            pst.setInt(1, companyID);
            rs = pst.executeQuery();
            while (rs.next())
            {
                companyInfo = rs.getString("name") + "(" + rs.getString("num") + ")";
            }
            pst.close();
            rs.close();
        }
        catch (SQLException e)
        {
            Log.errorlog("执行SQL：" + sql + " 失败", "dao.DocumentDao.getCompanyInfoByID");
        }
        System.out.print(companyInfo);
        return companyInfo;
    }


    /**
     * 返回表格公司的详细信息,在表格显示
     *
     * @param filter 表格显示相关的过滤实体
     * @return 返回所有的公司信息列表的json数据
     */
    public String getCompanyResult(FilterBean filter)
    {
        String sql;
        String countsql;
        String failDocNum;
        List<CompanyBean> list = new ArrayList<>();
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
        PreparedStatement pst;
        ResultSet rs;
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
                //System.out.println(this.getFailDocNumByCompanyID(rs.getInt("id"),-1));
                failDocNum=this.getFailDocNumByCompanyID(rs.getInt("id"),-1);
                if(!failDocNum.equals("0"))
                    companyBean.setName(rs.getString("name")+"(<font color='red'>"+failDocNum+"</font>个失败文档)");
                else
                    companyBean.setName(rs.getString("name"));
                companyBean.setKind(rs.getString("kind"));
                companyBean.setTrade(rs.getString("trade"));
                companyBean.setDetail("<a href='list.jsp?companyid=" + rs.getInt("id") + "'>详细信息</a>");
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

    /**
     * @param companyid  公司的id
     * @param status     成功或者失败的文档
     * @param filterBean 表格起始页,过滤条件等的过滤实体
     * @return 返回一个公司的失败或者成功的文档json格式的数据
     */
    public String getFileResult(int companyid, int status, FilterBean filterBean)
    {
        String sql = "SELECT File.id AS fileid,filename,pdfpath,htmlpath,time,num,name,Company.id AS companyid FROM " +
                "File,Company WHERE File.id IN (SELECT fileid FROM FileCompany WHERE FileCompany.companyid=?) AND " +
                "Company.id=? AND File.status=? LIMIT ?,?";
        String countsql = "SELECT COUNT(File.id) AS rows FROM File,Company WHERE File.id IN (SELECT fileid FROM " +
                "FileCompany WHERE FileCompany.companyid=?) AND Company.id=? AND File.status=?";
        List<FileBean> list = new ArrayList<>();
        conn = ConnectDB.GetConnectionMysql();
        AjaxReturnDocumentBean ajaxReturnDocumentBean = new AjaxReturnDocumentBean();
        PreparedStatement pst;
        ResultSet rs;
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
                    fileBean.setTwopath("<a class='btn btn-primary btn-xs' target='__black' href='file/" + rs.getString("pdfpath") +
                            "'>PDF</a>&nbsp;&nbsp;&nbsp;<a class='btn btn-primary btn-xs' target='__black' href='getHtml.jsp?fileid="+rs.getInt("fileid") + "'>HTML</a>");
                }
                else
                {
                    fileBean.setTwopath("<a class='btn btn-primary btn-xs' href='/file" + rs.getString("pdfpath") +
                            "'>PDF</a>&nbsp;&nbsp;&nbsp;<a fileid='" + rs.getString("fileid") + "' filepath='/file"
                            +rs.getString("pdfpath")+
                            "' class='btn btn-primary btn-xs' href='javascript:void(0);' title='点击修改' " +
                            "id='outside'>处理</a>&nbsp;&nbsp;<input " +
                            "name='selectedfile' value='" + rs.getString("fileid") + "' type='checkbox'>");
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
        ObjectMapper objectMapper;
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

    //获取某个公司失败的文档数量
    public String getFailDocNumByCompanyID(int companyID, int status)
    {
        String sql = "SELECT COUNT(File.id) AS rows FROM File,Company WHERE File.id IN (SELECT fileid FROM " +
                "FileCompany WHERE FileCompany.companyid=?) AND Company.id=? AND File.status=?";
        conn = ConnectDB.GetConnectionMysql();
        PreparedStatement pst;
        ResultSet rs;
        String failDocNum = null;
        try
        {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, companyID);
            pst.setInt(2, companyID);
            pst.setInt(3, status);
            rs = pst.executeQuery();
            while (rs.next())
            {
                failDocNum = rs.getString("rows");
            }
            pst.close();
            rs.close();
        }
        catch (SQLException e)
        {
            Log.errorlog("执行SQL：" + sql + " 失败", "dao.DocumentDao.getFailDocNumByCompanyID");
        }
        //		if (failDocNum.equals("0"))
        //			failDocNum="";
        return failDocNum;

    }

}
