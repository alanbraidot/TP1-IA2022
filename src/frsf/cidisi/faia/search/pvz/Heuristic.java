package frsf.cidisi.faia.search.pvz;


import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class Heuristic implements IEstimatedCostFunction{

	@Override
	public double getEstimatedCost(NTree node) {
		PvzAgentState pvzState = (PvzAgentState) node.getAgentState();
		
		if(pvzState.getZombiesAlive()>0) {			
			
			double costValue = 0.0;
			for(int row = 0; row < PvzEnvironmentState.MATRIX_ROW_LENGTH; row++) {
	            for(int col = 1; col < PvzEnvironmentState.MATRIX_COLUMN_LENGTH; col++) {
	            	
	                if(pvzState.isZombie(pvzState.getGardenPosition(row, col))) { //if its a zombie
	                	
	                	int dangerWeight = PvzEnvironmentState.MATRIX_COLUMN_LENGTH - 1 - col;	//distance from the house equals the value of the column on which the zombie is at
	                	int distanceToPlant = pvzState.getDistance(pvzState.getRowPosition(), pvzState.getColumnPosition(), row, col);
	                	//we add up all the distances from all the currently alive zombies the plant perceives, the node with the smallest one
	                	//will be one with one less zombie
	                	costValue = costValue + distanceToPlant + 100*dangerWeight;
	                	//which is closer to the house than some other zombie which the plant could possibly kill
	                }
	            }
	        }
			/*System.out.println(" - Action: " + node.getAction()
								+ " | Pos: (" + pvzState.getRowPosition() + ", " + pvzState.getColumnPosition() + ")"
								+ " | - ZombiesAlive: " + pvzState.getZombiesAlive()
								+ " | - CostValue: "+costValue+";");*/
			return costValue;
			
		}
		/*System.out.println(" - Action: " + node.getAction()
		+ " | Pos: (" + pvzState.getRowPosition() + ", " + pvzState.getColumnPosition() + ")"
		+ " | - ZombiesAlive: " + pvzState.getZombiesAlive()
		+ " | - CostValue: 0.0;");*/
		return 0.0;
	}

}
