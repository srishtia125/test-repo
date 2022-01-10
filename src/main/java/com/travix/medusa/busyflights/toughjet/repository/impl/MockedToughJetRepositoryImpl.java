package com.travix.medusa.busyflights.toughjet.repository.impl;

import com.travix.medusa.busyflights.toughjet.domain.ToughJetRequest;
import com.travix.medusa.busyflights.toughjet.domain.ToughJetResponse;
import com.travix.medusa.busyflights.toughjet.repository.ToughJetRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class MockedToughJetRepositoryImpl implements ToughJetRepository {
    @Override
    public List<ToughJetResponse> getFlightDetails(ToughJetRequest toughJetRequest) {
        {
            List<ToughJetResponse> toughJetResponses = new ArrayList<>();
            ToughJetResponse toughJetTestResponse = new ToughJetResponse("toughJet",1200,10,12,
                    "LHR","IND","2022-02-03T10:15:30","2022-02-03T10:15:30");

            ToughJetResponse toughJetTestResponse2 = new ToughJetResponse("toughJet",1000,10,12,
                    "LHR","IND","2022-02-03T10:15:30Z","2022-02-03T10:15:30Z");
            toughJetResponses.add(toughJetTestResponse);
            toughJetResponses.add(toughJetTestResponse2);
            return toughJetResponses;
        }
    }
}
