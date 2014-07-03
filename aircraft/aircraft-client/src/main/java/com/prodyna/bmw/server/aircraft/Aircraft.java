package com.prodyna.bmw.server.aircraft;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.prodyna.bmw.server.aircraft.type.AircraftType;

/**
 * 
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Entity
@NamedQuery(name = Aircraft.QUERY_GET_ALL_AIRCRAFTS_PAGINATED, query = "select a from Aircraft a order by a.registration")
@Table(name = "AIR_AIRCRAFT", uniqueConstraints = @UniqueConstraint(columnNames = { "registration" }))
public class Aircraft {

	public static final String QUERY_GET_ALL_AIRCRAFTS_PAGINATED = "Aircraft.findAllPaginated";

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", unique = true)
	private String id;

	/**
	 * http://en.wikipedia.org/wiki/Aircraft_registration
	 */
	@Column(name = "registration", nullable = false)
	@NotNull
	private String registration;

	@NotNull
	@ManyToOne
	private AircraftType aircraftType;

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
		Aircraft other = (Aircraft) obj;
		if (aircraftType == null) {
			if (other.aircraftType != null) {
				return false;
			}
		} else if (!aircraftType.equals(other.aircraftType)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (registration == null) {
			if (other.registration != null) {
				return false;
			}
		} else if (!registration.equals(other.registration)) {
			return false;
		}
		return true;
	}

	public AircraftType getAircraftType() {
		return aircraftType;
	}

	public String getId() {
		return id;
	}

	public String getRegistration() {
		return registration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aircraftType == null) ? 0 : aircraftType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((registration == null) ? 0 : registration.hashCode());
		return result;
	}

	public void setAircraftType(AircraftType aircraftType) {
		this.aircraftType = aircraftType;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	@Override
	public String toString() {
		return "Aircraft [id=" + id + ", registration=" + registration + "]";
	}

}
