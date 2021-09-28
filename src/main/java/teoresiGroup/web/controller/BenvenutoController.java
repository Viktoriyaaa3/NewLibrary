package teoresiGroup.web.controller;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class BenvenutoController {
	//
	private final static Logger log= Logger.getLogger(BenvenutoController.class.getName());
	@GetMapping("/ciao")

	//@RequestMapping("/")
public String ciao(Model model) {
		log.info(model);
		model.addAttribute("mex", "Hello");
	return "welcome";
}
}
