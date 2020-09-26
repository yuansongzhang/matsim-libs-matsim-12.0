/* *********************************************************************** *
 * project: org.matsim.*
 * ParallelInitialReplanner.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2008 by the members listed in the COPYING,        *
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

package org.matsim.withinday.replanning.parallel;

import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.withinday.replanning.replanners.interfaces.WithinDayInitialReplanner;
import org.matsim.withinday.replanning.replanners.interfaces.WithinDayInitialReplannerFactory;

/**
 * A class for running {@link WithinDayInitialReplanner} in parallel using threads.
 *
 * @author Christoph Dobler
 */
public class ParallelInitialReplanner extends ParallelReplanner<WithinDayInitialReplannerFactory> {
		
	public ParallelInitialReplanner(int numOfThreads, EventsManager eventsManager) {
		super(numOfThreads, eventsManager);
		this.init("ParallelInitialReplanner");
	}
		
}
