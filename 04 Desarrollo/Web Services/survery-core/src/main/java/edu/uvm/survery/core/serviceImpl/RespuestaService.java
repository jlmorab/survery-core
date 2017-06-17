package edu.uvm.survery.core.serviceImpl;

import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uvm.survery.core.dao.IRespuestaDao;
import edu.uvm.survery.core.model.Respuesta;
import edu.uvm.survery.core.model.StatusGeneral;
import edu.uvm.survery.core.service.IEncuestadoService;
import edu.uvm.survery.core.service.IOpcionPreguntaService;
import edu.uvm.survery.core.service.IPreguntaService;
import edu.uvm.survery.core.service.IRespuestaService;
import edu.uvm.survery.core.service.IStatusGeneralService;

@Service
public class RespuestaService implements IRespuestaService {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IRespuestaDao respuestaDao;
	
	@Autowired
	private IStatusGeneralService statusGeneralService;
	
	@Autowired
	private IEncuestadoService encuestadoService;
	
	@Autowired
	private IPreguntaService preguntaService;
	
	@Autowired
	private IOpcionPreguntaService opcionPreguntaService;

	@Transactional
	public Respuesta addAndUpdate(Respuesta entity) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException {
		logger.trace("Service > addAndUpdate");
		return respuestaDao.addAndUpdate(entity);
	}//end addAndUpdate()

	@Transactional
	public Boolean delete(Respuesta entity) throws IllegalArgumentException, TransactionRequiredException {
		logger.trace("Service > delete");
		return respuestaDao.delete(entity);
	}//end delete()

	public Respuesta findById(Long id) throws IllegalArgumentException {
		logger.trace("Service > findById");
		return respuestaDao.findById(id);
	}//end findById()
	
	public void obtainReferenceFields(Respuesta entity, Integer status, Integer respondent, Integer question, Integer questionOption) throws IllegalArgumentException {
		String method = "obtainReferenceFields";
		logger.trace("Service > " + method);
		
		try {
			if(status != null) { statusGeneralService.findById(StatusGeneral.VIGENTE); }
			if(respondent != null) { encuestadoService.findById(respondent); }
			if(question != null) { preguntaService.findById(question); }
			if(questionOption != null) { opcionPreguntaService.findById(questionOption); }
		} catch(Exception ex) {
			logger.error(method + ": " + ex.getMessage());
		}//end try
	}//end obtainReferenceFields()

}
