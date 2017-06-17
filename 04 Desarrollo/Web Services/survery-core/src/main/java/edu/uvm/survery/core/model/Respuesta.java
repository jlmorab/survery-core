package edu.uvm.survery.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * The persistent class for the tra_respuesta database table.
 */
@JsonSerialize
@Entity
@Table(name="tra_respuesta")
@NamedQuery(name="Respuesta.findAll", query="SELECT r FROM Respuesta r")
public class Respuesta implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="i_respuesta")
	private Long 				i_respuesta;
	
	//bi-directional many-to-one association to StatusGeneral
	@ManyToOne
	@JoinColumn(name="i_respuesta_status")
	private StatusGeneral		i_respuesta_status;

	//bi-directional many-to-one association to Encuestado
	@ManyToOne
	@JoinColumn(name="i_respuesta_encuestado")
	private Encuestado 			i_respuesta_encuestado;

	//bi-directional many-to-one association to Pregunta
	@ManyToOne
	@JoinColumn(name="i_respuesta_pregunta")
	private Pregunta 			i_respuesta_pregunta;
	
	//bi-directional many-to-one association to OpcionPregunta
	@ManyToOne
	@JoinColumn(name="i_respuesta_opcion_pregunta")
	private OpcionPregunta		i_respuesta_opcion_pregunta;

	@Lob
	@Column(name="x_respuesta")
	private String 				x_respuesta;

	
	public Respuesta() {}

	public Respuesta(StatusGeneral i_respuesta_status, Encuestado i_respuesta_encuestado, Pregunta i_respuesta_pregunta, OpcionPregunta i_respuesta_opcion_pregunta, String x_respuesta) {
		this.i_respuesta_status 			= i_respuesta_status;
		this.i_respuesta_encuestado 		= i_respuesta_encuestado;
		this.i_respuesta_pregunta 			= i_respuesta_pregunta;
		this.i_respuesta_opcion_pregunta 	= i_respuesta_opcion_pregunta;
		this.x_respuesta 					= x_respuesta;
	}

	
	public Long getI_respuesta() {
		return i_respuesta;
	}

	public void setI_respuesta(Long i_respuesta) {
		this.i_respuesta = i_respuesta;
	}

	public StatusGeneral getI_respuesta_status() {
		return i_respuesta_status;
	}

	public void setI_respuesta_status(StatusGeneral i_respuesta_status) {
		this.i_respuesta_status = i_respuesta_status;
	}

	public Encuestado getI_respuesta_encuestado() {
		return i_respuesta_encuestado;
	}

	public void setI_respuesta_encuestado(Encuestado i_respuesta_encuestado) {
		this.i_respuesta_encuestado = i_respuesta_encuestado;
	}

	public Pregunta getI_respuesta_pregunta() {
		return i_respuesta_pregunta;
	}

	public void setI_respuesta_pregunta(Pregunta i_respuesta_pregunta) {
		this.i_respuesta_pregunta = i_respuesta_pregunta;
	}

	public OpcionPregunta getI_respuesta_opcion_pregunta() {
		return i_respuesta_opcion_pregunta;
	}

	public void setI_respuesta_opcion_pregunta(OpcionPregunta i_respuesta_opcion_pregunta) {
		this.i_respuesta_opcion_pregunta = i_respuesta_opcion_pregunta;
	}

	public String getX_respuesta() {
		return x_respuesta;
	}

	public void setX_respuesta(String x_respuesta) {
		this.x_respuesta = x_respuesta;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((i_respuesta == null) ? 0 : i_respuesta.hashCode());
		result = prime * result + ((i_respuesta_encuestado == null) ? 0 : i_respuesta_encuestado.hashCode());
		result = prime * result + ((i_respuesta_opcion_pregunta == null) ? 0 : i_respuesta_opcion_pregunta.hashCode());
		result = prime * result + ((i_respuesta_pregunta == null) ? 0 : i_respuesta_pregunta.hashCode());
		result = prime * result + ((i_respuesta_status == null) ? 0 : i_respuesta_status.hashCode());
		result = prime * result + ((x_respuesta == null) ? 0 : x_respuesta.hashCode());
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
		Respuesta other = (Respuesta) obj;
		if (i_respuesta == null) {
			if (other.i_respuesta != null)
				return false;
		} else if (!i_respuesta.equals(other.i_respuesta))
			return false;
		if (i_respuesta_encuestado == null) {
			if (other.i_respuesta_encuestado != null)
				return false;
		} else if (!i_respuesta_encuestado.equals(other.i_respuesta_encuestado))
			return false;
		if (i_respuesta_opcion_pregunta == null) {
			if (other.i_respuesta_opcion_pregunta != null)
				return false;
		} else if (!i_respuesta_opcion_pregunta.equals(other.i_respuesta_opcion_pregunta))
			return false;
		if (i_respuesta_pregunta == null) {
			if (other.i_respuesta_pregunta != null)
				return false;
		} else if (!i_respuesta_pregunta.equals(other.i_respuesta_pregunta))
			return false;
		if (i_respuesta_status == null) {
			if (other.i_respuesta_status != null)
				return false;
		} else if (!i_respuesta_status.equals(other.i_respuesta_status))
			return false;
		if (x_respuesta == null) {
			if (other.x_respuesta != null)
				return false;
		} else if (!x_respuesta.equals(other.x_respuesta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Respuesta [i_respuesta=" + i_respuesta + ", i_respuesta_status=" + i_respuesta_status
				+ ", i_respuesta_encuestado=" + i_respuesta_encuestado + ", i_respuesta_pregunta="
				+ i_respuesta_pregunta + ", i_respuesta_opcion_pregunta=" + i_respuesta_opcion_pregunta
				+ ", x_respuesta=" + x_respuesta + "]";
	}
}