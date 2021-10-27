package teoresiGroup.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import teoresiGroup.web.model.UserDettagli;
import teoresiGroup.web.model.UtentiModel;
import teoresiGroup.web.service.Interfacce.UtentiService;
@SessionAttributes({"currentUser", "currentUserId"})
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
		
	//	UtentiModel utenti= new UtentiModel();
		//model.addAttribute("utenteForm", utenti);
		boolean myBooleanVariable = true;
		model.addAttribute("myBooleanVariable", myBooleanVariable);
		return new ModelAndView("login", "utenteForm", new UtentiModel());
		
	}
	


	@GetMapping("/controllo")
	public ModelAndView test(Model model) {
		
		//boolean myBooleanVariable = true;
		//model.addAttribute("myBooleanVariable", myBooleanVariable);
		return new ModelAndView("login", "utenteForm", new UtentiModel());
		
	}
	
	@PostMapping("/controllo")
	public ModelAndView logOk(@ModelAttribute("utenteForm") UtentiModel utenti, Model model, HttpSession session) {
		
		//users=utentiService.getAll();
		log.info("Controllo i dati che mi stanno arrivando dall'utente: ");
		List<UtentiModel> dati=null;
	
		if(utenti!=null)
		{
			
			utenti.getPassword();
			
			log.info("I dati che sono arrivati: " + utenti.getId() + " - " + utenti.getPassword() + " " + utenti.getUsername() + " "
					+utenti.getNome()+ " " + utenti.getRuolo());
		
		
		dati = utentiService.ByPassAndUsername(utenti.getPassword(), utenti.getUsername());
		
		log.info(utentiService.ByPassAndUsername(utenti.getPassword().toString(), utenti.getUsername().toString()));
		
		
		}
		int a= dati.size();
		
		if(a<=0)
		{
			return new ModelAndView("cliente");
		}
		else {
			
			boolean myBooleanVariable = true;
			log.info("dati.get(0): " +dati.get(0).getNome());
			model.addAttribute("myBooleanVariable", myBooleanVariable);
		   	return new ModelAndView("dashboard", "utenteForm", dati.get(0).getNome());
		}
		
	}
	private void validatePrinciple(Object principal) {
        if (!(principal instanceof UserDettagli)) {
            throw new  IllegalArgumentException("Principal can not be null!");
        }
	}

}
