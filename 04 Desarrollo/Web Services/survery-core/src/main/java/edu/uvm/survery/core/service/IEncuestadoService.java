package edu.uvm.survery.core.service;

import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

import org.springframework.transaction.annotation.Transactional;

import edu.uvm.survery.core.model.Encuestado;

@Transactional
public interface IEncuestadoService {
	public abstract Encuestado addAndUpdate(Encuestado entity) 
		throws EntityExistsException, IllegalArgumentException, TransactionRequiredException;
	public abstract Boolean delete(Encuestado entity) 
		throws IllegalArgumentException, TransactionRequiredException;
	public abstract Encuestado findById(Integer id) 
		throws IllegalArgumentException;
	public abstract void obtainReferenceFields(Encuestado entity, Integer status, Integer survery) 
		throws IllegalArgumentException;
}
