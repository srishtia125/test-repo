package com.travix.medusa.busyflights.busyflights.service;

import com.travix.medusa.busyflights.busyflights.domain.BusyFlightsRequest;
import com.travix.medusa.busyflights.busyflights.domain.BusyFlightsResponse;

import java.util.List;

public interface ProviderService {

    List<BusyFlightsResponse> getFlightDetails(BusyFlightsRequest busyFlightsRequest);
}
