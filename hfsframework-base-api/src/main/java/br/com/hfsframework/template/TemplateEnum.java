/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.template;

// TODO: Auto-generated Javadoc
/**
 * The Enum TemplateEnum.
 */
public enum TemplateEnum {
	
	/** The Resource. */
	Resource("resource", "Resource", "Resource Suporte"),
	
	/** The Repository. */
	IRepository("domain", "Repository", "Data Access Object"),
	
	Formatter("serializer", "Formatter", "Formatter"),
	
	Deserializer("deserializer", "Deserializer", "Deserializer"),
	
	ListSerializer("serializer", "ListSerializer", "List Serializer"),
	
	Service("service", "Service", "Service Controller"),
	
	IService("service", "Service", "IService Controller"),
	
	RestController("controller", "RestController", "Rest Controller"),
	
	RestClient("client", "RestClient", "Rest Client"),
	
	Controller("controller", "Controller", "Server Controller"),
	
	ViewController("view", "Controller", "Controller"),
	
	/** The Rel MB. */
	RelController("view", "RelController", "Controller de Relatório"),
	
	/** The Converter. */
	Converter("converters", "Converter", "Converter"),

	/** The list. */
	list("", "list", "listar página thymeleaf"),
	
	/** The edit. */
	edit("", "edit", "editar página thymeleaf"),

	JSPList("", "list", "listar página jsp"),
	
	JSPEdit("", "edit", "editar página jsp"),

	JSList("", "list", "listar javascript"),
	
	JSEdit("", "edit", "editar javascript"),

	/** The report. */
	report("", "report", "reportar página"),
	
	/** The portrait. */
	portrait("relatorios", "Portrait", "Relatório de Orientação Retrato"),
	
	/** The paisagem. */
	landscape("relatorios", "Landscape", "Relatório de Orientação Paisagem"),
	
	ServiceTest("test", "ServiceTest", "Service Test"),
	
	RestTest("test", "RestTest", "Rest Test");
	
	/** The diretorio. */
	private String diretorio;
	
	/** The tipo. */
	private String tipo;
	
	/** The descricao. */
	private String descricao;
	
	/**
	 * Instantiates a new template enum.
	 *
	 * @param diretorio
	 *            the diretorio
	 * @param tipo
	 *            the tipo
	 * @param descricao
	 *            the descricao
	 */
	private TemplateEnum(String diretorio, String tipo, String descricao){
		this.diretorio = diretorio;
		this.tipo = tipo;
		this.descricao = descricao;
	}

	/**
	 * Pega o the diretorio.
	 *
	 * @return o the diretorio
	 */
	public String getDiretorio() {
		return diretorio;
	}

	/**
	 * Pega o the tipo.
	 *
	 * @return o the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Pega o the descricao.
	 *
	 * @return o the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
}
