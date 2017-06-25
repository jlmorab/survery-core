package edu.uvm.survery.core.serviceImpl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uvm.survery.core.dao.ITipoPreguntaDao;
import edu.uvm.survery.core.model.TipoPregunta;
import edu.uvm.survery.core.service.ITipoPreguntaService;

@Service
public class TipoPreguntaService implements ITipoPreguntaService {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private ITipoPreguntaDao TipoPreguntaDao;

	public TipoPregunta findById(Integer id) throws IllegalArgumentException {
		logger.trace("Service > findById");
		return TipoPreguntaDao.findById(id); 
	}//end findById()

	public List<TipoPregunta> all() {
		return all(null);
	}//end all()
	
	public List<TipoPregunta> all(Integer status) throws IllegalArgumentException {
		logger.trace("Service > all");
		return TipoPreguntaDao.all(status);
	}//end all()

}
