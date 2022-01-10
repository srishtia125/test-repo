package com.travix.medusa.busyflights.crazyair.service;

import com.travix.medusa.busyflights.crazyair.domain.CrazyAirRequest;
import com.travix.medusa.busyflights.crazyair.domain.CrazyAirResponse;
import com.travix.medusa.busyflights.crazyair.repository.CrazyAirRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CrazyAirService {

    @Autowired
    private CrazyAirRepository crazyAirRepository;

    public List<CrazyAirResponse> getFlightDetails(@RequestBody CrazyAirRequest crazyAirRequest){
        return crazyAirRepository.getFlightDetails(crazyAirRequest);
    }

}
