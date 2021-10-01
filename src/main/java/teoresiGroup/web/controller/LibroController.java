package teoresiGroup.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import teoresiGroup.web.Repository.LibroRepo;
import teoresiGroup.web.model.LibriModel;
import teoresiGroup.web.service.Interfacce.LibroService;

import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
@Controller
@RequestMapping("/book")
public class LibroController {
	private static final Logger log= Logger.getLogger(LibroController.class.getName());
	@Autowired
	public LibroService libroService;
	
	@GetMapping("/lb")
	public ModelAndView hello(Model model)
	{
		log.info("Sono in libri form");
		LibriModel libri= new LibriModel();
		log.info("Dati inseriti: " + libri);
		model.addAttribute("libroForm",libri);
		return new ModelAndView("libroVista", "libroForm", new LibriModel());
		
	}
	
	@PostMapping("/libro")
	public ModelAndView ciao(@ModelAttribute("libroForm") LibriModel libro) {
		
log.info("Sono in Libri aggiungi post");


if(libro!=null)
{ 
	log.info("i dati che sono arrivati: " + libro.getAutore() + " " + libro.getTitolo()+
			" " + libro.getNumeroPezzi());
	
	libroService.add(libro);

	return  new ModelAndView("regLibroSucesso", "libroForm", libro);
	
}
else return  new ModelAndView("error");
		
	}
	
	
	
	/*-------------CERCO LIBRO--------*/
	
	
	@GetMapping("/update")
	public ModelAndView aggiorna(@RequestParam(value="autore") String autore, ModelMap mm) {
		log.info("Sono nel metodo update di libri");
		//libroService.
		if(autore!=null) {
			mm.addAttribute("libroF", libroService.getByName(autore));
		}
		
		return new ModelAndView("unaVista", "datiLibri", libroService.getAll());
	}
	@GetMapping("/all")
	public String showAll(Model model) {
	    model.addAttribute("books", libroService.getAll());
	    return "unaVista";
	}

	
@PutMapping("/aggiorna")
public String update(@ModelAttribute("datiLibri") LibriModel lb) {
	
	libroService.update(lb);
	return "unaVista";
}

	
	
	
	
	
	
	
	
	
	
	
	
	
/*    @PostMapping("/update")
public String update(@ModelAttribute("datiLibri") Libri libr){
    libri.update(libr);
    return "redirect:/";
}*/
	
	
	
	
	
	
}
