package com.travix.medusa.busyflights.busyflights.service.impl;

import com.travix.medusa.busyflights.busyflights.converter.CrazyAirToBusyFlightConverter;
import com.travix.medusa.busyflights.busyflights.domain.BusyFlightsRequest;
import com.travix.medusa.busyflights.crazyair.domain.CrazyAirResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static com.travix.medusa.busyflights.facory.BusyFlightsRequestFactory.getDefaultRequest;
import static com.travix.medusa.busyflights.facory.CrazyAirResponseFactory.getDefaultCrazyAirResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CrazyAirServiceImplTest {

	private BusyFlightsRequest busyFlightsRequest;
	private CrazyAirServiceImpl crazyAirService;
	private RestTemplate mockedRestTemplate;
	private CrazyAirToBusyFlightConverter crazyAirToBusyFlightConverter;

	@BeforeEach
	void test() {
		busyFlightsRequest = getDefaultRequest();
		mockedRestTemplate = mock(RestTemplate.class);
		crazyAirToBusyFlightConverter = mock(CrazyAirToBusyFlightConverter.class);
		crazyAirService = new CrazyAirServiceImpl(mockedRestTemplate, crazyAirToBusyFlightConverter);
	}

	@Test
	void getFlightDetails_useCorrectUri() {
		mockSuccessRestCall();
		ArgumentCaptor<URI> uriArgumentCaptor = ArgumentCaptor.forClass(URI.class);
		crazyAirService.getFlightDetails(busyFlightsRequest);
		verify(mockedRestTemplate, only()).getForEntity(uriArgumentCaptor.capture(), any());
		final URI uri = uriArgumentCaptor.getValue();
		assertEquals("/crazyAir/flights", uri.getPath());
		assertEquals("origin=LHR&destination=IND&departureDate=2022-01-01&returnDate=2022-01-31&passengerCount=4", uri.getQuery());
	}

	@Test
	void getFlightDetails_successApiCall_useConverterOnce() {
		mockSuccessRestCall();
		crazyAirService.getFlightDetails(busyFlightsRequest);
		verify(crazyAirToBusyFlightConverter, only()).convert(any());

	}

	private void mockSuccessRestCall() {
		ResponseEntity responseEntity = mock(ResponseEntity.class);
		when(responseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
		when(responseEntity.getBody()).thenReturn(new CrazyAirResponse[]{getDefaultCrazyAirResponse()});
		when(mockedRestTemplate.getForEntity(any(URI.class), any())).thenReturn(responseEntity);
	}


}