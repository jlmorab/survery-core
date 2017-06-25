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
 * The persistent class for the cat_tipo_pregunta database table.
 */
@JsonSerialize
@Entity
@Table(name="cat_tipo_pregunta")
@NamedQuery(name="TipoPregunta.findAll", query="SELECT t FROM TipoPregunta t")
public class TipoPregunta implements Serializable {
	
	public static final int ABIERTA 			= 1, 
							OPCION_MULTIPLE 	= 2, 
							SELECCION_MULTIPLE 	= 3;
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="i_tipo_pregunta")
	private Integer 		i_tipo_pregunta;
	
	//bi-directional many-to-one association to StatusGeneral
	@ManyToOne
	@JoinColumn(name="i_tipo_pregunta_status")
	private StatusGeneral 	i_tipo_pregunta_status;

	@Column(name="n_tipo_pregunta")
	private String 			n_tipo_pregunta;
	

	public TipoPregunta() {}


	public Integer getI_tipo_pregunta() {
		return i_tipo_pregunta;
	}

	public void setI_tipo_pregunta(Integer i_tipo_pregunta) {
		this.i_tipo_pregunta = i_tipo_pregunta;
	}

	public StatusGeneral getI_tipo_pregunta_status() {
		return i_tipo_pregunta_status;
	}

	public void setI_tipo_pregunta_status(StatusGeneral i_tipo_pregunta_status) {
		this.i_tipo_pregunta_status = i_tipo_pregunta_status;
	}

	public String getN_tipo_pregunta() {
		return n_tipo_pregunta;
	}
	
	public void setN_tipo_pregunta(String n_tipo_pregunta) {
		this.n_tipo_pregunta = n_tipo_pregunta;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((i_tipo_pregunta == null) ? 0 : i_tipo_pregunta.hashCode());
		result = prime * result + ((i_tipo_pregunta_status == null) ? 0 : i_tipo_pregunta_status.hashCode());
		result = prime * result + ((n_tipo_pregunta == null) ? 0 : n_tipo_pregunta.hashCode());
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
		TipoPregunta other = (TipoPregunta) obj;
		if (i_tipo_pregunta == null) {
			if (other.i_tipo_pregunta != null)
				return false;
		} else if (!i_tipo_pregunta.equals(other.i_tipo_pregunta))
			return false;
		if (i_tipo_pregunta_status == null) {
			if (other.i_tipo_pregunta_status != null)
				return false;
		} else if (!i_tipo_pregunta_status.equals(other.i_tipo_pregunta_status))
			return false;
		if (n_tipo_pregunta == null) {
			if (other.n_tipo_pregunta != null)
				return false;
		} else if (!n_tipo_pregunta.equals(other.n_tipo_pregunta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoPregunta [i_tipo_pregunta=" + i_tipo_pregunta + ", i_tipo_pregunta_status=" + i_tipo_pregunta_status
				+ ", n_tipo_pregunta=" + n_tipo_pregunta + "]";
	}
}