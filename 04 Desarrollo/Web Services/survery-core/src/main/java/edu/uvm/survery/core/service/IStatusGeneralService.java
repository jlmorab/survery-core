package edu.uvm.survery.core.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import edu.uvm.survery.core.model.StatusGeneral;

@Transactional
public interface IStatusGeneralService {
	public abstract StatusGeneral findById(Integer id) 
		throws IllegalArgumentException;
	public abstract List<StatusGeneral> all();
}
