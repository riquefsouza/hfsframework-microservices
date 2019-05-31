package br.com.hfsframework.oauth.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.hfsframework.oauth.serializer.RoleListSerializer;

@Entity 
@Table(name = "AUT_USER")
public class User implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	@Id	
	@GenericGenerator(name = "AUT_USER_ID_GENERATOR",
	strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
    parameters = {
    	@Parameter(name = "sequence_name", value = "AUT_USER_SEQ"),
        @Parameter(name = "initial_value", value = "1"),
        @Parameter(name = "increment_size", value = "1")
	})		
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUT_USER_ID_GENERATOR")
	private Long id; 
	
	@NotBlank
	@Size(min=4, max=64)
	@Column(nullable = false, length = 64)
	private String username;
	
	@NotBlank	
	@Size(min=4, max=64)
	@Column(nullable = false, length = 64)
	private String password;

	@NotBlank
	@Email
	@Size(min=16, max=100)
	@Column(nullable = false, length = 100)
	private String email;
	
	@NotBlank
	@URL
	@Size(min=8, max=255)
	@Column(name = "url_photo", nullable = false, length = 255)
	private String urlPhoto;
	
	@JsonSerialize(using = RoleListSerializer.class)
	//@OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Role> roles; 

	public User() {
		super();
		this.roles = new ArrayList<Role>();
	} 

	public User(String username, String password, String email, String urlPhoto, List<Role> roles) { 
		this.username = username; 
		this.password = password; 
		this.email = email;
		this.urlPhoto = urlPhoto;
		this.roles = roles; 
	} 

	public User(String username, String password, String email, String urlPhoto) { 
		this.username = username; 
		this.password = password; 
		this.email = email;
		this.urlPhoto = urlPhoto;
		this.roles = new ArrayList<Role>();
	} 
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrlPhoto() {
		return urlPhoto;
	}

	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", urlPhoto=" + urlPhoto + ", roles=" + roles + "]";
	} 
	
}
