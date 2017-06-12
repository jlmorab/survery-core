package edu.uvm.survery.core.serviceImpl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uvm.survery.core.dao.IStatusGeneralDao;
import edu.uvm.survery.core.model.StatusGeneral;
import edu.uvm.survery.core.service.IStatusGeneralService;

@Service
public class StatusGeneralService implements IStatusGeneralService {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IStatusGeneralDao statusGeneralDao;
	
	public StatusGeneral findById(Integer id) throws IllegalArgumentException {
		logger.trace("Service > findById");
		return statusGeneralDao.findById(id);
	}

	public List<StatusGeneral> all() {
		logger.trace("Service > all");
		return statusGeneralDao.all();
	}

}
