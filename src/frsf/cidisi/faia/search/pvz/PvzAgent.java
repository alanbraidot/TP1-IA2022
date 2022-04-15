package frsf.cidisi.faia.search.pvz;

import java.util.Vector;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;

public class PvzAgent extends SearchBasedAgent{
    
    public PvzAgent(){

        //The Pvz Goal
        PzvGoal goal = new PvzGoal();

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

        // Create the Problem which the Pacman will resolve
        Problem problem = new Problem(goal, pvzState, operators);
        this.setProblem(problem);
    }


}
