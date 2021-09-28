package teoresiGroup.web.model;

import org.apache.log4j.Logger;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Utenti")
public class UtentiModel implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


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
	@Column(name="telefono")
private String telefono;
	@Column(name="email")
private String email;

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

}
