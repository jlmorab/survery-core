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
 * The persistent class for the cat_pregunta database table.
 */
@JsonSerialize
@Entity
@Table(name="cat_pregunta")
@NamedQuery(name="Pregunta.findAll", query="SELECT p FROM Pregunta p")
public class Pregunta implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="i_pregunta")
	private Integer 		i_pregunta;
	
	//bi-directional many-to-one association to StatusGeneral
	@ManyToOne
	@JoinColumn(name="i_pregunta_status")
	private StatusGeneral 	i_pregunta_status;
	
	//bi-directional many-to-one association to Encuesta
	@ManyToOne
	@JoinColumn(name="i_pregunta_encuesta")
	private Encuesta 		i_pregunta_encuesta;

	@Column(name="q_pregunta_orden")
	private Integer 		q_pregunta_orden;
	
	@Column(name="n_pregunta")
	private String 			n_pregunta;	

	
	public Pregunta() {}

	public Pregunta(StatusGeneral i_pregunta_status, Encuesta i_pregunta_encuesta, Integer q_pregunta_orden, String n_pregunta) {
		this.i_pregunta_status 		= i_pregunta_status;
		this.i_pregunta_encuesta 	= i_pregunta_encuesta;
		this.q_pregunta_orden 		= q_pregunta_orden;
		this.n_pregunta 			= n_pregunta;
	}

	
	public Integer getI_pregunta() {
		return i_pregunta;
	}

	public void setI_pregunta(Integer i_pregunta) {
		this.i_pregunta = i_pregunta;
	}

	public StatusGeneral getI_pregunta_status() {
		return i_pregunta_status;
	}

	public void setI_pregunta_status(StatusGeneral i_pregunta_status) {
		this.i_pregunta_status = i_pregunta_status;
	}

	public Encuesta getI_pregunta_encuesta() {
		return i_pregunta_encuesta;
	}

	public void setI_pregunta_encuesta(Encuesta i_pregunta_encuesta) {
		this.i_pregunta_encuesta = i_pregunta_encuesta;
	}

	public Integer getQ_pregunta_orden() {
		return q_pregunta_orden;
	}

	public void setQ_pregunta_orden(Integer q_pregunta_orden) {
		this.q_pregunta_orden = q_pregunta_orden;
	}

	public String getN_pregunta() {
		return n_pregunta;
	}

	public void setN_pregunta(String n_pregunta) {
		this.n_pregunta = n_pregunta;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((i_pregunta == null) ? 0 : i_pregunta.hashCode());
		result = prime * result + ((i_pregunta_encuesta == null) ? 0 : i_pregunta_encuesta.hashCode());
		result = prime * result + ((i_pregunta_status == null) ? 0 : i_pregunta_status.hashCode());
		result = prime * result + ((n_pregunta == null) ? 0 : n_pregunta.hashCode());
		result = prime * result + ((q_pregunta_orden == null) ? 0 : q_pregunta_orden.hashCode());
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
		Pregunta other = (Pregunta) obj;
		if (i_pregunta == null) {
			if (other.i_pregunta != null)
				return false;
		} else if (!i_pregunta.equals(other.i_pregunta))
			return false;
		if (i_pregunta_encuesta == null) {
			if (other.i_pregunta_encuesta != null)
				return false;
		} else if (!i_pregunta_encuesta.equals(other.i_pregunta_encuesta))
			return false;
		if (i_pregunta_status == null) {
			if (other.i_pregunta_status != null)
				return false;
		} else if (!i_pregunta_status.equals(other.i_pregunta_status))
			return false;
		if (n_pregunta == null) {
			if (other.n_pregunta != null)
				return false;
		} else if (!n_pregunta.equals(other.n_pregunta))
			return false;
		if (q_pregunta_orden == null) {
			if (other.q_pregunta_orden != null)
				return false;
		} else if (!q_pregunta_orden.equals(other.q_pregunta_orden))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pregunta [i_pregunta=" + i_pregunta + ", i_pregunta_status=" + i_pregunta_status
				+ ", i_pregunta_encuesta=" + i_pregunta_encuesta + ", q_pregunta_orden=" + q_pregunta_orden
				+ ", n_pregunta=" + n_pregunta + "]";
	}
}