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
import javax.validation.Valid;
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
		@NamedQuery(name = Booking.QUERY_FIND_BOOKING_BY_ID, query = "select b from Booking b where b.bookingId = :"
				+ Booking.QUERY_PARAMETER_BOOKINGID),
		@NamedQuery(name = Booking.QUERY_FIND_BOOKING_FOR_PILOT, query = "select b from Booking b where b.pilot.id = :"
				+ Booking.QUERY_PARAMETER_PILOT_ID),
		@NamedQuery(name = Booking.QUERY_FIND_BOOKING_BY_CORE_ATTRIBUTES, query = "select b from Booking b where b.aircraft = :"
				+ Booking.QUERY_PARAMETER_AIRCRAFT
				+ " and b.pilot = :"
				+ Booking.QUERY_PARAMETER_PILOT
				+ " and b.start = :"
				+ Booking.QUERY_PARAMETER_START
				+ " and b.end = :"
				+ Booking.QUERY_PARAMETER_END),
		@NamedQuery(name = Booking.QUERY_GET_ALL_BOOKINGS_PAGINATED, query = "select b from Booking b order by b.start") })
public class Booking implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String QUERY_FIND_BOOKING_BY_ID = "Booking.findBookingById";

	public static final String QUERY_FIND_BOOKING_BY_CORE_ATTRIBUTES = "Booking.findBookingByCoreAttributes";

	public static final String QUERY_FIND_BOOKING_FOR_PILOT = "Booking.findBookingForPilot";

	public static final String QUERY_PARAMETER_BOOKINGID = "bookingId";
	public static final String QUERY_PARAMETER_PILOT = "pilot";
	public static final String QUERY_PARAMETER_PILOT_ID = "pilotId";
	public static final String QUERY_PARAMETER_AIRCRAFT = "aircraft";
	public static final String QUERY_PARAMETER_START = "start";
	public static final String QUERY_PARAMETER_END = "end";

	public static final String QUERY_GET_ALL_BOOKINGS_PAGINATED = "Booking.findAllBookingsPaginated";

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", unique = true)
	private String bookingId;

	@ManyToOne
	@NotNull
	@Valid
	private Aircraft aircraft;

	@ManyToOne
	@NotNull
	@Valid
	private Pilot pilot;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, name = "startDate")
	private Date start;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, name = "endDate")
	private Date end;

	@NotNull
	@Column(nullable = false)
	private BookingState bookingState = BookingState.INITIAL;

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
		if (end == null) {
			if (other.end != null) {
				return false;
			}
		} else if (!dateEquals(end, other.end)) {
			return false;
		}
		if (pilot == null) {
			if (other.pilot != null) {
				return false;
			}
		} else if (!pilot.equals(other.pilot)) {
			return false;
		}
		if (start == null) {
			if (other.start != null) {
				return false;
			}
		} else if (!dateEquals(start, other.start)) {
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

	public BookingState getBookingState() {
		return bookingState;
	}

	public Date getEnd() {
		return end;
	}

	public Pilot getPilot() {
		return pilot;
	}

	public Date getStart() {
		return start;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aircraft == null) ? 0 : aircraft.hashCode());
		result = prime * result
				+ ((bookingId == null) ? 0 : bookingId.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((pilot == null) ? 0 : pilot.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public void setBookingState(BookingState bookingState) {
		this.bookingState = bookingState;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", aircraft=" + aircraft
				+ ", pilot=" + pilot + ", startDate=" + start + ", endDate="
				+ end + ", bookingState=" + bookingState + "]";
	}

}
