package com.travix.medusa.busyflights.crazyair.controller;

import com.travix.medusa.busyflights.crazyair.domain.CrazyAirRequest;
import com.travix.medusa.busyflights.crazyair.domain.CrazyAirResponse;
import com.travix.medusa.busyflights.crazyair.service.CrazyAirService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CrazyAirController {

    private final CrazyAirService crazyAirService;

    @GetMapping(value = "/crazyAir/flights")
    public ResponseEntity<List<CrazyAirResponse>> getFlightDetails(@RequestParam final String origin,
                                                                   @RequestParam final String destination,
                                                                   @RequestParam final String departureDate,
                                                                   @RequestParam final String returnDate,
                                                                   @RequestParam final int passengerCount) {
        CrazyAirRequest crazyAirRequest = new CrazyAirRequest(origin, destination, departureDate, returnDate, passengerCount);
        return new ResponseEntity(crazyAirService.getFlightDetails(crazyAirRequest), HttpStatus.OK);
    }
}
