package edu.uvm.survery.core.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import edu.uvm.survery.core.model.TipoEncuesta;

@Transactional
public interface ITipoEncuestaService {
	public abstract TipoEncuesta findById(Integer id) 
		throws IllegalArgumentException;
	public abstract List<TipoEncuesta> all();
	public abstract List<TipoEncuesta> all(Integer status) 
		throws IllegalArgumentException;
}
