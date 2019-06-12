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

import br.com.hfsframework.admin.client.domain.VwAdmLogValueDTO;
import br.com.hfsframework.base.IBaseToDTO;

@Entity
@Table(name = "VW_ADM_LOG_VALUE")
@NamedQueries({
	@NamedQuery(name = "VwAdmLogValue.findByFilters", query = "SELECT DISTINCT v FROM VwAdmLogValue v "
			+ "WHERE v.user = ?1 AND v.dateNumber = ?2 "
			+ "AND v.operation = ?3  AND v.ip = ?4 "
			+ "AND v.entity = ?5 AND v.mtable = ?6 AND v.key = ?7 "
			+ "ORDER BY v.dateNumber DESC, v.mcolumn ASC")	
})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class VwAdmLogValue implements Serializable, IBaseToDTO<VwAdmLogValueDTO> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name = "id")
	private Long id;

	/** The user. */
	@Column(name = "user")
	private String user;

	/** The date. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date")
	private Date date;

	/** The date numero. */
	@Column(name = "datenumber")
	private Long dateNumber;

	/** The operation. */
	@Column(name = "operation")
	private String operation;

	/** The ip. */
	@Column(name = "ip")
	private String ip;

	/** The entity. */
	@Column(name = "entity")
	private String entity;

	/** The mtable. */
	@Column(name = "mtable")
	private String mtable;

	/** The key. */
	@Column(name = "key")
	private String key;

	/** The mcolumn. */
	@Column(name = "mcolumn")
	private String mcolumn;

	/** The value anterior. */
	@Column(name = "previousvalue")
	private String previousValue;

	/** The value. */
	@Column(name = "value")
	private String value;

	/**
	 * Instantiates a new vw adm log.
	 */
	public VwAdmLogValue() {
		super();
		limpar();
	}
		

	/**
	 * Instantiates a new vw adm log value.
	 *
	 * @param id the id
	 * @param user the user
	 * @param date the date
	 * @param dateNumber the date numero
	 * @param operation the operation
	 * @param ip the ip
	 * @param entity the entity
	 * @param mtable the mtable
	 * @param key the key
	 * @param mcolumn the mcolumn
	 * @param previousValue the value anterior
	 * @param value the value
	 */
	public VwAdmLogValue(Long id, String user, Date date, String operation, String ip, String entity, String mtable, 
			String key, Long dateNumber, String mcolumn, String previousValue, String value) {
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
		this.mcolumn = mcolumn;
		this.previousValue = previousValue;
		this.value = value;
	}


	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = 0L;
		this.user = "";
		this.date = new Date();
		this.operation = "";
		this.ip = "";
		this.entity = "";
		this.mtable = "";
		this.key = "";
		this.dateNumber = 0L;
		this.mcolumn = "";
		this.previousValue = "";
		this.value = "";
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
	 * Pega o the date.
	 *
	 * @return o the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Atribui o the date.
	 *
	 * @param date
	 *            o novo the date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Pega o the date numero.
	 *
	 * @return o the date numero
	 */
	public Long getDateNumber() {
		return dateNumber;
	}

	/**
	 * Atribui o the date numero.
	 *
	 * @param dateNumber
	 *            o novo the date numero
	 */
	public void setDateNumber(Long dateNumber) {
		this.dateNumber = dateNumber;
	}

	/**
	 * Pega o the operation.
	 *
	 * @return o the operation
	 */
	public String getOperation() {
		return operation;
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
	 * Pega o the entity.
	 *
	 * @return o the entity
	 */
	public String getEntity() {
		return entity;
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
		return mtable;
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
		return key;
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
	 * Pega o the mcolumn.
	 *
	 * @return o the mcolumn
	 */
	public String getMcolumn() {
		return mcolumn;
	}

	/**
	 * Atribui o the mcolumn.
	 *
	 * @param mcolumn
	 *            o novo the mcolumn
	 */
	public void setMcolumn(String mcolumn) {
		this.mcolumn = mcolumn;
	}

	/**
	 * Pega o the value anterior.
	 *
	 * @return o the value anterior
	 */
	public String getPreviousValue() {
		return previousValue;
	}

	/**
	 * Atribui o the value anterior.
	 *
	 * @param previousValue
	 *            o novo the value anterior
	 */
	public void setPreviousValue(String previousValue) {
		this.previousValue = previousValue;
	}

	/**
	 * Pega o the value.
	 *
	 * @return o the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Atribui o the value.
	 *
	 * @param value
	 *            o novo the value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Checks if is alterado.
	 *
	 * @return true, if is alterado
	 */
	public boolean isChanged() {
		return ObjectUtils.compare(this.value, this.previousValue) != 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "mcolumn=" + getMcolumn() + ", previousValue=" + previousValue + ", value=" + value;
	}
	
	public VwAdmLogValueDTO toDTO() {
		VwAdmLogValueDTO dto = new VwAdmLogValueDTO();
		dto.setId(id);
		dto.setUser(user);
		dto.setDate(date);
		dto.setDateNumber(dateNumber);
		dto.setOperation(operation);
		dto.setIp(ip);
		dto.setEntity(entity);
		dto.setMtable(mtable);
		dto.setKey(key);
		dto.setMcolumn(mcolumn);
		dto.setPreviousValue(previousValue);
		dto.setValue(value);
		
		return dto;
	}
	
}
