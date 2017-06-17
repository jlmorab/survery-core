package edu.uvm.survery.core.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import edu.uvm.survery.core.extjs.DateDeserializer;
import edu.uvm.survery.core.extjs.DateSerializer;


/**
 * The persistent class for the tra_encuestado database table.
 */
@JsonSerialize
@Entity
@Table(name="tra_encuestado")
@NamedQuery(name="Encuestado.findAll", query="SELECT e FROM Encuestado e")
public class Encuestado implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="i_encuestado")
	private Integer 		i_encuestado;

	//bi-directional many-to-one association to StatusGeneral
	@ManyToOne
	@JoinColumn(name="i_encuestado_status")
	private StatusGeneral 	i_encuestado_status;
	
	//bi-directional many-to-one association to Encuesta
	@ManyToOne
	@JoinColumn(name="i_encuestado_encuesta")
	private Encuesta 		i_encuestado_encuesta;

	@Column(name="n_encuestado_contacto")
	private String 			n_encuestado_contacto;

	@Column(name="x_encuestado_email")
	private String 			x_encuestado_email;
	
	@JsonSerialize(using = DateSerializer.class)
	@JsonDeserialize(using = DateDeserializer.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="d_encuestado")
	private Date 			d_encuestado;

	
	public Encuestado() {}

	public Encuestado(StatusGeneral i_encuestado_status, Encuesta i_encuestado_encuesta, String n_encuestado_contacto, String x_encuestado_email, Date d_encuestado) {
		this.i_encuestado_status 	= i_encuestado_status;
		this.i_encuestado_encuesta 	= i_encuestado_encuesta;
		this.n_encuestado_contacto 	= n_encuestado_contacto;
		this.x_encuestado_email 	= x_encuestado_email;
		this.d_encuestado 			= d_encuestado;
	}

	
	public Integer getI_encuestado() {
		return i_encuestado;
	}

	public void setI_encuestado(Integer i_encuestado) {
		this.i_encuestado = i_encuestado;
	}

	public StatusGeneral getI_encuestado_status() {
		return i_encuestado_status;
	}

	public void setI_encuestado_status(StatusGeneral i_encuestado_status) {
		this.i_encuestado_status = i_encuestado_status;
	}

	public Encuesta getI_encuestado_encuesta() {
		return i_encuestado_encuesta;
	}

	public void setI_encuestado_encuesta(Encuesta i_encuestado_encuesta) {
		this.i_encuestado_encuesta = i_encuestado_encuesta;
	}

	public String getN_encuestado_contacto() {
		return n_encuestado_contacto;
	}

	public void setN_encuestado_contacto(String n_encuestado_contacto) {
		this.n_encuestado_contacto = n_encuestado_contacto;
	}

	public String getX_encuestado_email() {
		return x_encuestado_email;
	}

	public void setX_encuestado_email(String x_encuestado_email) {
		this.x_encuestado_email = x_encuestado_email;
	}

	public Date getD_encuestado() {
		return d_encuestado;
	}

	public void setD_encuestado(Date d_encuestado) {
		this.d_encuestado = d_encuestado;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((d_encuestado == null) ? 0 : d_encuestado.hashCode());
		result = prime * result + ((i_encuestado == null) ? 0 : i_encuestado.hashCode());
		result = prime * result + ((i_encuestado_encuesta == null) ? 0 : i_encuestado_encuesta.hashCode());
		result = prime * result + ((i_encuestado_status == null) ? 0 : i_encuestado_status.hashCode());
		result = prime * result + ((n_encuestado_contacto == null) ? 0 : n_encuestado_contacto.hashCode());
		result = prime * result + ((x_encuestado_email == null) ? 0 : x_encuestado_email.hashCode());
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
		Encuestado other = (Encuestado) obj;
		if (d_encuestado == null) {
			if (other.d_encuestado != null)
				return false;
		} else if (!d_encuestado.equals(other.d_encuestado))
			return false;
		if (i_encuestado == null) {
			if (other.i_encuestado != null)
				return false;
		} else if (!i_encuestado.equals(other.i_encuestado))
			return false;
		if (i_encuestado_encuesta == null) {
			if (other.i_encuestado_encuesta != null)
				return false;
		} else if (!i_encuestado_encuesta.equals(other.i_encuestado_encuesta))
			return false;
		if (i_encuestado_status == null) {
			if (other.i_encuestado_status != null)
				return false;
		} else if (!i_encuestado_status.equals(other.i_encuestado_status))
			return false;
		if (n_encuestado_contacto == null) {
			if (other.n_encuestado_contacto != null)
				return false;
		} else if (!n_encuestado_contacto.equals(other.n_encuestado_contacto))
			return false;
		if (x_encuestado_email == null) {
			if (other.x_encuestado_email != null)
				return false;
		} else if (!x_encuestado_email.equals(other.x_encuestado_email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Encuestado [i_encuestado=" + i_encuestado + ", i_encuestado_status=" + i_encuestado_status
				+ ", i_encuestado_encuesta=" + i_encuestado_encuesta + ", n_encuestado_contacto="
				+ n_encuestado_contacto + ", x_encuestado_email=" + x_encuestado_email + ", d_encuestado="
				+ d_encuestado + "]";
	}
}