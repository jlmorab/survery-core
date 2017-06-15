package edu.uvm.survery.core.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

import org.springframework.transaction.annotation.Transactional;

import edu.uvm.survery.core.extjs.ExtData;
import edu.uvm.survery.core.model.Pregunta;

@Transactional
public interface IPreguntaService {
	public abstract Pregunta addAndUpdate(Pregunta entity) 
		throws EntityExistsException, IllegalArgumentException, TransactionRequiredException;
	public abstract Boolean delete(Pregunta entity) 
		throws IllegalArgumentException;
	public abstract Pregunta findById(Integer id) 
		throws IllegalArgumentException;
	public abstract Pregunta findByDefinition(Integer survery, String name) 
		throws IllegalArgumentException;
	public abstract List<Pregunta> all();
	public abstract List<Pregunta> all(Integer status) 
		throws IllegalArgumentException;
	public abstract List<Pregunta> all(Integer status, Integer survery) 
		throws IllegalArgumentException;
	public abstract Pregunta create(ExtData response, Integer surveryId, String name) 
		throws IllegalArgumentException;
	public abstract Integer consecutiveOrder(Integer survery) 
		throws IllegalArgumentException;
}
