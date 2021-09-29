package teoresiGroup.web.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Libri")
public class LibriModel implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)/*se metto AUTO mi creerà la tabella hibernate-sequence e prtirà a scrivere dala prima posizione rispetto che da quelle già presenti*/
	private int id;
	@Column(name="titolo")
	private String titolo;
	@Column(name="autore")
	private String autore;
	@Column(name="dataPubblicazione")
	private Date dataPubblicazione;
	@Column(name="numeroPezzi")
	private int numeroPezzi;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}
	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}
	public int getNumeroPezzi() {
		return numeroPezzi;
	}
	public void setNumeroPezzi(int numeroPezzi) {
		this.numeroPezzi = numeroPezzi;
	}
	public LibriModel(int id, String titolo, String autore, Date dataPubblicazione, int numeroPezzi) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.autore = autore;
		this.dataPubblicazione = dataPubblicazione;
		this.numeroPezzi = numeroPezzi;
	}
	public LibriModel() {
		super();
	}
	@Override
	public String toString() {
		return "LibriModel [id=" + id + ", titolo=" + titolo + ", autore=" + autore + ", dataPubblicazione="
				+ dataPubblicazione + ", numeroPezzi=" + numeroPezzi + "]";
	}
	


}
