package teoresiGroup.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import teoresiGroup.web.model.UtentiModel;
import teoresiGroup.web.service.Interfacce.UtentiService;
/*Authentication auth= SecurityContextHolder.getContext().getAuthentication();
if(auth== null || auth instanceof AnonymousAuthenticationToken) {
	return new ModelAndView("login", "utenteForm", new UtentiModel());}
return new ModelAndView("login", "utenteForm", new UtentiModel());*/

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
			log.info("I dati che sono arrivati: " + utenti.getPassword() + " " + utenti.getUsername()  + " "+ utenti.getRuolo() 
			+ " "+ utenti.getAbilitato() + " "+ utenti.getAuthorities() +" "+ utenti.getRoles());
		
		
		dati = utentiService.ByPassAndUsername(utenti.getPassword(), utenti.getUsername());
		log.info("Vedo cosa mi ha trovato trimite CriteriaAPi");
		log.info(utentiService.ByPassAndUsername(utenti.getPassword().toString(), utenti.getUsername().toString()));
		
		
		}
		int a= dati.size();
		/*Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		if(auth== null || auth instanceof AnonymousAuthenticationToken) {
			return new ModelAndView("login", "utenteForm", new UtentiModel());}
		return new ModelAndView("login", "utenteForm", new UtentiModel());
		*/
		if(a<=0)
		{
			return new ModelAndView("cliente");
		}
		else
		//model.addAllAttributes(utenti.)
		return new ModelAndView("dashboard", "utenteForm", utenti);
		

	
		
			
	}
	
	/*@RequestMapping("/login/error")
	public String loginError(Model model) {
		
		model.addAttribute("loginError", true);
		return "cliente";
	}
	 @ExceptionHandler(Throwable.class)
	    @ResponseStatus()
	    public String exception(final Throwable throwable, final Model model) {
	        log.error("Exception during execution of SpringSecurity application", throwable);
	        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
	        model.addAttribute("errorMessage", errorMessage);
	        return "error";
	    }*/

}
