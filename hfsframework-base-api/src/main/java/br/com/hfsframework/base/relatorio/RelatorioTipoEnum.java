/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.base.relatorio;

// TODO: Auto-generated Javadoc
/**
 * The Enum RelatorioTipoEnum.
 */
public enum RelatorioTipoEnum {
	
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
	
	/** The grupo. */
	private String grupo;
	
	/** The tipo conteudo. */
	private String tipoConteudo;

	/** The descricao. */
	private String descricao;

	/**
	 * Instantiates a new relatorio tipo enum.
	 *
	 * @param grupo the grupo
	 * @param tipoConteudo            the tipo conteudo
	 * @param descricao            the descricao
	 */
	private RelatorioTipoEnum(String grupo, String tipoConteudo, String descricao) {
		this.grupo = grupo;
		this.tipoConteudo = tipoConteudo;
		this.descricao = descricao;
	}
	
	/**
	 * Gets the grupo.
	 *
	 * @return the grupo
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * Pega o the tipo conteudo.
	 *
	 * @return o the tipo conteudo
	 */
	public String getTipoConteudo() {
		return tipoConteudo;
	}

	/**
	 * Pega o the descricao.
	 *
	 * @return o the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Gets the grupos.
	 *
	 * @return the grupos
	 */
	public static String[] getGrupos(){
		return new String[] {"Documentos", "Planilhas", "Texto puro", "Outros"};
	}
}
