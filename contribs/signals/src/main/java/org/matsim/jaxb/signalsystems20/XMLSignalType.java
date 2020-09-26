//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-558 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.20 at 07:21:49 PM MESZ 
//


package org.matsim.jaxb.signalsystems20;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for signalType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="signalType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.matsim.org/files/dtd}matsimObjectType">
 *       &lt;sequence>
 *         &lt;element name="lane" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.matsim.org/files/dtd}idRefType">
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="turningMoveRestrictions" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="toLink" type="{http://www.matsim.org/files/dtd}idRefType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="linkIdRef" use="required" type="{http://www.matsim.org/files/dtd}matsimIdType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "signalType", propOrder = {
    "lane",
    "turningMoveRestrictions"
})
public final class XMLSignalType extends XMLMatsimObjectType {

    private List<XMLSignalType.XMLLane> lane;
    private XMLSignalType.XMLTurningMoveRestrictions turningMoveRestrictions;
    @XmlAttribute(required = true)
    private String linkIdRef;

    /**
     * Gets the value of the lane property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lane property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLane().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLSignalType.XMLLane }
     * 
     * 
     */
    public List<XMLSignalType.XMLLane> getLane() {
        if (lane == null) {
            lane = new ArrayList<XMLSignalType.XMLLane>();
        }
        return this.lane;
    }

    /**
     * Gets the value of the turningMoveRestrictions property.
     * 
     * @return
     *     possible object is
     *     {@link XMLSignalType.XMLTurningMoveRestrictions }
     *     
     */
    public XMLSignalType.XMLTurningMoveRestrictions getTurningMoveRestrictions() {
        return turningMoveRestrictions;
    }

    /**
     * Sets the value of the turningMoveRestrictions property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLSignalType.XMLTurningMoveRestrictions }
     *     
     */
    public void setTurningMoveRestrictions(XMLSignalType.XMLTurningMoveRestrictions value) {
        this.turningMoveRestrictions = value;
    }

    /**
     * Gets the value of the linkIdRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkIdRef() {
        return linkIdRef;
    }

    /**
     * Sets the value of the linkIdRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkIdRef(String value) {
        this.linkIdRef = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://www.matsim.org/files/dtd}idRefType">
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class XMLLane
        extends XMLIdRefType
    {


    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="toLink" type="{http://www.matsim.org/files/dtd}idRefType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "toLink"
    })
    public static class XMLTurningMoveRestrictions {

        protected List<XMLIdRefType> toLink;

        /**
         * Gets the value of the toLink property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the toLink property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getToLink().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link XMLIdRefType }
         * 
         * 
         */
        public List<XMLIdRefType> getToLink() {
            if (toLink == null) {
                toLink = new ArrayList<XMLIdRefType>();
            }
            return this.toLink;
        }

    }

}