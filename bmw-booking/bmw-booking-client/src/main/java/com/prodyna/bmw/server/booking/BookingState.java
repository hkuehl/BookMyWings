package com.prodyna.bmw.server.booking;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlEnumValue;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
public enum BookingState {

	INITIAL {

		@Override
		public List<BookingState> allowedTransitions() {
			return Arrays.asList(RESERVED, CANCELLED);
		}

	},

	RESERVED {

		@Override
		public List<BookingState> allowedTransitions() {
			return Arrays.asList(LENT, CANCELLED);
		}

	},
	LENT {
		@Override
		public List<BookingState> allowedTransitions() {
			return Arrays.asList(FINISHED);
		}

	},
	FINISHED {

		@Override
		public List<BookingState> allowedTransitions() {
			return Collections.<BookingState> emptyList();
		}

	},
	@XmlEnumValue("cancelled")
	CANCELLED {

		@Override
		public List<BookingState> allowedTransitions() {
			return Collections.<BookingState> emptyList();
		}

	};

	/**
	 * @param bookingStateString
	 * @return
	 */
	public static BookingState fromString(String bookingStateString) {

		if (bookingStateString == null) {
			throw new IllegalArgumentException(
					"bookingStateString to parse was null");
		}

		for (BookingState bookingState : values()) {
			if (bookingStateString.equalsIgnoreCase(bookingState.toString())) {
				return bookingState;
			}
		}

		throw new RuntimeException(
				"No suitable Booking-state found for String "
						+ bookingStateString);
	}

	public abstract List<BookingState> allowedTransitions();

}
