package org.matsim.contrib.signals.builder;

import org.matsim.contrib.signals.controller.SignalControllerFactory;
import org.matsim.core.controler.AllowsConfiguration;

public final class Signals{

    public static void configure( AllowsConfiguration ao ){

        ao.addOverridingModule( new SignalsModule() );

        ao.addOverridingQSimModule( new SignalsQSimModule() );
    }

    public static class Configurator{
        private final SignalsModule signalsModule;
        public Configurator( AllowsConfiguration ao ){
            this.signalsModule = new SignalsModule() ;
            ao.addOverridingModule( this.signalsModule ) ;
            ao.addOverridingQSimModule( new SignalsQSimModule() ) ;
        }
        
        /**
         * Call this method when you want to add your own SignalController.
         * E.g. via 
         *  new Configurator(controler).addSignalControllerFactory(LaemmerSignalController.IDENTIFIER, LaemmerSignalController.LaemmerFactory.class);
         */
        public final void addSignalControllerFactory( String key, Class<? extends SignalControllerFactory> signalControllerFactoryClassName ) {
            signalsModule.addSignalControllerFactory( key, signalControllerFactoryClassName );
        }
    }

}
