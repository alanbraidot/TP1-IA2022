package frsf.cidisi.faia.search.pvz;

import java.util.Vector;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.search.pvz.actions.Fight;
import frsf.cidisi.faia.search.pvz.actions.GoDown;
import frsf.cidisi.faia.search.pvz.actions.GoLeft;
import frsf.cidisi.faia.search.pvz.actions.GoRight;
import frsf.cidisi.faia.search.pvz.actions.GoUp;
import frsf.cidisi.faia.search.pvz.actions.Harvest;
import frsf.cidisi.faia.search.pvz.actions.Plant;

public class PvzAgent extends SearchBasedAgent{
    
    public PvzAgent(){

        //The Pvz Goal
        PvzGoal goal = new PvzGoal();

        //The Pvz Agent State
        PvzAgentState pvzState = new PvzAgentState();
        this.setAgentState(pvzState);

        //Create the operators
        Vector<SearchAction> operators = new Vector<SearchAction>();
        operators.addElement(new Plant());
        operators.addElement(new Harvest());
        operators.addElement(new Fight());
        operators.addElement(new GoLeft());
        operators.addElement(new GoUp());
        operators.addElement(new GoRight());
        operators.addElement(new GoDown());

        // Create the Problem
        Problem problem = new Problem(goal, pvzState, operators);
        this.setProblem(problem);
    }

	@Override
	public void see(Perception p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Action selectAction() {
		// TODO Auto-generated method stub
		return null;
	}


}
