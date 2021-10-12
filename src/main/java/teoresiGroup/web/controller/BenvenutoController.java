package teoresiGroup.web.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDateTime;
import java.time.*;
@Controller
@RequestMapping("/")
public class BenvenutoController {
	//
	private final static Logger log= Logger.getLogger(BenvenutoController.class.getName());
	
	//private final static Logger log= LoggerFactory.getLogger(BenvenutoController.class.getName());
	
	@GetMapping("/")

	//@RequestMapping("/")
public String welcome(Model model) {
		log.info("Sono nel metodo iniziale");
		model.addAttribute("mex", "Effettua una scelta");
		
		model.addAttribute("standardDate", new Date());
		model.addAttribute("localDateTime", LocalDateTime.now());
		model.addAttribute("localDate", LocalDate.now());
		model.addAttribute("timestamp", Instant.now());
	return "welcome";
}
}
