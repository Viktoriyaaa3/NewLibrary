package teoresiGroup.web.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.thymeleaf.expression.Calendars;


@Entity
@Table(name="Libri")
public class LibriModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -359571035473284961L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)/*se metto AUTO mi creerà la tabella hibernate-sequence e partirà a scrivere dalla prima posizione rispetto che da quelle già presenti*/
	private int id;
	@Column(name="titolo")
	private String titolo;
	@Column(name="autore")
	private String autore;
	@Column(name="dataPubblicazione")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataPubblicazione;
	@Column(name="numeroPezzi")
	private int numeroPezzi;
	/*Usare Period per calcolare il periodo del prestito libro*/
	/*Simple date format*/
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
	public LocalDate getDataPubblicazione() {
		return dataPubblicazione;
	}
	public void setDataPubblicazione(LocalDate dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}
	
	public int getNumeroPezzi() {
		return numeroPezzi;
	}
	public void setNumeroPezzi(int numeroPezzi) {
		this.numeroPezzi = numeroPezzi;
	}
	
	public LibriModel(int id, String titolo, String autore, LocalDate dataPubblicazione, int numeroPezzi) {
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
