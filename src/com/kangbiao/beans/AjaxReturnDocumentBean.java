package com.kangbiao.beans;

import java.util.List;

/**
 * 通过ajax返回公司文档数据的实体类，属性有记录总数，过滤后的记录和一个文档实体的列表,文档尸体可以屎公司和文件信息
 * @author kangbiao
 *
 */
public class AjaxReturnDocumentBean
{
	private String recordsTotal = null;
	private String recordsFiltered = null;
	private List<?> data =null;
	
	public String getRecordsFiltered()
	{
		return recordsFiltered;
	}

	public void setRecordsFiltered(String recordsFiltered)
	{
		this.recordsFiltered = recordsFiltered;
	}



	public String getRecordsTotal()
	{
		return recordsTotal;
	}

	public void setRecordsTotal(String recordsTotal)
	{
		this.recordsTotal = recordsTotal;
	}

	public List<?> getData()
	{
		return data;
	}

	public void setData(List<?> data)
	{
		this.data = data;
	}

}
