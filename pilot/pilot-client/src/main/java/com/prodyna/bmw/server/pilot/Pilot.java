package com.prodyna.bmw.server.pilot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Entity
@NamedQuery(name = Pilot.QUERY_GET_ALL_PILOTS_PAGINATED, query = "select p from Pilot p order by p.lastName")
@Table(name = "PIL_PILOT")
public class Pilot {

	public static final String QUERY_GET_ALL_PILOTS_PAGINATED = "Pilot.findAllPaginated";

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", unique = true)
	private String id;

	@Column(name = "lastName", nullable = false)
	@NotNull
	private String lastName;

	@Column(name = "firstName", nullable = false)
	@NotNull
	private String firstName;

	@Column(name = "pilot_user_name")
	@NotNull
	private String userName;

	@Column(name = "password")
	@NotNull
	private String password;

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
		Pilot other = (Pilot) obj;
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}
		return true;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Pilot [id=" + id + ", lastName=" + lastName + ", firstName="
				+ firstName + "]";
	}

}
