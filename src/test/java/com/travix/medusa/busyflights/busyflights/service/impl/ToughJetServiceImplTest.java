package com.travix.medusa.busyflights.busyflights.service.impl;

import com.travix.medusa.busyflights.busyflights.converter.ToughJetToBusyResponseConverter;
import com.travix.medusa.busyflights.busyflights.domain.BusyFlightsRequest;
import com.travix.medusa.busyflights.toughjet.domain.ToughJetResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static com.travix.medusa.busyflights.facory.BusyFlightsRequestFactory.getDefaultRequest;
import static com.travix.medusa.busyflights.facory.ToughJetResponseFactory.getDefaultToughJetResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ToughJetServiceImplTest {

	private BusyFlightsRequest busyFlightsRequest;
	private ToughJetServiceImpl toughJetService;
	private RestTemplate mockedRestTemplate;
	private ToughJetToBusyResponseConverter toughJetToBusyResponseConverter;

	@BeforeEach
	void test() {
		busyFlightsRequest = getDefaultRequest();
		mockedRestTemplate = mock(RestTemplate.class);
		toughJetToBusyResponseConverter = mock(ToughJetToBusyResponseConverter.class);
		toughJetService = new ToughJetServiceImpl(mockedRestTemplate, toughJetToBusyResponseConverter);
	}

	@Test
	void getFlightDetails_useCorrectUri() {
		mockSuccessRestCall();
		ArgumentCaptor<URI> uriArgumentCaptor = ArgumentCaptor.forClass(URI.class);
		toughJetService.getFlightDetails(busyFlightsRequest);
		verify(mockedRestTemplate, only()).getForEntity(uriArgumentCaptor.capture(), any());
		final URI uri = uriArgumentCaptor.getValue();
		assertEquals("/toughJet/flights", uri.getPath());
		assertEquals("from=LHR&to=IND&outboundDate=2022-01-01&inboundDate=2022-01-31&numberOfAdults=4", uri.getQuery());
	}

	@Test
	void getFlightDetails_successApiCall_useConverterOnce() {
		mockSuccessRestCall();
		toughJetService.getFlightDetails(busyFlightsRequest);
		verify(toughJetToBusyResponseConverter, only()).convert(any());

	}

	private void mockSuccessRestCall() {
		ResponseEntity responseEntity = mock(ResponseEntity.class);
		when(responseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
		when(responseEntity.getBody()).thenReturn(new ToughJetResponse[]{getDefaultToughJetResponse()});
		when(mockedRestTemplate.getForEntity(any(URI.class), any())).thenReturn(responseEntity);
	}


}