package com.travix.medusa.busyflights.toughjet.controller;

import com.travix.medusa.busyflights.toughjet.domain.ToughJetRequest;
import com.travix.medusa.busyflights.toughjet.domain.ToughJetResponse;
import com.travix.medusa.busyflights.toughjet.service.ToughJetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ToughJetController {

    private final ToughJetService toughJetService;

    @GetMapping(value = "/toughJet/flights")
    public ResponseEntity<List<ToughJetResponse>> getFlightDetails( @RequestParam("from") final String from,
                                                                    @RequestParam("to") final String to,
                                                                    @RequestParam("outboundDate") final String outboundDate,
                                                                    @RequestParam("inboundDate") final String inboundDate,
                                                                    @RequestParam("numberOfAdults") final Integer numberOfAdults){

        ToughJetRequest toughJetRequest  = new ToughJetRequest(from,to,outboundDate,inboundDate,numberOfAdults);
        return new ResponseEntity(toughJetService.getFlightDetails(toughJetRequest), HttpStatus.OK);
    }
}
