package br.com.hfsframework.admin.domain;

import java.io.Serializable;

public class TestModeVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The active. */
	private Boolean active;
	
	/** The login. */
	private String login;
	
	/** The sector. */
	private String sector;
	
	/** The custom. */
	private Long custom;
	
	/**
	 * Instantiates a new modo teste VO.
	 */
	public TestModeVO() {
		super();
		this.active = false;
		this.login = "";
		this.sector = "";
		this.custom = -1L;
	}

	/**
	 * Instantiates a new modo teste VO.
	 *
	 * @param active the active
	 * @param login the login
	 * @param sector the sector
	 * @param custom the custom
	 */
	public TestModeVO(Boolean active, String login, String sector, Long custom) {
		super();
		this.active = active;
		this.login = login;
		this.sector = sector;
		this.custom = custom;
	}

	/**
	 * Gets the active.
	 *
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login.
	 *
	 * @param login the new login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Gets the sector.
	 *
	 * @return the sector
	 */
	public String getSector() {
		return sector;
	}

	/**
	 * Sets the sector.
	 *
	 * @param sector the new sector
	 */
	public void setSector(String sector) {
		this.sector = sector;
	}

	/**
	 * Gets the custom.
	 *
	 * @return the custom
	 */
	public Long getCustom() {
		return custom;
	}

	/**
	 * Sets the custom.
	 *
	 * @param custom the new custom
	 */
	public void setCustom(Long custom) {
		this.custom = custom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((custom == null) ? 0 : custom.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((sector == null) ? 0 : sector.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestModeVO other = (TestModeVO) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (custom == null) {
			if (other.custom != null)
				return false;
		} else if (!custom.equals(other.custom))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (sector == null) {
			if (other.sector != null)
				return false;
		} else if (!sector.equals(other.sector))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ModoTesteVO [active=" + active + ", login=" + login + ", sector=" + sector + ", custom=" + custom + "]";
	}

	
}
