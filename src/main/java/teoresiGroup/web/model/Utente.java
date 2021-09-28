package teoresiGroup.web.model;

import javax.persistence.Column;

public class Utente {
	private int id;
	
private String nome;
	
private String cognome;
	
private String codFiscale;
	
private String telefono;
	
private String email;


public Utente() {
	super();
}

public Utente(int id, String nome, String cognome, String codFiscale, String telefono, String email) {
	super();
	this.id = id;
	this.nome = nome;
	this.cognome = cognome;
	this.codFiscale = codFiscale;
	this.telefono = telefono;
	this.email = email;
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



}
