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
	private UtentiService utentiService;


	@GetMapping("/log")
	public ModelAndView logIn(Model model) {
		
		UtentiModel utenti= new UtentiModel();
		model.addAttribute("utenteForm", utenti);
	
		return new ModelAndView("login", "utenteForm", new UtentiModel());
		
	}
	
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
		
		
		}
		int a= dati.size();
		
		if(a<=0)
		{
			return new ModelAndView("cliente");
		}
		else
		return new ModelAndView("dashboard", "utenteForm", utenti);
		

	
		
			
	}

}
