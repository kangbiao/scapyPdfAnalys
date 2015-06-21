package NetReptile.ReptileMain;

import NetReptile.DataFormat.LogTool;
import NetReptile.ReptileInterface.Reptile;
/**
 * 连接的控制类  控制Reptile的开始和关闭
 * @author liaoshichao
 */
public class NetControl {
	private static Reptile reptile;

	public static void StartReptile() {
		LogTool.I("Start Reptile");
		reptile = reptile == null ? new ReptileMain() : reptile;
		reptile.startReptile();
	}
     
	public static void CloseReptile() {
		LogTool.I("Close Reptile");
		if(reptile!=null)
			reptile.closeReptile();
	}
	
	public static int getRunStatus(){
		if(reptile==null) return Reptile.STOP;
		 return reptile.runStatus();
	}
	
}
