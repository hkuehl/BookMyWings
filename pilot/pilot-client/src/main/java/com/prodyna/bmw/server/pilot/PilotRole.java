package com.prodyna.bmw.server.pilot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Entity
@Table(name = "PIL_ROLES")
public class PilotRole {

	@Id
	private String pilotRoleMappingId;

	@Column(name = "pilot_user_name")
	private String pilotUsername;

	@Column(name = "pilot_role")
	private String pilotRole;

	public String getPilotRole() {
		return pilotRole;
	}

	public String getPilotRoleMappingId() {
		return pilotRoleMappingId;
	}

	public String getPilotUsername() {
		return pilotUsername;
	}

	public void setPilotRole(String pilotRole) {
		this.pilotRole = pilotRole;
	}

	public void setPilotRoleMappingId(String pilotRoleMappingId) {
		this.pilotRoleMappingId = pilotRoleMappingId;
	}

	public void setPilotUsername(String pilotUsername) {
		this.pilotUsername = pilotUsername;
	}

}
