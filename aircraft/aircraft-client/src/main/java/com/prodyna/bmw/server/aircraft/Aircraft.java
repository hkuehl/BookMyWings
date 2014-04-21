package com.prodyna.bmw.server.aircraft;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Entity
@NamedQuery(name = Aircraft.QUERY_GET_ALL_AIRCRAFTS_PAGINATED, query = "select a from Aircraft a order by a.registration")
@Table(name = "AIR_AIRCRAFT")
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((registration == null) ? 0 : registration.hashCode());
		return result;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

}
