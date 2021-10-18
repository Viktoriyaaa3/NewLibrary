package teoresiGroup.web.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="Utenti")
public class UtentiModel implements UserDetails {

	


	final static Logger logger=Logger.getLogger(UtentiModel.class.getName());
	 
	 
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@Column(name="nome")
private String nome;
	@Column(name="cognome")
private String cognome;
	@Column(name="codFiscale")
private String codFiscale;
	@Column(name="dataNascita")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataNascita;
	@Column(name="telefono")
private String telefono;
	@Column(name="email")
private String email;
	
	
	
	/*SERVE PER I RUOLI E AUTENTICAZIONE*/
	@Column(name="password")
	private String password;
	@Column(name="username")
	private String username;
	@Column(name="enabled")
	private String abilitato;
	@Column(name="ruolo")
	private String ruolo;
	
	/*
	 @ManyToMany 
	    @JoinTable( 
	        name = "users_roles", 
	        joinColumns = @JoinColumn(
	          name = "user_id", referencedColumnName = "id"), 
	        inverseJoinColumns = @JoinColumn(
	          name = "role_id", referencedColumnName = "id")) 
	    private Collection<Role> roles;
	*/
	
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public String getAbilitato() {
		return abilitato;
	}
	public void setAbilitato(String abilitato) {
		this.abilitato = abilitato;
	}
/*@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private LibriModel model;
	
	
	
	

public LibriModel getModel() {
		return model;
	}
	public void setModel(LibriModel model) {
		this.model = model;
	}*/
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
	
	public static Logger getLogger() {
		return logger;
	}
	
public LocalDate getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getCognome() {
	return cognome;
}
public void setCognome(String cognome) {
	this.cognome = cognome;
}
public String getCodFiscale() {
	return codFiscale;
}
public void setCodFiscale(String codFiscale) {
	this.codFiscale = codFiscale;
}
public String getTelefono() {
	return telefono;
}
public void setTelefono(String telefono) {
	this.telefono = telefono;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public UtentiModel() {
	super();
}
public UtentiModel(String nome, String cognome, String codFiscale, String telefono, String email) {
	super();
	this.nome = nome;
	this.cognome = cognome;
	this.codFiscale = codFiscale;
	this.telefono = telefono;
	this.email = email;
}
public UtentiModel(int id, String nome, String cognome, String codFiscale, String telefono, String email) {
	super();
	this.id = id;
	this.nome = nome;
	this.cognome = cognome;
	this.codFiscale = codFiscale;
	this.telefono = telefono;
	this.email = email;
}
public UtentiModel(String nome, String cognome, String codFiscale, String telefono, String email, String password,
		String username) {
	super();
	this.nome = nome;
	this.cognome = cognome;
	this.codFiscale = codFiscale;
	this.telefono = telefono;
	this.email = email;
	this.password = password;
	this.username = username;
}

public UtentiModel(String nome, String cognome, String codFiscale, LocalDate dataNascita, String telefono, String email,
		String password, String username) {
	super();
	this.nome = nome;
	this.cognome = cognome;
	this.codFiscale = codFiscale;
	this.dataNascita = dataNascita;
	this.telefono = telefono;
	this.email = email;
	this.password = password;
	this.username = username;
}
@Override
public String toString() {
	return "UtentiModel [password=" + password + ", username=" + username + "]";
}



@ElementCollection(targetClass=Role.class, fetch=FetchType.EAGER )
 @CollectionTable(name="user_role", joinColumns=@JoinColumn(name="user_id"))/*tabella non eiste nel db, crea inautomatico*/
@Enumerated(EnumType.STRING)
private Set<Role> roles;

public Set<Role> getRoles() {
	return roles;
}
public void setRoles(Set<Role> roles) {
	this.roles = roles;
}
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	// TODO Auto-generated method stub
	return getRoles();
}
@Override
public boolean isAccountNonExpired() {
	// TODO Auto-generated method stub
	return true;
}
@Override
public boolean isAccountNonLocked() {
	// TODO Auto-generated method stub
	return true;
}
@Override
public boolean isCredentialsNonExpired() {
	// TODO Auto-generated method stub
	return true;
}
@Override
public boolean isEnabled() {
	// TODO Auto-generated method stub
	return isEnabled();
}


//@OneToOne(mappedBy="um", cascade=CascadeType.ALL, orphanRemoval=true)
@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="um" , orphanRemoval=true)

private Set<Profili> profili= new HashSet<>();

public Set<Profili> getProfili() {
	return profili;
}
public void setProfili(Set<Profili> profili) {
	this.profili = profili;
}


}
