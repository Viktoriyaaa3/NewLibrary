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
	//private static final Logger logger= LoggerFactory.getLogger(UtentiController.class);
	@Autowired
	//private UtentiRepo utentiRepo;
	private UtentiService utentiService;
	//@Autowired
	//private UtentiRepo utentiRepo;
	List<UtentiModel> um;
UtentiModel utenti;
	
@GetMapping("/registra")
public ModelAndView nuovo(Model model) {
	log.info("Sono nel metodo per registrare gli utenti");
	
	UtentiModel utenti= new UtentiModel();
	
	
	model.addAttribute("utenteForm", utenti);
	
//	log.info("")
	return new ModelAndView("cliente", "utenteForm", new UtentiModel());
	//log.info(utenti.toString());
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
//ricerca per nome


/*@GetMapping("/search")
public String getByName() {
	log.info("Sono nel metodo di ricerca per nome");
	UtentiModel utenti= utentiRepo.getByName("Vik");
	log.info(utenti.getNome());
	return utenti.getNome();
}*/

@GetMapping("/cNome")
public String nome() {
	log.info("sono nel metodo cerca nome");
	log.info(utentiService.dammiNome());
	
	return "result";
	
}

/*
private void getAll() {
	um =utentiRepo.getAll();
}
*/
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
	
	//PARAMETRI ORDINAMENTO
	/*List<String> ParamOrderBy = parametri.get("orderby");
	if (ParamOrderBy != null)
	{
		try
		{
			OrderBy = Integer.parseInt(ParamOrderBy.get(0)); //Colonna del filtro 
			OrderType = ParamOrderBy.get(1); //Tipo di ordinamento (ASC,DESC)
			ChangeOrder = (ParamOrderBy.get(2).equals("1")) ? true : false; //Inversione dell'ordinamento
		} 
		catch (NumberFormatException ex)
		{
			OrderBy = 0;
		}
	}*/
	
	//PARAMETRI PAGING
	/*List<String> ParamPaging = parametri.get("paging");
	if (ParamPaging != null)
	{
		try
		{
			PageNum = Integer.parseInt(ParamPaging.get(0)); //Numero della pagina
			RecForPage = Integer.parseInt(ParamPaging.get(1)); //Record per pagina
			int DiffPage = Integer.parseInt(ParamPaging.get(2)); //vede se il numero della pagina rientra nel range

			if (PageNum >= 1)
				PageNum += DiffPage;
			else
				PageNum = 1;

		} 
		catch (NumberFormatException ex)
		{
			PageNum = 1;
			RecForPage = 10;
		}
	}
	*/
	if (Filter.length() > 0)
		recordset = utentiService.ByNome(Filter); //Otteniamo i clienti per nominativo
	else
	{
		if (um == null)
			nome(); //otteniamo tutti i clienti
		/*---------al posto del metodo nome, fare metodo get AllUtenti*/

		recordset = um;
	}
	
	//verifico se recordset è diverso da null
	/*
	if (recordset != null)
	{
		recordset = recordset
				.stream()
				.filter(u -> !u.getNome().equals("-1"))
				//.filter(u -> u.getCard() != null) elimino chi non ha i bollini
				.collect(Collectors.toList());
		
		LongSummaryStatistics BolliniStatistics = recordset
				.stream()
				.collect(Collectors.summarizingLong(p -> p.getCard().getBollini()));
		
		NumRecords = BolliniStatistics.getCount();
		BolliniByFilter = BolliniStatistics.getSum();
		BolliniTot = utentiRepo.QtaTotBollini();
		
		SkipValue = GetSkipValue(PageNum, NumRecords);
		
		recordset = GestOrderRecordset(recordset, OrderBy, ChangeOrder)
				.stream()
				.skip(SkipValue)
				.limit(RecForPage)
				.collect(Collectors.toList());
		
	}
	
	setPages(PageNum, NumRecords);*/
	
	model.addAttribute("Titolo", "Ricerca Clienti");
	model.addAttribute("Titolo2", "Risultati Ricerca ");
	model.addAttribute("NumRecords", NumRecords);
	model.addAttribute("clienti", recordset);
	model.addAttribute("filter", Filter);//questo mi serve
	/*model.addAttribute("OrderType", OrderType);
	model.addAttribute("OrderBy", OrderBy);
	model.addAttribute("PageNum", PageNum);
	model.addAttribute("RecPage", RecForPage);
	model.addAttribute("Pages", Pages);
	model.addAttribute("IsClienti", IsClienti);
	model.addAttribute("BolTot", BolliniTot);
	model.addAttribute("BolFil", BolliniByFilter);
	model.addAttribute("PageLink", getPageLink(Filter));*/
	
	return "clienti";

	
	
	
	
	

	
}




}
