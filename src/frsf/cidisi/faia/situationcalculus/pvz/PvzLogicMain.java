package frsf.cidisi.faia.situationcalculus.pvz;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SituationCalculusBasedAgentSimulator;

public class PvzLogicMain {
	
	public static void main(String[] args) throws PrologConnectorException {
		PvzLogicAgent agent = new PvzLogicAgent();
		
		PvzLogicEnvironment environment = new PvzLogicEnvironment();
		
		SituationCalculusBasedAgentSimulator simulator =
                new SituationCalculusBasedAgentSimulator(environment, agent);

        simulator.start();
		
	}

}
