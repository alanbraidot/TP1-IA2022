package frsf.cidisi.faia.search.pvz;

import java.util.ArrayList;

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
        // Create a new perception to return
        PvzPerception perception = new PvzPerception();
        
        // Get the actual position of the agent to be able to create the
        // perception
        int row = this.getEnvironmentState().getAgentPosition()[0];
        int col = this.getEnvironmentState().getAgentPosition()[1];

        // Set the perception sensors
        perception.setTopSensor(this.getTopColumn(row, col));
        perception.setLeftSensor(this.getLeftRow(row, col));
        perception.setRightSensor(this.getRightRow(row, col));
        perception.setBottomSensor(this.getBottomColumn(row, col));

        // Return the perception
        return perception;
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

       
        // If the agent has no suns, he failed
        if (agentSuns <= 0)
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
