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

import edu.uvm.survery.core.dao.IEncuestadoDao;
import edu.uvm.survery.core.model.Encuestado;
import edu.uvm.survery.core.model.StatusGeneral;
import edu.uvm.survery.core.service.IStatusGeneralService;

@Repository
@SuppressWarnings("unused")
public class EncuestadoDao implements IEncuestadoDao {

	@Autowired
	private IStatusGeneralService statusGeneralService;
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private final String prefix 		= "enc", 
						 model 			= "Encuestado", 
						 genericTable 	= "FROM " + model + " " + prefix + " ";
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	@Transactional
	public Encuestado addAndUpdate(Encuestado entity) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException {
		String method = "addAndUpdate";
		logger.trace("Dao > " + method);
		
		try {
			if(entity.getI_encuestado() != null) {
				Encuestado respondent = findById(entity.getI_encuestado());
				
				if(respondent == null) {
					entity.setI_encuestado(null);
					entityManager.persist(entity);
					return entity;
				} else {
					return entityManager.merge(entity);
				}//end if
			} else {
				entityManager.persist(entity);
				return entity;
			}//end if
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
	}//end addAndUpdate()

	@Transactional
	public Boolean delete(Encuestado entity) throws IllegalArgumentException, TransactionRequiredException {
		String method = "delete";
		logger.trace("Dao > " + method);
		
		Boolean result = false;
		try {
			Encuestado respondent = findById(entity.getI_encuestado());
			
			if(respondent != null) {
				respondent.setI_encuestado_status(statusGeneralService.findById(StatusGeneral.CANCELADO));
				entityManager.merge(respondent);
				result = true;
			} else {
				logger.error(method + ": Encuestado(" + entity.getI_encuestado() + ") no fue localizado.");
			}//end if
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
		return result;
		
	}//end delete()

	public Encuestado findById(Integer id) throws IllegalArgumentException {
		String method = "findById";
		logger.trace("Dao > " + method);
		
		try {
			return entityManager.find(Encuestado.class, id);
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
	}//end findById()

}
