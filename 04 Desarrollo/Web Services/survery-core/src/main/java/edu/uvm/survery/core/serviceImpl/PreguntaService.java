package edu.uvm.survery.core.serviceImpl;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uvm.survery.core.dao.IPreguntaDao;
import edu.uvm.survery.core.extjs.ExtData;
import edu.uvm.survery.core.extjs.ExtData.FlashType;
import edu.uvm.survery.core.model.Encuesta;
import edu.uvm.survery.core.model.Pregunta;
import edu.uvm.survery.core.model.StatusGeneral;
import edu.uvm.survery.core.service.IEncuestaService;
import edu.uvm.survery.core.service.IPreguntaService;
import edu.uvm.survery.core.service.IStatusGeneralService;

@Service
public class PreguntaService implements IPreguntaService {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IPreguntaDao preguntaDao;
	
	@Autowired
	private IStatusGeneralService statusGeneralService;
	
	@Autowired
	private IEncuestaService encuestaService;

	@Transactional
	public Pregunta addAndUpdate(Pregunta entity) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException {
		logger.trace("Service > addAndUpdate");
		return preguntaDao.addAndUpdate(entity);
	}//end addAndUpdate()

	@Transactional
	public Boolean delete(Pregunta entity) throws IllegalArgumentException {
		logger.trace("Service > delete");
		return preguntaDao.delete(entity);
	}//end delete()

	public Pregunta findById(Integer id) throws IllegalArgumentException {
		logger.trace("Service > findById");
		return preguntaDao.findById(id);
	}//end findById()

	public Pregunta findByDefinition(Integer survery, String name) throws IllegalArgumentException {
		logger.trace("Service > findBydefinition");
		return preguntaDao.findByDefinition(survery, name);
	}//end findByDefinition()

	public List<Pregunta> all() {
		return all(null, null);
	}//end all()

	public List<Pregunta> all(Integer status) throws IllegalArgumentException {
		return all(status, null);
	}//end all()

	public List<Pregunta> all(Integer status, Integer survery) throws IllegalArgumentException {
		logger.trace("Service > all");
		return preguntaDao.all(status, survery);
	}//end all()
	
	public Integer consecutiveOrder(Integer survery) throws IllegalArgumentException {
		logger.trace("Service > order");
		return preguntaDao.consecutiveOrder(survery);
	}
	
	public Pregunta create(ExtData response, Integer surveryId, String name) throws IllegalArgumentException {
		String method = "create";
		logger.trace("Service > " + method);
		
		Pregunta result = null;
		try {
			Encuesta survery = encuestaService.findById(surveryId);
			Pregunta entity = preguntaDao.findByDefinition(surveryId, name);
			
			if(entity == null) {
				Integer order = this.consecutiveOrder(surveryId);
				entity = new Pregunta(statusGeneralService.findById(StatusGeneral.VIGENTE), survery, order, name);
				result = preguntaDao.addAndUpdate(entity);
			} else {
				String msg = "Pregunta ya existe.";
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
