package DocDistill.TableTools;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import NetReptile.DataFormat.LogTool;
/**
 * 表格提取的工具类
 * @author liaoshichao
 *
 */
public class TableTools {
	private static Pattern nump=Pattern.compile("^[ 0-9,\\.,  ]*$");
	private static Pattern hasNum=Pattern.compile(".*\\d+.*");
	/**
	 * 将普通条目转化为相应的正则表达式
	 * 并比对
	 * @param str 构造正则条目
	 * @param text 用来匹配的内容
	 * @return
	 */
	public static boolean RegexbyStr(String str, String text) {
		return text.contains(str);
	}
	/**
	 * 将小字符数组添加到大字符数组中去
	 * @param str1 大字符数组
	 * @param str2 小字符数组
	 * @param start 开始拷贝的位置
	 */
	public static void StringArrayCopy(String str1[],String str2[],int start){
		for(int i=start,n=0;n<str2.length;n++,i++)
			str1[i]=str2[n];
	}
	/**
	 * 判断是否为数字 eg: 52,156,236,
	 * @param str
	 * @return
	 */
	public static boolean isNumString(String str){
		if(nump.matcher(str).matches())
			return true;
		return false;
	}
	
	/**
	 * 取出ArrayList中有效的数字
	 * 仅仅限于字段为 期末余额 年初余额的表中
	 * @param array
	 * @return
	 */
	public static String getStringByList(ArrayList<String> array) {
		if(array==null)
			return "-,-";
		if (array.size() < 2)
			return "-,-";
		ArrayList<String> li = new ArrayList<String>();
		for (String s : array) {
			if (hasNum.matcher(s).matches())
				li.add(s);
		}
		if(li.size()<=0)
			return "-,-";
		if (li.size() < 2)
			return "-,"+li.get(0);
		if(li.size()==3)
			return li.get(1).replace(",", "") + ","
			+ li.get(2).replace(",", "");
		if (li.size() >= 4) {
			if (li.size() % 2 == 0)
				return li.get(0).replace(",", "") + ","
						+ li.get(2).replace(",", "");
			return li.get(1).replace(",", "") + ","
					+ li.get(2).replace(",", "");
		}
		if (li.size() >= 2)
			return li.get(0).replace(",", "") + ","
					+ li.get(1).replace(",", "");
		return "-,-";
	}
	/**
	 * 提取map中数据到List
	 * @param list
	 * @param item
	 * @param map
	 * @return
	 */
	public static boolean appendStrToArrayofMap(ArrayList<String> list,
			String item[], HashMap<String, ArrayList<String>> map) {
		list.clear();
		for (int i = 0; i < item.length; i++) {
			try {
				list.add(TableTools.getStringByList(map.get(item[i])));
//				System.out.println(item[i]+"------"+TableTools.getStringByList(map.get(item[i])));
			} catch (Exception e) {
				LogTool.E("error  in TableTools( appendStrToArrayofMap )"+e.toString());
				return false;
			}
		}
		return true;
	}
	/**
	 * 利用反射设置值
	 * @param obj 表对象
	 * @param item ArrayList<String>
	 * @return
	 */
	public static boolean setItemByClass(Object obj, ArrayList<String> item) {
		Class cls = obj.getClass();
		for (int i = 0; i < item.size(); i++) {
			try {
				Method method = cls.getDeclaredMethod("setItem" + (i + 1),String.class);
				method.invoke(obj, getFormatNumStr(item.get(i)));
//				method.invoke(obj, "10,10");
			} catch (Exception e) {
				LogTool.E("error  in TableTools( setItemByClass )( setItem"+(i+1)+ e.toString());
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 制造格式化字符串
	 * @param str
	 * @return
	 */
	private static String getFormatNumStr(String str){
		if(str==null)
			return "0,0";
		StringBuilder builder=new StringBuilder(); 
		for(int i=0;i<str.length();i++){
			if((str.charAt(i)>=48 && str.charAt(i)<=57)||str.charAt(i)==','||str.charAt(i)=='.')
				builder.append(str.charAt(i));
		}
		return builder.toString();
	}
}
