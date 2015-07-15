package NetReptile.ReptileInterface;
/**
 * 爬虫主类必须实现的方法，便于控制和管理 
 * @author liaoshichao
 */
public interface Reptile {
	public static final int RUNNING=1;
	public static final int SUSPEND=0;
	public static final int STOP=-1;
	/* 开启爬虫进程 */
	void startReptile();
	/* 关闭爬虫进程 */
	void closeReptile();
	/* 重启爬虫进程 */
	void restartReptile();
	/* 爬虫子线程处理完一个任务后,回调此方法 便于主进程得知进度*/
	void DealInfo();
	
	int runStatus();
	
	void setPlanTime(int h,int m);
}
