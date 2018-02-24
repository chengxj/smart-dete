package com.ultrapower.dete.server.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ultrapower.dete.server.service.download.DownloadService;
import com.ultrapower.dete.server.service.download.IDownload;

@Path("/resources")
public class WebController {
	
	protected IDownload downloadService = new DownloadService();
		
	@GET
	@Path("/{pathName}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public byte[] getResources(@PathParam("pathName") String pathName) {	
		return downloadService.download(pathName);
	}

}
