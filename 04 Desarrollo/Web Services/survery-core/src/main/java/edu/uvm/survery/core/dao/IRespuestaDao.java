package edu.uvm.survery.core.dao;

import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

import edu.uvm.survery.core.model.Respuesta;

public interface IRespuestaDao {
	public abstract Respuesta addAndUpdate(Respuesta entity) 
		throws EntityExistsException, IllegalArgumentException, TransactionRequiredException;
	public abstract Boolean delete(Respuesta entity) 
		throws IllegalArgumentException, TransactionRequiredException;
	public abstract Respuesta findById(Long id) 
		throws IllegalArgumentException;
}
