package com.travix.medusa.busyflights.busyflights.converter;

import com.travix.medusa.busyflights.busyflights.domain.BusyFlightsResponse;
import com.travix.medusa.busyflights.crazyair.domain.CrazyAirResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.travix.medusa.busyflights.busyflights.enums.Providers.CRAZY_AIR;
import static com.travix.medusa.busyflights.facory.CrazyAirResponseFactory.getDefaultCrazyAirResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CrazyAirToBusyFlightConverterTest {

	private CrazyAirResponse crazyAirResponse;
	private CrazyAirToBusyFlightConverter crazyAirToBusyFlightConverter;

	@BeforeEach
	void setup() {
		crazyAirResponse = getDefaultCrazyAirResponse();
		crazyAirToBusyFlightConverter = new CrazyAirToBusyFlightConverter();
	}

	@Test
	void convert_setCorrectPrice() {
		final BusyFlightsResponse busyFlightsResponse = crazyAirToBusyFlightConverter.convert(crazyAirResponse);
		assertEquals(crazyAirResponse.getPrice(), busyFlightsResponse.getPrice(), 0);
	}

	@Test
	void convert_setCorrectCabinClass() {
		final BusyFlightsResponse busyFlightsResponse = crazyAirToBusyFlightConverter.convert(crazyAirResponse);
		assertEquals(crazyAirResponse.getCabinclass(), busyFlightsResponse.getCabinclass());
	}

	@Test
	void convert_setCorrectAirline() {
		final BusyFlightsResponse busyFlightsResponse = crazyAirToBusyFlightConverter.convert(crazyAirResponse);
		assertEquals(CRAZY_AIR.getName(), busyFlightsResponse.getAirline());
	}

	@Test
	void convert_setCorrectDepartureAirportCode() {
		final BusyFlightsResponse busyFlightsResponse = crazyAirToBusyFlightConverter.convert(crazyAirResponse);
		assertEquals(crazyAirResponse.getDepartureAirportCode(), busyFlightsResponse.getDepartureAirportCode());
	}

	@Test
	void convert_setCorrectDestinationAirportCode() {
		final BusyFlightsResponse busyFlightsResponse = crazyAirToBusyFlightConverter.convert(crazyAirResponse);
		assertEquals(crazyAirResponse.getDestinationAirportCode(), busyFlightsResponse.getDestinationAirportCode());
	}

	@Test
	void convert_setCorrectDepartureDate() {
		final BusyFlightsResponse busyFlightsResponse = crazyAirToBusyFlightConverter.convert(crazyAirResponse);
		assertEquals(crazyAirResponse.getDepartureDate(), busyFlightsResponse.getDepartureDate());
	}

	@Test
	void convert_setCorrectArrivalDate() {
		final BusyFlightsResponse busyFlightsResponse = crazyAirToBusyFlightConverter.convert(crazyAirResponse);
		assertEquals(crazyAirResponse.getArrivalDate(), busyFlightsResponse.getArrivalDate());
	}

}