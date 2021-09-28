package teoresiGroup.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import teoresiGroup.web.model.LibriModel;

@Controller
@RequestMapping("/book")
public class LibroController {
	@GetMapping("/lb")
	public String hello(Model model)
	{
		model.addAttribute("nome", "Viktoriya");
		model.addAttribute("cognome", "Tull");
		model.addAttribute("codFiscale", "nvkjfdvlsd");
		model.addAttribute("telefono", "3487084433");
		model.addAttribute("email", "V@a");
		return "libroVista";
		
	}
	
	@PostMapping("/nuovo")
	public String ciao(Model model) {
		model.addAttribute("nome", "Viktoriya");
		model.addAttribute("cognome", "Tull");
		model.addAttribute("codFiscale", "nvkjfdvlsd");
		model.addAttribute("telefono", "3487084433");
		model.addAttribute("email", "V@a");
		
		return "libroVista";
	}

}
