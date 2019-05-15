package br.com.hfsframework.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity 
@Table(name = "ADM_USER")
public class User implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	@Id 
	@SequenceGenerator(name="ADM_USER_ID_GENERATOR", sequenceName="ADM_USER_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADM_USER_ID_GENERATOR")
	private Long id; 
	
	private String username;
	
	private String password;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL) 
	private List<Role> roles; 

	User() { 
	} 

	public User(String username, String password, List<Role> roles) { 
		this.username = username; 
		this.password = password; 
		this.roles = roles; 
	} 

	public String getUsername() { 
		return username; 
	} 

	public void setUsername(String username) { 
		this.username = username; 
	} 

	public String getPassword() { 
		return password; 
	} 

	public void setPassword(String password) { 
		this.password = password; 
	} 

	public List<Role> getRoles() { 
		return roles; 
	} 

	public void setRoles(List<Role> roles) { 
		this.roles = roles; 
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", roles=" + roles + "]";
	} 
		
}
