package frsf.cidisi.faia.search.pvz;

import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class CostFunction implements IStepCostFunction{
	
	
	//Calculates the cost of ONE single node of the tree, i.e moving up. Not to be confused with a heuristic function which computes the cost from a given node to the goal

	@Override
	public double calculateCost(NTree node) {
		// TODO Auto-generated method stub
		  return node.getAction().getCost();
	}

}
