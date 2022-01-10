package com.travix.medusa.busyflights.toughjet.service;

import com.travix.medusa.busyflights.toughjet.domain.ToughJetRequest;
import com.travix.medusa.busyflights.toughjet.domain.ToughJetResponse;
import com.travix.medusa.busyflights.toughjet.repository.ToughJetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ToughJetService {

    private final ToughJetRepository toughJetRepository;

    public List<ToughJetResponse> getFlightDetails(ToughJetRequest toughJetRequest){
        // validate request
        return toughJetRepository.getFlightDetails(toughJetRequest);
    }

}
