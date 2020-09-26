/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2014 by the members listed in the COPYING,        *
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

package org.matsim.contrib.zone;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.util.PolygonExtracter;
import org.matsim.api.core.v01.Id;
import org.matsim.contrib.zone.io.ZoneShpReader;
import org.matsim.contrib.zone.io.ZoneShpWriter;
import org.matsim.contrib.zone.io.ZoneXmlReader;
import org.matsim.contrib.zone.io.ZoneXmlWriter;
import org.matsim.core.utils.geometry.geotools.MGC;
import org.matsim.core.utils.io.UncheckedIOException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

public class Zones {
	public static Map<Id<Zone>, Zone> readZones(String zonesXmlFile, String zonesShpFile) {
		try {
			return readZones(new File(zonesXmlFile).toURI().toURL(), new File(zonesShpFile).toURI().toURL());
		} catch (MalformedURLException e) {
			throw new UncheckedIOException(e);
		}
	}

	public static Map<Id<Zone>, Zone> readZones(URL zonesXmlUrl, URL zonesShpUrl) {
		ZoneXmlReader xmlReader = new ZoneXmlReader();
		xmlReader.readURL(zonesXmlUrl);
		Map<Id<Zone>, Zone> zones = xmlReader.getZones();

		ZoneShpReader shpReader = new ZoneShpReader(zones);
		shpReader.readZones(zonesShpUrl);
		return zones;
	}

	public static void writeZones(Map<Id<Zone>, Zone> zones, String coordinateSystem, String zonesXmlFile,
			String zonesShpFile) {
		new ZoneXmlWriter(zones).write(zonesXmlFile);
		new ZoneShpWriter(zones, coordinateSystem).write(zonesShpFile);
	}

	public static void transformZones(Map<Id<Zone>, Zone> zones, String fromCoordSystem, String toCoordSystem) {
		MathTransform transform = getTransform(fromCoordSystem, toCoordSystem);

		for (Zone z : zones.values()) {
			z.setMultiPolygon(transformMultiPolygon(z.getMultiPolygon(), transform));
		}
	}

	public static MathTransform getTransform(String fromCoordSystem, String toCoordSystem) {
		CoordinateReferenceSystem fromCrs = MGC.getCRS(fromCoordSystem);
		CoordinateReferenceSystem toCrs = MGC.getCRS(toCoordSystem);

		try {
			return CRS.findMathTransform(fromCrs, toCrs, true);
		} catch (FactoryException e) {
			throw new RuntimeException(e);
		}
	}

	public static MultiPolygon transformMultiPolygon(MultiPolygon multiPolygon, MathTransform transform) {
		try {
			return (MultiPolygon)JTS.transform(multiPolygon, transform);
		} catch (TransformException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Polygon> getPolygons(Zone zone) {
		return PolygonExtracter.getPolygons(zone.getMultiPolygon());
	}
}
