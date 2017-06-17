package edu.uvm.survery.core.dao;

import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

import edu.uvm.survery.core.model.Encuestado;

public interface IEncuestadoDao {
	public abstract Encuestado addAndUpdate(Encuestado entity) 
		throws EntityExistsException, IllegalArgumentException, TransactionRequiredException;
	public abstract Boolean delete(Encuestado entity) 
		throws IllegalArgumentException, TransactionRequiredException;
	public abstract Encuestado findById(Integer id) 
		throws IllegalArgumentException;
}
