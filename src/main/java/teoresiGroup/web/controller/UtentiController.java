package teoresiGroup.web.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import teoresiGroup.web.model.UtentiModel;
//Simport teoresiGroup.web.service.Interfacce.UtentiService;
import teoresiGroup.web.service.Interfacce.UtentiService;
import teoresiGroup.web.service.Interfacce.provaUserService;

@Controller
@RequestMapping("/cliente")
public class UtentiController {
	private final static Logger log = Logger.getLogger(UtentiController.class.getName());
	//@Autowired
	//private UtentiService utentiService;
	//@Autowired UtentiServiceImpl uImpl;
	@Autowired
	private provaUserService prova;
	
	//@Autowired
	//private UtentiCrudRepository crudRepo;
	//@Autowired
	//private BCryptPasswordEncoder bcpe;//da aggiungere la password criptata
	
	
	List<UtentiModel> um;
UtentiModel utenti;
	
@GetMapping("/registraCliente")
public ModelAndView nuovo(Model model) {
	log.info("Sono nel metodo per registrare gli utenti");
	
	UtentiModel utenti= new UtentiModel();
	
	
	model.addAttribute("utenteForm", utenti);
	

	return new ModelAndView("cliente", "utenteForm", new UtentiModel());
	
}


/*------------FUNZIONA-------------*/

@PostMapping("/addCliente")
public ModelAndView sumbit( @Valid @ModelAttribute("utenteForm") UtentiModel utenti)
{
	if(utenti!=null) {
		
		try {
			//crudRepo.add(utenti);
			prova.add(utenti);
			return new ModelAndView("result", "utenteForm", utenti);
		}
		catch(Exception e) {
			log.info("errore nel savalre nuov utente" + e.getStackTrace());
			return new ModelAndView("result", "utenteForm", utenti);
		}
	}
	else return new ModelAndView("result", "utenteForm", utenti);

}



/*-----------------NON ANCORA USATE----------------*/

@GetMapping("/cNome")
public String nome() {
	log.info("sono nel metodo cerca nome");
	//log.info(utentiService.dammiNome());
	
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
@GetMapping(value="/cerca/{nome}")
public String GetClientFilter(@PathVariable("nome") Map<String, List<String>> parametri, Model model) {
	//List
	
	long NumRecords = 0;
	long SkipValue = 0;
	long BolliniByFilter = 0;
	long BolliniTot = 0;

	String Filter = "";
	String TypeFilter = "";

	boolean ChangeOrder = false;

	List<UtentiModel> recordset;
	
	//PARAMETRI FILTRO
	List<String> ParamFiltro = parametri.get("nome");
	if (ParamFiltro != null)
	{
		Filter = ParamFiltro.get(0);  //Filtro applicato 
		TypeFilter = ParamFiltro.get(1); //Classe filtr (DA ABILITARE)
	}
	
	
	if (Filter.length() > 0)
		recordset = prova.getByNome(Filter); //Otteniamo i clienti per nominativo
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
/*------------FUNZIONA----------*/
@GetMapping("/allUtenti")
public ModelAndView all(Model model) {
	Iterable<UtentiModel> utenti= prova.getAll();
	utenti.forEach((UtentiModel l)->{
		model.addAttribute("utenti", utenti);
	});
	
	
	
	return new ModelAndView("tuttiOperatori", "utenti" ,utenti);
	
}


}
