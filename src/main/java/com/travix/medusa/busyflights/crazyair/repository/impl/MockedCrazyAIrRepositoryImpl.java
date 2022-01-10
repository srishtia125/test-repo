package com.travix.medusa.busyflights.crazyair.repository.impl;
import com.travix.medusa.busyflights.crazyair.domain.CrazyAirRequest;
import com.travix.medusa.busyflights.crazyair.domain.CrazyAirResponse;
import com.travix.medusa.busyflights.crazyair.repository.CrazyAirRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MockedCrazyAIrRepositoryImpl implements CrazyAirRepository {

    @Override
    public List<CrazyAirResponse> getFlightDetails(CrazyAirRequest CrazyAirRequest) {
        {
            List<CrazyAirResponse> crazyAirResponses = new ArrayList<>();
            CrazyAirResponse crazyAirTestResponse = new CrazyAirResponse("CrazyAir",1200,"B",
                    "LHR","IND","2022-02-03T10:15:30","2022-02-03T10:15:30");

            CrazyAirResponse crazyAirTestResponse2 = new CrazyAirResponse("CrazyAir",1000,"E",
                    "LHR","IND","2022-02-03T10:15:30Z","2022-02-03T10:15:30Z");
            crazyAirResponses.add(crazyAirTestResponse);
            crazyAirResponses.add(crazyAirTestResponse2);
            return crazyAirResponses;
        }
    }

}
