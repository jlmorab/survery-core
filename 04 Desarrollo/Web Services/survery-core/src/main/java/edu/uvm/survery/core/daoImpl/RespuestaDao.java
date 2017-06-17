package edu.uvm.survery.core.daoImpl;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TransactionRequiredException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.uvm.survery.core.dao.IRespuestaDao;
import edu.uvm.survery.core.model.Respuesta;
import edu.uvm.survery.core.model.StatusGeneral;
import edu.uvm.survery.core.service.IStatusGeneralService;

@Repository
@SuppressWarnings("unused")
public class RespuestaDao implements IRespuestaDao {

	@Autowired
	private IStatusGeneralService statusGeneralService;
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private final String prefix 		= "res", 
						 model 			= "Respuesta",
						 genericTable 	= "FROM " + model + " " + prefix + " ";
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	@Transactional
	public Respuesta addAndUpdate(Respuesta entity) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException {
		String method = "addAndUpdate";
		logger.trace("Dao > " + method);
		
		try {
			if(entity.getI_respuesta() != null) {
				Respuesta answer = findById(entity.getI_respuesta());
				
				if(answer == null) {
					entity.setI_respuesta(null);
					entityManager.persist(entity);
					return entity;
				} else {
					return entityManager.merge(entity);
				}//end if
			} else {
				entityManager.persist(entity);
				return entity;
			}
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
	}//end addAndUpdate()

	@Transactional
	public Boolean delete(Respuesta entity) throws IllegalArgumentException, TransactionRequiredException {
		String method = "delete";
		logger.trace(method);
		
		Boolean result = false;
		try {
			Respuesta answer = findById(entity.getI_respuesta());
			
			if(answer != null) {
				answer.setI_respuesta_status(statusGeneralService.findById(StatusGeneral.VIGENTE));
				entityManager.merge(entity);
				result = true;
			} else { 
				logger.error(method + ": Respuesta(" + entity.getI_respuesta() + ") no fue localizada.");
			}//end if
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
		return result;
		
	}//end delete()

	public Respuesta findById(Long id) throws IllegalArgumentException {
		String method = "findById";
		logger.trace("Dao > " + method);
		
		try {
			return entityManager.find(Respuesta.class, id);
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end tru
	}//end findById()

}
