package frsf.cidisi.faia.search.pvz;

import java.util.ArrayList;
import java.util.Random;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class PvzEnvironment extends Environment{

	public PvzEnvironment() {
        // Create the environment state
        this.environmentState = new PvzEnvironmentState();
    }

    @Override
    public PvzEnvironmentState getEnvironmentState() {
        return (PvzEnvironmentState) super.getEnvironmentState();
    }

    /**
     * This method is called by the simulator. Given the Agent, it creates
     * a new perception reading, for example, the agent position.
     * @param agent
     * @return A perception that will be given to the agent by the simulator.
     */
    @Override
    public Perception getPercept() {
    	
    	//Before perceiving, the state of the environment is updated. New suns are generated, zombies move and others appear.
    	PvzEnvironmentState environmentState = this.getEnvironmentState();
    	updateSunflowers(environmentState);
    	updateZombies(environmentState);
    	
        // Create a new perception to return
        PvzPerception perception = new PvzPerception();
        
        // Get the actual position of the agent to be able to create the
        // perception
        int row = environmentState.getAgentPosition()[0];
        int col = environmentState.getAgentPosition()[1];

        // Set the perception sensors
        perception.setTopSensor(this.getTopColumn(row, col));
        perception.setLeftSensor(this.getLeftRow(row, col));
        perception.setRightSensor(this.getRightRow(row, col));
        perception.setBottomSensor(this.getBottomColumn(row, col));
        
        //It is verified that a zombie has not advanced to the agent's position. If you have done so, you must perform the updates.
        if(perception.getRightSensor().size()>0 && environmentState.isZombie(perception.getRightSensor().get(0))) {
			environmentState.setAgentSuns(environmentState.getAgentSuns() + (perception.getRightSensor().get(0)*2));
			environmentState.setGardenPosition(row, col, PvzPerception.EMPTY_PERCEPTION);
			environmentState.decreaseRemainingZombies();
			environmentState.killZombie(row, col);
		}

        // Return the perception
        return perception;
    }

    private void updateZombies(PvzEnvironmentState environmentState) {
    	
    	//Zombies can only spawn in the column farthest from the house. Free rows are searched
		ArrayList<Integer> freeRows = new ArrayList<>();
		for(int i=0; i<PvzEnvironmentState.MATRIX_ROW_LENGTH; i++) {
			if(environmentState.getGardenPosition(i, PvzEnvironmentState.MATRIX_COLUMN_LENGTH-1) == PvzPerception.EMPTY_PERCEPTION) {
				freeRows.add(i);
			}
		}
    	
    	for(Zombie z : environmentState.getZombies()) {

			//It is verified that the zombie does not exist and there are free rows
			if(z.getPosition() == null){
				
				if(freeRows.size()>0) {
					//Randomizing appear
					if(new Random().nextInt(100) < 10){ //10% chance to appear
						int row = new Random().nextInt(freeRows.size()); //The cell in which the zombie will appear is randomized
						int[] position = new int[] {freeRows.get(row), PvzEnvironmentState.MATRIX_COLUMN_LENGTH-1};
						z.setPosition(position); //The new position is assigned
						environmentState.setGardenPosition(z.getRowPosition(), z.getColumnPosition(), z.getType()); //The matrix is updated
						freeRows.remove(row); //The row is deleted as free
					}
				}
			}
			
			else {

				int row = z.getRowPosition();
				int col = z.getColumnPosition();
				int nextColumnState = environmentState.getGardenPosition(row, col-1);
				
				if(!environmentState.isZombie(nextColumnState)) {
					
					//TODO Uncomment.
					if(z.getLastMovement() >= 3 || new Random().nextInt(10 + 0) < 2) { //The zombie must advance
					//if(z.getLastMovement() >= 3 || true) { //The zombie must advance
						
						z.decreaseColumn(); //The zombie advances a column.
						
						if(environmentState.isSunflower(nextColumnState)) {
							environmentState.getSunflowers().removeIf(s -> (s.getRowPosition() == row && s.getColumnPosition() == col-1));
						}
						
						//The matrix is updated after the movement
						environmentState.setGardenPosition(row, col, PvzPerception.EMPTY_PERCEPTION);
						environmentState.setGardenPosition(row, z.getColumnPosition(), z.getType());
						
						//It is checked if the zombie arrived at the house
						if(z.getColumnPosition()==0) {
							environmentState.setHouseAttacked(true);
						}
					}
				}
			}
		}
    	
    	//The number of zombies alive is updated.
    	environmentState.calculateZombiesAlive();
	}

	private void updateSunflowers(PvzEnvironmentState environmentState) {
		for(Sunflower s : environmentState.getSunflowers()){
			s.increaseSuns(new Random().nextInt(3 - 1) + 1);
			environmentState.setGardenPosition(s.getRowPosition(), s.getColumnPosition(), s.getSuns());
		}
	}

	@Override
    public String toString() {
        return environmentState.toString();
    }

    @Override
    public boolean agentFailed(Action actionReturned) {

        PvzEnvironmentState pvzEnvironmentState =
                this.getEnvironmentState();

        int agentSuns = pvzEnvironmentState.getAgentSuns();
        boolean houseAttacked = pvzEnvironmentState.getHouseAttacked();

       
        //The agent fails if he has no suns or a zombie arrives at the house
        if (agentSuns < 1 || houseAttacked)
            return true;

        return false;
    }

   
    public ArrayList<Integer> getTopColumn(int row, int col) {
        return ((PvzEnvironmentState) this.environmentState)
                .getTopColumn(row, col);
    }

    public ArrayList<Integer> getLeftRow(int row, int col) {
        return ((PvzEnvironmentState) this.environmentState)
                .getLeftRow(row, col);
    }

    public ArrayList<Integer> getRightRow(int row, int col) {
        return ((PvzEnvironmentState) this.environmentState)
                .getRightRow(row, col);
    }

    public ArrayList<Integer> getBottomColumn(int row, int col) { 
        return ((PvzEnvironmentState) this.environmentState)
                .getBottomColumn(row, col);
    }
    
}
