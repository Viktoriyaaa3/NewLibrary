package teoresiGroup.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import teoresiGroup.web.model.UserDettagli;
import teoresiGroup.web.model.UtentiModel;
import teoresiGroup.web.service.Interfacce.UtentiService;
import teoresiGroup.web.service.Interfacce.provaUserService;
@SessionAttributes({"currentUser", "currentUserId"})
@Controller
@RequestMapping("/login")
public class LoginController {
	private final static Logger log= Logger.getLogger(LoginController.class.getName());
	@Autowired
	private UtentiService utentiService;
	 @Autowired 
	  private BCryptPasswordEncoder passwordEncoder;
@Autowired
private provaUserService userService;


	@GetMapping("/loginUtente")
	public ModelAndView loginGetDati(/*@PathVariable("username") String username, @PathVariable("password") String password,*/ Model model) {
		/* List<UtentiModel> userAccountList = (List<UtentiModel>) userService
	                .findByUsernameAndPassword(username, password);
		 StringBuffer retBuf = new StringBuffer();
		 if (userAccountList != null) {
	            for (UtentiModel userAccount : userAccountList) {
	                retBuf.append("user name = ");
	                retBuf.append(userAccount.getUsername());
	                retBuf.append(", password = ");
	                retBuf.append(userAccount.getPassword());
	            }
		 }*/
		//boolean myBooleanVariable = true;
		//model.addAttribute("myBooleanVariable", myBooleanVariable);
		return new ModelAndView("login", "utenteForm", new UtentiModel());
		
	}
	
	@PostMapping("/loginUtente")
	public ModelAndView loginPostDati(@ModelAttribute("utenteForm") UtentiModel utenti, Model model) {
		
		//users=utentiService.getAll();
		log.info("Controllo i dati che mi stanno arrivando dall'utente: ");
		List<UtentiModel> dati=null;
	log.info(utenti.getUsername() + " " + utenti.getPassword());
		if(utenti!=null)
		{
			String usern=null;
			String pass=null;
			usern=utenti.getUsername();
			log.info("vedo username "+pass);
			
			pass=utenti.getPassword();
			log.info("vedo la password "+pass);
		/*	utenti.getPassword();
			
			log.info("I dati che sono arrivati: " + utenti.getId() + " - " + utenti.getPassword() + " " + utenti.getUsername() + " "
					+utenti.getNome()+ " " + utenti.getRuolo());
		*/
		
		//dati = utentiService.ByPassAndUsername(utenti.getPassword(), utenti.getUsername());
		dati=userService.findByUsernameAndByPassword(usern, pass);
		log.info(userService.findByUsernameAndByPassword(utenti.getUsername(), utenti.getPassword()));
		log.info(userService.findByUsernameAndByPassword(usern, pass));
		
		
		}
		int a= dati.size();
		
		if(a<=0)
		{
			return new ModelAndView("cliente");
		}
		else {
			
		//	boolean myBooleanVariable = true;
			log.info("dati.get(0): " +dati.get(0).getNome());
			//model.addAttribute("myBooleanVariable", myBooleanVariable);
		   	return new ModelAndView("dashboard", "utenteForm", dati.get(0).getNome());
		}
		
	}
	/*private void validatePrinciple(Object principal) {
        if (!(principal instanceof UserDettagli)) {
            throw new  IllegalArgumentException("Principal can not be null!");
        }
	}*/

}
