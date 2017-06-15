package edu.uvm.survery.core.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

import edu.uvm.survery.core.model.Pregunta;

public interface IPreguntaDao {
	public abstract Pregunta addAndUpdate(Pregunta entity) 
		throws EntityExistsException, IllegalArgumentException, TransactionRequiredException;
	public abstract Boolean delete(Pregunta entity) 
		throws IllegalArgumentException, TransactionRequiredException;
	public abstract Pregunta findById(Integer id)
		throws IllegalArgumentException;
	public abstract Pregunta findByDefinition(Integer survery, String name) 
		throws IllegalArgumentException;
	public abstract List<Pregunta> all(Integer status, Integer survery)
		throws IllegalArgumentException;
	public abstract Integer consecutiveOrder(Integer survery) 
		throws IllegalArgumentException;
}
