package frsf.cidisi.faia.search.pvz;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class PvzEnvironmentGoal extends GoalTest{

	@Override
	public boolean isGoalState(AgentState agentState) {
		if(((PvzAgentState) agentState).isAgentAlive()){
			return true;
		}
		return false;
	}

}
