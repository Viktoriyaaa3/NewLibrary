package teoresiGroup.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import teoresiGroup.web.Repository.LibroRepo;
import teoresiGroup.web.model.LibriModel;

import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
@Controller
@RequestMapping("/book")
public class LibroController {
	private static final Logger log= Logger.getLogger(LibroController.class.getName());
	@Autowired
	private LibroRepo libroRepo;
	
	@GetMapping("/lb")
	public ModelAndView hello(Model model)
	{
		log.info("Sono in libri form");
		LibriModel libri= new LibriModel();
		//Date d;
		//GregorianCalendar c;
		log.info("Dati inseriti: " + libri);
		
		return new ModelAndView("libroVista", "libroForm", new LibriModel());
		
	}
	
	@PostMapping("/libro")
	public ModelAndView ciao(@ModelAttribute("libroForm") LibriModel libro) {
		
log.info("Sono in Libri aggiungi post");


if(libro!=null)
{ 
	log.info("i dati che sono arrivati: " + libro.getAutore() + " " + libro.getTitolo()+
			" " + libro.getNumeroPezzi());
	
	libroRepo.add(libro);

	return  new ModelAndView("regLibroSucesso", "libroForm", libro);
	
}
else return  new ModelAndView("error");
		
	}

}
