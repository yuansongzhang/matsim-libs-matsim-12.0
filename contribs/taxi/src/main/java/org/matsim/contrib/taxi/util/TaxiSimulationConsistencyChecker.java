/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2016 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */

package org.matsim.contrib.taxi.util;

import org.apache.log4j.Logger;
import org.matsim.contrib.dvrp.optimizer.Request;
import org.matsim.contrib.taxi.passenger.TaxiRequest;
import org.matsim.contrib.taxi.passenger.TaxiRequest.TaxiRequestStatus;
import org.matsim.contrib.taxi.passenger.SubmittedTaxiRequestsCollector;
import org.matsim.contrib.taxi.run.TaxiConfigGroup;
import org.matsim.core.mobsim.framework.events.MobsimBeforeCleanupEvent;
import org.matsim.core.mobsim.framework.listeners.MobsimBeforeCleanupListener;
import org.matsim.core.utils.misc.Time;

import com.google.inject.Inject;

public class TaxiSimulationConsistencyChecker implements MobsimBeforeCleanupListener {
	private final SubmittedTaxiRequestsCollector requestCollector;
	private final TaxiConfigGroup taxiCfg;

	@Inject
	public TaxiSimulationConsistencyChecker(SubmittedTaxiRequestsCollector requestCollector, TaxiConfigGroup taxiCfg) {
		this.requestCollector = requestCollector;
		this.taxiCfg = taxiCfg;
	}

	public void addCheckAllRequestsPerformed() {
		for (Request r : requestCollector.getRequests().values()) {
			TaxiRequest tr = (TaxiRequest)r;
			if (tr.getStatus() != TaxiRequestStatus.PERFORMED) {
				if (taxiCfg.isBreakSimulationIfNotAllRequestsServed()) {
					throw new IllegalStateException(
							"Not all taxi requests served at simulation end time. This exception can be disabled in the taxi config group.");
				} else {
					Logger.getLogger(getClass())
							.warn("Taxi request not performed. Request time:\t" + Time.writeTime(
									tr.getEarliestStartTime()) + "\tPassenger:\t" + tr.getPassengerId());
				}
			}
		}
	}

	@Override
	public void notifyMobsimBeforeCleanup(final MobsimBeforeCleanupEvent e) {
		addCheckAllRequestsPerformed();
	}
}
