package DocDispatcher.DataFormat;

/**
 * Pdf 文件参数
 * @author liaoshichao
 */
public class PdfFile {
	private String filepath;
	private String filename;
	private String folderpath;

	public PdfFile(String path, String filename,String folderpath) {
		this.filepath = path;
		this.filename = filename;
		this.folderpath=folderpath;
	}

	public String getFilepath() {
		return this.filepath;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String name) {
		this.filename = name;
	}

	public void setFilePath(String path) {
		this.filepath = path;
	}
	
	public String getFolderpath() {
		return folderpath;
	}

	public void setFolderpath(String folderpath) {
		this.folderpath = folderpath;
	}
}
