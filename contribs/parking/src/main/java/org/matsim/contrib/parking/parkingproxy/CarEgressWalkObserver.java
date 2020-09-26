/* *********************************************************************** *
 * project: org.matsim.*
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2020 by the members listed in the COPYING,        *
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

package org.matsim.contrib.parking.parkingproxy;

import java.io.File;

import org.matsim.api.core.v01.TransportMode;
import org.matsim.core.controler.events.BeforeMobsimEvent;
import org.matsim.core.controler.listener.BeforeMobsimListener;

/**
 * Before the mobsim, gets the current {@linkplain PenaltyCalculator} from the provided {@linkplain PenaltyGenerator} and
 * resets that latter so it can - theoretically - gather information during the new iteration. However, it is your own
 * responsibility to feed it with data. The penalty for each space-time-gridcell with non-zero penalty is dumped for each
 * iteration. In the zeroth iteration, {@linkplain PenaltyCalculator#getDummyCalculator()} is used to calculate the penalties.
 * 
 * @author tkohl / Senozon
 *
 */
class CarEgressWalkObserver implements BeforeMobsimListener {
	
	private static final String INSERTIONKEY = "[INSERTIONKEY]";
	public static final String OUTFILE_PENALTIES = "penalties_iter" + INSERTIONKEY + ".csv";
	public static final String CARMODE = TransportMode.car;	
	
	private final PenaltyGenerator penaltyGenerator;
	private final PenaltyFunction penaltyFunction;
	
	private PenaltyCalculator penaltyCalculator;
	
	/**
	 * Sets the class up with the {@linkplain PenaltyCalculator.DefaultPenaltyFunction} and the specified {@linkplain PenaltyGenerator}.
	 * 
	 * @param penaltyGenerator
	 */
	public CarEgressWalkObserver(PenaltyGenerator penaltyGenerator, PenaltyFunction penaltyFunction) {
		this.penaltyGenerator = penaltyGenerator;
		this.penaltyFunction = penaltyFunction;
	}

	@Override
	public void notifyBeforeMobsim(BeforeMobsimEvent event) {
		// update the Penalties to the result of the last iteration
		if (event.getIteration() == 0) {
			this.penaltyCalculator = PenaltyCalculator.getDummyCalculator();
		} else {
			this.penaltyCalculator = this.penaltyGenerator.generatePenaltyCalculator();
			this.penaltyCalculator.setPenaltyFunction(this.penaltyFunction);
			this.penaltyGenerator.reset();
		}
		this.penaltyCalculator.dump(new File(event.getServices().getConfig().controler().getOutputDirectory(),
				OUTFILE_PENALTIES.replace(INSERTIONKEY, Integer.toString(event.getIteration()))));
	}
	
	/*package*/ PenaltyCalculator getPenaltyCalculator() {
		return this.penaltyCalculator;
	}

}
