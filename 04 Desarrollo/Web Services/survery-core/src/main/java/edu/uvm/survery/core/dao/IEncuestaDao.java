package edu.uvm.survery.core.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

import edu.uvm.survery.core.model.Encuesta;

public interface IEncuestaDao {
	public abstract Encuesta addAndSave(Encuesta entity) 
		throws EntityExistsException, IllegalArgumentException, TransactionRequiredException;
	public abstract boolean delete(Encuesta entity) 
		throws IllegalArgumentException, TransactionRequiredException;
	public abstract Encuesta findById(Integer id) 
		throws IllegalArgumentException;
	public abstract Encuesta findByName(String name) 
		throws IllegalArgumentException;
	public abstract List<Encuesta> all(Integer status) 
		throws IllegalArgumentException;
}
