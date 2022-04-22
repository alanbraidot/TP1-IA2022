package frsf.cidisi.faia.search.pvz.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.search.pvz.PvzAgentState;
import frsf.cidisi.faia.search.pvz.PvzEnvironmentState;
import frsf.cidisi.faia.search.pvz.PvzPerception;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class Plant extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		PvzAgentState pvzState = (PvzAgentState) s;
		
		int col = pvzState.getColumnPosition();
		int row = pvzState.getRowPosition();
		
		if(pvzState.getGardenPosition(row, col) == PvzPerception.EMPTY_PERCEPTION && pvzState.getSuns() > 1) {
			pvzState.setSuns(pvzState.getSuns() - 1);
			pvzState.setGardenPosition(row, col, PvzPerception.SUNFLOWER_PERCEPTION);
			return pvzState;
		}
		else {
			//TODO Throw exception
		}
		
		return null;
	}

	@Override
	public Double getCost() {
		return 1.0;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		PvzEnvironmentState environmentState = (PvzEnvironmentState) est;
		PvzAgentState pvzState = (PvzAgentState) ast;
		
		int row = environmentState.getAgentPosition()[0];
		int col = environmentState.getAgentPosition()[1];
		
		if(environmentState.getGardenPosition(row, col) == PvzPerception.EMPTY_PERCEPTION && environmentState.getAgentSuns() > 1) {
			environmentState.setGardenPosition(row, col, PvzPerception.SUNFLOWER_PERCEPTION);
			pvzState.setGardenPosition(row, col, PvzPerception.SUNFLOWER_PERCEPTION);
			
			environmentState.setAgentSuns(environmentState.getAgentSuns() - 1);
			pvzState.setSuns(environmentState.getAgentSuns());
			return environmentState;
		}
		else {
			//TODO Throw exception
		}
		
		return null;
	}

	@Override
	public String toString() {
		return "Plant";
	}
    
}
