package edu.uvm.survery.core.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import edu.uvm.survery.core.dao.IStatusGeneralDao;
import edu.uvm.survery.core.model.StatusGeneral;

@Repository
public class StatusGeneralDao implements IStatusGeneralDao {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private final String prefix 		= "sta", 
						 model 			= "StatusGeneral",
						 genericTable 	= "FROM " + model + " " + prefix + " ";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public StatusGeneral findById(Integer id) throws IllegalArgumentException {
		String method = "findById";
		logger.trace("Dao > " + method);
		
		try {
			return entityManager.find(StatusGeneral.class, id);
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
	}//end findById()

	@SuppressWarnings("unchecked")
	public List<StatusGeneral> all() {
		String method = "all";
		logger.trace("Dao > " + method);
		
		try {
			Query q = entityManager.createQuery(genericTable);
			return q.getResultList();
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
	}//end all()

}
