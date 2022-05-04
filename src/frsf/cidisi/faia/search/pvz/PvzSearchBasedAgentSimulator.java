package frsf.cidisi.faia.search.pvz;

import java.util.Arrays;
import java.util.Vector;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.GoalBasedAgent;
import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.state.AgentState;

public class PvzSearchBasedAgentSimulator extends PvzGoalBasedAgentSimulator {
	
	public PvzSearchBasedAgentSimulator(Environment environment, Vector<Agent> agents) {
        super((PvzEnvironment) environment, agents);
    }

    public PvzSearchBasedAgentSimulator(Environment environment, Agent agent) {
        this(environment, new Vector<Agent>(Arrays.asList(agent)));
    }

    @Override
    public boolean agentSucceeded(Action actionReturned) {
        //TODO: 
        // ACA HAY QUE HACER UN BUCLE PARA CUANDO HAY MAS DE UN AGENTE DEFINIDO
        // POR AHORA EL FRAMEWORK ES MONOAGENTE :)
        SearchBasedAgent sa = (SearchBasedAgent) getAgents().firstElement();
        Problem p = sa.getProblem();
        GoalTest gt = p.getGoalState();
        PvzAgentState aSt = (PvzAgentState) p.getAgentState();

        return (aSt.getSuns()>0);
    }

    @Override
    public boolean agentFailed(Action actionReturned) {
        return this.environment.agentFailed(actionReturned);
    }

    @Override
    public String getSimulatorName() {
        return "Search Based Simulator";
    }

    void showSolution() {
        GoalBasedAgent agent = (GoalBasedAgent) this.getAgents().firstElement();

        agent.getSolver().showSolution();
    }

    @Override
    public void actionReturned(Agent agent, Action action) {
        this.updateState(action);
        this.showSolution();
    }

}
