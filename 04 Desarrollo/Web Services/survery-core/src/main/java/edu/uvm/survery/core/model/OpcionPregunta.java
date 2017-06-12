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
 * The persistent class for the cat_opcion_pregunta database table.
 */
@JsonSerialize
@Entity
@Table(name="cat_opcion_pregunta")
@NamedQuery(name="OpcionPregunta.findAll", query="SELECT o FROM OpcionPregunta o")
public class OpcionPregunta implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="i_opcion_pregunta")
	private Integer 		i_opcion_pregunta;

	//bi-directional many-to-one association to StatusGeneral
	@ManyToOne
	@JoinColumn(name="i_opcion_pregunta_status")
	private StatusGeneral 	i_opcion_pregunta_status;
	
	//bi-directional many-to-one association to Pregunta
	@ManyToOne
	@JoinColumn(name="i_opcion_pregunta_pregunta")
	private Pregunta 		i_opcion_pregunta_pregunta;

	@Column(name="n_opcion_pregunta")
	private String 			n_opcion_pregunta;
	
	@Column(name="f_opcion_pregunta_correcto")
	private Integer 		f_opcion_pregunta_correcto;

	
	public OpcionPregunta() {}

	public OpcionPregunta(StatusGeneral i_opcion_pregunta_status, Pregunta i_opcion_pregunta_pregunta, String n_opcion_pregunta) {
		this.assignParams(i_opcion_pregunta_status, i_opcion_pregunta_pregunta, n_opcion_pregunta, null);
	}
	
	public OpcionPregunta(StatusGeneral i_opcion_pregunta_status, Pregunta i_opcion_pregunta_pregunta, String n_opcion_pregunta, Integer f_opcion_pregunta_correcto) {
		this.assignParams(i_opcion_pregunta_status, i_opcion_pregunta_pregunta, n_opcion_pregunta, f_opcion_pregunta_correcto);
	}

	
	public Integer getI_opcion_pregunta() {
		return i_opcion_pregunta;
	}

	public void setI_opcion_pregunta(Integer i_opcion_pregunta) {
		this.i_opcion_pregunta = i_opcion_pregunta;
	}

	public StatusGeneral getI_opcion_pregunta_status() {
		return i_opcion_pregunta_status;
	}

	public void setI_opcion_pregunta_status(StatusGeneral i_opcion_pregunta_status) {
		this.i_opcion_pregunta_status = i_opcion_pregunta_status;
	}

	public Pregunta getI_opcion_pregunta_pregunta() {
		return i_opcion_pregunta_pregunta;
	}

	public void setI_opcion_pregunta_pregunta(Pregunta i_opcion_pregunta_pregunta) {
		this.i_opcion_pregunta_pregunta = i_opcion_pregunta_pregunta;
	}

	public String getN_opcion_pregunta() {
		return n_opcion_pregunta;
	}

	public void setN_opcion_pregunta(String n_opcion_pregunta) {
		this.n_opcion_pregunta = n_opcion_pregunta;
	}

	public Integer getF_opcion_pregunta_correcto() {
		return f_opcion_pregunta_correcto;
	}

	public void setF_opcion_pregunta_correcto(Integer f_opcion_pregunta_correcto) {
		this.f_opcion_pregunta_correcto = f_opcion_pregunta_correcto;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((f_opcion_pregunta_correcto == null) ? 0 : f_opcion_pregunta_correcto.hashCode());
		result = prime * result + ((i_opcion_pregunta == null) ? 0 : i_opcion_pregunta.hashCode());
		result = prime * result + ((i_opcion_pregunta_pregunta == null) ? 0 : i_opcion_pregunta_pregunta.hashCode());
		result = prime * result + ((i_opcion_pregunta_status == null) ? 0 : i_opcion_pregunta_status.hashCode());
		result = prime * result + ((n_opcion_pregunta == null) ? 0 : n_opcion_pregunta.hashCode());
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
		OpcionPregunta other = (OpcionPregunta) obj;
		if (f_opcion_pregunta_correcto == null) {
			if (other.f_opcion_pregunta_correcto != null)
				return false;
		} else if (!f_opcion_pregunta_correcto.equals(other.f_opcion_pregunta_correcto))
			return false;
		if (i_opcion_pregunta == null) {
			if (other.i_opcion_pregunta != null)
				return false;
		} else if (!i_opcion_pregunta.equals(other.i_opcion_pregunta))
			return false;
		if (i_opcion_pregunta_pregunta == null) {
			if (other.i_opcion_pregunta_pregunta != null)
				return false;
		} else if (!i_opcion_pregunta_pregunta.equals(other.i_opcion_pregunta_pregunta))
			return false;
		if (i_opcion_pregunta_status == null) {
			if (other.i_opcion_pregunta_status != null)
				return false;
		} else if (!i_opcion_pregunta_status.equals(other.i_opcion_pregunta_status))
			return false;
		if (n_opcion_pregunta == null) {
			if (other.n_opcion_pregunta != null)
				return false;
		} else if (!n_opcion_pregunta.equals(other.n_opcion_pregunta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OpcionPregunta [i_opcion_pregunta=" + i_opcion_pregunta + ", i_opcion_pregunta_status="
				+ i_opcion_pregunta_status + ", i_opcion_pregunta_pregunta=" + i_opcion_pregunta_pregunta
				+ ", n_opcion_pregunta=" + n_opcion_pregunta + ", f_opcion_pregunta_correcto="
				+ f_opcion_pregunta_correcto + "]";
	}
	
	
	private void assignParams(StatusGeneral i_opcion_pregunta_status, Pregunta i_opcion_pregunta_pregunta, String n_opcion_pregunta, Integer f_opcion_pregunta_correcto) {
		this.i_opcion_pregunta_status 	= i_opcion_pregunta_status;
		this.i_opcion_pregunta_pregunta = i_opcion_pregunta_pregunta;
		this.n_opcion_pregunta 			= n_opcion_pregunta;
		this.f_opcion_pregunta_correcto = f_opcion_pregunta_correcto;
	}
}