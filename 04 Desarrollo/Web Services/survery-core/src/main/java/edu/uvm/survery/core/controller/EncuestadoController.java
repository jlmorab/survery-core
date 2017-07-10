package edu.uvm.survery.core.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.uvm.survery.core.extjs.ExtData;
import edu.uvm.survery.core.extjs.ExtData.FlashType;
import edu.uvm.survery.core.model.Encuestado;
import edu.uvm.survery.core.model.Respuesta;
import edu.uvm.survery.core.model.StatusGeneral;
import edu.uvm.survery.core.service.IEncuestadoService;
import edu.uvm.survery.core.service.IRespuestaService;
import edu.uvm.survery.core.utils.ExtUtils;

@RestController
@RequestMapping("/encuestado/")
public class EncuestadoController {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IEncuestadoService encuestadoService;
	
	@Autowired
	private IRespuestaService respuestaService;
	
	/**
	 * Registra un encuestado y sus respuestas de encuesta
	 * @param survery 		Id de Encuesta
	 * @param contact 		Nombre de contacto
	 * @param email			Email
	 * @param date 			Fecha de realización de la encuesta
	 * @param answers 		Modelos de Respuesta
	 * @return ExtData
	 * @throws ServletException
	 */
	@RequestMapping(value="register-respondent.action", method=RequestMethod.POST)
	public ExtData registerRespondentEncuestado(
			@RequestParam(value="survery") Integer survery, 
			@RequestParam(value="contact") String contact, 
			@RequestParam(value="email", required=false) String email, 
			@RequestParam(value="date") Date date, 
			@RequestParam(value="answers") List<Respuesta> answers) throws ServletException {
		
		ExtData response = new ExtData();
		String method = "registerRespondentEncuestado";
		logger.trace("Controller > " + method);
		
		try {
			Encuestado respondent = new Encuestado(null, null, contact, email, date);
			encuestadoService.obtainReferenceFields(respondent, StatusGeneral.VIGENTE, survery);
			respondent = encuestadoService.addAndUpdate(respondent);
			
			if(respondent != null) {
				for(Respuesta answer : answers) {
					answer.setI_respuesta_encuestado(respondent);
					respuestaService.obtainReferenceFields(
						answer, 
						StatusGeneral.VIGENTE, 
						null, 
						answer.getI_respuesta_pregunta().getI_pregunta(), 
						answer.getI_respuesta_opcion_pregunta().getI_opcion_pregunta()
					);
					respuestaService.addAndUpdate(answer);
				}//end for
			} else {
				String msg = "No fue posible registrar al encuestado.";
				response.addFlash(FlashType.ERROR, msg);
				logger.error(method + ": " + msg);
			}//end if
			
			return ExtUtils.loadResponse(response);
		} catch(Exception ex) {
			return ExtUtils.returnException(response, method, ex.getMessage());
		}//end try
		
	}//end registerRespondentEncuestado()
	
}
