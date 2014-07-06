package com.prodyna.bmw.server.pilot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Entity
@Table(name = "PIL_ROLES")
public class PilotRole {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", unique = true)
	private String pilotRoleMappingId;

	@Column(name = "pilot_role")
	private String pilotRole;

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
		PilotRole other = (PilotRole) obj;
		if (pilotRole == null) {
			if (other.pilotRole != null) {
				return false;
			}
		} else if (!pilotRole.equals(other.pilotRole)) {
			return false;
		}
		if (pilotRoleMappingId == null) {
			if (other.pilotRoleMappingId != null) {
				return false;
			}
		} else if (!pilotRoleMappingId.equals(other.pilotRoleMappingId)) {
			return false;
		}
		return true;
	}

	public String getPilotRole() {
		return pilotRole;
	}

	public String getPilotRoleMappingId() {
		return pilotRoleMappingId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((pilotRole == null) ? 0 : pilotRole.hashCode());
		result = prime
				* result
				+ ((pilotRoleMappingId == null) ? 0 : pilotRoleMappingId
						.hashCode());
		return result;
	}

	public void setPilotRole(String pilotRole) {
		this.pilotRole = pilotRole;
	}

	public void setPilotRoleMappingId(String pilotRoleMappingId) {
		this.pilotRoleMappingId = pilotRoleMappingId;
	}

}
