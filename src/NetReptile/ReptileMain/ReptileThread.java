package NetReptile.ReptileMain;

import java.util.LinkedList;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import DataBase.Company;
import DataBase.HibernateTools;
import DataControl.CompanyControl;
import NetReptile.DataFormat.CompanyData;
import NetReptile.DataFormat.Internet;
import NetReptile.DataFormat.LogTool;
import NetReptile.DataFormat.NetConf;
import NetReptile.DataFormat.PDFData;
/**
 * 子线程获取数据并保存
 * @author liaoshichao
 */
public class ReptileThread extends Thread{
	private ReptileMain reptile;
	private NetConf conf;
	private String DataTime;
	private CompanyData company;
	private LinkedList<PDFData> list;
	
	private HttpClient client;
	private HttpGet get;
	public ReptileThread(ReptileMain reptile,NetConf conf,String Time){
		this.reptile=reptile;
		this.conf=conf;
		this.DataTime=Time;
		client=new DefaultHttpClient();
		list=new LinkedList<PDFData>();
	}
	
	public void run(){
		while((company=reptile.getCompany())!=null){
			SavaInSQL(company);
			InitData();
			for(PDFData data:list){
				get = new HttpGet(data.getFileUrl());
				try {
					HttpEntity entity = client.execute(get).getEntity();
					/* 保存文件 */
					SavaPDF.SaveFile(company,data,entity.getContent());
				} catch (Exception e) {
					e.printStackTrace();
				}finally
				    { get.releaseConnection();  }
			}
		}
		reptile.DealInfo();  /* 通知ReptileMain 当前进度 */
	}
	
	/*本地持久化*/
	private void SavaInSQL(CompanyData company){
		if (!CompanyControl.getControl().isExitByCompanyCode(company.getCode())) {
			Company cpy=new Company();
			cpy.setNum(company.getCode());
			cpy.setName(company.getName());
			cpy.setTrade(company.getTrade());
			HibernateTools.savaData(cpy);
		}
	}
	/* 初始化数据,放数据入链表  便于存储 */
	private void  InitData(){
		list.clear();
		try{
			int page=1;
			JSONObject json=null;
			JSONArray array;
			ParamCreate param=new ParamCreate(company.getCode(),ParamCreate.ANNOUN_TYPE, conf.getTime(),DataTime);
			while(true){
				/* 获取Pdf 文件的下载地址 */
				param.setPage(page++);
				get=new HttpGet(ParamCreate.createFDPParams(Internet.PDFDATAURL,param));
				json=new JSONObject(EntityUtils.toString(client.execute(get).getEntity()));
				/* 异常则拿不到数据  弹出 */
				try {
					array = json.getJSONArray(PDFData.JSONDATA);
				} catch (Exception e) {
					get.releaseConnection();
					break;
				}
				/*  遍历JSON数组  将数据放入链表 */
				for(int i=0;i<array.length();i++){
					 json=array.getJSONObject(i);
					list.add(new PDFData(Internet.PDFPARENT+json.getString(PDFData.FILEPATH),
							json.getString(PDFData.FILENAME).replace(" ", ""),json.getString(PDFData.UPTIME)));
				}
				get.releaseConnection(); //关闭链接
			}
		} catch (ClientProtocolException e) {
			LogTool.E("err in ReptileThread ----->"+e.toString());
		} catch (Exception e) {
			LogTool.E("err in ReptileThread ----->"+e.toString());
		}
	}
}
