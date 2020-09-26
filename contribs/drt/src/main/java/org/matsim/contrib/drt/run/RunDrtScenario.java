/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2017 by the members listed in the COPYING,        *
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

package org.matsim.contrib.drt.run;

import org.matsim.contrib.dvrp.run.DvrpConfigGroup;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.vis.otfvis.OTFVisConfigGroup;

import com.google.common.base.Preconditions;

/**
 * @author michalm
 */
public class RunDrtScenario {
	public static void run(String configFile, boolean otfvis) {
		Config config = ConfigUtils.loadConfig(configFile, new MultiModeDrtConfigGroup(), new DvrpConfigGroup(),
				new OTFVisConfigGroup());
		DrtControlerCreator.createControlerWithSingleModeDrt(config, otfvis).run();
	}

	public static void main(String[] args) {
		Preconditions.checkArgument(args.length == 1,
				"RunDrtScenario needs one argument: path to the configuration file");
		RunDrtScenario.run(args[0], false);
	}
}
