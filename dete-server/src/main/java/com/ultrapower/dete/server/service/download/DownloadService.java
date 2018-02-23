package com.ultrapower.dete.server.service.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class DownloadService implements IDownload {
	
	private static Logger log = LoggerFactory.getLogger(DownloadService.class);
	private static final String PATH = System.getProperty("user.dir") + File.separator + "data" + File.separator + "download" + File.separator;
	
	public byte[] download(String pathName) {
		InputStream input = null;
		byte[] dto = null;
		try {
			String path = PATH + pathName;
			input = new FileInputStream(path);
			dto = new byte[input.available()];
			input.read(dto);
		} catch (FileNotFoundException e) {
			log.info("导出文件出错" + e.getLocalizedMessage());
		} catch (IOException e) {
			log.info("导出文件出错" + e.getLocalizedMessage());
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					log.info("导出文件出错" + e.getLocalizedMessage());
				}
				input = null;
			}
		}
		return dto;
	}

}
