package NetReptile.ReptileMain;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.glass.ui.Pixels.Format;

import DataBase.Company;
import DataBase.FileCompany;
import DataBase.HibernateTools;
import DataControl.CompanyControl;
import DocDispatcher.DispatcherMain.FileDispathcher;
import NetReptile.DataFormat.CompanyData;
import NetReptile.DataFormat.PDFData;
import ServerPublic.ServerPublic;

/**
 * Pdf 文件存储和数据库持久化
 * 并把文件放入目录提取队列中来
 * @author liaoshichao
 */
public class SavaPDF {
//	private static SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss");
	public static void SaveFile(CompanyData company, PDFData pdf, InputStream in)
			throws Exception {
		/* 文件夹 */
		String folder = company.getCode() + company.getName();
		/* 文件名 */
		String filename = pdf.getFileName();
		/* 父文件夹 */
		String rootfolder=ServerPublic.FolderPath + File.separator + folder+ File.separator +filename;
		/* pdf文件夹 */
		String folderpath = rootfolder	+ File.separator + ServerPublic.PDFFOLD;
		String filepath = folderpath + File.separator + filename+".pdf";
		File folderfile = new File(folderpath);
		if (!folderfile.exists())
			folderfile.mkdirs();
		File file = new File(filepath);
		/* 保存到本地 */
		SaveFile(file, in);
		/* 数据库存储 */
		filename=filename+".pdf";
		Company cpy = CompanyControl.getControl().getCompanyByCode(
				company.getCode());
		DataBase.File dbfile=new DataBase.File();
		dbfile.setFilename(filename);
		dbfile.setPdfpath(filepath.replace(ServerPublic.FolderPath, ""));
//		dbfile.setTime(format.parse(pdf.getTime()));
		dbfile.setTime(new Date());
		dbfile.setStatus(DataBase.File.NODEAL);
		HibernateTools.savaData(dbfile);
		FileCompany fileCompany=new FileCompany(dbfile, cpy);
		HibernateTools.savaData(fileCompany);
		/* 通知 */
		NotifyDocDispatcher(filepath, filename, rootfolder);

	}
	
	/**
	 * 通知文档提取进程
	 * @param filepath
	 * @param filename
	 */
	private static void NotifyDocDispatcher(String filepath, String filename,String folderpath) {
		FileDispathcher.getFileDispathcher().putFiledata(filepath, filename,folderpath);
	}
	
	/**
	 * 文件保存
	 * @param file
	 * @param in
	 * @throws Exception
	 */
	private static void SaveFile(File file, InputStream in) throws Exception {
		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(file));
		BufferedInputStream _in = new BufferedInputStream(in);
		int n = 0;
		while ((n = _in.read()) != -1) {
			out.write(n);
		}
		out.flush();
		out.close();
		in.close();
		_in.close();
	}
}
