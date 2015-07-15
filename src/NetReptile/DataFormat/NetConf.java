package NetReptile.DataFormat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.JSONObject;

import ServerPublic.ServerPublic;

import com.google.gson.Gson;

/**
 * 初始化加载配置文件(用JSON保存的配置文件) 
 * 也可设定新的配置 并用JSON存储在文件中
 * @author liaoshichao
 */
public class NetConf {
	private static final String TIME = "time";
	private static final String TASKHOUR = "taskhour"; /* 每次开始运行的时间(时) */
	private static final String TASKMINUTE = "taskmin"; /* 每次开始运行的时间(分) */
	private static final String TASKSECOND = "tasksecond"; /* 每次开始运行的时间(秒) */
	private static final String ISRIGHTSUS="isrightsus";
	private static final String GETNUM="getnum";
	
	private static String Confpath = "netconf";

	private String time;  /* 时间戳 */
	private int taskhour;
	private int taskmin;
	private int tasksecond;
	private int dealnum;
	private boolean isRightSus;

	private static JSONObject json; /* 防止Gson 将它转成JSON */

	public NetConf() {
		/* 相对路径加载配置文件 */
		Confpath = ServerPublic.CONFFOLD + File.separator + Confpath;
	}

	/* 初始化　从文件中读取配置文件，并赋值给变量 */
	public void Init() {
		try {
			FileReader reader = new FileReader(new File(Confpath));
			StringBuilder builder = new StringBuilder();
			char temp[] = new char[50];
			while (reader.read(temp) != -1)
				builder.append(temp);
			reader.close();
			json = new JSONObject(builder.toString());

			setTime(json.getString(TIME));
			setTaskHour(json.getInt(TASKHOUR));
			setTaskMin(json.getInt(TASKMINUTE));
			setTaskSecond(json.getInt(TASKSECOND));
			setRightSus(json.getBoolean(ISRIGHTSUS));
			setDealnum(json.getInt(GETNUM));
			json = null;
		} catch (FileNotFoundException e) {
			/* 配置文件不存在 异常 */
			LogTool.E("Can't find netConf file!");
		} catch (Exception e) {
			LogTool.E("err Read ConfFile -->"+e.toString());
			setTime(null);
			setTaskHour(23);
			setTaskMin(59);
			setTaskSecond(59);
			setRightSus(true);
			setDealnum(0);
		}
	}

	/* 将新的数据保存在文件中 */
	public void SaveConf() {
		Gson gson = new Gson();
		try {
			FileWriter writer = new FileWriter(new File(Confpath));
			writer.write(gson.toJson(this));
			writer.close();
		} catch (Exception e) {
		}
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getTaskHour() {
		return this.taskhour;
	}

	public void setTaskHour(int hour) {
		this.taskhour=hour;
	}

	public int getTaskMin() {
		return this.taskmin;
	}

	public void setTaskMin(int min) {
		this.taskmin=min;
	}

	public int getTaskSecond() {
		return this.tasksecond;
	}

	public void setTaskSecond(int second) {
		this.tasksecond=second;
	}
	public int getDealnum() {
		return dealnum;
	}

	public void setDealnum(int dealnum) {
		this.dealnum = dealnum;
	}

	public boolean isRightSus() {
		return isRightSus;
	}

	public void setRightSus(boolean isRightSus) {
		this.isRightSus = isRightSus;
	}
}
