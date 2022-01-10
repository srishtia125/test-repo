package com.travix.medusa.busyflights.busyflights.service.impl;

import com.travix.medusa.busyflights.busyflights.converter.CrazyAirToBusyFlightConverter;
import com.travix.medusa.busyflights.busyflights.domain.BusyFlightsRequest;
import com.travix.medusa.busyflights.busyflights.domain.BusyFlightsResponse;
import com.travix.medusa.busyflights.busyflights.service.ProviderService;
import com.travix.medusa.busyflights.crazyair.domain.CrazyAirResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class CrazyAirServiceImpl implements ProviderService {

    private final RestTemplate crazyAirRestTemplate;
    private final CrazyAirToBusyFlightConverter crazyAirToBusyFlightResponseConverter;

    @Override
    public List<BusyFlightsResponse> getFlightDetails(BusyFlightsRequest busyFlightsRequest) throws RestClientException {
        List<CrazyAirResponse> crazyAirResponses = new ArrayList<>();
        try {

	        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080/crazyAir/flights")
			        .queryParam("origin", busyFlightsRequest.getOrigin())
			        .queryParam("destination", busyFlightsRequest.getDestination())
			        .queryParam("departureDate", busyFlightsRequest.getDepartureDate())
			        .queryParam("returnDate", busyFlightsRequest.getReturnDate())
			        .queryParam("passengerCount", String.valueOf(busyFlightsRequest.getNumberOfPassengers()));

	        final ResponseEntity<CrazyAirResponse[]> responseEntity = crazyAirRestTemplate.getForEntity(uriComponentsBuilder.build().toUri(),
			        CrazyAirResponse[].class);

	        if (responseEntity.getStatusCode().is2xxSuccessful()) {
		        crazyAirResponses = Arrays.asList(responseEntity.getBody());
	        }
        } catch (RestClientException ex) {
	        log.error("Rest Exception", ex);
        }
	    //map to busy response
	    return crazyAirResponses.stream().
			    map(crazyAirToBusyFlightResponseConverter::convert).collect(Collectors.toList());
    }
}
