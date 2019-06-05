package br.com.hfsframework.admin.domain;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class AdmUserIpPK implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The usuarioSeq. */
	@Column(name = "UIP_USU_SEQ")
	private Long usuarioSeq;

	/** The ip. */
	@Column(name = "UIP_IP")
	private String ip;

	/**
	 * Instantiates a new adm usuario PK.
	 */
	public AdmUserIpPK() {
		super();
		limpar();
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.usuarioSeq = 0L;
		this.ip = "";
	}

	/**
	 * Gets the usuario seq.
	 *
	 * @return the usuario seq
	 */
	public Long getUsuarioSeq() {
		return usuarioSeq;
	}

	/**
	 * Sets the usuario seq.
	 *
	 * @param usuarioSeq
	 *            the new usuario seq
	 */
	public void setUsuarioSeq(Long usuarioSeq) {
		this.usuarioSeq = usuarioSeq;
	}

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return this.ip;
	}

	/**
	 * Sets the ip.
	 *
	 * @param ip
	 *            the new ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AdmUserIpPK)) {
			return false;
		}
		AdmUserIpPK castOther = (AdmUserIpPK) other;
		return (this.usuarioSeq == castOther.usuarioSeq) && this.ip.equals(castOther.ip);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.usuarioSeq ^ (this.usuarioSeq >>> 32)));
		hash = hash * prime + this.ip.hashCode();

		return hash;
	}
}