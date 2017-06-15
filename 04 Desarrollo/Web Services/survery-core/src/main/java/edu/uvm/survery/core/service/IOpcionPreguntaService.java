package edu.uvm.survery.core.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

import org.springframework.transaction.annotation.Transactional;

import edu.uvm.survery.core.extjs.ExtData;
import edu.uvm.survery.core.model.OpcionPregunta;

@Transactional
public interface IOpcionPreguntaService {
	public abstract OpcionPregunta addAndUpdate(OpcionPregunta entity) 
		throws EntityExistsException, IllegalArgumentException, TransactionRequiredException;
	public abstract Boolean delete(OpcionPregunta entity)
		throws IllegalArgumentException, TransactionRequiredException;
	public abstract OpcionPregunta findById(Integer id) 
		throws IllegalArgumentException;
	public abstract OpcionPregunta findByDefinition(Integer question, String name)
		throws IllegalArgumentException;
	public abstract List<OpcionPregunta> all();
	public abstract List<OpcionPregunta> all(Integer status) 
		throws IllegalArgumentException;
	public abstract List<OpcionPregunta> all(Integer status, Integer question) 
		throws IllegalArgumentException;
	public abstract List<OpcionPregunta> all(Integer status, Integer question, Integer isCorrect) 
		throws IllegalArgumentException;
	public abstract OpcionPregunta create(ExtData response, Integer questionId, String name) 
		throws IllegalArgumentException;
}
