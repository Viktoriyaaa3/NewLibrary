package teoresiGroup.web.model;

import java.util.Date;

import javax.persistence.Column;

public class Libri {
	private int id;
	
	private String titolo;
	
	private String autore;
	
	private Date dataPubblicazione;
	
	private int numeroPezzi;

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

	public Libri(String titolo, String autore, Date dataPubblicazione, int numeroPezzi) {
		super();
		this.titolo = titolo;
		this.autore = autore;
		this.dataPubblicazione = dataPubblicazione;
		this.numeroPezzi = numeroPezzi;
	}

	public Libri() {
		super();
	}
	
}
