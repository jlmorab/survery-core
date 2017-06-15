package edu.uvm.survery.core.utils;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uvm.survery.core.extjs.ExtData;
import edu.uvm.survery.core.extjs.ExtData.FlashType;

public class ExtUtils {
	
	protected final static Log logger = LogFactory.getLog(ExtUtils.class);
	
	public static ExtData returnException(ExtData response, String reference, String message) throws IllegalArgumentException {
		String method = "returnException";
		logger.trace("Controller > " + method);
		
		ExtData result = response;
		try {
			logger.error(reference + ": " + message);
			response.addFlash(FlashType.ERROR, message);
			response.setResponse(false, message);
		} catch(Exception ex) {
			logger.error(method + ": " + ex.getMessage());
		}//end try
		return result;
	}//end returnException()
	
	public static ExtData loadResponse(ExtData response) throws IllegalArgumentException {
		return loadResponse(response, null);
	}//end loadResponse()
	
	public static ExtData loadResponse(ExtData response, Map<String, Object> data) throws IllegalArgumentException {
		String method = "loadResponse";
		logger.trace("Controller > " + method);
		
		try {
			if(data!= null) { response.add(data); }
			return response;
		} catch(Exception ex) {
			logger.error(method + ": " + ex.getMessage());
			return new ExtData(false, ex.getMessage());
		}//end try
	}//end loadResponse()

}
