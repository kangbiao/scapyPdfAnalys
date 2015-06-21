package ServerTools;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTools {
	public static void charSet(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
	}
	
	public static String charToUTF8(String str){
		String re;
		try{
		 re = new String(str.getBytes(),"UTF-8");
		}catch(Exception e){
			System.out.println(e.toString());
			return str;
		}
		return re;
	}
}
