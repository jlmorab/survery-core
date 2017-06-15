package edu.uvm.survery.core.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

import org.springframework.transaction.annotation.Transactional;

import edu.uvm.survery.core.extjs.ExtData;
import edu.uvm.survery.core.model.Encuesta;

@Transactional
public interface IEncuestaService {
	public abstract Encuesta addAndUpdate(Encuesta entity)
		throws EntityExistsException, IllegalArgumentException, TransactionRequiredException;
	public abstract Boolean delete(Encuesta entity) 
		throws IllegalArgumentException, TransactionRequiredException;
	public abstract Encuesta findById(Integer id) 
		throws IllegalArgumentException;
	public abstract Encuesta findByName(String name) 
		throws IllegalArgumentException;
	public abstract List<Encuesta> all();
	public abstract List<Encuesta> all(Integer status) 
			throws IllegalArgumentException;
	public abstract List<Encuesta> all(Integer status, Integer type) 
		throws IllegalArgumentException;
	public abstract Encuesta create(ExtData response, Integer typeId, String name) 
		throws IllegalArgumentException;
}
