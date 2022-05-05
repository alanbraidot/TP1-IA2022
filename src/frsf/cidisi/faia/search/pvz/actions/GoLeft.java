package frsf.cidisi.faia.search.pvz.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.search.pvz.PvzAgentState;
import frsf.cidisi.faia.search.pvz.PvzEnvironmentState;
import frsf.cidisi.faia.search.pvz.PvzPerception;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class GoLeft extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		PvzAgentState pvzState = (PvzAgentState) s;
		
		int row = pvzState.getRowPosition();
		int col = pvzState.getColumnPosition();
		
		if(col > 1){ //It is queried that it is greater than 1 since column 0 is the house
			col = col - 1;
			pvzState.setColumnPosition(col);
			
			int newPerception = pvzState.getGardenPosition(row, col); //The state of the cell to which the agent was moved is stored.
			
			//If there is a sunflower in the new cell, the suns generated are added to the agent and the sunflower is restarted
			if(newPerception > PvzPerception.SUNFLOWER_PERCEPTION) {
				pvzState.setSuns(pvzState.getSuns() + newPerception);
				pvzState.setGardenPosition(row, col, PvzPerception.SUNFLOWER_PERCEPTION);
			}
			
			//If a zombie exists in the new cell, the number of suns on the plant is decreased by twice the zombie's life and the zombie dies.
			if(pvzState.isZombie(newPerception)) {
				pvzState.setSuns(pvzState.getSuns() + (newPerception*2)); //The operator + is used since the life of the zombie is represented with negative numbers
				pvzState.setGardenPosition(row, col, PvzPerception.EMPTY_PERCEPTION);
				pvzState.decreaseZombies();
			}
			
			return pvzState;
		}
		else {
			//TODO Throw exception
		}
		
		return null;
	}

	@Override
	public Double getCost() {
		return 0.0;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		PvzEnvironmentState environmentState = (PvzEnvironmentState) est;
		PvzAgentState pvzState = (PvzAgentState) ast;
		
		int row = environmentState.getAgentPosition()[0];
		int col = environmentState.getAgentPosition()[1];
		
		if(col > 1) { //It is queried that it is greater than 1 since column 0 is the house
			col = col - 1;
			pvzState.setColumnPosition(col);
			environmentState.setAgentPosition(new int[] {row, col});
			
			int newPerception = environmentState.getGardenPosition(row, col); //The state of the cell to which the agent was moved is stored.
			
			//If there is a sunflower in the new cell, the suns generated are added to the agent and the sunflower is restarted
			if(newPerception > PvzPerception.SUNFLOWER_PERCEPTION) {
				environmentState.setAgentSuns(environmentState.getAgentSuns() + newPerception);
				pvzState.setSuns(environmentState.getAgentSuns());
				environmentState.harvestSunflower(row, col);
			}
			
			//If a zombie exists in the new cell, the number of suns on the plant is decreased by twice the zombie's life and the zombie dies.
			if(environmentState.isZombie(newPerception)) {
				environmentState.setAgentSuns(environmentState.getAgentSuns() + (newPerception*2)); //The operator + is used since the life of the zombie is represented with negative numbers
				pvzState.setSuns(environmentState.getAgentSuns());
				environmentState.setGardenPosition(row, col, PvzPerception.EMPTY_PERCEPTION);
				environmentState.decreaseRemainingZombies();
				environmentState.killZombie(row, col);
			}
			
			return environmentState;
		}
		else {
			//TODO Throw exception
		}	

		return null;
	}

	@Override
	public String toString() {
		return "GoLeft";
	}
    
}
