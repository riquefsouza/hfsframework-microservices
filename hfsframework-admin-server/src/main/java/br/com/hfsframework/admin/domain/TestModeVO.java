package br.com.hfsframework.admin.domain;

import java.io.Serializable;

public class TestModeVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The ativo. */
	private Boolean ativo;
	
	/** The login. */
	private String login;
	
	/** The setor. */
	private String setor;
	
	/** The cargo. */
	private Long cargo;
	
	/**
	 * Instantiates a new modo teste VO.
	 */
	public TestModeVO() {
		super();
		this.ativo = false;
		this.login = "";
		this.setor = "";
		this.cargo = -1L;
	}

	/**
	 * Instantiates a new modo teste VO.
	 *
	 * @param ativo the ativo
	 * @param login the login
	 * @param setor the setor
	 * @param cargo the cargo
	 */
	public TestModeVO(Boolean ativo, String login, String setor, Long cargo) {
		super();
		this.ativo = ativo;
		this.login = login;
		this.setor = setor;
		this.cargo = cargo;
	}

	/**
	 * Gets the ativo.
	 *
	 * @return the ativo
	 */
	public Boolean getAtivo() {
		return ativo;
	}

	/**
	 * Sets the ativo.
	 *
	 * @param ativo the new ativo
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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
	 * Gets the setor.
	 *
	 * @return the setor
	 */
	public String getSetor() {
		return setor;
	}

	/**
	 * Sets the setor.
	 *
	 * @param setor the new setor
	 */
	public void setSetor(String setor) {
		this.setor = setor;
	}

	/**
	 * Gets the cargo.
	 *
	 * @return the cargo
	 */
	public Long getCargo() {
		return cargo;
	}

	/**
	 * Sets the cargo.
	 *
	 * @param cargo the new cargo
	 */
	public void setCargo(Long cargo) {
		this.cargo = cargo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((setor == null) ? 0 : setor.hashCode());
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
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (cargo == null) {
			if (other.cargo != null)
				return false;
		} else if (!cargo.equals(other.cargo))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (setor == null) {
			if (other.setor != null)
				return false;
		} else if (!setor.equals(other.setor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ModoTesteVO [ativo=" + ativo + ", login=" + login + ", setor=" + setor + ", cargo=" + cargo + "]";
	}

	
}
