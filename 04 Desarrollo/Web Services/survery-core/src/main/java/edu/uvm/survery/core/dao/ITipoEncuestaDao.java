package edu.uvm.survery.core.dao;

import java.util.List;

import edu.uvm.survery.core.model.TipoEncuesta;

public interface ITipoEncuestaDao {
	public abstract TipoEncuesta findById(Integer id) 
		throws IllegalArgumentException;
	public abstract List<TipoEncuesta> all(Integer status) 
		throws IllegalArgumentException;
}
