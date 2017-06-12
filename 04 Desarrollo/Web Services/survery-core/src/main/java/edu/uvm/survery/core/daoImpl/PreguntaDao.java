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

import edu.uvm.survery.core.dao.IPreguntaDao;
import edu.uvm.survery.core.model.Pregunta;
import edu.uvm.survery.core.model.StatusGeneral;
import edu.uvm.survery.core.service.IStatusGeneralService;
import edu.uvm.survery.core.utils.PersistenceUtils;

@Repository
public class PreguntaDao implements IPreguntaDao {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private final String prefix 		= "pre", 
						 model 			= "Pregunta", 
						 genericTable 	= "FROM " + model + " " + prefix + " ";
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	@Autowired
	private IStatusGeneralService statusGeneralService;

	@Transactional
	public Pregunta addAndUpdate(Pregunta entity) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException {
		String method = "addAndUpdate";
		logger.trace("Dao > " + method);
		
		try {
			Pregunta question = findByDefinition(entity.getI_pregunta_encuesta().getI_encuesta(), entity.getN_pregunta());
			if(question == null) {
				entityManager.persist(entity);
				return entity; 
			} else {
				question.setI_pregunta_status(statusGeneralService.findById(StatusGeneral.VIGENTE));
				question.setQ_pregunta_orden(entity.getQ_pregunta_orden());
				question = entityManager.merge(entity);
				return question;
			}//end if
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
	}//end addAndUpdate()

	@Transactional
	public Boolean delete(Pregunta entity) throws IllegalArgumentException, TransactionRequiredException {
		String method = "delete";
		logger.trace("Dao > " + method);
		
		Boolean result = false;
		try {
			Pregunta question = findById(entity.getI_pregunta());
			if(question != null) {
				question.setI_pregunta_status(statusGeneralService.findById(StatusGeneral.CANCELADO));
				entityManager.merge(question);
				result = true;
			} else {
				logger.error(method + ": Pregunta(" + entity.getI_pregunta() + ") no fue localizada.");
			}//end if
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
		return result;
	}//end delete()

	public Pregunta findById(Integer id) throws IllegalArgumentException {
		String method = "findById";
		logger.trace("Dao > " + method);
		
		try {
			return entityManager.find(Pregunta.class, id);
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
	}//end findById()

	@SuppressWarnings("unchecked")
	public Pregunta findByDefinition(Integer survery, String name) throws IllegalArgumentException {
		String method = "findByName";
		logger.trace("Dao > " + method);
		
		try {
			String query = genericTable + 
						   "WHERE " + prefix + ".i_pregunta_encuesta.i_encuesta = :survery AND " +
						   			  prefix + ".n_pregunta = :name";
			Query q = entityManager.createQuery(query);
			q.setParameter("survery", survery);
			q.setParameter("name", name);
			List<Pregunta> questions = q.getResultList();
			return (Pregunta) PersistenceUtils.getFirstResult(questions);
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
	}//end findByDefinition()

	@SuppressWarnings("unchecked")
	public List<Pregunta> all(Integer status, Integer survery) throws IllegalArgumentException {
		String method = "all";
		logger.trace("Dao > " + method);
		
		try {
			String condition = (status != null ? prefix + ".i_pregunta_status.i_status_general = :status" : "");
				   condition = condition + (condition.length() > 0 ? " AND " : "") + (survery != null ? prefix + ".i_pregunta_encuesta.i_encuesta = :survery": "");
				   
			String query = genericTable + 
						   (condition.length() > 0 ? "WHERE " : "") + condition;
			Query q = entityManager.createQuery(query);
			if(status != null) { q.setParameter("status", status); }
			if(survery != null) { q.setParameter("survery", survery); }
			return q.getResultList();
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
	}//end all()

}
