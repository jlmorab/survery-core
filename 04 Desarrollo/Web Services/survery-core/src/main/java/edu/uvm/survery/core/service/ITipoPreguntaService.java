package edu.uvm.survery.core.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import edu.uvm.survery.core.model.TipoPregunta;

@Transactional
public interface ITipoPreguntaService {
	public abstract TipoPregunta findById(Integer id) 
		throws IllegalArgumentException;
	public abstract List<TipoPregunta> all();
	public abstract List<TipoPregunta> all(Integer status) 
		throws IllegalArgumentException;
}
