package com.travix.medusa.busyflights.busyflights.service.impl;

import com.travix.medusa.busyflights.AbstractApplicationIntTests;
import com.travix.medusa.busyflights.busyflights.domain.BusyFlightsRequest;
import com.travix.medusa.busyflights.busyflights.domain.BusyFlightsResponse;
import com.travix.medusa.busyflights.facory.BusyFlightsRequestFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BusyFlightsServiceIntTest extends AbstractApplicationIntTests {

	@Autowired
	private BusyFlightsService busyFlightsService;

	@Test
	public void getFlightDetails_returnsSortedData() {
		final BusyFlightsRequest busyFlightsRequest = BusyFlightsRequestFactory.getDefaultRequest();

		final List<BusyFlightsResponse> flightDetails = busyFlightsService.getFlightDetails(busyFlightsRequest);

		assertNotNull(flightDetails);

		assertEquals(4, flightDetails.size());

		final List<Double> pricesList = flightDetails.stream().map(BusyFlightsResponse::getPrice).collect(Collectors.toList());

		//assert prices are already sorted by natural order , since they are double values
		assertEquals(pricesList, pricesList.stream().sorted().collect(Collectors.toList()));
	}

}