package teoresiGroup.web.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="logins")
public class Logins implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional=false)
	private String serie;
	@Basic(optional=false)
	private String nomeUtente;
	@Basic(optional=false)
	private String token;
	
	@Temporal(TemporalType.TIME)
	@Basic(optional=false)
	private Date usato;

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getUsato() {
		return usato;
	}

	public void setUsato(Date usato) {
		this.usato = usato;
	}

	public Logins(String serie, String nomeUtente, String token, Date usato) {
		super();
		this.serie = serie;
		this.nomeUtente = nomeUtente;
		this.token = token;
		this.usato = usato;
	}

	public Logins() {
		super();
	}
	

}
