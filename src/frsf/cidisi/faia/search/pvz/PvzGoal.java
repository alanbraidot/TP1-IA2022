package frsf.cidisi.faia.search.pvz;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class PvzGoal extends GoalTest{

	@Override
	public boolean isGoalState(AgentState agentState) {
		if(((PvzAgentState) agentState).isNoMoreZombies() &&
			((PvzAgentState) agentState).isAgentAlive()){
			return true;
		}
		return false;
	}
    
}
