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
	private String jsonString="";
	
	public ReturnDataTools(boolean success,String error,String json){
		this.success=success;
		this.errorMessage=error;
		this.jsonString=json;
	}
	
    public ReturnDataTools(boolean success,String error){
		this.success=success;
		this.errorMessage=error;
	}
	
	public ReturnDataTools(){
		
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
	
	public void setJsonString(Object object) {
		Gson gson=new GsonBuilder().serializeNulls().create();
		this.jsonString=gson.toJson(object);
	}
	
	public void setJsonString(Object []object){
		Gson gson=new GsonBuilder().serializeNulls().create();
		this.jsonString=gson.toJson(object);
	}
	
	@Override
	public String toString() {
		Gson gson=new GsonBuilder().serializeNulls().create();
		return gson.toJson(this);
	}
}

