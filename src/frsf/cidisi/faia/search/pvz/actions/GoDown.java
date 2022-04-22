package frsf.cidisi.faia.search.pvz.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.search.pvz.PvzAgentState;
import frsf.cidisi.faia.search.pvz.PvzEnvironmentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class GoDown extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		PvzAgentState pvzState = (PvzAgentState) s;
		
		int row = pvzState.getRowPosition();
		
		if(row < PvzEnvironmentState.MATRIX_ROW_LENGTH-1) {
			row = row + 1;
			pvzState.setRowPosition(row);
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
		
		if(row < PvzEnvironmentState.MATRIX_ROW_LENGTH-1) {
				row = row + 1;
				pvzState.setRowPosition(row);
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
		return "GoDown";
	}
    
}
