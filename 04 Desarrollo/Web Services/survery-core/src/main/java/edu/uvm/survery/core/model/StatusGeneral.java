package edu.uvm.survery.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * The persistent class for the sys_status_general database table.
 */
@JsonSerialize
@Entity
@Table(name="sys_status_general")
@NamedQuery(name="StatusGeneral.findAll", query="SELECT s FROM StatusGeneral s")
public class StatusGeneral implements Serializable {
	
	public static final int VIGENTE 	= 1,
							SUSPENDIDO 	= 2, 
							CANCELADO 	= 3;
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="i_status_general")
	private Integer 	i_status_general;

	@Column(name="n_status_general")
	private String 		n_status_general;

	
	public StatusGeneral() {}

	
	public Integer getI_status_general() {
		return i_status_general;
	}

	public void setI_status_general(Integer i_status_general) {
		this.i_status_general = i_status_general;
	}

	public String getN_status_general() {
		return n_status_general;
	}

	public void setN_status_general(String n_status_general) {
		this.n_status_general = n_status_general;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((i_status_general == null) ? 0 : i_status_general.hashCode());
		result = prime * result + ((n_status_general == null) ? 0 : n_status_general.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatusGeneral other = (StatusGeneral) obj;
		if (i_status_general == null) {
			if (other.i_status_general != null)
				return false;
		} else if (!i_status_general.equals(other.i_status_general))
			return false;
		if (n_status_general == null) {
			if (other.n_status_general != null)
				return false;
		} else if (!n_status_general.equals(other.n_status_general))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StatusGeneral [i_status_general=" + i_status_general + ", n_status_general=" + n_status_general + "]";
	}

}