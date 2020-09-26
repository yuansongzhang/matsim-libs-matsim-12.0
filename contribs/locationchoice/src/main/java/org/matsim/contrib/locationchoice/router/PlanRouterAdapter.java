/* *********************************************************************** *
 * project: org.matsim.*
 * PlanRouterAdapter.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2012 by the members listed in the COPYING,        *
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
package org.matsim.contrib.locationchoice.router;

import java.util.List;

import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.population.Activity;
import org.matsim.api.core.v01.population.Leg;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.api.core.v01.population.PlanElement;
import org.matsim.api.core.v01.population.PopulationFactory;
import org.matsim.core.controler.MatsimServices;
import org.matsim.core.population.PopulationUtils;
import org.matsim.core.population.algorithms.PersonAlgorithm;
import org.matsim.core.population.algorithms.PlanAlgorithm;
import org.matsim.core.population.routes.RouteFactories;
import org.matsim.core.router.PlanRouter;
import org.matsim.core.router.TripRouter;
import org.matsim.core.router.util.LeastCostPathCalculator;
import org.matsim.core.router.util.LeastCostPathCalculatorFactory;
import org.matsim.core.router.util.TravelDisutility;
import org.matsim.core.router.util.TravelTime;
import org.matsim.facilities.FacilitiesUtils;

/**
 * Adapts a PlanRouter to the PlansCalcRoute interface.
 * Note that it is difficult to guarantee full backward compatibility,
 * as the legs are not modified "in place" anymore.
 * @author thibautd
 * @deprecated this is just a way to keep old code running,
 * and should by no means be used in new code.
 */
@Deprecated // use TripRouter instead. kai, dec'13 
public class PlanRouterAdapter implements PlanAlgorithm, PersonAlgorithm {
	private final LeastCostPathCalculator routeAlgo;
    private final RouteFactories routeFactory;
    private final PlanRouter planRouter;

	@Deprecated // use TripRouter instead. kai, dec'13 
	public static double handleLeg(TripRouter tripRouter, final Person person,
			final Leg leg, final Activity fromAct, final Activity toAct,
			final double depTime) {
		List<? extends PlanElement> trip = tripRouter.calcRoute(
					leg.getMode(),
			  FacilitiesUtils.toFacility( fromAct, null ),
			  FacilitiesUtils.toFacility( toAct, null ),
					depTime,
					person);
	
		if ( trip.size() != 1 ) {
			throw new IllegalStateException( "This method can only be used with "+
					"routing modules returning single legs. Got the following trip "+
					"for mode "+leg.getMode()+": "+trip );
		}
	
		Leg tripLeg = (Leg) trip.get( 0 );
		leg.setRoute( tripLeg.getRoute() );
		leg.setTravelTime(tripLeg.getTravelTime().seconds());
		leg.setDepartureTime(tripLeg.getDepartureTime().seconds());

		return PopulationUtils.decideOnTravelTimeForLeg(tripLeg).seconds();
	}

    @Deprecated // use TripRouter instead. kai, dec'13
	public PlanRouterAdapter(
			final MatsimServices controler) {
		this.planRouter = new PlanRouter(
							controler.getTripRouterProvider().get(),
							controler.getScenario().getActivityFacilities() );
		
		TravelTime time = controler.getLinkTravelTimes();
		TravelDisutility disutility =
			controler.getTravelDisutilityFactory().createTravelDisutility(
					time);

        LeastCostPathCalculatorFactory factory = controler.getLeastCostPathCalculatorFactory();
        Network network = controler.getScenario().getNetwork();
		this.routeAlgo = factory.createPathCalculator(network, disutility, time);
        PopulationFactory populationFactory = controler.getScenario().getPopulation().getFactory();
		this.routeFactory = ((PopulationFactory) populationFactory).getRouteFactories();
	}


    @Deprecated // use TripRouter instead. kai, dec'13
	public final LeastCostPathCalculator getLeastCostPathCalculator(){
		return this.routeAlgo;
	}

    @Override
	@Deprecated // use TripRouter instead. kai, dec'13 
	public void run(final Person person) {
		for (Plan plan : person.getPlans()) {
			run( plan);
		}
	}

	@Override
	@Deprecated // use TripRouter instead. kai, dec'13 
	public void run(final Plan plan) {
		planRouter.run( plan );
	}

	/**
	 * @param person
	 * @param leg the leg to calculate the route for.
	 * @param fromAct the Act the leg starts
	 * @param toAct the Act the leg ends
	 * @param depTime the time (seconds from midnight) the leg starts
	 * @return the estimated travel time for this leg
	 */
	@Deprecated // use TripRouter instead. kai, dec'13 
	public double handleLeg(
			final Person person,
			final Leg leg,
			final Activity fromAct,
			final Activity toAct,
			final double depTime) {
		TripRouter tripRouter = this.planRouter.getTripRouter();
		return handleLeg(tripRouter, person, leg, fromAct, toAct, depTime);
	}

	@Deprecated // use TripRouter instead. kai, dec'13 
	public RouteFactories getRouteFactory() {
		return routeFactory;
	}

}

