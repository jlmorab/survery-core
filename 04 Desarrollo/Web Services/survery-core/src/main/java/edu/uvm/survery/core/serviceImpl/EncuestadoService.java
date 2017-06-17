package edu.uvm.survery.core.serviceImpl;

import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uvm.survery.core.dao.IEncuestadoDao;
import edu.uvm.survery.core.model.Encuestado;
import edu.uvm.survery.core.service.IEncuestaService;
import edu.uvm.survery.core.service.IEncuestadoService;
import edu.uvm.survery.core.service.IStatusGeneralService;

@Service
public class EncuestadoService implements IEncuestadoService {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IEncuestadoDao encuestadoDao;
	
	@Autowired
	private IStatusGeneralService statusGeneralService;
	
	@Autowired
	private IEncuestaService encuestaService;
	
	@Transactional
	public Encuestado addAndUpdate(Encuestado entity) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException {
		logger.trace("Service > addAndUpdate");
		return encuestadoDao.addAndUpdate(entity);
	}//end addAndUpdate()

	@Transactional
	public Boolean delete(Encuestado entity) throws IllegalArgumentException, TransactionRequiredException {
		logger.trace("Service > delete");
		return encuestadoDao.delete(entity);
	}//end delete()

	public Encuestado findById(Integer id) throws IllegalArgumentException {
		logger.trace("Service > findById");
		return encuestadoDao.findById(id);
	}//end findById()
	
	public void obtainReferenceFields(Encuestado entity, Integer status, Integer survery) throws IllegalArgumentException {
		String method  = "obtainReferenceFields";
		logger.trace("Controller > " + method);
		
		try {
			if(status != null) { entity.setI_encuestado_status( statusGeneralService.findById(status) ); }
			if(survery != null) { entity.setI_encuestado_encuesta( encuestaService.findById(survery) ); }
		} catch(Exception ex) {
			logger.error(method + ": " + ex.getMessage());
		}//end try
	}//end obtainReferenceFields()

}
