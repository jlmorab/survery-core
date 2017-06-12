package edu.uvm.survery.core.serviceImpl;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uvm.survery.core.dao.IEncuestaDao;
import edu.uvm.survery.core.model.Encuesta;
import edu.uvm.survery.core.service.IEncuestaService;

@Service
public class EncuestaService implements IEncuestaService {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IEncuestaDao encuestaDao;
	
	public Encuesta addAndUpdate(Encuesta entity) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException {
		logger.trace("Service > addAndUpdate");
		return encuestaDao.addAndSave(entity);
	}//end addAndUpdate()

	public Boolean delete(Encuesta entity) throws IllegalArgumentException, TransactionRequiredException {
		logger.trace("Service > delete");
		return encuestaDao.delete(entity);
	}//end delete()

	public Encuesta findById(Integer id) throws IllegalArgumentException {
		logger.trace("Service > findById");
		return encuestaDao.findById(id);
	}//end findById()

	public Encuesta findByName(String name) throws IllegalArgumentException {
		logger.trace("Service > findByName");
		return encuestaDao.findByName(name);
	}//end findByName()

	public List<Encuesta> all() {
		return all(null, null);
	}//end all()

	public List<Encuesta> all(Integer status) throws IllegalArgumentException {
		return all(status, null);
	}//end all()

	public List<Encuesta> all(Integer status, Integer type) throws IllegalArgumentException {
		logger.trace("Service > all");
		return encuestaDao.all(status, type);
	}//end all()

}
