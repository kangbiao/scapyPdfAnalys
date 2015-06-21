package com.kangbiao.dao;

import DataControl.CompanyControl;
import DataControl.FileDataControl;
import NetReptile.ReptileMain.NetControl;

/**
 * 获取提示信息数字标签的类
 * 
 * @author kangbiao
 *
 */
public class PartNumDao
{
	// 获取公司数量
	public String getCompanyNum()
	{
		return CompanyControl.getControl().getNumForCompany()+"";
	}

	// 获取总的文档数量
	public String getDocumentNum()
	{
		return FileDataControl.getControl().getFileNums()+"";
	}

	// 获取待处理的文档数量
	public String getFailDocumentNum()
	{
		return FileDataControl.getControl().getUnDealFileNums()+"";
	}

	// 获取爬虫状态
	public String getReptileStatus()
	{
		if (NetControl.getRunStatus() == -1)
			return "<font color='red'>已停止</font>";
		if (NetControl.getRunStatus() == 1)
			return "<font color='green'>正在运行...</font>";
		return "<font color='yellow'>已挂起</font>";
	}
}
