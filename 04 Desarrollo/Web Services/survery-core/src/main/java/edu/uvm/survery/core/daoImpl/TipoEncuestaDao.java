package edu.uvm.survery.core.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import edu.uvm.survery.core.dao.ITipoEncuestaDao;
import edu.uvm.survery.core.model.TipoEncuesta;

@Repository
public class TipoEncuestaDao implements ITipoEncuestaDao {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private final String prefix 		= "ten", 
						 model 			= "TipoEncuesta", 
						 genericTable 	= "FROM " + model + " " + prefix + " ";
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	public TipoEncuesta findById(Integer id) throws IllegalArgumentException {
		String method = "findById";
		logger.trace("Dao > " + method);
		
		try {
			return entityManager.find(TipoEncuesta.class, id);
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
	}//end findById()

	@SuppressWarnings("unchecked")
	public List<TipoEncuesta> all(Integer status) throws IllegalArgumentException {
		String method = "all";
		logger.trace("Dao > " + method);
		
		try {
			String query = genericTable + 
						   (status != null ? "WHERE i_tipo_encuesta_status.i_status_general = :status" : "");
			
			Query q = entityManager.createQuery(query);
			if(status != null) { q.setParameter("status", status); }
			return q.getResultList();
		} catch(RuntimeException ex) {
			logger.error(method + ": " + ex.getMessage());
			throw ex;
		}//end try
	}//end all()

}
