package com.travix.medusa.busyflights.toughjet.repository;

import com.travix.medusa.busyflights.toughjet.domain.ToughJetRequest;
import com.travix.medusa.busyflights.toughjet.domain.ToughJetResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToughJetRepository {

        public  List<ToughJetResponse> getFlightDetails(ToughJetRequest toughJetRequest);
}
