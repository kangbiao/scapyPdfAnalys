package ServerTools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * 创建返回数据
 * @author liaoshichao
 */
public class ReturnDataTools {
	private boolean success=false;
	private String errorMessage="";
	private Object jsonString="";
	
	
	public ReturnDataTools(){
		
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public void setJsonString(Object object) {
		this.jsonString=object;
	}
	
//	public void setJsonString(Object []object){
//		this.jsonString=Object;
//	}
	
	@Override
	public String toString() {
		Gson gson=new GsonBuilder().serializeNulls().create();
		return gson.toJson(this);
	}
}

