package br.com.hfsframework.admin.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.hfsframework.util.CalendarUtil;

@Entity
@Table(name = "VW_ADM_LOG")
@NamedQueries({
	@NamedQuery(name = "VwAdmLog.findEntidades", query = "SELECT DISTINCT v.entidade FROM VwAdmLog v ORDER BY v.entidade"),
	@NamedQuery(name = "VwAdmLog.findByFiltros", query = "SELECT DISTINCT v FROM VwAdmLog v "
			+ "WHERE (v.entidade = ?1 OR ?1 IS NULL) AND (v.user = ?2  OR ?2 IS NULL) "
			+ "AND (v.ip = ?3  OR ?3 IS NULL) AND (v.operacao = ?4 OR ?4 IS NULL) "
			+ "AND (v.chave = ?5 OR ?5 IS NULL) AND (v.dateNumber >= ?6 OR ?6 IS NULL) "
			+ "AND (v.dateNumber <= ?7 OR ?7 IS NULL) "
			+ "ORDER BY v.id DESC")	
})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class VwAdmLog implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name = "id")
	private Long id;
	
	/** The user. */
	private String user;
	
	/** The data. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@Column(name = "datenumber")
	private Long dateNumber;
	
	/** The operacao. */
	private String operacao;
	
	/** The ip. */
	private String ip;
	
	/** The entidade. */
	private String entidade;
	
	/** The tabela. */
	private String tabela;
	
	/** The chave. */
	private String chave;
	
	/** The data inicio. */
	@Transient
	private Date dataInicio;
	
	/** The data fim. */
	@Transient
	private Date dataFim;
	
	/** The chave valor. */
	@Transient
	private String chaveValor;
	
	/** The lista log valor. */
	@Transient
	private List<VwAdmLogValue> listaLogValor;

	/**
	 * Instantiates a new vw adm log.
	 */
	public VwAdmLog() {
		super();
		this.listaLogValor = new ArrayList<VwAdmLogValue>();
		limpar();
	}
			
	/**
	 * Instantiates a new vw adm log.
	 *
	 * @param id the id
	 * @param user the user
	 * @param data the data
	 * @param operacao the operacao
	 * @param ip the ip
	 * @param entidade the entidade
	 * @param tabela the tabela
	 * @param chave the chave
	 * @param dateNumber the data numero
	 */
	public VwAdmLog(Long id, String user, Date data, String operacao, String ip, 
			String entidade, String tabela, String chave, Long dateNumber) {
		super();
		this.id = id;
		this.user = user;
		this.data = data;
		this.dateNumber = dateNumber;
		this.operacao = operacao;
		this.ip = ip;
		this.entidade = entidade;
		this.tabela = tabela;
		this.chave = chave;
	}

	/**
	 * Limpar.
	 */
	public void limpar(){
		this.id = null;
		this.user = null;
		this.data = null;
		this.operacao = null;
		this.ip = null;
		this.entidade = null;
		this.tabela = null;
		this.chave = null;
		
		this.chaveValor = null;
		this.dataInicio = null;
		this.dataFim = null;
		this.listaLogValor.clear();
	}

	/**
	 * Pega o the id.
	 *
	 * @return o the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Atribui o the id.
	 *
	 * @param id
	 *            o novo the id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Pega o the user.
	 *
	 * @return o the user
	 */
	public String getUsuario() {
		return this.user;
	}

	/**
	 * Atribui o the user.
	 *
	 * @param user
	 *            o novo the user
	 */
	public void setUsuario(String user) {
		this.user = user;
	}

	/**
	 * Pega o the data.
	 *
	 * @return o the data
	 */
	public Date getData() {
		return this.data;
	}

	/**
	 * Atribui o the data.
	 *
	 * @param data
	 *            o novo the data
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * Pega o the operacao.
	 *
	 * @return o the operacao
	 */
	public String getOperacao() {
		return this.operacao;
	}

	/**
	 * Atribui o the operacao.
	 *
	 * @param operacao
	 *            o novo the operacao
	 */
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	/**
	 * Pega o the ip.
	 *
	 * @return o the ip
	 */
	public String getIp() {
		return this.ip;
	}

	/**
	 * Atribui o the ip.
	 *
	 * @param ip
	 *            o novo the ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Pega o the entidade.
	 *
	 * @return o the entidade
	 */
	public String getEntidade() {
		return this.entidade;
	}

	/**
	 * Atribui o the entidade.
	 *
	 * @param entidade
	 *            o novo the entidade
	 */
	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}

	/**
	 * Pega o the tabela.
	 *
	 * @return o the tabela
	 */
	public String getTabela() {
		return this.tabela;
	}

	/**
	 * Atribui o the tabela.
	 *
	 * @param tabela
	 *            o novo the tabela
	 */
	public void setTabela(String tabela) {
		this.tabela = tabela;
	}

	/**
	 * Pega o the chave.
	 *
	 * @return o the chave
	 */
	public String getChave() {
		return this.chave;
	}

	/**
	 * Atribui o the chave.
	 *
	 * @param chave
	 *            o novo the chave
	 */
	public void setChave(String chave) {
		this.chave = chave;
	}

	/**
	 * Pega o the data inicio.
	 *
	 * @return o the data inicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * Atribui o the data inicio.
	 *
	 * @param dataInicio
	 *            o novo the data inicio
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * Pega o the data fim.
	 *
	 * @return o the data fim
	 */
	public Date getDataFim() {
		return dataFim;
	}

	/**
	 * Atribui o the data fim.
	 *
	 * @param dataFim
	 *            o novo the data fim
	 */
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	/**
	 * Pega o the chave valor.
	 *
	 * @return o the chave valor
	 */
	public String getChaveValor() {
		return chaveValor;
	}

	/**
	 * Atribui o the chave valor.
	 *
	 * @param chaveValor
	 *            o novo the chave valor
	 */
	public void setChaveValor(String chaveValor) {
		this.chaveValor = chaveValor;
	}

	/**
	 * Gets the operacao texto.
	 *
	 * @return the operacao texto
	 */
	public String getOperacaoTexto() {
		if ((operacao != null) && (!operacao.isEmpty())) {
			if (operacao.equals("I")) {
				return "Inclusão";
			}
			if (operacao.equals("U")) {
				return "Alteração";
			}
			return "Exclusão";
		}
		return "";
	}

	/**
	 * Gets the data numero.
	 *
	 * @return the data numero
	 */
	public Long getDataNumero() {
		return dateNumber;
	}

	/**
	 * Sets the data numero.
	 *
	 * @param dateNumber
	 *            the new data numero
	 */
	public void setDataNumero(Long dateNumber) {
		this.dateNumber = dateNumber;
	}

	/**
	 * Gets the lista log valor.
	 *
	 * @return the lista log valor
	 */
	public List<VwAdmLogValue> getListaLogValor() {
		return listaLogValor;
	}

	/**
	 * Sets the lista log valor.
	 *
	 * @param listaLogValor
	 *            the new lista log valor
	 */
	public void setListaLogValor(List<VwAdmLogValue> listaLogValor) {
		this.listaLogValor = listaLogValor;
	}

	/**
	 * Sets the data formatada.
	 *
	 * @param dataFormatada the new data formatada
	 */
	public void setDataFormatada(String dataFormatada) {
		this.data = CalendarUtil.toDate(dataFormatada, CalendarUtil.DATA_HORA_PADRAO);
	}
	
	/**
	 * Gets the data formatada.
	 *
	 * @return the data formatada
	 */
	public String getDataFormatada() {
		return CalendarUtil.Formatar(this.data, CalendarUtil.DATA_HORA_PADRAO);
	}
	
	/**
	 * Sets the data inicio formatada.
	 *
	 * @param dataInicioFormatada the new data inicio formatada
	 */
	public void setDataInicioFormatada(String dataInicioFormatada) {
		this.dataInicio = CalendarUtil.toDate(dataInicioFormatada, CalendarUtil.DATA_PADRAO);
	}
	
	/**
	 * Gets the data inicio formatada.
	 *
	 * @return the data inicio formatada
	 */
	public String getDataInicioFormatada() {
		return CalendarUtil.Formatar(this.dataInicio, CalendarUtil.DATA_PADRAO);
	}
	
	/**
	 * Sets the data fim formatada.
	 *
	 * @param dataFimFormatada the new data fim formatada
	 */
	public void setDataFimFormatada(String dataFimFormatada) {
		this.dataFim = CalendarUtil.toDate(dataFimFormatada, CalendarUtil.DATA_PADRAO);
	}
	
	/**
	 * Gets the data fim formatada.
	 *
	 * @return the data fim formatada
	 */
	public String getDataFimFormatada() {
		return CalendarUtil.Formatar(this.dataFim, CalendarUtil.DATA_PADRAO);
	}
	
}
