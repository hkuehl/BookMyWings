package com.prodyna.bmw.server.booking;

import java.io.Serializable;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.prodyna.bmw.server.aircraft.Aircraft;
import com.prodyna.bmw.server.pilot.Pilot;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Entity
@Table(name = "BOK_BOOKING", uniqueConstraints = @UniqueConstraint(columnNames = {
		"aircraft_id", "pilot_id", "startDate", "endDate" }))
@NamedQueries({
		@NamedQuery(name = Booking.QUERY_FIND_AIRCRAFTS_FOR_PILOT, query = "select b.aircraft from Booking b where b.pilot = :"
				+ Booking.QUERY_PARAMETER_PILOT),
		@NamedQuery(name = Booking.QUERY_FIND_BOOKING_BY_ID, query = "select b from Booking b where b.bookingId = :"
				+ Booking.QUERY_PARAMETER_BOOKINGID),
		@NamedQuery(name = Booking.QUERY_FIND_BOOKING_BY_CORE_ATTRIBUTES, query = "select b from Booking b where b.aircraft = :"
				+ Booking.QUERY_PARAMETER_AIRCRAFT
				+ " and b.pilot = :"
				+ Booking.QUERY_PARAMETER_PILOT
				+ " and b.startDate = :"
				+ Booking.QUERY_PARAMETER_START
				+ " and b.endDate = :"
				+ Booking.QUERY_PARAMETER_END) })
public class Booking implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String QUERY_FIND_AIRCRAFTS_FOR_PILOT = "Booking.findAircraftsForPilot";

	public static final String QUERY_FIND_BOOKING_BY_ID = "Booking.findBookingById";

	public static final String QUERY_FIND_BOOKING_BY_CORE_ATTRIBUTES = "Booking.findBookingByCoreAttributes";

	public static final String QUERY_PARAMETER_BOOKINGID = "bookingId";
	public static final String QUERY_PARAMETER_PILOT = "pilot";
	public static final String QUERY_PARAMETER_AIRCRAFT = "aircraft";
	public static final String QUERY_PARAMETER_START = "start";
	public static final String QUERY_PARAMETER_END = "end";

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", unique = true)
	private String bookingId;

	@ManyToOne
	@NotNull
	private Aircraft aircraft;

	@ManyToOne
	@NotNull
	private Pilot pilot;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	private boolean dateEquals(Date thisDate, Date otherDate) {
		Calendar thisCalendar = Calendar.getInstance();
		Calendar otherCalendar = Calendar.getInstance();
		thisCalendar.setTime(thisDate);
		otherCalendar.setTime(otherDate);

		for (int property : Arrays.asList(Calendar.YEAR, Calendar.MONTH,
				Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE,
				Calendar.SECOND)) {
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
		Booking other = (Booking) obj;
		if (aircraft == null) {
			if (other.aircraft != null) {
				return false;
			}
		} else if (!aircraft.equals(other.aircraft)) {
			return false;
		}
		if (bookingId == null) {
			if (other.bookingId != null) {
				return false;
			}
		} else if (!bookingId.equals(other.bookingId)) {
			return false;
		}
		if (endDate == null) {
			if (other.endDate != null) {
				return false;
			}
		} else if (!dateEquals(endDate, other.endDate)) {
			return false;
		}
		if (pilot == null) {
			if (other.pilot != null) {
				return false;
			}
		} else if (!pilot.equals(other.pilot)) {
			return false;
		}
		if (startDate == null) {
			if (other.startDate != null) {
				return false;
			}
		} else if (!dateEquals(startDate, other.startDate)) {
			return false;
		}
		return true;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public String getBookingId() {
		return bookingId;
	}

	public Date getEnd() {
		return endDate;
	}

	public Pilot getPilot() {
		return pilot;
	}

	public Date getStart() {
		return startDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aircraft == null) ? 0 : aircraft.hashCode());
		result = prime * result
				+ ((bookingId == null) ? 0 : bookingId.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((pilot == null) ? 0 : pilot.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public void setEnd(Date end) {
		this.endDate = end;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	public void setStart(Date start) {
		this.startDate = start;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", aircraft=" + aircraft
				+ ", pilot=" + pilot + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}

}
