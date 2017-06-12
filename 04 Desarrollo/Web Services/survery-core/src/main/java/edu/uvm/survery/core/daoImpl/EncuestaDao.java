package edu.uvm.survery.core.daoImpl;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.uvm.survery.core.dao.IEncuestaDao;
import edu.uvm.survery.core.model.Encuesta;
import edu.uvm.survery.core.model.StatusGeneral;
import edu.uvm.survery.core.service.IStatusGeneralService;
import edu.uvm.survery.core.utils.PersistenceUtils;

@Repository
public class EncuestaDao implements IEncuestaDao {

	@Autowired
	private IStatusGeneralService statusGeneralService;
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private final String prefix 		= "enc", 
						 model 			= "Encuesta", 
						 genericTable 	= "FROM " + model + " " + prefix + " ";
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager; 
	
	@Transactional
	public Encuesta addAndSave(Encuesta entity) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException {
		String method = "addAndSave";
		logger.trace("Dao > " + method);
		
		try {
			Encuesta survery = findByName(entity.getN_encuesta());
			if(survery == null) {
				entityManager.persist(entity);
				return entity;
			} else {
				entity.setI_encuesta_status(statusGeneralService.findById(StatusGeneral.VIGENTE));
				entity.setI_encuesta_tipo_encuesta(entity.getI_encuesta_tipo_encuesta());
				return entityManager.merge(entity);
			}//end if
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
	}//end addAndSave()

	@Transactional
	public boolean delete(Encuesta entity) throws IllegalArgumentException, TransactionRequiredException {
		String method = "delete";
		logger.trace("Dao > " + method);
		
		Boolean result = false;
		try {
			Encuesta survery = findById(entity.getI_encuesta());
			if(survery != null) {
				survery.setI_encuesta_status(statusGeneralService.findById(StatusGeneral.CANCELADO));
				entityManager.merge(survery);
				result = true;
			} else {
				logger.error(method + ": Encuesta(" + entity.getI_encuesta() + ") no fue localizada.");
			}//end if
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
		return result;
	}//end delete()

	public Encuesta findById(Integer id) throws IllegalArgumentException {
		String method = "findById";
		logger.trace("Dao > " + method);
		
		try {
			return entityManager.find(Encuesta.class, id);
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
	}//end findById()
	
	@SuppressWarnings("unchecked")
	public Encuesta findByName(String name) throws IllegalArgumentException {
		String method = "findByName";
		logger.trace("Dao > " + method);
		
		try {
			String query = genericTable +
						   "WHERE " + prefix + ".n_encuesta = :name";
			Query q = entityManager.createQuery(query);
			q.setParameter("name", name);
			List<Encuesta> surverys = q.getResultList();
			return PersistenceUtils.getFirstResult(surverys);
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
	}//end findByName()

	@SuppressWarnings("unchecked")
	public List<Encuesta> all(Integer status, Integer type) throws IllegalArgumentException {
		String method = "all";
		logger.trace("Dao > " + method);
		
		try {
			String condition = (status != null ? prefix + ".i_encuesta_status.i_status_general = :status " : ""); 
				   condition = condition + (condition.length() > 0 ? " AND " : "") + (type != null ? prefix + ".i_encuesta_tipo_encuesta = :type" : "");
			
			String query = genericTable + 
						   (condition.length() > 0 ? "WHERE " + condition : "");
			Query q = entityManager.createQuery(query);
			if(status != null) { q.setParameter("status", status); }
			if(type != null) { q.setParameter("type", type); }
			return q.getResultList();
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
	}//end all()

}
