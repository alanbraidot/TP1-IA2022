package frsf.cidisi.faia.search.pvz;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.search.pvz.actions.FightDown;
import frsf.cidisi.faia.search.pvz.actions.FightLeft;
import frsf.cidisi.faia.search.pvz.actions.FightRight;
import frsf.cidisi.faia.search.pvz.actions.FightUp;
import frsf.cidisi.faia.search.pvz.actions.GoDown;
import frsf.cidisi.faia.search.pvz.actions.GoLeft;
import frsf.cidisi.faia.search.pvz.actions.GoRight;
import frsf.cidisi.faia.search.pvz.actions.GoUp;
import frsf.cidisi.faia.search.pvz.actions.Harvest;
import frsf.cidisi.faia.search.pvz.actions.Plant;
import frsf.cidisi.faia.solver.search.BreathFirstSearch;
import frsf.cidisi.faia.solver.search.DepthFirstSearch;
import frsf.cidisi.faia.solver.search.Search;

public class PvzAgent extends SearchBasedAgent{
    
    public PvzAgent(){
    }

	public PvzAgent(int[] agentPosition, int agentSuns, int remainingZombies, int zombiesAlive) {
		
        //The Pvz Goal
        PvzAgentGoal goal = new PvzAgentGoal();

        //The Pvz Agent State
        PvzAgentState pvzState = new PvzAgentState();
        
        //The starting position, suns and zombies are assigned.
        pvzState.setPosition(agentPosition);
        pvzState.setSuns(agentSuns);
        pvzState.setZombies(remainingZombies);
        pvzState.setZombiesAlive(zombiesAlive);
        
        this.setAgentState(pvzState);

        //Create the operators
        Vector<SearchAction> operators = new Vector<SearchAction>();
        operators.addElement(new Plant());
        operators.addElement(new FightUp());
        operators.addElement(new FightDown());
        operators.addElement(new FightLeft());
        operators.addElement(new FightRight());
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
		this.getAgentState().updateState(p);
	}

	@Override
	public Action selectAction() {
		// Create the search strategy
        //DepthFirstSearch strategy = new DepthFirstSearch();
        
        BreathFirstSearch strategy = new BreathFirstSearch();

        /**
         * Another search strategy examples:
         * 
         * Depth First Search:
         * DepthFirstSearch strategy = new DepthFirstSearch();
         * 
         * Breath First Search:
         * BreathFirstSearch strategy = new BreathFirstSearch();
         * 
         * Uniform Cost:
         * IStepCostFunction costFunction = new CostFunction();
         * UniformCostSearch strategy = new UniformCostSearch(costFunction);
         * 
         * A Star Search:
         * IStepCostFunction cost = new CostFunction();
         * IEstimatedCostFunction heuristic = new Heuristic();
         * AStarSearch strategy = new AStarSearch(cost, heuristic);
         * 
         * Greedy Search:
         * IEstimatedCostFunction heuristic = new Heuristic();
         * GreedySearch strategy = new GreedySearch(heuristic);
         */

        // Create a Search object with the strategy
        Search searchSolver = new Search(strategy);

        /* Generate an XML file with the search tree. It can also be generated
         * in other formats like PDF with PDF_TREE */
        searchSolver.setVisibleTree(Search.EFAIA_TREE);

        // Set the Search searchSolver.
        this.setSolver(searchSolver);

        // Ask the solver for the best action
        Action selectedAction = null;
        try {
            selectedAction =
                    this.getSolver().solve(new Object[]{this.getProblem()});
        } catch (Exception ex) {
            Logger.getLogger(PvzAgent.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the selected action
        return selectedAction;
	}
	
}
