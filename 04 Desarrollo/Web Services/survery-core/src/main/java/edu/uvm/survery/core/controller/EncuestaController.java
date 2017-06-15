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
import edu.uvm.survery.core.model.Encuesta;
import edu.uvm.survery.core.model.StatusGeneral;
import edu.uvm.survery.core.service.IEncuestaService;
import edu.uvm.survery.core.utils.ExtUtils;

@RestController
@RequestMapping("/encuesta/")
public class EncuestaController {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IEncuestaService encuestaService;
	
	/**
	 * Obtiene todas las encuestas
	 * @param status		Id de estatus de registro
	 * @param type 			Id de tipo de encuesta
	 * @return ExtData 		data: models |> Modelo de encuestas
	 * @throws ServletException
	 */
	@RequestMapping(value="index.action", method=RequestMethod.GET)
	public ExtData indexEncuesta(
			@RequestParam(value="status", required=false) Integer status, 
			@RequestParam(value="type", required=false) Integer type) throws ServletException {
		
		ExtData response = new ExtData(); Map<String, Object> data = new HashMap<String, Object>();
		String method = "indexEncuesta";
		logger.trace("Controller > " + method);
		
		try {
			status = (status != null ? status : StatusGeneral.VIGENTE);
			List<Encuesta> surverys = encuestaService.all(status, type);
			data.put("models", surverys);
			
			return ExtUtils.loadResponse(response, data);
		} catch(Exception ex) {
			return ExtUtils.returnException(response, method, ex.getMessage());
		}//end try
		
	}//end indexEncuesta()
	
	/**
	 * Obtiene una encuesta en base a los parámetros proporcionados
	 * @param id 			Id de la encuesta
	 * @param name 			Nombre de la encuesta
	 * @return ExtData 		data: model |> Modelo de encuesta
	 * @throws ServletException
	 */
	@RequestMapping(value="view.action", method=RequestMethod.GET)
	public ExtData viewEncuesta(
			@RequestParam(value="id", required=false) Integer id, 
			@RequestParam(value="name", required=false) String name) throws ServletException {
		
		ExtData response = new ExtData(); Map<String, Object> data = new HashMap<String, Object>();
		String method = "viewEncuesta";
		logger.trace("Controller > " + method);
		
		try {
			Encuesta survery = null;
			
			if(id != null || name != null) {
				if(id != null) {
					survery = encuestaService.findById(id);
				} else {
					survery = encuestaService.findByName(name);
				}//end if
				data.put("model", survery);
			} else {
				return ExtUtils.returnException(response, method, "No se proporcionaron parámetros.");
			}//end if
			
			return ExtUtils.loadResponse(response, data);
		} catch(Exception ex) {
			return ExtUtils.returnException(response, method, ex.getMessage());
		}//end try
		
	}//end viewEncuesta()
	
	/**
	 * Registra una encuesta
	 * @param type 			Id de tipo de encuesta
	 * @param name			Nombre de encuesta
	 * @return ExtData 		data: model |> Modelo de encuesta
	 * @throws ServletException
	 */
	@RequestMapping(value="create.action", method=RequestMethod.POST)
	public ExtData createEncuesta(
			@RequestParam(value="type") Integer type, 
			@RequestParam(name="name") String name) throws ServletException {
		
		ExtData response = new ExtData(); Map<String, Object> data = new HashMap<String, Object>();
		String method = "createEncuesta";
		logger.trace("Controller > " + method);
		
		try {
			Encuesta survery = encuestaService.create(response, type, name);
			data.put("model", survery);
			
			return ExtUtils.loadResponse(response, data);
		} catch(Exception ex) {
			return ExtUtils.returnException(response, method, ex.getMessage());
		}//end try
		
	}//end createEncuesta()
	
}
