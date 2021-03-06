package br.com.hfsframework.admin.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.hfsframework.admin.client.domain.AdmUserProfileDTO;
import br.com.hfsframework.base.IBaseToDTO;

@Entity
@Table(name = "ADM_USER_PROFILE")
@NamedQueries({
	@NamedQuery(name = "AdmUserProfile.deleteByProfile", query = "DELETE FROM AdmUserProfile fp WHERE fp.id.profileSeq = ?1"),
	@NamedQuery(name = "AdmUserProfile.deleteByCodUser", query = "DELETE FROM AdmUserProfile fp WHERE fp.id.userSeq = ?1")
})	
public class AdmUserProfile implements Serializable, IBaseToDTO<AdmUserProfileDTO> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@EmbeddedId
	private AdmUserProfilePK id;

	/* The adm profile. */
	// bi-directional many-to-one association to AdmProfile
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "USP_PRF_SEQ", nullable=false, insertable = false, updatable = false)
	private AdmProfile admProfile;

	/**
	 * Instantiates a new adm cargo profile.
	 */
	public AdmUserProfile() {
		limpar();
	}
	
	/**
	 * Limpar.
	 */
	public void limpar(){
		this.id = new AdmUserProfilePK();
		//this.admProfile = new AdmProfile();
	}

	/**
	 * Pega o the id.
	 *
	 * @return o the id
	 */
	public AdmUserProfilePK getId() {
		return this.id;
	}

	/**
	 * Atribui o the id.
	 *
	 * @param id
	 *            o novo the id
	 */
	public void setId(AdmUserProfilePK id) {
		this.id = id;
	}

	/*
	 * Pega o the adm profile.
	 *
	 * @return o the adm profile
	 */
	public AdmProfile getAdmProfile() {
		return this.admProfile;
	}

	/**
	 * Atribui o the adm profile.
	 *
	 * @param admProfile
	 *            o novo the adm profile
	 */
	public void setAdmProfile(AdmProfile admProfile) {
		this.admProfile = admProfile;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdmUserProfile other = (AdmUserProfile) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public AdmUserProfileDTO toDTO() {
		AdmUserProfileDTO dto = new AdmUserProfileDTO();
		dto.setId(Long.parseLong(Integer.toString(id.hashCode())));
		dto.setUserSeq(id.getUserSeq());
		dto.setProfileSeq(id.getProfileSeq());
		dto.setAdmProfile(admProfile.toDTO());
		
		return dto;
	}
}