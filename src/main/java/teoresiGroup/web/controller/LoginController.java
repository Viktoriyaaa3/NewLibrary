package teoresiGroup.web.controller;

import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import teoresiGroup.web.Repository.UtentiRepo;
import teoresiGroup.web.model.UtentiModel;
import teoresiGroup.web.service.Interfacce.UtentiService;

@Controller
@RequestMapping("/login")
public class LoginController {
	private final static Logger log= Logger.getLogger(LoginController.class.getName());
	@Autowired
	//private UtentiRepo utentiRepo;
	private UtentiService utentiService;
	/*pag di registrazione è get, il submit è il post*/
	/*GETmAPPING= definisce metodo accessibile solo via get
	 * PostMapping:
	 * PutMappung
	 * DeleteMapping
	 * PatchMapping*/
	/*ALTRI MAPPING CHE POSSO USARE SOPRA I METODI
	 * 
	 * WebRequest, Nativeequest
	 * javax.servletServletRequest o Response
	 * http.HttpSession
	 * java.security.Principal
	 * java.util.Locale
	 * RequestParam, RequestHeader, RequestBody, HttpEntity<B>, SessionAttribute,ecc*/

/*es:
 * 
 * public String hello(HttpServletRequest request, HttpServletResponse response){}*/

/*es:
 * 
 * public String hello(HttpServletRequest request)
 * { if(request.getParameter("name")!=null)
 * return "Ciao" + request.getParameter("name")
 * else return"ciao, non ti conosco";
 * 
 * STESSA COSA CON SESSION*/
	
	/*Con @RequestParam("name")String name) io accedo ai paramentri nella query*/
/*@RequestAttribute lo uso quando faccio il forward*/

/*usare redirect quando voglio inviare la richiesta a un'altra pagina o uun altro controller. e forward per indirizzare più richieste*/

	@GetMapping("/log")
	public ModelAndView logIn(Model model) {
		
		UtentiModel utenti= new UtentiModel();
		model.addAttribute("utenteForm", utenti);
		
		/*
		 * 
		 * if(password e username esiste)
		 * return :tiSeiLoggato
		 * 
		 * else return pagina di registrazione*/
		return new ModelAndView("login", "utenteForm", new UtentiModel());
		
	}
	
	@SuppressWarnings("unused")
	@PostMapping("/controllo")
	public ModelAndView logOk(@ModelAttribute("utenteForm") UtentiModel utenti, Model model) {
		
		log.info("Controllo i dati che mi stanno arrivando dall'utente: ");
		List<UtentiModel> dati=null;
		String nome;
		
		if(utenti!=null)
		{
		
			utenti.getPassword();
			utenti.getUsername();
			log.info("I dati che sono arrivati: " + utenti.getPassword() + " " + utenti.getUsername() );
		
		
		dati = utentiService.ByPassAndUsername(utenti.getPassword(), utenti.getUsername());
		log.info("Vedo cosa mi ha trovato trimite CriteriaAPi");
		log.info(utentiService.ByPassAndUsername(utenti.getPassword().toString(), utenti.getUsername().toString()));
		
		//log.info(a);
		//log.info(a);
		/*if(dati.isEmpty() && dati==null)
				{log.info("errore password o username");
				 return "error";}*/
		
		}
		int a= dati.size();
		
		if(a<=0)
		{
			return new ModelAndView("cliente");
		}
		else
		//if(utenti.getPassword() == null)
		//log.info(dati.get(1));
		return new ModelAndView("dashboard", "utenteForm", utenti);
		

	
		
			
	}

}
