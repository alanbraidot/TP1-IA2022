package frsf.cidisi.faia.search.pvz.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.search.pvz.PvzAgentState;
import frsf.cidisi.faia.search.pvz.PvzEnvironmentState;
import frsf.cidisi.faia.search.pvz.PvzPerception;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class FightLeft extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		PvzAgentState pvzState = (PvzAgentState) s;
		
		int row = pvzState.getRowPosition();
		int col = pvzState.getColumnPosition();
		
		/* The 'FightUp' action can be selected only if there is an zombie
		 * in the left position. Otherwise return 'null'. */
		if(col > 0) {
			int perception = pvzState.getGardenPosition(row, col-1);
			
			if(pvzState.isZombie(perception) && pvzState.getSuns() > perception){
				
				pvzState.setSuns(pvzState.getSuns() - perception);
				pvzState.setGardenPosition(row, col-1, PvzPerception.EMPTY_PERCEPTION);
				
				return pvzState;
			}
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
		
		if(col > 0) {
			int perception = environmentState.getGardenPosition(row, col-1);
			
			if(environmentState.isZombie(perception) && environmentState.getAgentSuns() > perception){
				
				environmentState.setAgentSuns(pvzState.getSuns() - perception);
				environmentState.setGardenPosition(row, col-1, PvzPerception.EMPTY_PERCEPTION);
				
				pvzState.setGardenPosition(row, col-1, PvzPerception.EMPTY_PERCEPTION);
				
				return environmentState;
			}
		}
		
		return null;
	}

	@Override
	public String toString() {
		return "FightLeft";
	}

}
