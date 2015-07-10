import java.util.ArrayList;

import javax.swing.text.GapContent;

import com.google.gson.Gson;

import DataControl.TableItem;



public class Test {
	public static void main(String[] args) {
		String listname[]=TableItem.ItemMap.get(1);
		ArrayList<ItemClass> list=new ArrayList<ItemClass>();
		for(int i=0;i<listname.length;i++){
			list.add(new ItemClass(i+"",listname[i]));
		}
		System.out.println(new Gson().toJson(list));
	}
	

}
class ItemClass{
	String id;
	String name;
	public ItemClass(String id,String name) {
		this.id=id;
		this.name=name;
	}
}