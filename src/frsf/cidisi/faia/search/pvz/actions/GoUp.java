package frsf.cidisi.faia.search.pvz.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.search.pvz.PvzAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class GoUp extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		// TODO Auto-generated method stub
		PvzAgentState pvzState = (PvzAgentState) s;
		
		int row = pvzState.getRowPosition();
		int col = pvzState.getColumnPosition();
		
		if(row < 4)
				row = row + 1;
		else
			//Throw exception
			
		pvzState.setRowPosition(row);
		
		return pvzState;
	}

	@Override
	public Double getCost() {
		return 0.0;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "GoUp";
	}
    
}
