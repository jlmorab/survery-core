package edu.uvm.survery.core.serviceImpl;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uvm.survery.core.dao.IEncuestaDao;
import edu.uvm.survery.core.extjs.ExtData;
import edu.uvm.survery.core.extjs.ExtData.FlashType;
import edu.uvm.survery.core.model.Encuesta;
import edu.uvm.survery.core.model.StatusGeneral;
import edu.uvm.survery.core.model.TipoEncuesta;
import edu.uvm.survery.core.service.IEncuestaService;
import edu.uvm.survery.core.service.IStatusGeneralService;
import edu.uvm.survery.core.service.ITipoEncuestaService;

@Service
public class EncuestaService implements IEncuestaService {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IEncuestaDao encuestaDao;
	
	@Autowired
	private IStatusGeneralService statusGeneralService;
	
	@Autowired
	private ITipoEncuestaService tipoEncuestaService;
	
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
	
	public Encuesta create(ExtData response, Integer typeId, String name) throws IllegalArgumentException {
		String method = "create";
		logger.trace("Service > " + method);
		
		Encuesta result = null;
		try {
			TipoEncuesta type = tipoEncuestaService.findById(typeId);
			Encuesta entity = encuestaDao.findByName(name);
			
			if(entity == null) {
				entity = new Encuesta(statusGeneralService.findById(StatusGeneral.VIGENTE), type, name);
				result = encuestaDao.addAndSave(entity);
			} else {
				String msg = "Encuesta ya existe.";
				response.addFlash(FlashType.ERROR, msg);
				logger.error(method + ": " + msg);
			}//end if
		} catch(Exception ex) {
			logger.error(method + ": " + ex.getMessage());
			response.addFlash(FlashType.ERROR, ex.getMessage());
		}//end try
		return result;
		
	}//end create()

}
