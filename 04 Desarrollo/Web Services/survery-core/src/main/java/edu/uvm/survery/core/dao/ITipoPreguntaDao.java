package edu.uvm.survery.core.dao;

import java.util.List;

import edu.uvm.survery.core.model.TipoPregunta;

public interface ITipoPreguntaDao {
	public abstract TipoPregunta findById(Integer id) 
		throws IllegalArgumentException;
	public abstract List<TipoPregunta> all(Integer status) 
		throws IllegalArgumentException;
}
