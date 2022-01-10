package com.travix.medusa.busyflights.busyflights.converter;

import com.travix.medusa.busyflights.busyflights.domain.BusyFlightsResponse;
import com.travix.medusa.busyflights.crazyair.domain.CrazyAirResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.travix.medusa.busyflights.busyflights.enums.Providers.CRAZY_AIR;

@Component
public class CrazyAirToBusyFlightConverter implements Converter<CrazyAirResponse, BusyFlightsResponse> {

	public BusyFlightsResponse convert(CrazyAirResponse crazyAirResponse) {
		BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();
		busyFlightsResponse.setPrice(crazyAirResponse.getPrice());
		busyFlightsResponse.setCabinclass(crazyAirResponse.getCabinclass());
		busyFlightsResponse.setAirline(CRAZY_AIR.getName());
		busyFlightsResponse.setDepartureAirportCode(crazyAirResponse.getDepartureAirportCode());
        busyFlightsResponse.setDestinationAirportCode(crazyAirResponse.getDestinationAirportCode());
        busyFlightsResponse.setDepartureDate(crazyAirResponse.getDepartureDate());
        busyFlightsResponse.setArrivalDate(crazyAirResponse.getArrivalDate());
        return busyFlightsResponse;
    }
}

