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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import teoresiGroup.web.Repository.LibroCrudRepository;
import teoresiGroup.web.Repository.LibroRepo;
import teoresiGroup.web.model.LibriModel;
import teoresiGroup.web.service.Interfacce.LibroService;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
@Controller
@RequestMapping("/book")
public class LibroController {
	private static final Logger log= Logger.getLogger(LibroController.class.getName());
	@Autowired
	public LibroService libroService;
	@Autowired 
	private LibroCrudRepository repo;
	
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
	/*reindirizza alla pagina di registrazione. cambiare vesro visualizza tutto*/
}
else return  new ModelAndView("error");
		
	}
	
	
	

	@GetMapping("/all")
	public ModelAndView all(Model model) {
		Iterable<LibriModel> lib= repo.findAll();
		lib.forEach((LibriModel l)->{
			model.addAttribute("books", lib);
		});
		
		
		
		return new ModelAndView("unaVista");
		
	}
	
@GetMapping("/aggiorna")
public ModelAndView update(Model model) {
	LibriModel lib=new LibriModel();
	model.addAttribute("book", lib);
	return new ModelAndView( "aggiorna");
}
@PostMapping("/aggiorna")
public ModelAndView update(@ModelAttribute("book") LibriModel l, Model model)
{
	libroService.update(l);
	
	return new ModelAndView ("unaVista");
}



@GetMapping("/search")
public ModelAndView cerco(Model model)
{log.info("SOno nel metodo per cercare un libro: get search");
	LibriModel lib= new LibriModel();
	model.addAttribute("book", lib);
	return new ModelAndView("cercoLibro", "book", new LibriModel());
}

@PostMapping("/trovato")
public ModelAndView trovato(@ModelAttribute("book") LibriModel lib, Model model)
{
	log.info("Son nel metodo per cercare i libri: post trovato");
	List<LibriModel> libri= null;
	LibriModel l=null;
	
	if(lib!=null) {
		l=libroService.getById(lib.getId());
		return new ModelAndView("libroTrovato", "book", l);
	}
	else 
		return new ModelAndView("error");

	
}
	
	
	
	
	
}
