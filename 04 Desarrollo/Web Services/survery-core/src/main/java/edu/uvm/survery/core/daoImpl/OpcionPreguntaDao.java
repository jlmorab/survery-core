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

import edu.uvm.survery.core.dao.IOpcionPreguntaDao;
import edu.uvm.survery.core.model.OpcionPregunta;
import edu.uvm.survery.core.model.StatusGeneral;
import edu.uvm.survery.core.service.IStatusGeneralService;
import edu.uvm.survery.core.utils.PersistenceUtils;

@Repository
public class OpcionPreguntaDao implements IOpcionPreguntaDao {
	
	@Autowired
	private IStatusGeneralService statusGeneralService;
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private final String prefix 		= "opr",
						 model 			= "OpcionPregunta", 
						 genericTable 	= "FROM " + model + " " + prefix + " ";
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@Transactional
	public OpcionPregunta addAndUpdate(OpcionPregunta entity) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException {
		String method = "addAndUpdate";
		logger.trace("Dao > " + method);
		
		try {
			OpcionPregunta option = findByDefinition(entity.getI_opcion_pregunta_pregunta().getI_pregunta(), entity.getN_opcion_pregunta());
			if(option == null) {
				entityManager.persist(entity);
				return entity;
			} else {
				option.setI_opcion_pregunta_status(statusGeneralService.findById(StatusGeneral.VIGENTE));
				option.setF_opcion_pregunta_correcto(entity.getF_opcion_pregunta_correcto());
				option = entityManager.merge(option);
				return option;
			}//end if
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
	}//end addAndUpdate()

	@Transactional
	public Boolean delete(OpcionPregunta entity) throws IllegalArgumentException, TransactionRequiredException {
		String method = "delete";
		logger.trace("Dao > " + method);
		
		Boolean result = false;
		try {
			OpcionPregunta option = findById(entity.getI_opcion_pregunta());
			if(option != null) {
				option.setI_opcion_pregunta_status(statusGeneralService.findById(StatusGeneral.CANCELADO));
				entityManager.merge(option);
				result = true;
			} else {
				logger.error(method + ": Opción de Pregunta(" + entity.getI_opcion_pregunta() + ") no fue localizada.");
			}
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
		return result;
	}//end delete()

	public OpcionPregunta findById(Integer id) throws IllegalArgumentException {
		String method = "findById";
		logger.trace("Dao > " + method);
		
		try {
			return entityManager.find(OpcionPregunta.class, id);
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
	}//end findById()

	@SuppressWarnings("unchecked")
	public OpcionPregunta findByDefinition(Integer question, String name) throws IllegalArgumentException {
		String method = "findByDefinition";
		logger.trace("Dao > " + method);
		
		try {
			String query = genericTable + 
						   "WHERE " + prefix + ".i_opcion_pregunta_pregunta.i_pregunta = :question AND " +
						   			  prefix + ".n_opcion_pregunta = :name";
			Query q = entityManager.createQuery(query);
			q.setParameter("question", question);
			q.setParameter("name", name);
			List<OpcionPregunta> options = q.getResultList();
			return PersistenceUtils.getFirstResult(options);
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
	}//end findByDefinition()

	@SuppressWarnings("unchecked")
	public List<OpcionPregunta> all(Integer status, Integer question, Integer isCorrect) throws IllegalArgumentException {
		String method = "all";
		logger.trace("Dao > " + method);
		
		try {
			String condition = (status != null ? prefix + ".i_opcion_pregunta_status.i_status" : "");
				   condition = condition + (condition.length() > 0 ? " AND " : "") + (question != null ? prefix + ".i_opcion_pregunta_pregunta.i_pregunta = :question" : "");
				   condition = condition + (condition.length() > 0 ? " AND " : "") + (isCorrect != null ? prefix + ".f_opcion_pregunta_correct = :correct" : "");
			
			String query = genericTable + 
						   (condition.length() > 0 ? "WHERE " : "") + condition;
			Query q = entityManager.createQuery(query);
			return q.getResultList();
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
	}//end all()

}
