package edu.uvm.survery.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.uvm.survery.core.extjs.ExtData;
import edu.uvm.survery.core.model.StatusGeneral;
import edu.uvm.survery.core.service.IStatusGeneralService;
import edu.uvm.survery.core.utils.ExtUtils;

@RestController
@RequestMapping("/status/")
public class StatusGeneralController {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IStatusGeneralService statusGeneralService;
	
	/**
	 * Obtiene todos los estatus de registro
	 * @return ExtData 				data: models 	|> Modelos de StatusGeneral
	 * @throws ServletException
	 */
	@RequestMapping(value="index.action", method=RequestMethod.GET)
	public ExtData indexStatusGeneral() throws ServletException {
		
		ExtData response = new ExtData(); Map<String, Object> data = new HashMap<String, Object>();
		String method = "indexStatusGeneral";
		logger.trace("Controller > " + method);
		
		try {
			List<StatusGeneral> status = statusGeneralService.all();
			data.put("models", status);
			
			return ExtUtils.loadResponse(response, data);
		} catch(Exception ex) {
			return ExtUtils.returnException(response, method, ex.getMessage());
		}//end try
		
	}//end indexStatusGeneral()
	
	/**
	 * Obtiene un estatus de registro por su id
	 * @param id 					Id de estatus de registro
	 * @return ExtData 				data: model > Modelo de StatusGeneral
	 * @throws ServletException
	 */
	@RequestMapping(value="view.action", method=RequestMethod.GET)
	public ExtData viewStatusGeneral(
			@RequestParam(value="id") Integer id) throws ServletException {
		
		ExtData response = new ExtData(); Map<String, Object> data = new HashMap<String, Object>();
		String method = "viewStatusGeneral";
		logger.trace("Controller > " + method);
		
		try {
			StatusGeneral status = statusGeneralService.findById(id);
			data.put("model", status);
			
			return ExtUtils.loadResponse(response, data);
		} catch(Exception ex) {
			return ExtUtils.returnException(response, method, ex.getMessage());
		}//end try
		
	}
}
