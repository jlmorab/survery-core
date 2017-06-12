package edu.uvm.survery.core.dao;

import java.util.List;

import edu.uvm.survery.core.model.StatusGeneral;

public interface IStatusGeneralDao {
	public abstract StatusGeneral findById(Integer id) 
		throws IllegalArgumentException;
	public abstract List<StatusGeneral> all();
}
