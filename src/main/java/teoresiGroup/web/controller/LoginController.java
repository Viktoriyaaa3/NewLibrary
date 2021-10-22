package teoresiGroup.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
		/*Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		if(auth== null || auth instanceof AnonymousAuthenticationToken) {
			return new ModelAndView("login", "utenteForm", new UtentiModel());}
		return new ModelAndView("login", "utenteForm", new UtentiModel());*/
		
		UtentiModel utenti= new UtentiModel();
		model.addAttribute("utenteForm", utenti);
	
		return new ModelAndView("login", "utenteForm", new UtentiModel());
		
	}
	
	@PostMapping("/controllo")
	public ModelAndView logOk(@ModelAttribute("utenteForm") UtentiModel utenti, Model model) {
		
		
		log.info("Controllo i dati che mi stanno arrivando dall'utente: ");
		List<UtentiModel> dati=null;
		List<UtentiModel> modl=null;
		String nome;
		/*List<UtentiModel> provo=null;
		provo=utentiService.getAll();
		log.info("variabile provo" + provo.toString());*/
		String cercoUsername=utenti.getUsername();
		if(utenti!=null)
		{
			/*
			List<UtentiModel> ceracre=utentiService.cerca(provo, utente->utente.getNome().equals(cercoUsername));
			log.info("VEDO COSA MI ARRIVA CON PREDICATE");
			log.info("vediamo.."+ceracre.toArray().toString());
			log.info("vediamo.."+ceracre.toArray());
			log.info("vediamo.."+ceracre);*/
			utenti.getPassword();
			
			utenti.getNome();
			utenti.getRuolo();
			log.info("I dati che sono arrivati: " + utenti.getPassword() + " " + utenti.getUsername() + " "
					+utenti.getNome()+ " " + utenti.getRuolo());
		
		modl=utentiService.getAll();
		if(modl!=null && !modl.isEmpty())
		{
			for(int i=0; i<modl.size(); i++) {
				log.info("lunghezza modl" + i);
			}
			log.info("modl non è vuoto");
			//modl.forEach(null);
			log.info(modl.containsAll(modl));
			log.info(modl.iterator());
		}else {
			log.info("l'oggetto e vuoto");
		}
		List<UtentiModel> provo=null;
		provo=utentiService.getAll();
		log.info("variabile provo" + provo.toString());
		dati = utentiService.ByPassAndUsername(utenti.getPassword(), utenti.getUsername());
		log.info("GURADO COSA È ARRIVATO IN UTENTI SERVICE");
		log.info("GURADO COSA È ARRIVATO IN DATI");
		log.info("Vedo cosa mi ha trovato trimite CriteriaAPi");
		log.info(utentiService.ByPassAndUsername(utenti.getPassword().toString(), utenti.getUsername().toString()));
		
		
		}
		int a= dati.size();
		
		if(a<=0)
		{
			return new ModelAndView("cliente");
		}
		else
		//model.addAllAttributes(utenti.)
		return new ModelAndView("dashboard", "utenteForm", utenti);
		

	
		
			
	}

}
