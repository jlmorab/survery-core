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
import edu.uvm.survery.core.model.Pregunta;
import edu.uvm.survery.core.service.IPreguntaService;

@Service
public class PreguntaService implements IPreguntaService {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IPreguntaDao preguntaDao;

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

}
