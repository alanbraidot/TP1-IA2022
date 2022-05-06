package frsf.cidisi.faia.search.pvz;

import java.util.Vector;

import frsf.cidisi.faia.agent.GoalBasedAgent;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.search.pvz.actions.GoDown;
import frsf.cidisi.faia.search.pvz.actions.GoLeft;
import frsf.cidisi.faia.search.pvz.actions.GoRight;
import frsf.cidisi.faia.search.pvz.actions.GoUp;
import frsf.cidisi.faia.search.pvz.actions.Plant;
import frsf.cidisi.faia.simulator.Simulator;
import frsf.cidisi.faia.simulator.events.EventType;
import frsf.cidisi.faia.simulator.events.SimulatorEventNotifier;

public abstract class PvzGoalBasedAgentSimulator extends Simulator {
	
	
	

    /**
     * 
     * @param environment
     */
    public PvzGoalBasedAgentSimulator(PvzEnvironment environment, Vector<Agent> agents) {
        super(environment, agents);
    }

    public PvzGoalBasedAgentSimulator(PvzEnvironment environment, Agent agent) {
        Vector<Agent> ags = new Vector<Agent>();
        ags.add(agent);

        this.environment = environment;
        this.agents = ags;
    }

    @Override
    public void start() {

        System.out.println("----------------------------------------------------");
        System.out.println("--- " + this.getSimulatorName() + " ---");
        System.out.println("----------------------------------------------------");
        System.out.println();

        Perception perception;
        Action action;
        PvzAgent agent;
        PvzEnvironmentState state = (PvzEnvironmentState) environment.getEnvironmentState();

        agent = (PvzAgent) this.getAgents().firstElement();
        
        PvzAgentState pvzAgentState = (PvzAgentState) agent.getAgentState();
       
        /*
         * Simulation starts. The environment sends perceptions to the agent, and
         * it returns actions. The loop condition evaluation is placed at the end.
         * This works even when the agent starts with a goal state (see agentSucceeded
         * method in the SearchBasedAgentSimulator).
         */
        do {

            System.out.println("------------------------------------");

            System.out.println("Sending perception to agent...");
            perception = this.getPercept();
            agent.see(perception);
            System.out.println("Perception: " + perception);

            System.out.println("Agent State: " + agent.getAgentState());
            System.out.println("Environment: " + environment);

            System.out.println("Asking the agent for an action...");
            action = agent.selectAction();

            
            //TODO Corregir
            if (action == null) {
            	if(pvzAgentState.getPosition()[0]==3
            			&& (pvzAgentState.getGardenPosition(pvzAgentState.getRowPosition(),
            					pvzAgentState.getColumnPosition())
            						== PvzPerception.EMPTY_PERCEPTION)) {
            		
            			action = new Plant();
            	}
            	else {
            		if (pvzAgentState.isGoingUp()==true && pvzAgentState.getPosition()[0]!=0)
                        action = new GoUp();
                    else
                    	if (pvzAgentState.getPosition()[0]!=4) {
                    		action = new GoDown();
                    	} else {
                    		action = new GoUp();
                    	}
                    	
            	}
            }
            
            
            System.out.println("Action returned: " + action);
            System.out.println();

            this.actionReturned(agent, action);

            //TODO Acomodar
        } while (agentSucceeded(action) && !agentFailed(action) && (state.isRemainingZombies()));

        // Check what happened, if agent has reached the goal or not.
        if (pvzAgentState.getSuns()>0 && !(state.isRemainingZombies())) {
            System.out.println("Agent has reached the goal!");
        } else {
            System.out.println("ERROR: The simulation has finished, but the agent has not reached his goal.");
        }

        // Leave a blank line
        System.out.println();

        // FIXME: This call can be moved to the Simulator class
        this.environment.close();

        // Launch simulationFinished event
        SimulatorEventNotifier.runEventHandlers(EventType.SimulationFinished, null);
    }

    /**
     * Here we update the state of the agent and the real state of the
     * simulator.
     * @param action
     */
    protected void updateState(Action action) {
        this.getEnvironment().updateState(((GoalBasedAgent) agents.elementAt(0)).getAgentState(), action);
    }

    public abstract boolean agentSucceeded(Action action);

    public abstract boolean agentFailed(Action action);

    /**
     * This method is executed in the mail loop of the simulation when the
     * agent returns an action.
     * @param agent
     * @param action
     */
    public abstract void actionReturned(Agent agent, Action action);

    /**
     * @return The name of the simulator, e.g. 'SearchBasedAgentSimulator'
     */
    public abstract String getSimulatorName();
}