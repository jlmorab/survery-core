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
import edu.uvm.survery.core.model.TipoEncuesta;
import edu.uvm.survery.core.service.ITipoEncuestaService;
import edu.uvm.survery.core.utils.ExtUtils;

@RestController
@RequestMapping("/tipoencuesta/")
public class TipoEncuestaController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private ITipoEncuestaService tipoEncuestaService;
	
	/**
	 * Obtiene todos los tipos de encuesta
	 * @param status			Id de estatus de registro
	 * @return ExtData 			data: models |> Modelo de TipoEncuesta
	 * @throws ServletException
	 */
	@RequestMapping(value="index.action", method=RequestMethod.GET)
	public ExtData indexTipoEncuesta(
			@RequestParam(value="status", required=false) Integer status) throws ServletException {
		
		ExtData response = new ExtData(); Map<String, Object> data = new HashMap<String, Object>();
		String method = "indexTipoEncuesta";
		logger.trace("Controller > " + method);
		
		try {
			status = (status != null ? status : StatusGeneral.VIGENTE);
			List<TipoEncuesta> types = tipoEncuestaService.all(status);
			data.put("models", types);
			
			return ExtUtils.loadResponse(response, data);
		} catch(Exception ex) {
			return ExtUtils.returnException(response, method, ex.getMessage());
		}//end try
		
	}//end indexTipoEncuesta()
	
	/**
	 * Obtiene un tipo de encuesta por su id
	 * @param id 			Id de tipo de encuesta
	 * @return ExtData 		data: model |> Modelo de estatus de registro
	 * @throws ServletException
	 */
	@RequestMapping(value="view.action", method=RequestMethod.GET)
	public ExtData viewTipoEncuesta(
			@RequestParam(value="id") Integer id) throws ServletException {
		
		ExtData response = new ExtData(); Map<String, Object> data = new HashMap<String, Object>();
		String method = "viewTipoEncuesta";
		logger.trace("Controller > " + method);
		
		try {
			TipoEncuesta type = tipoEncuestaService.findById(id);
			data.put("type", type);
			
			return ExtUtils.loadResponse(response, data);
		} catch(Exception ex) {
			return ExtUtils.returnException(response, method, ex.getMessage());
		}//end try
		
	}//end viewTipoEncuesta()
}
