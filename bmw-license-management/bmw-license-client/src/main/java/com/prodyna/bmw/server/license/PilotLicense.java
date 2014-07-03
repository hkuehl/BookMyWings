package com.prodyna.bmw.server.license;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.prodyna.bmw.server.aircraft.type.AircraftType;
import com.prodyna.bmw.server.pilot.Pilot;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Entity
@Table(name = "LIC_LICENSE")
@NamedQueries({
		@NamedQuery(name = PilotLicense.QUERY_FIND_LICENSE_FOR_PILOT_AND_TYPE, query = "select l from PilotLicense l where l.pilot.id = :"
				+ PilotLicense.QUERY_PARM_PILOT
				+ " and l.aircraftType.id =:"
				+ PilotLicense.QUERY_PARM_AIRCRAFTTYPE),
		@NamedQuery(name = PilotLicense.QUERY_FIND_LICENSE_FOR_PILOT, query = "select l from PilotLicense l where l.pilot.id = :"
				+ PilotLicense.QUERY_PARM_PILOT) })
public class PilotLicense {

	public static final String QUERY_FIND_LICENSE_FOR_PILOT_AND_TYPE = "PilotLicense.findLicensesForPilotAndAircraftType";
	public static final String QUERY_FIND_LICENSE_FOR_PILOT = "PilotLicense.findLicensesForPilot";

	public static final String QUERY_PARM_PILOT = "pilotParm";

	public static final String QUERY_PARM_AIRCRAFTTYPE = "aircraftTypeParm";

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", unique = true)
	private String id;

	@ManyToOne
	@NotNull
	@Valid
	private Pilot pilot;

	@ManyToOne
	@NotNull
	@Valid
	private AircraftType aircraftType;

	@Temporal(TemporalType.DATE)
	@NotNull
	private Date validFrom;

	@Temporal(TemporalType.DATE)
	@NotNull
	private Date validThru;

	private boolean dateEquals(Date thisDate, Date otherDate) {
		Calendar thisCalendar = Calendar.getInstance();
		Calendar otherCalendar = Calendar.getInstance();
		thisCalendar.setTime(thisDate);
		otherCalendar.setTime(otherDate);

		for (int property : Arrays.asList(Calendar.YEAR, Calendar.MONTH,
				Calendar.DAY_OF_MONTH)) {
			if (thisCalendar.get(property) != otherCalendar.get(property)) {
				return false;
			}
		}

		return true;
	}

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
		PilotLicense other = (PilotLicense) obj;
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
		if (pilot == null) {
			if (other.pilot != null) {
				return false;
			}
		} else if (!pilot.equals(other.pilot)) {
			return false;
		}
		if (validFrom == null) {
			if (other.validFrom != null) {
				return false;
			}
		} else if (!dateEquals(validFrom, other.validFrom)) {
			return false;
		}
		if (validThru == null) {
			if (other.validThru != null) {
				return false;
			}
		} else if (!dateEquals(validThru, other.validThru)) {
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

	public Pilot getPilot() {
		return pilot;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public Date getValidThru() {
		return validThru;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aircraftType == null) ? 0 : aircraftType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pilot == null) ? 0 : pilot.hashCode());
		result = prime * result
				+ ((validFrom == null) ? 0 : validFrom.hashCode());
		result = prime * result
				+ ((validThru == null) ? 0 : validThru.hashCode());
		return result;
	}

	public void setAircraftType(AircraftType aircraftType) {
		this.aircraftType = aircraftType;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public void setValidThru(Date validThru) {
		this.validThru = validThru;
	}

	@Override
	public String toString() {
		return "PilotLicense [id=" + id + ", pilot=" + pilot
				+ ", aircraftType=" + aircraftType + ", validFrom=" + validFrom
				+ ", validThru=" + validThru + "]";
	}

}
