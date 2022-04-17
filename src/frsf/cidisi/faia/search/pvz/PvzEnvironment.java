package frsf.cidisi.faia.search.pvz;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.examples.search.pacman.PacmanEnvironmentState;
import frsf.cidisi.faia.examples.search.pacman.PacmanPerception;

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
        perception.setTopSensor(this.getTopCell(row, col));
        perception.setLeftSensor(this.getLeftCell(row, col));
        perception.setRightSensor(this.getRightCell(row, col));
        perception.setBottomSensor(this.getBottomCell(row, col));

        // Return the perception
        return perception;
    }

    @Override
    public String toString() {
        return environmentState.toString();
    }

    @Override
    public boolean agentFailed(Action actionReturned) {

        PacmanEnvironmentState pacmanEnvironmentState =
                this.getEnvironmentState();

        int agentEnergy = pacmanEnvironmentState.getAgentEnergy();

        // FIXME: The pacman agent always has the same energy
        // If the agent has no energy, he failed
        if (agentEnergy <= 0)
            return true;

        return false;
    }

    // The following methods are Pacman-specific:
    
    public int getTopCell(int row, int col) {
        return ((PacmanEnvironmentState) this.environmentState)
                .getTopCell(row, col);
    }

    public int getLeftCell(int row, int col) {
        return ((PacmanEnvironmentState) this.environmentState)
                .getLeftCell(row, col);
    }

    public int getRightCell(int row, int col) {
        return ((PacmanEnvironmentState) this.environmentState)
                .getRightCell(row, col);
    }

    public int getBottomCell(int row, int col) {
        return ((PacmanEnvironmentState) this.environmentState)
                .getBottomCell(row, col);
    }
    
}
