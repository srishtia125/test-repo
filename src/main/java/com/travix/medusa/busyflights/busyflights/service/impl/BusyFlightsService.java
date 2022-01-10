package com.travix.medusa.busyflights.busyflights.service.impl;


import com.travix.medusa.busyflights.busyflights.domain.BusyFlightsRequest;
import com.travix.medusa.busyflights.busyflights.domain.BusyFlightsResponse;
import com.travix.medusa.busyflights.busyflights.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class BusyFlightsService {

    private final List<ProviderService> providerServices;

    public List<BusyFlightsResponse> getFlightDetails(BusyFlightsRequest busyFlightsRequest) {

        List<BusyFlightsResponse> flightDetails = new ArrayList<>();
        providerServices.forEach(providerService ->
                flightDetails.addAll(providerService.getFlightDetails(busyFlightsRequest))
        );
        Collections.sort(flightDetails, Comparator.comparingDouble(BusyFlightsResponse::getPrice));
        return flightDetails;
    }
}
