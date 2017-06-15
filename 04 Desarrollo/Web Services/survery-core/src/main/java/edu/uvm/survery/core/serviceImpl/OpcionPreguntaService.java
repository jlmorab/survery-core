package edu.uvm.survery.core.serviceImpl;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uvm.survery.core.dao.IOpcionPreguntaDao;
import edu.uvm.survery.core.extjs.ExtData;
import edu.uvm.survery.core.extjs.ExtData.FlashType;
import edu.uvm.survery.core.model.OpcionPregunta;
import edu.uvm.survery.core.model.Pregunta;
import edu.uvm.survery.core.model.StatusGeneral;
import edu.uvm.survery.core.service.IOpcionPreguntaService;
import edu.uvm.survery.core.service.IPreguntaService;
import edu.uvm.survery.core.service.IStatusGeneralService;

@Service
public class OpcionPreguntaService implements IOpcionPreguntaService {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IOpcionPreguntaDao opcionPreguntaDao;
	
	@Autowired
	private IStatusGeneralService statusGeneralService;
	
	@Autowired
	private IPreguntaService preguntaService;
	
	@Transactional
	public OpcionPregunta addAndUpdate(OpcionPregunta entity) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException {
		logger.trace("Service > addAndUpdate");
		return opcionPreguntaDao.addAndUpdate(entity);
	}//end addAndUpdate()

	@Transactional
	public Boolean delete(OpcionPregunta entity) throws IllegalArgumentException, TransactionRequiredException {
		logger.trace("Service > delete");
		return opcionPreguntaDao.delete(entity);
	}//end delete()

	public OpcionPregunta findById(Integer id) throws IllegalArgumentException {
		logger.trace("Service > findById");
		return opcionPreguntaDao.findById(id);
	}//end findById()

	public OpcionPregunta findByDefinition(Integer question, String name) throws IllegalArgumentException {
		logger.trace("Service > findByDefinition");
		return opcionPreguntaDao.findByDefinition(question, name);
	}//end findByDefinition();

	public List<OpcionPregunta> all() {
		return all(null, null, null);
	}//end all()

	public List<OpcionPregunta> all(Integer status) throws IllegalArgumentException {
		return all(status, null, null);
	}//end all()

	public List<OpcionPregunta> all(Integer status, Integer question) throws IllegalArgumentException {
		return all(status, question, null);
	}//end all()

	public List<OpcionPregunta> all(Integer status, Integer question, Integer isCorrect) throws IllegalArgumentException {
		logger.trace("Service > all");
		return opcionPreguntaDao.all(status, question, isCorrect);
	}//end all()

	public OpcionPregunta create(ExtData response, Integer questionId, String name) throws IllegalArgumentException {
		String method = "create";
		logger.trace("Service > " + method);
		
		OpcionPregunta result = null;
		try {
			Pregunta question = preguntaService.findById(questionId);
			OpcionPregunta entity = opcionPreguntaDao.findByDefinition(questionId, name);
			
			if(entity == null) {
				entity = new OpcionPregunta(statusGeneralService.findById(StatusGeneral.VIGENTE), question, name);
				result = opcionPreguntaDao.addAndUpdate(entity);
			} else {
				String msg = "Opción de pregunta ya existe.";
				response.addFlash(FlashType.ERROR, msg);
				logger.error(method + ": " + msg);
			}//end if
		} catch(Exception ex) {
			logger.error(method + ": " + ex.getMessage());
			response.addFlash(FlashType.ERROR, ex.getMessage());
		}//end try
		return result;
		
	}//end create()
}
