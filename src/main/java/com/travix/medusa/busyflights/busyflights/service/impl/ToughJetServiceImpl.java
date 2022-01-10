package com.travix.medusa.busyflights.busyflights.service.impl;

import com.travix.medusa.busyflights.busyflights.converter.ToughJetToBusyResponseConverter;
import com.travix.medusa.busyflights.busyflights.domain.BusyFlightsRequest;
import com.travix.medusa.busyflights.busyflights.domain.BusyFlightsResponse;
import com.travix.medusa.busyflights.busyflights.service.ProviderService;
import com.travix.medusa.busyflights.toughjet.domain.ToughJetResponse;
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
@Slf4j
@Service
public class ToughJetServiceImpl implements ProviderService {

    private  final RestTemplate toughJetRestTemplate;
    private final ToughJetToBusyResponseConverter toughJetToBusyFlightResponseConverter;
    
    @Override
    public List<BusyFlightsResponse> getFlightDetails(BusyFlightsRequest busyFlightsRequest) {

        List<ToughJetResponse> toughJetResponses = new ArrayList<>();
        try {
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080/toughJet/flights")
                    .queryParam("from", busyFlightsRequest.getOrigin())
                    .queryParam("to", busyFlightsRequest.getDestination())
                    .queryParam("outboundDate", busyFlightsRequest.getDepartureDate())
                    .queryParam("inboundDate", busyFlightsRequest.getReturnDate())
                    .queryParam("numberOfAdults", String.valueOf(busyFlightsRequest.getNumberOfPassengers()));

            final ResponseEntity<ToughJetResponse[]> responseEntity = toughJetRestTemplate.getForEntity(uriComponentsBuilder.build().toUri(),
                    ToughJetResponse[].class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                toughJetResponses = Arrays.asList(responseEntity.getBody());
            }
        }
        catch (RestClientException ex){
            log.error("Rest Exception",ex);
        }
        List<BusyFlightsResponse> busyFlightsResponses = toughJetResponses.stream().
                map(toughJetResponse -> toughJetToBusyFlightResponseConverter.convert(toughJetResponse)).collect(Collectors.toList());

        return busyFlightsResponses;
    }
}
