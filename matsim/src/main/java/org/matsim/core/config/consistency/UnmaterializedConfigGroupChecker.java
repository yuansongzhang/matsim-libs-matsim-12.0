
/* *********************************************************************** *
 * project: org.matsim.*
 * UnmaterializedConfigGroupChecker.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2019 by the members listed in the COPYING,        *
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

 package org.matsim.core.config.consistency;

import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigGroup;

public class UnmaterializedConfigGroupChecker implements ConfigConsistencyChecker {
	@Override
	public void checkConsistency(Config config) {
		for (ConfigGroup configGroup : config.getModules().values()) {
			if (configGroup.getClass().equals(ConfigGroup.class)) {
				throw new RuntimeException("Unmaterialized config group: "+configGroup.getName());
			}
		}
	}
}
