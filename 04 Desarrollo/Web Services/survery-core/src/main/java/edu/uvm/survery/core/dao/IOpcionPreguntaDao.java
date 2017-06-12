package edu.uvm.survery.core.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

import edu.uvm.survery.core.model.OpcionPregunta;

public interface IOpcionPreguntaDao {
	public abstract OpcionPregunta addAndUpdate(OpcionPregunta entity) 
		throws EntityExistsException, IllegalArgumentException, TransactionRequiredException;
	public abstract Boolean delete(OpcionPregunta entity) 
		throws IllegalArgumentException, TransactionRequiredException;
	public abstract OpcionPregunta findById(Integer id)
		throws IllegalArgumentException;
	public abstract OpcionPregunta findByDefinition(Integer question, String name) 
		throws IllegalArgumentException;
	public abstract List<OpcionPregunta> all(Integer status, Integer question, Integer isCorrect)
		throws IllegalArgumentException;
}
