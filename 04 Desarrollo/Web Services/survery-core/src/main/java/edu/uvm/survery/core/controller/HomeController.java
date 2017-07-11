package edu.uvm.survery.core.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/home/")
public class HomeController {

	protected final Log logger = LogFactory.getLog(getClass());
	
	/**
	 * Despliegue de fontend de la aplicación
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "main.action", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("AIR :: Administracion Integral de Recursos > Desplegado...");
		model.addAttribute("controllerMessage", "This is the message from the controller!");
		return "home";
	}//end home()
	
}
