package edu.uvm.survery.core.serviceImpl;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uvm.survery.core.dao.IOpcionPreguntaDao;
import edu.uvm.survery.core.model.OpcionPregunta;
import edu.uvm.survery.core.service.IOpcionPreguntaService;

@Service
public class OpcionPreguntaService implements IOpcionPreguntaService {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IOpcionPreguntaDao opcionPreguntaDao;
	
	@Transactional
	public OpcionPregunta addAndUpdate(OpcionPregunta entity) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException {
		logger.trace("Service > addAndUpdate");
		return opcionPreguntaDao.addAndUpdate(entity);
	}//end addAndUpdate()

	@Transactional
	public Boolean delete(OpcionPregunta entity) throws IllegalArgumentException, TransactionRequiredException {
		logger.trace("Service > delete");
		return opcionPreguntaDao.delete(entity);
	}//end delete()

	public OpcionPregunta findById(Integer id) throws IllegalArgumentException {
		logger.trace("Service > findById");
		return opcionPreguntaDao.findById(id);
	}//end findById()

	public OpcionPregunta findByDefinition(Integer question, String name) throws IllegalArgumentException {
		logger.trace("Service > findByDefinition");
		return opcionPreguntaDao.findByDefinition(question, name);
	}//end findByDefinition();

	public List<OpcionPregunta> all() {
		return all(null, null, null);
	}//end all()

	public List<OpcionPregunta> all(Integer status) throws IllegalArgumentException {
		return all(status, null, null);
	}//end all()

	public List<OpcionPregunta> all(Integer status, Integer question) throws IllegalArgumentException {
		return all(status, question, null);
	}//end all()

	public List<OpcionPregunta> all(Integer status, Integer question, Integer isCorrect) throws IllegalArgumentException {
		logger.trace("Service > all");
		return opcionPreguntaDao.all(status, question, isCorrect);
	}//end all()

}
