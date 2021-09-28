package teoresiGroup.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
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

	@GetMapping
	public String logIn() {
		/*
		 * 
		 * if(password e username esiste)
		 * return :tiSeiLoggato
		 * 
		 * else return pagina di registrazione*/
		return "tiSeiLoggato";
	}
	
	@PostMapping
	public String logOk() {
		
		return "redirect/welcomePage";
	}

}
