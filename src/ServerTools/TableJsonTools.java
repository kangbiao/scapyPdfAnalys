package ServerTools;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * 表格头交换数据
 * @author liaoshichao
 */
public class TableJsonTools {
	private boolean success=false;
	private HashMap<Object,Object> jsonString=new HashMap<Object, Object>();
	
	public void setSuccess(boolean success){
		this.success=success;
	}
	
	public void putData(ArrayList<ArrayList<String>> list){
		jsonString.put("data", list);
	}
	
	public void putTableHead(ArrayList<String> list){
		jsonString.put("tableheadArr", list);
	}

	@Override
	public String toString() {
		Gson gson=new GsonBuilder().serializeNulls().create();
		return gson.toJson(this);
	}
	
	
}
