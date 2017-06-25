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
import edu.uvm.survery.core.model.TipoPregunta;
import edu.uvm.survery.core.service.ITipoPreguntaService;
import edu.uvm.survery.core.utils.ExtUtils;

@RestController
@RequestMapping("/TipoPregunta/")
public class TipoPreguntaController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private ITipoPreguntaService TipoPreguntaService;
	
	/**
	 * Obtiene todos los tipos de pregunta
	 * @param status			Id de estatus de registro
	 * @return ExtData 			data: models |> Modelo de TipoPregunta
	 * @throws ServletException
	 */
	@RequestMapping(value="index.action", method=RequestMethod.GET)
	public ExtData indexTipoPregunta(
			@RequestParam(value="status", required=false) Integer status) throws ServletException {
		
		ExtData response = new ExtData(); Map<String, Object> data = new HashMap<String, Object>();
		String method = "indexTipoPregunta";
		logger.trace("Controller > " + method);
		
		try {
			status = (status != null ? status : StatusGeneral.VIGENTE);
			List<TipoPregunta> types = TipoPreguntaService.all(status);
			data.put("models", types);
			
			return ExtUtils.loadResponse(response, data);
		} catch(Exception ex) {
			return ExtUtils.returnException(response, method, ex.getMessage());
		}//end try
		
	}//end indexTipoPregunta()
	
	/**
	 * Obtiene un tipo de pregunta por su id
	 * @param id 			Id de tipo de pregunta
	 * @return ExtData 		data: model |> Modelo de estatus de registro
	 * @throws ServletException
	 */
	@RequestMapping(value="view.action", method=RequestMethod.GET)
	public ExtData viewTipoPregunta(
			@RequestParam(value="id") Integer id) throws ServletException {
		
		ExtData response = new ExtData(); Map<String, Object> data = new HashMap<String, Object>();
		String method = "viewTipoPregunta";
		logger.trace("Controller > " + method);
		
		try {
			TipoPregunta type = TipoPreguntaService.findById(id);
			data.put("type", type);
			
			return ExtUtils.loadResponse(response, data);
		} catch(Exception ex) {
			return ExtUtils.returnException(response, method, ex.getMessage());
		}//end try
		
	}//end viewTipoPregunta()
}
