package teoresiGroup.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/newBook")

public class NewLibroController {

	@Autowired
	private LibroService service;
	
	@GetMapping("/boo")
	public ModelAndView index(@RequestParam(name = "id", required = false) String idProdotto, ModelMap mm) {
		if(idProdotto != null) {
			mm.addAttribute("prodottoDaModificare", service.getById(Integer.parseInt(idProdotto)));
		}
		
		return new ModelAndView("index", "listaProdotti", service.getAll());
	}
	
	@PostMapping("/add")
	public String add(@ModelAttribute("datiProdotto") LibriModel p) {
		
		service.add(p);
		
		return "redirect:/";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute("datiProdotto") LibriModel p) {
		service.update(p);
		
		return "redirect:/";
	}
	/*
	@GetMapping("/delete")
	public String delete(@RequestParam("id") String idProdotto) {
		if(idProdotto != null)
			service.delete(Integer.parseInt(idProdotto));
		
		return "redirect:/";
	}*/

}
