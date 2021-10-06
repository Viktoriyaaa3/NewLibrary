package teoresiGroup.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="operatore")
public class OperatoreModel {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@Column(name="nome")
	private String nome;
	@Column(name="cognnome")
	private String cognome;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public OperatoreModel(int id, String nome, String cognome) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
	}
	public OperatoreModel(String nome, String cognome) {
		super();
		this.nome = nome;
		this.cognome = cognome;
	}
	public OperatoreModel() {
		super();
	}
	@Override
	public String toString() {
		return "OperatoreModel [id=" + id + ", nome=" + nome + ", cognome=" + cognome + "]";
	}

	
	
}
