package com.huawei._1_fw.component.log;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class LogUtilsEx {
	// #region Fields

	private static File logFile = new File("C:\\test.txt");

	// #endregion

	// #region log

	public static void log(String strMsg) {
		try {
			FileUtils.writeStringToFile(logFile, strMsg + "\n", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// #endregion
}
