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
    	uptadeZombies(environmentState);
    	
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

        // Return the perception
        return perception;
    }

    private void uptadeZombies(PvzEnvironmentState environmentState) {
    	
		for(Zombie z : environmentState.getZombies()) {
			
			//Zombies can only spawn in the column farthest from the house. Free rows are searched
			ArrayList<Integer> freeRows = new ArrayList<>();
			for(int i=0; i<PvzEnvironmentState.MATRIX_ROW_LENGHT; i++) {
				if(environmentState.getGardenPosition(i, PvzEnvironmentState.MATRIX_COLUMN_LENGHT-1) == PvzPerception.EMPTY_PERCEPTION) {
					freeRows.add(i);
				}
			}
			
			//It is verified that the zombie does not exist and there are free rows
			if(z.getPosition() == null && freeRows.size()>0){
				
				//Randomizing appear
				if((new Random().nextInt(10 + 0) + 0) < 2){ //20% chance to appear
					
					int[] position = new int[] {freeRows.get(0), PvzEnvironmentState.MATRIX_COLUMN_LENGHT-1};
					z.setPosition(position); //The new position is assigned
					environmentState.setGardenPosition(z.getRowPosition(), z.getColumnPosition(), z.getType()); //The matrix is updated
					freeRows.remove(0); //The row is deleted as free
				}
			}
			else {

				int row = z.getRowPosition();
				int col = z.getColumnPosition();
				int nextColumnState = environmentState.getGardenPosition(row, col-1);
				
				if(!environmentState.isZombie(nextColumnState)) {
					
					//TODO Uncomment.
					//if(z.getLastMovement() >= 3 || new Random().nextInt(10 + 0) < 2) {
					if(z.getLastMovement() >= 3 || true) { //The zombie must advance
						
						z.decreaseColumn(); //The zombie advances a column.
						
						if(environmentState.isSunflower(nextColumnState)) {
							environmentState.getSunflowers().removeIf(s -> (s.getRowPosition() == row && s.getColumnPosition() == col-1));
						}
						
						if(environmentState.isAgentPosition(row, col-1)) {
							//TODO Decrease agent suns x2, remove zombie and update matrix.
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
	}

	private void updateSunflowers(PvzEnvironmentState environmentState) {
		for(Sunflower s : environmentState.getSunflowers()){
			//TODO Uncomment 
			//s.increaseSuns(new Random().nextInt(3 + 1) + 1);
			s.increaseSuns(1);
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

       
        // If the agent has no suns, he failed
        if (agentSuns <= 0 || houseAttacked)
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
