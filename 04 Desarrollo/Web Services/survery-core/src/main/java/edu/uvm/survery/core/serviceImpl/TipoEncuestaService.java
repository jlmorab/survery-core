package edu.uvm.survery.core.serviceImpl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uvm.survery.core.dao.ITipoEncuestaDao;
import edu.uvm.survery.core.model.TipoEncuesta;
import edu.uvm.survery.core.service.ITipoEncuestaService;

@Service
public class TipoEncuestaService implements ITipoEncuestaService {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private ITipoEncuestaDao tipoEncuestaDao;

	public TipoEncuesta findById(Integer id) throws IllegalArgumentException {
		logger.trace("Service > findById");
		return tipoEncuestaDao.findById(id); 
	}//end findById()

	public List<TipoEncuesta> all() {
		return all(null);
	}//end all()
	
	public List<TipoEncuesta> all(Integer status) throws IllegalArgumentException {
		logger.trace("Service > all");
		return tipoEncuestaDao.all(status);
	}//end all()

}
