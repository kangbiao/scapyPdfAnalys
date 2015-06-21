package NetReptile.DataFormat;

import java.io.File;
import java.io.FileInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import ServerPublic.ServerPublic;

/**
 * Log 工具类，单例，提供日志输出
 * 
 * @author liaoshichao
 */
public class LogTool {
	private static final String LogConf = "logconf.xml";
	private static Logger logger;

	private LogTool() {
	}

	static {
		try {
			ConfigurationSource source = new ConfigurationSource(
					new FileInputStream(new File(ServerPublic.CONFFOLD
							+ File.separator + LogConf)));
			Configurator.initialize(null, source);
		} catch (Exception e) {
		}
		if (logger == null)
			logger = LogManager.getLogger();
	}

	public static void E(String message) {
		logger.error(message);
	}

	public static void I(String message) {
		logger.info(message);
	}
}
