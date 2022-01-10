package com.travix.medusa.busyflights.busyflights.converter;

import com.travix.medusa.busyflights.busyflights.domain.BusyFlightsResponse;
import com.travix.medusa.busyflights.toughjet.domain.ToughJetResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.travix.medusa.busyflights.busyflights.enums.Providers.TOUGH_JET;
import static com.travix.medusa.busyflights.facory.ToughJetResponseFactory.getDefaultToughJetResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertNull;

public class ToughJetToBusyResponseConverterTest {

	private ToughJetResponse toughJetResponse;
	private ToughJetToBusyResponseConverter toughJetToBusyResponseConverter;

	@BeforeEach
	void setup() {
		toughJetResponse = getDefaultToughJetResponse();
		toughJetToBusyResponseConverter = new ToughJetToBusyResponseConverter();
	}

	@Test
	void convert_setCorrectPrice() {
		final BusyFlightsResponse busyFlightsResponse = toughJetToBusyResponseConverter.convert(toughJetResponse);
		final double discount = toughJetResponse.getDiscount();
		final double tax = toughJetResponse.getTax();
		final double basePrice = toughJetResponse.getBasePrice();
		double discountValue = 0.0;
		if (discount > 0) {
			discountValue = (basePrice * discount) / 100.00;
		}

		double finalPrice = basePrice + tax - discountValue;

		assertEquals(finalPrice, busyFlightsResponse.getPrice(), 0);

	}


	@Test
	void convert_setNullCabinClass() {
		final BusyFlightsResponse busyFlightsResponse = toughJetToBusyResponseConverter.convert(toughJetResponse);
		assertNull(busyFlightsResponse.getCabinclass());
	}

	@Test
	void convert_setCorrectAirline() {
		final BusyFlightsResponse busyFlightsResponse = toughJetToBusyResponseConverter.convert(toughJetResponse);
		assertEquals(TOUGH_JET.getName(), busyFlightsResponse.getAirline());
	}

	@Test
	void convert_setCorrectDepartureAirportCode() {
		final BusyFlightsResponse busyFlightsResponse = toughJetToBusyResponseConverter.convert(toughJetResponse);
		assertEquals(toughJetResponse.getDepartureAirportName(), busyFlightsResponse.getDepartureAirportCode());
	}

	@Test
	void convert_setCorrectDestinationAirportCode() {
		final BusyFlightsResponse busyFlightsResponse = toughJetToBusyResponseConverter.convert(toughJetResponse);
		assertEquals(toughJetResponse.getArrivalAirportName(), busyFlightsResponse.getDestinationAirportCode());
	}

	@Test
	void convert_setCorrectDepartureDate() {
		final BusyFlightsResponse busyFlightsResponse = toughJetToBusyResponseConverter.convert(toughJetResponse);
		assertEquals(toughJetResponse.getOutboundDateTime(), busyFlightsResponse.getDepartureDate());
	}

	@Test
	void convert_setCorrectArrivalDate() {
		final BusyFlightsResponse busyFlightsResponse = toughJetToBusyResponseConverter.convert(toughJetResponse);
		assertEquals(toughJetResponse.getInboundDateTime(), busyFlightsResponse.getArrivalDate());
	}

}