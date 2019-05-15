package br.com.hfsframework.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ADM_ROLE")
public class Role implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="ADM_ROLE_ID_GENERATOR", sequenceName="ADM_ROLE_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADM_ROLE_ID_GENERATOR")
	private Long id;

	private String name;

	Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", name=" + name + "]";
	}
		
}
