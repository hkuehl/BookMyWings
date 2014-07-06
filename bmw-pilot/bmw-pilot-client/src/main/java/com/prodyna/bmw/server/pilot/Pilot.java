package com.prodyna.bmw.server.pilot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Pilot.QUERY_GET_ALL_PILOTS_PAGINATED, query = "select p from Pilot p order by p.lastName"),
		@NamedQuery(name = Pilot.QUERY_FIND_PILOT_BY_USERNAME, query = "select p from Pilot p where p.userName = :"
				+ Pilot.QUERY_PARM_USERNAME) })
@Table(name = "PIL_PILOT")
public class Pilot {

	public static final String QUERY_GET_ALL_PILOTS_PAGINATED = "Pilot.findAllPaginated";
	public static final String QUERY_FIND_PILOT_BY_USERNAME = "Pilot.findByUsername";
	public static final String QUERY_PARM_USERNAME = "username";

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
	private String password;

	@NotNull
	@ManyToOne
	private PilotRole pilotRole;

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
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (pilotRole == null) {
			if (other.pilotRole != null) {
				return false;
			}
		} else if (!pilotRole.equals(other.pilotRole)) {
			return false;
		}
		if (userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!userName.equals(other.userName)) {
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

	public PilotRole getPilotRole() {
		return pilotRole;
	}

	public String getUserName() {
		return userName;
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
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((pilotRole == null) ? 0 : pilotRole.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
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

	public void setPilotRole(PilotRole pilotRole) {
		this.pilotRole = pilotRole;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Pilot [id=" + id + ", lastName=" + lastName + ", firstName="
				+ firstName + ", userName=" + userName + ", password="
				+ password + ", pilotRole=" + pilotRole + "]";
	}

}
