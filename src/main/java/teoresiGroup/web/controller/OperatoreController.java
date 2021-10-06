package teoresiGroup.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import teoresiGroup.web.model.OperatoreModel;
import teoresiGroup.web.service.Interfacce.OperatoreService;

@Controller
@RequestMapping("/operatore")
public class OperatoreController {
	private static final Logger log= Logger.getLogger(OperatoreController.class.getName());
	
	
	@Autowired
	public OperatoreService operatoreService;
	
	
	
	
	@GetMapping("/lb")
	public ModelAndView hello(Model model)
	{
		log.info("Sono in libri form");
		OperatoreModel libri= new OperatoreModel();
		log.info("Dati inseriti: " + libri);
		model.addAttribute("libroForm",libri);
		return new ModelAndView("libroVista", "libroForm", new OperatoreModel());
		
	}
	
	@PostMapping("/libro")
	public ModelAndView ciao(@ModelAttribute("libroForm") OperatoreModel libro) {
		
log.info("Sono in Libri aggiungi post");


if(libro!=null)
{ 
	
	
	operatoreService.add(libro);

	return  new ModelAndView("regLibroSucesso", "libroForm", libro);
	/*reindirizza alla pagina di registrazione. cambiare vesro visualizza tutto*/
}
else return  new ModelAndView("error");
		
	}
	
	
	

	@GetMapping("/all")
	public ModelAndView all(Model model) {
		Iterable<OperatoreModel> lib= operatoreService.getAll();
		lib.forEach((OperatoreModel l)->{
			model.addAttribute("books", lib);
		});
		
		
		
		return new ModelAndView("tuttiLibri");
		
	}
	
@GetMapping("/aggiorna")
public ModelAndView update(Model model) {
	OperatoreModel lib=new OperatoreModel();
	model.addAttribute("book", lib);
	log.info("Dati inseriti: " + lib.getNome() + " " + lib.getCognome());
	return new ModelAndView( "aggiorna", "book", lib);
}
@PostMapping("/aggiorna")
public ModelAndView update(@ModelAttribute("book") OperatoreModel l, Model model)
{
	operatoreService.update(l);
	log.info("Dati in arrivo al post di update: " + l.getNome() + " " + l.getCognome());
	return new ModelAndView ("tuttiLibri");
}



@GetMapping("/search")
public ModelAndView cerco(Model model)
{log.info("SOno nel metodo per cercare un libro: get search");
OperatoreModel lib= new OperatoreModel();
	model.addAttribute("book", lib);
	return new ModelAndView("cercoLibro", "book", new OperatoreModel());
}

@PostMapping("/trovato")
public ModelAndView trovato(@ModelAttribute("book") OperatoreModel lib, Model model)
{
	log.info("Son nel metodo per cercare i libri: post trovato");
	List<OperatoreModel> libri= null;
	OperatoreModel l=null;

	try {	l=operatoreService.getById(lib.getId());
	return new ModelAndView("libroTrovato", "book", l);
		
		
	}catch(Exception e) {
		model.addAttribute("book", null);
		return new ModelAndView("error");
	}
	
}
	
	/*---------------EDIT---------------*/
	
@GetMapping("/edit/{id}")
public String showUpdateForm(@PathVariable("id") int id, Model model) {
	
	
	
   try { OperatoreModel lib = operatoreService.getById(id);
   model.addAttribute("book", lib);}
   catch(IllegalArgumentException e) {
     // .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	   
	   log.info(e.getStackTrace());
	   //inserire cosa fare in caso di errore
   }
    
  
    return "aggiorna";
}

@PostMapping("/update/{id}")
public String updateUser(@PathVariable("id") int id, @Validated  OperatoreModel lib, 
  BindingResult result, Model model) {
    if (result.hasErrors()) {
        lib.setId(id);
        return "aggiorna";
    }
        
    operatoreService.update(lib);
    return "redirect:/book/all";
}

/*--------DELETE----------*/

@GetMapping("/delete/{id}")
public String deleteUser(@PathVariable("id") int id, Model model) {
  try {   OperatoreModel user = operatoreService.getById(id);
     // .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
  operatoreService.delete(id);
  }
  catch(IllegalArgumentException e) {
	  
	     // .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	   
		   log.info(e.getStackTrace());
		   //inserire cosa fare in caso di errore
	   }
    return "redirect:/book/all";
}

}
