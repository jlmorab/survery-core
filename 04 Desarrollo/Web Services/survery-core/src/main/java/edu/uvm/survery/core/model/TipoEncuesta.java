package edu.uvm.survery.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * The persistent class for the cat_tipo_encuesta database table.
 */
@JsonSerialize
@Entity
@Table(name="cat_tipo_encuesta")
@NamedQuery(name="TipoEncuesta.findAll", query="SELECT t FROM TipoEncuesta t")
public class TipoEncuesta implements Serializable {
	
	public static final int ABIERTA 			= 1, 
							OPCION_MULTIPLE 	= 2, 
							SELECCION_MULTIPLE 	= 3;
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="i_tipo_encuesta")
	private Integer 		i_tipo_encuesta;
	
	//bi-directional many-to-one association to StatusGeneral
	@ManyToOne
	@JoinColumn(name="i_tipo_encuesta_status")
	private StatusGeneral 	i_tipo_encuesta_status;

	@Column(name="n_tipo_encuesta")
	private String 			n_tipo_encuesta;
	

	public TipoEncuesta() {}


	public Integer getI_tipo_encuesta() {
		return i_tipo_encuesta;
	}

	public void setI_tipo_encuesta(Integer i_tipo_encuesta) {
		this.i_tipo_encuesta = i_tipo_encuesta;
	}

	public StatusGeneral getI_tipo_encuesta_status() {
		return i_tipo_encuesta_status;
	}

	public void setI_tipo_encuesta_status(StatusGeneral i_tipo_encuesta_status) {
		this.i_tipo_encuesta_status = i_tipo_encuesta_status;
	}

	public String getN_tipo_encuesta() {
		return n_tipo_encuesta;
	}
	
	public void setN_tipo_encuesta(String n_tipo_encuesta) {
		this.n_tipo_encuesta = n_tipo_encuesta;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((i_tipo_encuesta == null) ? 0 : i_tipo_encuesta.hashCode());
		result = prime * result + ((i_tipo_encuesta_status == null) ? 0 : i_tipo_encuesta_status.hashCode());
		result = prime * result + ((n_tipo_encuesta == null) ? 0 : n_tipo_encuesta.hashCode());
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
		TipoEncuesta other = (TipoEncuesta) obj;
		if (i_tipo_encuesta == null) {
			if (other.i_tipo_encuesta != null)
				return false;
		} else if (!i_tipo_encuesta.equals(other.i_tipo_encuesta))
			return false;
		if (i_tipo_encuesta_status == null) {
			if (other.i_tipo_encuesta_status != null)
				return false;
		} else if (!i_tipo_encuesta_status.equals(other.i_tipo_encuesta_status))
			return false;
		if (n_tipo_encuesta == null) {
			if (other.n_tipo_encuesta != null)
				return false;
		} else if (!n_tipo_encuesta.equals(other.n_tipo_encuesta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoEncuesta [i_tipo_encuesta=" + i_tipo_encuesta + ", i_tipo_encuesta_status=" + i_tipo_encuesta_status
				+ ", n_tipo_encuesta=" + n_tipo_encuesta + "]";
	}
}