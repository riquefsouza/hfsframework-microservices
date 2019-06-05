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
	@NamedQuery(name = "VwAdmLog.findEntities", query = "SELECT DISTINCT v.entity FROM VwAdmLog v ORDER BY v.entity"),
	@NamedQuery(name = "VwAdmLog.findByFiltros", query = "SELECT DISTINCT v FROM VwAdmLog v "
			+ "WHERE (v.entity = ?1 OR ?1 IS NULL) AND (v.user = ?2  OR ?2 IS NULL) "
			+ "AND (v.ip = ?3  OR ?3 IS NULL) AND (v.operation = ?4 OR ?4 IS NULL) "
			+ "AND (v.key = ?5 OR ?5 IS NULL) AND (v.dateNumber >= ?6 OR ?6 IS NULL) "
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
	
	/** The date. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Column(name = "datenumber")
	private Long dateNumber;
	
	/** The operation. */
	private String operation;
	
	/** The ip. */
	private String ip;
	
	/** The entity. */
	private String entity;
	
	/** The mtable. */
	private String mtable;
	
	/** The key. */
	private String key;
	
	/** The date inicio. */
	@Transient
	private Date startDate;
	
	/** The date fim. */
	@Transient
	private Date endDate;
	
	/** The key value. */
	@Transient
	private String keyValue;
	
	/** The lista log value. */
	@Transient
	private List<VwAdmLogValue> listaLogValue;

	/**
	 * Instantiates a new vw adm log.
	 */
	public VwAdmLog() {
		super();
		this.listaLogValue = new ArrayList<VwAdmLogValue>();
		limpar();
	}
			
	/**
	 * Instantiates a new vw adm log.
	 *
	 * @param id the id
	 * @param user the user
	 * @param date the date
	 * @param operation the operation
	 * @param ip the ip
	 * @param entity the entity
	 * @param mtable the mtable
	 * @param key the key
	 * @param dateNumber the date numero
	 */
	public VwAdmLog(Long id, String user, Date date, String operation, String ip, 
			String entity, String mtable, String key, Long dateNumber) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.dateNumber = dateNumber;
		this.operation = operation;
		this.ip = ip;
		this.entity = entity;
		this.mtable = mtable;
		this.key = key;
	}

	/**
	 * Limpar.
	 */
	public void limpar(){
		this.id = null;
		this.user = null;
		this.date = null;
		this.operation = null;
		this.ip = null;
		this.entity = null;
		this.mtable = null;
		this.key = null;
		
		this.keyValue = null;
		this.startDate = null;
		this.endDate = null;
		this.listaLogValue.clear();
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
	 * Pega o the date.
	 *
	 * @return o the date
	 */
	public Date getData() {
		return this.date;
	}

	/**
	 * Atribui o the date.
	 *
	 * @param date
	 *            o novo the date
	 */
	public void setData(Date date) {
		this.date = date;
	}

	/**
	 * Pega o the operation.
	 *
	 * @return o the operation
	 */
	public String getOperation() {
		return this.operation;
	}

	/**
	 * Atribui o the operation.
	 *
	 * @param operation
	 *            o novo the operation
	 */
	public void setOperation(String operation) {
		this.operation = operation;
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
	 * Pega o the entity.
	 *
	 * @return o the entity
	 */
	public String getEntity() {
		return this.entity;
	}

	/**
	 * Atribui o the entity.
	 *
	 * @param entity
	 *            o novo the entity
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}

	/**
	 * Pega o the mtable.
	 *
	 * @return o the mtable
	 */
	public String getMtable() {
		return this.mtable;
	}

	/**
	 * Atribui o the mtable.
	 *
	 * @param mtable
	 *            o novo the mtable
	 */
	public void setMtable(String mtable) {
		this.mtable = mtable;
	}

	/**
	 * Pega o the key.
	 *
	 * @return o the key
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * Atribui o the key.
	 *
	 * @param key
	 *            o novo the key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Pega o the date inicio.
	 *
	 * @return o the date inicio
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Atribui o the date inicio.
	 *
	 * @param startDate
	 *            o novo the date inicio
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Pega o the date fim.
	 *
	 * @return o the date fim
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Atribui o the date fim.
	 *
	 * @param endDate
	 *            o novo the date fim
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Pega o the key value.
	 *
	 * @return o the key value
	 */
	public String getKeyValue() {
		return keyValue;
	}

	/**
	 * Atribui o the key value.
	 *
	 * @param keyValue
	 *            o novo the key value
	 */
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	/**
	 * Gets the operation texto.
	 *
	 * @return the operation texto
	 */
	public String getOperationTexto() {
		if ((operation != null) && (!operation.isEmpty())) {
			if (operation.equals("I")) {
				return "Inclusão";
			}
			if (operation.equals("U")) {
				return "Alteração";
			}
			return "Exclusão";
		}
		return "";
	}

	/**
	 * Gets the date numero.
	 *
	 * @return the date numero
	 */
	public Long getDataNumero() {
		return dateNumber;
	}

	/**
	 * Sets the date numero.
	 *
	 * @param dateNumber
	 *            the new date numero
	 */
	public void setDataNumero(Long dateNumber) {
		this.dateNumber = dateNumber;
	}

	/**
	 * Gets the lista log value.
	 *
	 * @return the lista log value
	 */
	public List<VwAdmLogValue> getListaLogValue() {
		return listaLogValue;
	}

	/**
	 * Sets the lista log value.
	 *
	 * @param listaLogValue
	 *            the new lista log value
	 */
	public void setListaLogValue(List<VwAdmLogValue> listaLogValue) {
		this.listaLogValue = listaLogValue;
	}

	/**
	 * Sets the date formated.
	 *
	 * @param dateFormated the new date formated
	 */
	public void setDataFormated(String dateFormated) {
		this.date = CalendarUtil.toDate(dateFormated, CalendarUtil.DATA_HORA_PADRAO);
	}
	
	/**
	 * Gets the date formated.
	 *
	 * @return the date formated
	 */
	public String getDataFormated() {
		return CalendarUtil.Formatar(this.date, CalendarUtil.DATA_HORA_PADRAO);
	}
	
	/**
	 * Sets the date inicio formated.
	 *
	 * @param startDateFormated the new date inicio formated
	 */
	public void setStartDateFormated(String startDateFormated) {
		this.startDate = CalendarUtil.toDate(startDateFormated, CalendarUtil.DATA_PADRAO);
	}
	
	/**
	 * Gets the date inicio formated.
	 *
	 * @return the date inicio formated
	 */
	public String getStartDateFormated() {
		return CalendarUtil.Formatar(this.startDate, CalendarUtil.DATA_PADRAO);
	}
	
	/**
	 * Sets the date fim formated.
	 *
	 * @param endDateFormated the new date fim formated
	 */
	public void setEndDateFormated(String endDateFormated) {
		this.endDate = CalendarUtil.toDate(endDateFormated, CalendarUtil.DATA_PADRAO);
	}
	
	/**
	 * Gets the date fim formated.
	 *
	 * @return the date fim formated
	 */
	public String getEndDateFormated() {
		return CalendarUtil.Formatar(this.endDate, CalendarUtil.DATA_PADRAO);
	}
	
}
