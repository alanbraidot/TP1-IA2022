package frsf.cidisi.faia.search.pvz;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class PvzSearchMain {
    
	public static void main(String[] args) throws PrologConnectorException {
		
		PvzEnvironment pvzEnvironment = new PvzEnvironment();
		
		PvzAgent pvzAgent = new PvzAgent(pvzEnvironment.getEnvironmentState().getAgentPosition(),
										pvzEnvironment.getEnvironmentState().getAgentSuns(),
										pvzEnvironment.getEnvironmentState().getRemainingZombies(),
										pvzEnvironment.getEnvironmentState().getZombiesAlive());
		
		PvzSearchBasedAgentSimulator simulator = new PvzSearchBasedAgentSimulator(pvzEnvironment, pvzAgent);
		simulator.start();
	}
}
