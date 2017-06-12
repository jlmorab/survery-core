package edu.uvm.survery.core.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.uvm.survery.core.service.ITipoEncuestaService;

@RestController
@RequestMapping("/tipoencuesta/")
public class TipoEncuestaController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private ITipoEncuestaService tipoEncuestaService;
	
//	@RequestMapping(value="index.action", method=RequestMethod.GET)
//	public 
	
}
