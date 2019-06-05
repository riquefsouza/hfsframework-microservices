package br.com.hfsframework.admin.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.ObjectUtils;

@Entity
@Table(name = "VW_ADM_LOG_VALOR")
@NamedQueries({
	@NamedQuery(name = "VwAdmLogValor.findByFiltros", query = "SELECT DISTINCT v FROM VwAdmLogValor v "
			+ "WHERE v.user = ?1 AND v.dataNumero = ?2 "
			+ "AND v.operacao = ?3  AND v.ip = ?4 "
			+ "AND v.entidade = ?5 AND v.tabela = ?6 AND v.chave = ?7 "
			+ "ORDER BY v.dataNumero DESC, v.coluna ASC")	
})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class VwAdmLogValue implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name = "id")
	private Long id;

	/** The user. */
	@Column(name = "user")
	private String user;

	/** The data. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data")
	private Date data;

	/** The data numero. */
	@Column(name = "datanumero")
	private Long dataNumero;

	/** The operacao. */
	@Column(name = "operacao")
	private String operacao;

	/** The ip. */
	@Column(name = "ip")
	private String ip;

	/** The entidade. */
	@Column(name = "entidade")
	private String entidade;

	/** The tabela. */
	@Column(name = "tabela")
	private String tabela;

	/** The chave. */
	@Column(name = "chave")
	private String chave;

	/** The coluna. */
	@Column(name = "coluna")
	private String coluna;

	/** The valor anterior. */
	@Column(name = "valoranterior")
	private String valorAnterior;

	/** The valor. */
	@Column(name = "valor")
	private String valor;

	/**
	 * Instantiates a new vw adm log.
	 */
	public VwAdmLogValue() {
		super();
		limpar();
	}
		

	/**
	 * Instantiates a new vw adm log valor.
	 *
	 * @param id the id
	 * @param user the user
	 * @param data the data
	 * @param dataNumero the data numero
	 * @param operacao the operacao
	 * @param ip the ip
	 * @param entidade the entidade
	 * @param tabela the tabela
	 * @param chave the chave
	 * @param coluna the coluna
	 * @param valorAnterior the valor anterior
	 * @param valor the valor
	 */
	public VwAdmLogValue(Long id, String user, Date data, String operacao, String ip, String entidade, String tabela, 
			String chave, Long dataNumero, String coluna, String valorAnterior, String valor) {
		super();
		this.id = id;
		this.user = user;
		this.data = data;
		this.dataNumero = dataNumero;
		this.operacao = operacao;
		this.ip = ip;
		this.entidade = entidade;
		this.tabela = tabela;
		this.chave = chave;
		this.coluna = coluna;
		this.valorAnterior = valorAnterior;
		this.valor = valor;
	}


	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = 0L;
		this.user = "";
		this.data = new Date();
		this.operacao = "";
		this.ip = "";
		this.entidade = "";
		this.tabela = "";
		this.chave = "";
		this.dataNumero = 0L;
		this.coluna = "";
		this.valorAnterior = "";
		this.valor = "";
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
		return user;
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
		return data;
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
	 * Pega o the data numero.
	 *
	 * @return o the data numero
	 */
	public Long getDataNumero() {
		return dataNumero;
	}

	/**
	 * Atribui o the data numero.
	 *
	 * @param dataNumero
	 *            o novo the data numero
	 */
	public void setDataNumero(Long dataNumero) {
		this.dataNumero = dataNumero;
	}

	/**
	 * Pega o the operacao.
	 *
	 * @return o the operacao
	 */
	public String getOperacao() {
		return operacao;
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
		return ip;
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
		return entidade;
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
		return tabela;
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
		return chave;
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
	 * Pega o the coluna.
	 *
	 * @return o the coluna
	 */
	public String getColuna() {
		return coluna;
	}

	/**
	 * Atribui o the coluna.
	 *
	 * @param coluna
	 *            o novo the coluna
	 */
	public void setColuna(String coluna) {
		this.coluna = coluna;
	}

	/**
	 * Pega o the valor anterior.
	 *
	 * @return o the valor anterior
	 */
	public String getValorAnterior() {
		return valorAnterior;
	}

	/**
	 * Atribui o the valor anterior.
	 *
	 * @param valorAnterior
	 *            o novo the valor anterior
	 */
	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	/**
	 * Pega o the valor.
	 *
	 * @return o the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Atribui o the valor.
	 *
	 * @param valor
	 *            o novo the valor
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * Checks if is alterado.
	 *
	 * @return true, if is alterado
	 */
	public boolean isAlterado() {
		return ObjectUtils.compare(this.valor, this.valorAnterior) != 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "coluna=" + getColuna() + ", valorAnterior=" + valorAnterior + ", valor=" + valor;
	}
}
