package NetReptile.ReptileMain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import NetReptile.DataFormat.CompanyData;
import NetReptile.DataFormat.Internet;
import NetReptile.DataFormat.LogTool;
import NetReptile.DataFormat.NetConf;
import NetReptile.ReptileInterface.Reptile;
/**
 * 爬虫守护进程的主要部分
 * 控制开始挂起等操作
 * @author liaoshichao
 */
public class ReptileMain implements Reptile{
	private static final int THREADNUM = 4;   /* 并发的线程数量 */
	private static boolean Running=false;
	private static int runStatus=-1;
	
	/* 运行线程数量 */
	private static int runThreadNum;
	
	/* 链表存储公司编号等基本数据,供子线程抓取 */
	private LinkedList<CompanyData> list;
	private HttpClient client;
	private Timer timer;
	private PlanTask plan;
	private HttpGet get;
	private NetConf conf;   //配置信息
	private String DataTime; //当前日期
	private SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	private int ListNum = 0;

	protected ReptileMain() {
	}
	
	/* 初始化 */
	private void Init(){
		timer=new Timer();
		conf=new NetConf();
		list = new LinkedList<CompanyData>();
		client = new DefaultHttpClient();
		HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000);
		HttpConnectionParams.setSoTimeout(client.getParams(), 10000);
		get = new HttpGet(Internet.DATAURL);
		Running=true;
		runStatus=Reptile.RUNNING;
		LogTool.I("Init ReptileMain ReptileMain running!");
	}
    /* 对接口的实现 */
	/*开始*/
	public void startReptile(){
		if(!Running){
			Init();
			Begin();
		}
	}
	/*关闭*/
	public void closeReptile(){
		runStatus=Reptile.STOP;
		timer=null;
		conf=null;
		list=null;
		client=null;
		get=null;
		Running=false;
		runThreadNum=0;
		System.gc();
	}
	/*重启*/
	public void restartReptile(){
		closeReptile();
		startReptile();
	}
	
	/* 得知子线程的处理结果  子线程执行完毕线程数量-1*/
	public void DealInfo() {
		runThreadNum--;
		if (runThreadNum <= 0)
			Suspend();
	}
	
	@Override
	public int runStatus() {
		return runStatus;
	}
	/**
	 * 开始抓取数据
	 * @param num
	 *            并发的线程数量
	 */
	private void Begin() {
		LogTool.I("Begin The ReptileMain!");
		ListNum = 0;
		conf.Init();
		initData();
		DataTime=format.format(new Date());
		LogTool.I("ReptileThread start get File!");
		
		runThreadNum=THREADNUM;
		for (int i = 0; i < THREADNUM; i++)
			new ReptileThread(this, conf,DataTime).start();
	}
	/**
	 * 休眠挂起，等待下一次开始
	 * 保存时间节点
	 */
	private void Suspend(){
		LogTool.I("Suspend The ReptileMain!");
		list.clear();
		rePlan();
		/* 设置完成时间 */
		conf.setTime(format.format(new Date()));
		conf.SaveConf();
		runStatus=Reptile.SUSPEND;
		System.gc();
	}
	
	/* 重新开始Plan  */
	private void rePlan() {
		Calendar calen = Calendar.getInstance();
		calen.set(Calendar.HOUR_OF_DAY, conf.getTaskHour());
		calen.set(Calendar.MINUTE, conf.getTaskMin());
		calen.set(Calendar.SECOND, conf.getTaskSecond());
		
		plan=new PlanTask();
		timer.schedule(plan, calen.getTime());
		LogTool.I("rePlan start Time!");
	}
	/* 获取数据 */
	protected synchronized CompanyData getCompany() {
		if (ListNum < list.size()) {
			return list.get(ListNum).setPosition(ListNum++);
		}
		return null;
	}

	/* 初始化数据 */
	private void initData() {
		LogTool.I("Begin Init Main Data");
		JSONObject json;
		try {
			String temp = EntityUtils.toString(client.execute(get).getEntity());
			json = new JSONObject(temp);
			temp = json.getString(CompanyData.JsonRoot);
			JSONArray array = new JSONObject(temp).getJSONArray(CompanyData.JsonData);
			for (int i = 0; i < array.length(); i++) {
				json = array.getJSONObject(i);
				
				/* 网页的代码 跳过了40 42 ,故此处同样跳过*/
				if (json.getString(CompanyData.ZQCode).substring(0, 2).equals("40")
						|| json.getString(CompanyData.ZQCode).substring(0, 2).equals("42"))
					continue;
				/*填充Json 数据到CompanyData*/
				list.add(new CompanyData(json.getString(CompanyData.ZQCode).replace(" ", "")
						, json.getString(CompanyData.CompanyName).replace(" ", ""), json.getString(CompanyData.Trade).replace(" ", "")));
			}
			LogTool.I("End Init Main Data");
		}catch (ClientProtocolException e) {
			LogTool.E("Err in ReptileMain ClientProtocol-->"+e.toString());
		} catch (Exception e) {
			LogTool.E("Err in ReptileMain-->"+e.toString());
		}finally{
			get.releaseConnection();
		}
	}

	/* 内部类 实现计划任务 */
	class PlanTask extends TimerTask {
		/* 计划时间到 此方法被回调 */
		public void run() {
			LogTool.I("Plan Time arrive!");
			Begin();
			plan.cancel();
		}
	}
}


