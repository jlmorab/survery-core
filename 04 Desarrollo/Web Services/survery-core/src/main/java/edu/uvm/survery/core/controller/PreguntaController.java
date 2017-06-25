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
import edu.uvm.survery.core.model.Pregunta;
import edu.uvm.survery.core.model.StatusGeneral;
import edu.uvm.survery.core.service.IPreguntaService;
import edu.uvm.survery.core.utils.ExtUtils;

@RestController
@RequestMapping("/pregunta/")
public class PreguntaController {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IPreguntaService preguntaService;
	
	/**
	 * Obtiene todas las preguntas
	 * @param status 		Id de estatus de registro
	 * @param survery 		Id de encuesta
	 * @return ExtData 		data: models |> Modelos de preguntas
	 * @throws ServletException
	 */
	@RequestMapping(value="index.action", method=RequestMethod.GET)
	public ExtData indexPregunta(
			@RequestParam(value="status", required=false) Integer status, 
			@RequestParam(value="survery", required=false) Integer survery ) throws ServletException {
		
		ExtData response = new ExtData(); Map<String, Object> data = new HashMap<String, Object>();
		String method = "indexPregunta";
		logger.trace("Controller > " + method);
		
		try {
			status = (status != null ? status : StatusGeneral.VIGENTE);
			
			List<Pregunta> questions = preguntaService.all(status, survery);
			data.put("models", questions);
			
			return ExtUtils.loadResponse(response, data);
		} catch(Exception ex) {
			return ExtUtils.returnException(response, method, ex.getMessage());
		}//end try
		
	}//end indexPregunta()
	
	/**
	 * Obtiene una pregunta en base a los parámetros proporcionados
	 * @param id 			Id de la pregunta
	 * @param survery 		Id de encuesta
	 * @param name 			Nombre de la encuesta
	 * @return ExtData 		data: model |> Modelo de pregunta
	 * @throws ServletException
	 */
	@RequestMapping(value="view.action", method=RequestMethod.GET)
	public ExtData viewPregunta(
			@RequestParam(value="id", required=false) Integer id, 
			@RequestParam(value="survery", required=false) Integer survery,
			@RequestParam(value="name", required=false) String name) throws ServletException {
		
		ExtData response = new ExtData(); Map<String, Object> data = new HashMap<String, Object>();
		String method = "viewPregunta";
		logger.trace("Controller > " + method);
		
		try {
			Pregunta question = null;
			
			if(id != null || (survery != null && name != null)) {
				if(id != null) {
					question = preguntaService.findById(id);
				} else {
					question = preguntaService.findByDefinition(survery, name);
				}//end if
				data.put("model", question);
			} else {
				return ExtUtils.returnException(response, method, "No se proporcionaron parámetros.");
			}//end if
			
			return ExtUtils.loadResponse(response, data); 
		} catch(Exception ex) {
			return ExtUtils.returnException(response, method, ex.getMessage());
		}//end try
		
	}//end viewPregunta()
	
	/**
	 * Registra una pregunta
	 * @param survery 		Id de encuesta
	 * @param type 			Id de tipo de pregunta
	 * @param name			Nombre de pregunta
	 * @return ExtData 		data: model |> Modelo de pregunta
	 * @throws ServletException
	 */
	@RequestMapping(value="create.action", method=RequestMethod.POST)
	public ExtData createPregunta(
			@RequestParam(value="survery") Integer survery, 
			@RequestParam(value="type") Integer type,
			@RequestParam(value="name") String name) throws ServletException {
		
		ExtData response = new ExtData(); Map<String, Object> data = new HashMap<String, Object>();
		String method = "createPregunta";
		logger.trace("Controller > " + method);
		
		try {
			Pregunta question = preguntaService.create(response, survery, type, name);
			data.put("model", question);
			
			return ExtUtils.loadResponse(response, data);
		} catch(Exception ex) {
			return ExtUtils.returnException(response, method, ex.getMessage());
		}//end try
		
	}//end createPregunta()
	
}
