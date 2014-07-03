package com.prodyna.bmw.server.aircraft.type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Entity
@Table(name = "AIR_AIRCRAFT_TYPE")
public class AircraftType {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", unique = true)
	private String id;

	@Column(unique = true, nullable = false)
	@NotNull
	private String typeString;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AircraftType other = (AircraftType) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (typeString == null) {
			if (other.typeString != null) {
				return false;
			}
		} else if (!typeString.equals(other.typeString)) {
			return false;
		}
		return true;
	}

	public String getId() {
		return id;
	}

	public String getTypeString() {
		return typeString;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((typeString == null) ? 0 : typeString.hashCode());
		return result;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTypeString(String typeString) {
		this.typeString = typeString;
	}

	@Override
	public String toString() {
		return "AircraftType [id=" + id + ", typeString=" + typeString + "]";
	}

}
