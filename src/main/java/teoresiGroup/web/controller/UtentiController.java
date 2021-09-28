package teoresiGroup.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import teoresiGroup.web.Repository.UtentiRepo;
import teoresiGroup.web.model.UtentiModel;

@Controller
@RequestMapping("/cliente")
public class UtentiController {
	private final static Logger log = Logger.getLogger(UtentiController.class.getName());
	@Autowired
	private UtentiRepo utentiRepo;
	
	
@GetMapping("/registra")
public ModelAndView nuovo(Model model) {
	log.info("Sono nel metodo nuovo");
	
	UtentiModel utenti= new UtentiModel();
	log.info("gli utenti ora: " + utenti.toString());
	
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
	{utentiRepo.add(utenti);
	return new ModelAndView("result", "utenteForm", utenti);}
	else return new ModelAndView("error");
	
}
//ricerca per nome


@GetMapping("/search")
public String getByName() {
	log.info("Sono nel metodo di ricerca per nome");
	UtentiModel utenti= utentiRepo.getByName("Vik");
	log.info(utenti.getNome());
	return utenti.getNome();
}

@GetMapping("/cNome")
public String nome() {
	log.info("sono nel metodo cerca nome");
	log.info(utentiRepo.dammiNome());
	return "result";
	
}









}
