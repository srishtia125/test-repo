package com.travix.medusa.busyflights.busyflights.converter;

import com.travix.medusa.busyflights.busyflights.domain.BusyFlightsResponse;
import com.travix.medusa.busyflights.toughjet.domain.ToughJetResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.travix.medusa.busyflights.busyflights.enums.Providers.TOUGH_JET;

@Component
public class ToughJetToBusyResponseConverter implements Converter<ToughJetResponse, BusyFlightsResponse> {

    @Override
    public BusyFlightsResponse convert(ToughJetResponse toughJetResponse) {
        BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();
	    busyFlightsResponse.setAirline(TOUGH_JET.getName());
	     busyFlightsResponse.setCabinclass("");
	    busyFlightsResponse.setDestinationAirportCode(toughJetResponse.getArrivalAirportName());
        busyFlightsResponse.setDepartureAirportCode(toughJetResponse.getDepartureAirportName());
        busyFlightsResponse.setPrice(convertToPrice(toughJetResponse.getBasePrice(),toughJetResponse.getTax(),toughJetResponse.getDiscount()));
        busyFlightsResponse.setArrivalDate(toughJetResponse.getInboundDateTime());
        busyFlightsResponse.setDepartureDate(toughJetResponse.getOutboundDateTime());
        return busyFlightsResponse;
    }

    private double convertToPrice(double basePrice, double tax, double discount) {
        if(discount > 0 )
         return (basePrice - (basePrice * (discount/100))) +tax;
        return basePrice + tax;
    }
}
