package edu.uvm.survery.core.service;

import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

import org.springframework.transaction.annotation.Transactional;

import edu.uvm.survery.core.model.Respuesta;

@Transactional
public interface IRespuestaService {
	public abstract Respuesta addAndUpdate(Respuesta entity) 
		throws EntityExistsException, IllegalArgumentException, TransactionRequiredException;
	public abstract Boolean delete(Respuesta entity) 
		throws IllegalArgumentException, TransactionRequiredException;
	public abstract Respuesta findById(Long id) 
		throws IllegalArgumentException;
	public abstract void obtainReferenceFields(Respuesta entity, Integer status, Integer respondent, Integer question, Integer questionOption) 
		throws IllegalArgumentException;
}
