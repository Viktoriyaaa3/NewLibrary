package teoresiGroup.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		Iterable<LibriModel> lib= libroService.getAll();
		lib.forEach((LibriModel l)->{
			model.addAttribute("books", lib);
		});
		
		
		
		return new ModelAndView("tuttiLibri");
		
	}
	
@GetMapping("/aggiorna")
public ModelAndView update(Model model) {
	LibriModel lib=new LibriModel();
	model.addAttribute("book", lib);
	log.info("Dati inseriti: " + lib.getAutore() + " " + lib.getTitolo());
	return new ModelAndView( "aggiorna", "book", lib);
}
@PostMapping("/aggiorna")
public ModelAndView update(@ModelAttribute("book") LibriModel l, Model model)
{
	libroService.update(l);
	log.info("Dati in arrivo al post di update: " + l.getAutore() + " " + l.getTitolo());
	return new ModelAndView ("tuttiLibri");
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
	/*
	if(lib!=null) {
		l=libroService.getById(lib.getId());
		return new ModelAndView("libroTrovato", "book", l);
	}
	else 
		return new ModelAndView("error");
*/
	try {	l=libroService.getById(lib.getId());
	return new ModelAndView("libroTrovato", "book", l);
		
		
	}catch(Exception e) {
		model.addAttribute("book", null);
		return new ModelAndView("error");
	}
	
}
	/*-----------------aggiorna----------------*/
	
	
@GetMapping("/edit/{id}")
public String showUpdateForm(@PathVariable("id") int id, Model model) {
   try { LibriModel lib = libroService.getById(id);
   model.addAttribute("book", lib);}
   catch(IllegalArgumentException e) {
     // .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	   
	   log.info(e.getStackTrace());
	   //inserire cosa fare in caso di errore
   }
    
  
    return "aggiorna";
}

@PostMapping("/update/{id}")
public String updateUser(@PathVariable("id") int id, @Validated LibriModel lib, 
  BindingResult result, Model model) {
    if (result.hasErrors()) {
        lib.setId(id);
        return "aggiorna";
    }
        
    libroService.update(lib);
    return "redirect:/book/all";
}

/*--------DELETE----------*/

@GetMapping("/delete/{id}")
public String deleteUser(@PathVariable("id") int id, Model model) {
  try {  LibriModel user = libroService.getById(id);
     // .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    libroService.delete(user);
  }
  catch(IllegalArgumentException e) {
	  
	     // .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	   
		   log.info(e.getStackTrace());
		   //inserire cosa fare in caso di errore
	   }
    return "redirect:/book/all";
}




	
}
