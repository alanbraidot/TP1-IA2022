package frsf.cidisi.faia.search.pvz;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class PvzSearchMain {
    
	public static void main(String[] args) throws PrologConnectorException {
		
		PvzEnvironment pvzEnvironment = new PvzEnvironment();
		
		PvzAgent pvzAgent = new PvzAgent(pvzEnvironment.getEnvironmentState().getAgentPosition(), pvzEnvironment.getEnvironmentState().getAgentSuns(), pvzEnvironment.getEnvironmentState().getRemainingZombies());
		
		SearchBasedAgentSimulator simulator = new SearchBasedAgentSimulator(pvzEnvironment, pvzAgent);
		simulator.start();
	}
}
