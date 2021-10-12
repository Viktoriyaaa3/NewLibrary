package teoresiGroup.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import teoresiGroup.web.model.LibriModel;
import teoresiGroup.web.service.Interfacce.LibroService;

@Controller
@RequestMapping("/prova")
public class provaController {
	private static final Logger log= Logger.getLogger(provaController.class.getName());
	
	@Autowired
	public LibroService libroService;
	
	 @GetMapping("/model")
	    public ModelAndView index(@RequestParam(value = "id", required = false) String idProdotto, ModelMap mm){
	        if(idProdotto!=null){
	         mm.addAttribute("libroForm", libroService.getById(Integer.parseInt(idProdotto)));
	        }
	        return new ModelAndView("prova", "libroForm", libroService.getAll());
	    }
	@GetMapping("/lb")
	public ModelAndView hello(Model model)
	{
		log.info("Sono in libri form");
		LibriModel libri= new LibriModel();
		log.info("Dati inseriti: " + libri);
		model.addAttribute("libroForm",libri);
		return new ModelAndView("prova", "libroForm", new LibriModel());
		
	}
	@PostMapping("/libro")
	public ModelAndView ciao(@ModelAttribute("libroForm") LibriModel libro) {
		
log.info("Sono in Libri aggiungi post");


if(libro!=null)
{ 
	log.info("i dati che sono arrivati: " + libro.getAutore() + " " + libro.getTitolo()+
			" " + libro.getNumeroPezzi());
	
	libroService.add(libro);

	return  new ModelAndView("prova", "libroForm", libro);
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
		
		
		
		return new ModelAndView("prova");
		
	}
}
