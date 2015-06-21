package com.kangbiao.beans;

/**
 * 文件实体类
 * @author kangbiao
 *
 */
public class FileBean
{
	private String company_name=null;
	private String company_code=null;
	private String filename=null;
	private String twopath=null;
	private String time=null;
	private int status;
	public String getCompany_name()
	{
		return company_name;
	}
	public void setCompany_name(String company_name)
	{
		this.company_name = company_name;
	}
	public String getCompany_code()
	{
		return company_code;
	}
	public void setCompany_code(String company_code)
	{
		this.company_code = company_code;
	}
	public String getFilename()
	{
		return filename;
	}
	public void setFilename(String filename)
	{
		this.filename = filename;
	}
	public String getTwopath()
	{
		return twopath;
	}
	public void setTwopath(String twopath)
	{
		this.twopath = twopath;
	}
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	
}
