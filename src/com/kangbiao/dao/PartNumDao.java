package com.kangbiao.dao;

import DataControl.CompanyControl;
import DataControl.FileDataControl;
import NetReptile.ReptileInterface.Reptile;
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
//		return CompanyControl.getControl().getNumForCompany()+"";
		return "5";
	}

	// 获取总的文档数量
	public String getDocumentNum()
	{
//		return FileDataControl.getControl().getFileNums()+"";
		return "4";
	}

	// 获取待处理的文档数量
	public String getFailDocumentNum()
	{
//		return "\n失败/总处理 : "
//				+ FileDataControl.getControl().getUnDealFileNums() + "/"
//				+ FileDataControl.getControl().getFileNums();
		return "sdfds";
	}

	// 获取爬虫状态
	public String getReptileStatus()
	{
//		if(NetControl.getRunStatus()==Reptile.STOP)
//		  return "<font color='red'>已停止</font>";
//		else if(NetControl.getRunStatus()==Reptile.RUNNING)
//			return "<font color='green'>正在运行</font>";
		return "<font color='yellow'>已挂起</font>";
	}

}
