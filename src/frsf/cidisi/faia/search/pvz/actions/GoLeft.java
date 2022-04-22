package frsf.cidisi.faia.search.pvz.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.search.pvz.PvzAgentState;
import frsf.cidisi.faia.search.pvz.PvzEnvironmentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class GoLeft extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		PvzAgentState pvzState = (PvzAgentState) s;
		
		int col = pvzState.getColumnPosition();
		
		if(col > 1){ //It is queried that it is greater than 1 since column 0 is the house
				col = col - 1;
				pvzState.setColumnPosition(col);
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
