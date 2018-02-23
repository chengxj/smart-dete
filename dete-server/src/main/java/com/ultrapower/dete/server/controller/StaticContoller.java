package com.ultrapower.dete.server.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ultrapower.dete.server.service.download.DownloadService;
import com.ultrapower.dete.server.service.download.IDownload;

/**
 * 静态资源访问
 * 暂不支持多级目录访问
 * @author chengxj
 *
 */
@Path("/resources")
public class StaticContoller {
	
	@GET
	@Path("/{pathName}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public byte[] getResources(@PathParam("pathName") String pathName) {		
		IDownload downloadService = new DownloadService();
		return downloadService.download(pathName);
	}

}
