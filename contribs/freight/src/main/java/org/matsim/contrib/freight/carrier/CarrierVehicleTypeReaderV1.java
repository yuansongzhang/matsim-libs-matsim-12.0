package org.matsim.contrib.freight.carrier;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Id;
import org.matsim.core.gbl.Gbl;
import org.matsim.core.utils.io.MatsimXmlParser;
import org.matsim.vehicles.CostInformation;
import org.matsim.vehicles.EngineInformation;
import org.matsim.vehicles.VehicleType;
import org.matsim.vehicles.VehicleUtils;
import org.xml.sax.Attributes;

import java.util.Stack;

class CarrierVehicleTypeReaderV1 extends MatsimXmlParser {
	private static final Logger log = Logger.getLogger(CarrierVehicleTypeReaderV1.class) ;

	private static int wrnCnt=10 ;

	private final CarrierVehicleTypes carrierVehicleTypes;
	private VehicleType currentType;

	CarrierVehicleTypeReaderV1( CarrierVehicleTypes carrierVehicleTypes ) {
		super();
		this.carrierVehicleTypes = carrierVehicleTypes;
		this.setValidating(false);
	}

//	/**
//	 * Reads types from xml-file.
//	 *
//	 * @param xml-filename containing vehicleTypes
//	 */
//	/* This is somewhat problematic for me (JWJoubert, Nov '13). The MatsimXmlParser
//	 * has a parse method, yet when calling it, it results in an XML error. Maybe
//	 * it would be better to
//	 * a) use a dtd file, and
//	 * b) rather use the infrastructure provided by the MatsimXmlParser, and
//	 *    override it if required.
//	 */
//	public void read(String filename){
//		logger.info("read vehicle types");
//		this.setValidating(false);
//		read(filename);
//		logger.info("done");
//	}

	@Override
	public void startTag( String name, Attributes atts, Stack<String> context ) {
		if(name.equals("vehicleType")){
			Id<VehicleType> currentTypeId = Id.create( atts.getValue( "id" ), VehicleType.class );
			this.currentType = VehicleUtils.getFactory().createVehicleType( currentTypeId ) ;
		}
		if(name.equals("allowableWeight")){
//			String weight = atts.getValue("weight");
//			Double.parseDouble( weight );
			// yyyyyy what is this?  kai, sep'19
			if ( wrnCnt>0 ){
				log.warn( "allowableWeight is ignored (and has always been)." );
				wrnCnt--;
				if( wrnCnt == 0 ){
					log.warn( Gbl.FUTURE_SUPPRESSED );
				}
			}
		}
		if(name.equals("engineInformation")){
//			EngineInformation engineInfo = new EngineInformation();
			EngineInformation engineInfo = this.currentType.getEngineInformation() ;
			engineInfo.setFuelConsumption( Double.parseDouble( atts.getValue( "gasConsumption" ) ) );
			engineInfo.setFuelType( EngineInformation.FuelType.valueOf( atts.getValue( "fuelType" ) ) );
//			this.currentEngineInfo = engineInfo;
		}

		if(name.equals("costInformation")){
			String fix = atts.getValue("fix");
			// yyyyyy shouldn't this be "perDay"??? kai, aug'19
			String perMeter = atts.getValue("perMeter");
			String perSecond = atts.getValue("perSecond");
			if(fix == null || perMeter == null || perSecond == null) throw new IllegalStateException("cannot read costInformation correctly. probably the paramName was written wrongly");
//			CostInformation vehicleCosts = new CostInformation();
			CostInformation vehicleCosts = this.currentType.getCostInformation();;
			vehicleCosts.setFixedCost( Double.valueOf( fix ) );
			vehicleCosts.setCostsPerMeter( Double.valueOf( perMeter ) );
			vehicleCosts.setCostsPerSecond( Double.valueOf( perSecond ) );
//			this.currentVehicleCosts = vehicleCosts;
		}
	}

	@Override
	public void endTag(String name, String content, Stack<String> context) {
		if(name.equals("description")){//Ca
//			this.currentDescription = content;
			this.currentType.setDescription( content );
		}
		if(name.equals("capacity")){
//			this.currentCapacity = content;
//			this.currentType.getCapacity().setWeightInTons( Double.parseDouble( content ) ) ;
			// yyyyyy note that this is interpretation!!
//			VehicleUtils.setFreightCapacityUnits(  );
			this.currentType.getCapacity().setOther( Double.parseDouble( content ) ) ;
		}
		if(name.equals("maxVelocity")){
//			this.maxVelo = content;
			this.currentType.setMaximumVelocity( Double.parseDouble( content ) );
		}
		if(name.equals("vehicleType")){
//			CarrierUtils.CarrierVehicleTypeBuilder typeBuilder = CarrierUtils.CarrierVehicleTypeBuilder.newInstance(currentTypeId );
//			if(currentDescription != null) typeBuilder.setDescription(currentDescription);
//			if(currentWeight != null) vehType.setAllowableTotalWeight(currentWeight);
//			if(currentCap != null) vehType.setFreightCapacity(currentCap);
//			if(currentVehicleCosts != null) typeBuilder.setVehicleCostInformation(currentVehicleCosts);
//			if(currentEngineInfo != null) typeBuilder.setEngineInformation(currentEngineInfo);
//			if(currentCapacity != null) typeBuilder.setCapacityWeightInTons(Double.parseDouble(currentCapacity ) );
//			if(maxVelo != null) typeBuilder.setMaxVelocity(Double.parseDouble(maxVelo));
//			VehicleType vehType = typeBuilder.build();
			carrierVehicleTypes.getVehicleTypes().put(this.currentType.getId(), currentType );
			reset();
		}

	}

	private void reset() {
//		currentTypeId = null;
//		currentDescription = null;
//		currentVehicleCosts = null;
//		currentEngineInfo = null;
		currentType = null ;
	}

}
