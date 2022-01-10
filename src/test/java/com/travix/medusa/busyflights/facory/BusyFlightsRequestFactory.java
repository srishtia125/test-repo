package com.travix.medusa.busyflights.facory;

import com.travix.medusa.busyflights.busyflights.domain.BusyFlightsRequest;

public class BusyFlightsRequestFactory {

	public static BusyFlightsRequest getDefaultRequest() {
		return new BusyFlightsRequest("LHR", "IND", "2022-01-01", "2022-01-31", 4);
	}
}
