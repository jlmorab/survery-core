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
import edu.uvm.survery.core.model.OpcionPregunta;
import edu.uvm.survery.core.model.StatusGeneral;
import edu.uvm.survery.core.service.IOpcionPreguntaService;
import edu.uvm.survery.core.utils.ExtUtils;

@RestController
@RequestMapping("/opcionpregunta/")
public class OpcionPreguntaController {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IOpcionPreguntaService opcionPreguntaService;
	
	/**
	 * Obtiene todas las opciones de pregunta
	 * @param status		Id de estatus de registro
	 * @param question 		Id de pregunta
	 * @param correct 		Clasificadas como correctas
	 * @return ExtData 		data: models |> Modelos de opciones de pregunta
	 * @throws ServletException
	 */
	@RequestMapping(value="index.action", method=RequestMethod.GET)
	public ExtData indexOpcionPregunta(
			@RequestParam(value="status", required=false) Integer status, 
			@RequestParam(value="question", required=false) Integer question, 
			@RequestParam(value="correct", required=false) Boolean correct) throws ServletException {
		
		ExtData response = new ExtData(); Map<String, Object> data = new HashMap<String, Object>();
		String method = "indexOpcionPregunta";
		logger.trace("Controller > " + method);
		
		try {
			status = (status != null ? status : StatusGeneral.VIGENTE);
			Integer isCorrect = (correct != null ? (correct ? 1 : 0) : null);
			
			List<OpcionPregunta> options = opcionPreguntaService.all(status, question, isCorrect);
			data.put("models", options);
			
			return ExtUtils.loadResponse(response, data);
		} catch(Exception ex) {
			return ExtUtils.returnException(response, method, ex.getMessage());
		}//end try
		
	}//end indexOpcionPregunta()
	
	/**
	 * Obtiene una opcion de pregunta en base a los parámetros proporcionados
	 * @param id 			Id de opción de pregunta
	 * @param question 		Id de pregunta
	 * @param name 			Nombre de pregunta
	 * @return ExtData 		data: model |> Modelo de opción de pregunta
	 * @throws ServletException
	 */
	@RequestMapping(value="view.action", method=RequestMethod.GET)
	public ExtData viewOpcionPregunta(
			@RequestParam(value="id", required=false) Integer id, 
			@RequestParam(value="question", required=false) Integer question, 
			@RequestParam(value="name", required=false) String name) throws ServletException {
		
		ExtData response = new ExtData(); Map<String, Object> data = new HashMap<String, Object>();
		String method = "viewOpcionPregunta";
		logger.trace("Controller > " + method);
		
		try { 
			if(id != null || (question != null && name != null)) {
				OpcionPregunta option = null;
				if(id != null) {
					option = opcionPreguntaService.findById(id);
				} else {
					option = opcionPreguntaService.findByDefinition(question, name);
				}//end if
				data.put("model", option);
			} else {
				return ExtUtils.returnException(response, method, "No se proporcionaron parámetros.");
			}//end if
			
			return ExtUtils.loadResponse(response, data);
		} catch(Exception ex) {
			return ExtUtils.returnException(response, method, ex.getMessage());
		}//end try
		
	}//end viewOpcionPregunta()
	
	/**
	 * Determina si existe una opción de pregunta
	 * @param question 		Id de Pregunta
	 * @param name 			Nombre de OpcionPregunta
	 * @return ExtData 		data: exist 	|> True|False
	 * 							  model 	|> Modelo de OpcionPregunta
	 * @throws ServletException
	 */
	@RequestMapping(value="exist.action", method=RequestMethod.GET)
	public ExtData existOpcionPregunta(
			@RequestParam(value="question") Integer question, 
			@RequestParam(value="name") String name) throws ServletException {
		
		ExtData response = new ExtData(); Map<String, Object> data = new HashMap<String, Object>();
		String method = "existOpcionPregunta";
		logger.trace("Controller > " + method);
		
		try {
			OpcionPregunta option = opcionPreguntaService.findByDefinition(question, name);
			data.put("exist", option != null ? true : false);
			data.put("model", option);
			
			return ExtUtils.loadResponse(response, data);
		} catch(Exception ex) {
			return ExtUtils.returnException(response, method, ex.getMessage());
		}//end try
		
	}//end existOpcionPregunta()
	
	/**
	 * Registra una opción de respuesta
	 * @param question 		Id de pregunta
	 * @param name 			Nombre de opción de pregunta
	 * @return ExtData 		data: model |> Modelo de opción de pregunta
	 * @throws ServletException
	 */
	@RequestMapping(value="create.action", method=RequestMethod.POST)
	public ExtData createOpcionPregunta(
			@RequestParam(value="question") Integer question, 
			@RequestParam(value="name") String name) throws ServletException {
		
		ExtData response = new ExtData(); Map<String, Object> data = new HashMap<String, Object>();
		String method = "createOpcionPregunta";
		logger.trace("Controller > " + method);
		
		try {
			OpcionPregunta option = opcionPreguntaService.create(response, question, name);
			data.put("model", option);
			
			return ExtUtils.loadResponse(response, data);
		} catch(Exception ex) {
			return ExtUtils.returnException(response, method, ex.getMessage());
		}//end try
		
	}//end createOpcionPregunta()
	
	/**
	 * Elimina una opción de pregunta
	 * @param id 		Id de OpcionPregunta
	 * @return ExtData
	 * @throws ServletException
	 */
	@RequestMapping(value="delete.action", method=RequestMethod.POST)
	public ExtData deleteOpcionPregunta(
			@RequestParam(value="id") Integer id) throws ServletException {
		
		ExtData response = new ExtData();
		String method = "deleteOpcionPregunta";
		logger.trace("Controller > " + method);
		
		try {
			OpcionPregunta option = opcionPreguntaService.findById(id);
			
			if(option != null) {
				opcionPreguntaService.delete(option);
			} else {
				String msg = "Opcion de Pregunta(" + id + ") no fue localizada.";
				logger.error(method + ": " + msg);
				return ExtUtils.returnException(response, method, msg);
			}//end if
			
			return ExtUtils.loadResponse(response);
		} catch(Exception ex) {
			return ExtUtils.returnException(response, method, ex.getMessage());
		}//end try
		
	}//end deleteOpcionPregunta()
}
