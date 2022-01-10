package com.travix.medusa.busyflights.busyflights.controller;


import com.travix.medusa.busyflights.busyflights.domain.BusyFlightsRequest;
import com.travix.medusa.busyflights.busyflights.service.impl.BusyFlightsService;
import com.travix.medusa.busyflights.crazyair.domain.CrazyAirResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@Validated
public class BusyFlightController {

    @Autowired
    BusyFlightsService busyFlightsService;

    @GetMapping(value = "/busyFlight/flights")
    public ResponseEntity<List<CrazyAirResponse>> searchFlights(@RequestParam @Size(min=3,max = 3, message = "Size should be 3")  final String origin,
                                                                   @RequestParam @Size(min = 3, max = 3, message = "Size should be 3")  final String destination,
                                                                   @RequestParam final String departureDate,
                                                                   @RequestParam final String returnDate,
                                                                   @RequestParam @Max(4) final int passengerCount){
        BusyFlightsRequest busyFlightsRequest = new BusyFlightsRequest(origin,destination,departureDate,returnDate,passengerCount);
        return new ResponseEntity(busyFlightsService.getFlightDetails(busyFlightsRequest), HttpStatus.OK);
    }
}

