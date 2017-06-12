package edu.uvm.survery.core.controller;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.uvm.survery.core.model.StatusGeneral;
import edu.uvm.survery.core.service.IStatusGeneralService;

@RestController
@RequestMapping("/status/")
public class StatusGeneralController {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IStatusGeneralService statusGeneralService;
	
	@RequestMapping("into")
	public String intoStatusGeneral() throws ServletException {
		return "Status General controller";
	}
	
	@RequestMapping(value="one.action", method=RequestMethod.GET)
	public String oneStatusGeneral() throws ServletException {
		StatusGeneral status = statusGeneralService.findById(StatusGeneral.VIGENTE);
		return status != null ? status.toString() : "Status vigente no encontrado";
	}
	
}
