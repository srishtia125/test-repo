package com.travix.medusa.busyflights.facory;

import com.travix.medusa.busyflights.toughjet.domain.ToughJetResponse;

public class ToughJetResponseFactory {

	public static ToughJetResponse getDefaultToughJetResponse() {
		return new ToughJetResponse("toughJet", 1200, 10, 12,
				"LHR", "IND", "2022-02-03T10:15:30", "2022-02-03T10:15:30");

	}
}
