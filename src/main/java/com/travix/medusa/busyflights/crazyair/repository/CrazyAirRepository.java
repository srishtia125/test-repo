package com.travix.medusa.busyflights.crazyair.repository;

import com.travix.medusa.busyflights.crazyair.domain.CrazyAirRequest;
import com.travix.medusa.busyflights.crazyair.domain.CrazyAirResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrazyAirRepository {
     public List<CrazyAirResponse> getFlightDetails(CrazyAirRequest crazyAirRequest);
    }
