package frsf.cidisi.faia.search.pvz;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class PvzAgentGoal extends GoalTest{

	@Override
	public boolean isGoalState(AgentState agentState) {
		if((((PvzAgentState) agentState).isNoMoreAliveZombies()
			&& ((PvzAgentState) agentState).isAgentAlive())
			
			|| ((PvzAgentState) agentState).hasNotEnoughSuns()){
			return true;
		}
		return false;
	}
    
}
