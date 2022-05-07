package frsf.cidisi.faia.search.pvz;


import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class Heuristic implements IEstimatedCostFunction{

	@Override
	public double getEstimatedCost(NTree node) {
		PvzAgentState pvZState = (PvzAgentState) node.getAgentState();
		
		if (pvZState.getZombiesAlive()>0) {
			int[][] garden = pvZState.getGarden();
			
			
			int CostValue = 0;
			for (int row = 0; row < PvzEnvironmentState.MATRIX_ROW_LENGTH; row++) {
	            for (int col = 1; col < PvzEnvironmentState.MATRIX_COLUMN_LENGTH; col++) {
	                if (pvZState.isZombie(garden[row][col])) { //if its a zombie
	                	int distance = col;						//distance from the house equals the value of the column on which the zombie is at
	                	CostValue = CostValue + 10*distance;		//we add up all the distances from all the currently alive zombies the plant perceives, the node with the smallest one will be one with one less zombie
	                											//which is closer to the house than some other zombie which the plant could possibly kill
	                }
	            }
	        }
			return CostValue;
			
		}
		
		return 0;
	}

}
