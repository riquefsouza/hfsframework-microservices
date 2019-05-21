/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.base.report;

public enum ReportTypeEnum {
	
	/** The pdf. */
	PDF("Documentos", "application/pdf", "Portable Document Format (.pdf)"),
	
	/** The docx. */
	DOCX("Documentos", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "Microsoft Word XML (.docx)"),
	
	/** The rtf. */
	RTF("Documentos", "application/rtf", "Rich Text Format (.rtf)"),
	
	/** The odt. */
	ODT("Documentos", "application/vnd.oasis.opendocument.text", "OpenDocument Text (.odt)"),

	/** The xlsx. */
	//XLS("Planilhas", "application/vnd.ms-excel", "Microsoft Excel (.xls)"),
	XLSX("Planilhas", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "Microsoft Excel XML (.xlsx)"),
	
	/** The ods. */
	ODS("Planilhas", "application/vnd.oasis.opendocument.spreadsheet", "OpenDocument Spreadsheet (.ods)"),

	/** The csv. */
	CSV("Texto puro", "text/plain", "Valores Separados Por Vírgula (.csv)"),
	
	/** The txt. */
	TXT("Texto puro", "text/plain", "Somente Texto (.txt)"),	 	

	/** The pptx. */
	PPTX("Outros", "application/vnd.openxmlformats-officedocument.presentationml.presentation", "Microsoft Powerpoint XML (.pptx)"),	
	
	/** The html. */
	//HTML("Outros", "text/html", "Linguagem de Marcação de Hipertexto (.html)");
	HTML("Outros", "application/zip", "Linguagem de Marcação de Hipertexto (.html)");
	
	private String group;
	
	private String contentType;

	private String description;

	private ReportTypeEnum(String group, String contentType, String description) {
		this.group = group;
		this.contentType = contentType;
		this.description = description;
	}
	
	public String getGroup() {
		return group;
	}

	public String getContentType() {
		return contentType;
	}

	public String getDescription() {
		return description;
	}

	public static String[] getGroups(){
		return new String[] {"Documentos", "Planilhas", "Texto puro", "Outros"};
	}
}
