package com.travix.medusa.busyflights.facory;

import com.travix.medusa.busyflights.crazyair.domain.CrazyAirResponse;

public class CrazyAirResponseFactory {

	public static CrazyAirResponse getDefaultCrazyAirResponse() {
		return new CrazyAirResponse("CrazyAir", 1200, "B",
				"LHR", "IND", "2022-02-03T10:15:30", "2022-02-03T10:15:30");

	}
}
