package edu.uvm.survery.core.extjs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExtData {

	@JsonProperty
	private boolean success;
	
	@JsonProperty
	private final List<Object> data = new ArrayList<Object>();
	
	@JsonProperty
	private long total = 0L;
	
	@JsonProperty
	private String message;
	
	@JsonProperty
	private final Map< String, List<String> > flash = new HashMap< String, List<String> >();
	
	@JsonProperty
	private long countFlash = 0L;
	
	public static enum FlashType {
		ERROR, SUCCESS, WARNING
	}//end enum
	
	
	public ExtData() {
		this.assignParams(true, "");
	}//end constructor()
	
	public ExtData(boolean success) {
		this.assignParams(success, null);
	}//end constructor()
	
	public ExtData(boolean success, String message) {
		this.assignParams(success, message);
	}//end constructor()
	
	
	public boolean getSuccess() {
		return this.success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	public long getTotal() {
		return this.total;
	}
	
	public long getCountFlash() {
		return this.countFlash;
	}
	
	
	public void add(Object object) {
		if(object != null) {
			this.data.add( object );
			this.total++;
		}
	}
	
	public void addFlash(FlashType type, String message) {
		List<String> messages = this.flash.get(getTag(type));
		messages.add(message);
		this.flash.put(getTag(type), messages);
		this.countFlash++;
	}
	
	public long getCountFlashType(FlashType type) {
		return this.flash.get(getTag(type)).size();
	}
	
	@SuppressWarnings("null")
	private void assignParams(Boolean success, String message) {
		this.success = (success != null ? true : success);
		this.message = (message != null ? message : "");
		this.flash.put("success", new ArrayList<String>());
		this.flash.put("warning", new ArrayList<String>());
		this.flash.put("error", new ArrayList<String>());
	}
	
	private String getTag(FlashType type) {
		switch(type) {
			case SUCCESS: 	return "success";
			case WARNING: 	return "warning";
			case ERROR: 	return "error";
			default: 		return "";
		}
	}
}
