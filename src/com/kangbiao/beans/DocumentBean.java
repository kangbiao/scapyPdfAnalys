package com.kangbiao.beans;

/**
 * 文档实体类
 * @author kangbiao
 *
 */
public class DocumentBean
{
	Integer index=null;
	String companycode = null;
	String companyinfo = null;
	String type = null;
	String industry = null;
	
	/**
	 * 设置一个构造器构造document对象，在添加进列表的时候不用添加引用
	 * @param companycode
	 * @param companyinfo
	 * @param type
	 * @param industry
	 */
	public DocumentBean(String companycode,String companyinfo,String type,String industry)
	{
		this.companycode = companycode;
		this.companyinfo = companyinfo;
		this.type = type;
		this.industry=industry;
	}
	
	public DocumentBean()
	{}

	public Integer getIndex()
	{
		return index;
	}
	public void setIndex(Integer index)
	{
		this.index = index;
	}
	String detail=null;
	
	public String getCompanycode()
	{
		return companycode;
	}
	public void setCompanycode(String companycode)
	{
		this.companycode = companycode;
	}
	public String getCompanyinfo()
	{
		return companyinfo;
	}
	public void setCompanyinfo(String companyinfo)
	{
		this.companyinfo = companyinfo;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getIndustry()
	{
		return industry;
	}
	public void setIndustry(String industry)
	{
		this.industry = industry;
	}
	public String getDetail()
	{
		return detail;
	}
	public void setDetail(String detail)
	{
		this.detail = detail;
	}

}
