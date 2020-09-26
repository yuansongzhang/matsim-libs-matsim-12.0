
/* *********************************************************************** *
 * project: org.matsim.*
 * ObjectFactory.java
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

 //
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-558 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.02.13 at 12:10:00 AM MEZ 
//


package org.matsim.jaxb.lanedefinitions20;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.matsim.jaxb.lanedefinitions20 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Coordinate_QNAME = new QName("http://www.matsim.org/files/dtd", "coordinate");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.matsim.jaxb.lanedefinitions20
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link XMLLocation }
     * 
     */
    public XMLLocation createXMLLocation() {
        return new XMLLocation();
    }

    /**
     * Create an instance of {@link XMLLaneType }
     * 
     */
    public XMLLaneType createXMLLaneType() {
        return new XMLLaneType();
    }

    /**
     * Create an instance of {@link XMLCoordinateType }
     * 
     */
    public XMLCoordinateType createXMLCoordinateType() {
        return new XMLCoordinateType();
    }

    /**
     * Create an instance of {@link XMLLanesToLinkAssignmentType }
     * 
     */
    public XMLLanesToLinkAssignmentType createXMLLanesToLinkAssignmentType() {
        return new XMLLanesToLinkAssignmentType();
    }

    /**
     * Create an instance of {@link XMLLaneType.XMLLeadsTo }
     * 
     */
    public XMLLaneType.XMLLeadsTo createXMLLaneTypeXMLLeadsTo() {
        return new XMLLaneType.XMLLeadsTo();
    }
    
    /**
     * Create an instance of {@link XMLLaneType.XMLAttributes }
     * 
     */
    public XMLLaneType.XMLAttributes createXMLLaneTypeXMLAttributes() {
    	return new XMLLaneType.XMLAttributes();
    }

    /**
     * Create an instance of {@link XMLMatsimObjectType }
     * 
     */
    public XMLMatsimObjectType createXMLMatsimObjectType() {
        return new XMLMatsimObjectType();
    }

    /**
     * Create an instance of {@link XMLMatsimTimeAttributeType }
     * 
     */
    public XMLMatsimTimeAttributeType createXMLMatsimTimeAttributeType() {
        return new XMLMatsimTimeAttributeType();
    }

    /**
     * Create an instance of {@link XMLMatsimParameterType }
     * 
     */
    public XMLMatsimParameterType createXMLMatsimParameterType() {
        return new XMLMatsimParameterType();
    }

    /**
     * Create an instance of {@link XMLLaneType.XMLCapacity }
     * 
     */
    public XMLLaneType.XMLCapacity createXMLLaneTypeXMLCapacity() {
        return new XMLLaneType.XMLCapacity();
    }

    /**
     * Create an instance of {@link XMLIdRefType }
     * 
     */
    public XMLIdRefType createXMLIdRefType() {
        return new XMLIdRefType();
    }
    
    /**
     * Create an instance of {@link XMLAttributeType }
     * 
     */
    public XMLAttributeType createXMLAttributeType() {
    	return new XMLAttributeType();
    }

    /**
     * Create an instance of {@link XMLLaneType.XMLRepresentedLanes }
     * 
     */
    public XMLLaneType.XMLRepresentedLanes createXMLLaneTypeXMLRepresentedLanes() {
        return new XMLLaneType.XMLRepresentedLanes();
    }

    /**
     * Create an instance of {@link XMLFacilityId }
     * 
     */
    public XMLFacilityId createXMLFacilityId() {
        return new XMLFacilityId();
    }

    /**
     * Create an instance of {@link XMLLaneType.XMLStartsAt }
     * 
     */
    public XMLLaneType.XMLStartsAt createXMLLaneTypeXMLStartsAt() {
        return new XMLLaneType.XMLStartsAt();
    }

    /**
     * Create an instance of {@link XMLLaneDefinitions }
     * 
     */
    public XMLLaneDefinitions createXMLLaneDefinitions() {
        return new XMLLaneDefinitions();
    }

    /**
     * Create an instance of {@link XMLActLocationType }
     * 
     */
    public XMLActLocationType createXMLActLocationType() {
        return new XMLActLocationType();
    }

    /**
     * Create an instance of {@link XMLLinkId }
     * 
     */
    public XMLLinkId createXMLLinkId() {
        return new XMLLinkId();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLCoordinateType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.matsim.org/files/dtd", name = "coordinate")
    public JAXBElement<XMLCoordinateType> createCoordinate(XMLCoordinateType value) {
        return new JAXBElement<XMLCoordinateType>(_Coordinate_QNAME, XMLCoordinateType.class, null, value);
    }

}