package com.kangbiao.beans;

/**
 * 客户端传过来的过滤条件实体类，<br/>
 * 属性有开始记录编号，搜索值，排序方式，排序依据，分页长度
 * @author kangbiao
 *
 */
public class FilterBean
{
	private int start=0;
	private String searchvalue=null;
	private int lengh=0;
	private String order =null ;
	private String orderby=null;
	public int getStart()
	{
		return start;
	}
	public void setStart(int start)
	{
		this.start = start;
	}
	public String getSearchvalue()
	{
		return searchvalue;
	}
	public void setSearchvalue(String searchvalue)
	{
		this.searchvalue = searchvalue;
	}
	public int getLengh()
	{
		return lengh;
	}
	public void setLengh(int lengh)
	{
		this.lengh = lengh;
	}
	public String getOrder()
	{
		return order;
	}
	public void setOrder(String order)
	{
		this.order = order;
	}
	public String getOrderby()
	{
		return orderby;
	}
	public void setOrderby(String orderby)
	{
		this.orderby = orderby;
	}
	
}
