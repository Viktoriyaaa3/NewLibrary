package teoresiGroup.web.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import javax.persistence.ManyToMany;

//@Builder
@Entity

public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="password")
private String password;
	@Column(name="username")
private String username;
//@Builder.Default
	@Column(name="enabled")
private Boolean enabled=true;


@ManyToMany(cascade=CascadeType.MERGE)
@JoinTable(name="Authority", joinColumns= {@JoinColumn(name="user_id", referencedColumnName="id")},
inverseJoinColumns= {@JoinColumn(name="authority_id", referencedColumnName = "id")})
private Set<Authority> authorities;


public Integer getId() {
	return id;
}


public void setId(Integer id) {
	this.id = id;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}


public String getUsername() {
	return username;
}


public void setUsername(String username) {
	this.username = username;
}


public Boolean getEnabled() {
	return enabled;
}


public void setEnabled(Boolean enabled) {
	this.enabled = enabled;
}


public Set<Authority> getAuthorities() {
	return authorities;
}


public void setAuthorities(Set<Authority> authorities) {
	this.authorities = authorities;
}


public User(Integer id, String password, String username, Boolean enabled, Set<Authority> authorities) {
	super();
	this.id = id;
	this.password = password;
	this.username = username;
	this.enabled = enabled;
	this.authorities = authorities;
}


public User() {
	super();
}




}
