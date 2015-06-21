package com.kangbiao.beans;

/**
 * 公司实体类
 * @author kangbiao
 *
 */
public class CompanyBean
{
	int id;
	String code=null;
	String name=null;
	String kind=null;
	String trade=null;
	String detail=null;
	public String getDetail()
	{
		return detail;
	}
	public void setDetail(String detail)
	{
		this.detail = detail;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getCode()
	{
		return code;
	}
	public void setCode(String code)
	{
		this.code = code;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getKind()
	{
		return kind;
	}
	public void setKind(String kind)
	{
		this.kind = kind;
	}
	public String getTrade()
	{
		return trade;
	}
	public void setTrade(String trade)
	{
		this.trade = trade;
	}
	
}
