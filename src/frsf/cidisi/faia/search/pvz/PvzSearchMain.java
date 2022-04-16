package frsf.cidisi.faia.search.pvz;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class PvzSearchMain {
    
	public static void main(String[] args) throws PrologConnectorException {
		PvzAgent pvzAgent = new PvzAgent();
		
		PvzEnvironment pvzEnvironment = new PvzEnvironment();
		
		SearchBasedAgentSimulator simulator = new SearchBasedAgentSimulator(pvzEnvironment, pvzAgent);
		simulator.start();
	}
}
