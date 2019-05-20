package br.com.hfsframework.util.copyright;

public enum CopyrightEnum {
	
	HFSFRAMEWORK_FOR_MICROSERVICES("copyright_hfsframework.txt"), 
	
	DEVELOPED_BY("copyright_developed.txt"),
	
	AUTHORIZATION_SERVER("copyright_authorization_server.txt"),
	
	AUTHORIZATION_CLIENT_WEB("copyright_authorization_client_web.txt"),
	
	ADMINISTRATIVE_SERVER("copyright_administrative_server.txt"),
	
	ADMINISTRATIVE_CLIENT_CONSOLE("copyright_administrative_client_console.txt"),
	
	ADMINISTRATIVE_CLIENT_WEB("copyright_administrative_client_web.txt");

	private String fileName;

	private CopyrightEnum(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

}
