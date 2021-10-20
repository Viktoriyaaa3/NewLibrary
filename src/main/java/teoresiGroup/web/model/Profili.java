package teoresiGroup.web.model;

import javax.persistence.*;
@Entity
@Table(name="Profili")
public class Profili {
	/*password non mi serve*/
	@Id
	@Column(name="userId")
	public String userId;
	@Column(name="password")
	public String password;
	@Column(name="tipo")
	public String tipo;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Profili(String userId, String password, String tipo) {
		super();
		this.userId = userId;
		this.password = password;
		this.tipo = tipo;
	}
	public Profili() {
		super();
	}
	
	
	//@OneToOne
	//@PrimaryKeyJoinColumn
	@ManyToOne
	@JoinColumn(name="username", referencedColumnName="username")
	private UtentiModel um;
	/*mettere username come primaryKey*/
	public UtentiModel getUm() {
		return um;
	}
	public void setUm(UtentiModel um) {
		this.um = um;
	}
	
	
	
	

}
