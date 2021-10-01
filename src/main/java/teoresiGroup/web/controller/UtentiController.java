package teoresiGroup.web.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.MatrixVariable;
import teoresiGroup.web.Repository.UtentiRepo;
import teoresiGroup.web.model.UtentiModel;
//Simport teoresiGroup.web.service.Interfacce.UtentiService;
import teoresiGroup.web.service.Interfacce.UtentiService;

import java.util.LongSummaryStatistics; /*vedere come funziona*/

@Controller
@RequestMapping("/cliente")
public class UtentiController {
	private final static Logger log = Logger.getLogger(UtentiController.class.getName());
	private UtentiService utentiService;
	
	List<UtentiModel> um;
UtentiModel utenti;
	
@GetMapping("/registra")
public ModelAndView nuovo(Model model) {
	log.info("Sono nel metodo per registrare gli utenti");
	
	UtentiModel utenti= new UtentiModel();
	
	
	model.addAttribute("utenteForm", utenti);
	

	return new ModelAndView("cliente", "utenteForm", new UtentiModel());
	
}


@PostMapping("/add")
public ModelAndView sumbit(@ModelAttribute("utenteForm") UtentiModel utenti)
{
	log.info("Sono nel metodo submit");
	
	if(utenti!=null)
	{ 
		log.info("Scrivo i dati ricevuti nel post, metodo add : " + utenti.getNome() + " " + utenti.getCognome() + " " + utenti.getCodFiscale()
	+ " "+ utenti.getEmail() + " " + utenti.getTelefono() + " " + utenti.getPassword() + " " + utenti.getUsername());
	
	if(utenti.getPassword().isEmpty() || utenti.getUsername().isEmpty())
		return new ModelAndView("error");

	/*aggiungere espressioni regolari per controllare email in arrivo*/
		
		utentiService.add(utenti);
	return new ModelAndView("result", "utenteForm", utenti);}
	else 
		return new ModelAndView("error");
	
}





/*-----------------NON ANCORA USATE----------------*/

@GetMapping("/cNome")
public String nome() {
	log.info("sono nel metodo cerca nome");
	log.info(utentiService.dammiNome());
	
	return "result";
	
}

@GetMapping //senza il path sarà il metodo di apertura
public String getUtenti(Model model) {
	log.info("Sono nel metodo get Utenti");
	List<UtentiModel> rec;
	if(um!=null)
	{
		/*filt: filtro i record Collectors per trasformare in una lista classi UtentiModel*/
		rec=um.stream().filter(u->!u.getNome().equals(-1))
				.skip(0)/*dico che parto da 0*/
				/*.limit(10) per indicare quante righe visualizzare*/
				.sorted(Comparator.comparing(UtentiModel::getNome).reversed())
				.collect(Collectors.toList());
	}
	
		model.addAttribute("Titolo", "Ricerca Utenti");
		model.addAttribute("Titolo2", "Risultati Ricerca");
		
	
	return "client";
}



/*variabili tipo matrice*/
@GetMapping(value="/cerca/{parametri}")
public String GetClientFilter(@MatrixVariable(pathVar="parametri") Map<String, List<String>> parametri, Model model) {
	
	
	long NumRecords = 0;
	long SkipValue = 0;
	long BolliniByFilter = 0;
	long BolliniTot = 0;

	String Filter = "";
	String TypeFilter = "";

	boolean ChangeOrder = false;

	List<UtentiModel> recordset;
	
	//PARAMETRI FILTRO
	List<String> ParamFiltro = parametri.get("filtro");
	if (ParamFiltro != null)
	{
		Filter = ParamFiltro.get(0);  //Filtro applicato 
		TypeFilter = ParamFiltro.get(1); //Classe filtr (DA ABILITARE)
	}
	
	
	if (Filter.length() > 0)
		recordset = utentiService.ByNome(Filter); //Otteniamo i clienti per nominativo
	else
	{
		if (um == null)
			nome(); //otteniamo tutti i clienti
		/*---------al posto del metodo nome, fare metodo get AllUtenti*/

		recordset = um;
	}
	
	
	
	model.addAttribute("Titolo", "Ricerca Clienti");
	model.addAttribute("Titolo2", "Risultati Ricerca ");
	model.addAttribute("NumRecords", NumRecords);
	model.addAttribute("clienti", recordset);
	model.addAttribute("filter", Filter);
	
	return "clienti";
	
}




}
