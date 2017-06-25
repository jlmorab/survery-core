package edu.uvm.survery.core.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cat_encuesta database table.
 */
@Entity
@Table(name="cat_encuesta")
@NamedQuery(name="Encuesta.findAll", query="SELECT e FROM Encuesta e")
public class Encuesta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="i_encuesta")
	private Integer 		i_encuesta;
	
	//bi-directional many-to-one association to StatusGeneral
	@ManyToOne
	@JoinColumn(name="i_encuesta_status")
	private StatusGeneral 	i_encuesta_status;

	@Column(name="n_encuesta")
	private String 			n_encuesta;
	

	public Encuesta() {}


	public Encuesta(StatusGeneral i_encuesta_status, String n_encuesta) {
		this.i_encuesta_status 			= i_encuesta_status;
		this.n_encuesta 				= n_encuesta;
	}

	
	public Integer getI_encuesta() {
		return i_encuesta;
	}

	public void setI_encuesta(Integer i_encuesta) {
		this.i_encuesta = i_encuesta;
	}

	public StatusGeneral getI_encuesta_status() {
		return i_encuesta_status;
	}

	public void setI_encuesta_status(StatusGeneral i_encuesta_status) {
		this.i_encuesta_status = i_encuesta_status;
	}
	
	public String getN_encuesta() {
		return n_encuesta;
	}

	public void setN_encuesta(String n_encuesta) {
		this.n_encuesta = n_encuesta;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((i_encuesta == null) ? 0 : i_encuesta.hashCode());
		result = prime * result + ((i_encuesta_status == null) ? 0 : i_encuesta_status.hashCode());
		result = prime * result + ((n_encuesta == null) ? 0 : n_encuesta.hashCode());
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
		Encuesta other = (Encuesta) obj;
		if (i_encuesta == null) {
			if (other.i_encuesta != null)
				return false;
		} else if (!i_encuesta.equals(other.i_encuesta))
			return false;
		if (i_encuesta_status == null) {
			if (other.i_encuesta_status != null)
				return false;
		} else if (!i_encuesta_status.equals(other.i_encuesta_status))
			return false;
		if (n_encuesta == null) {
			if (other.n_encuesta != null)
				return false;
		} else if (!n_encuesta.equals(other.n_encuesta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Encuesta [i_encuesta=" + i_encuesta + ", i_encuesta_status=" + i_encuesta_status + ", n_encuesta="
				+ n_encuesta + "]";
	}
	
}