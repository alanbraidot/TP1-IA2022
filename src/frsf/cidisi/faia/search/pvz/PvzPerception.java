package frsf.cidisi.faia.search.pvz;

import java.util.ArrayList;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class PvzPerception extends Perception{
	
	public static int UNKNOWN_PERCEPTION = -20;
    public static int EMPTY_PERCEPTION = -10;
    public static int ZOMBIE_TYPE1_PERCEPTION = -1;
    public static int ZOMBIE_TYPE2_PERCEPTION = -2;
    public static int ZOMBIE_TYPE3_PERCEPTION = -5;
    public static int ZOMBIE_TYPE4_PERCEPTION = -3;
    public static int ZOMBIE_TYPE5_PERCEPTION = -2;
    public static int ZOMBIE_TYPE6_PERCEPTION = -4;
    public static int SUNFLOWER_PERCEPTION = 0;
    
    private ArrayList<Integer> leftSensor;
    private ArrayList<Integer> topSensor;
    private ArrayList<Integer> rightSensor;
    private ArrayList<Integer> bottomSensor;
    
    public PvzPerception() {
    }
    
    public PvzPerception(Agent agent, Environment environment) {
        super(agent, environment);
    }
	

    /**
     * This method is used to setup the perception.
     */
    @Override
    public void initPerception(Agent agent, Environment environment) {
        PvzAgent pvzAgent = (PvzAgent) agent;
        PvzEnvironment pvzEnvironment = (PvzEnvironment) environment;
        PvzEnvironmentState environmentState = pvzEnvironment.getEnvironmentState();

        int row = environmentState.getAgentPosition()[0];
        int col = environmentState.getAgentPosition()[1];

        this.setTopSensor(pvzEnvironment.getTopColumn(row, col));
        this.setLeftSensor(pvzEnvironment.getLeftRow(row, col));
        this.setRightSensor(pvzEnvironment.getRightRow(row, col));
        this.setBottomSensor(pvzEnvironment.getBottomColumn(row, col));
    }


	public ArrayList<Integer> getLeftSensor() {
		return leftSensor;
	}

	public void setLeftSensor(ArrayList<Integer> leftSensor) {
		this.leftSensor = leftSensor;
	}

	public ArrayList<Integer> getTopSensor() {
		return topSensor;
	}

	public void setTopSensor(ArrayList<Integer> topSensor) {
		this.topSensor = topSensor;
	}

	public ArrayList<Integer> getRightSensor() {
		return rightSensor;
	}

	public void setRightSensor(ArrayList<Integer> rightSensor) {
		this.rightSensor = rightSensor;
	}

	public ArrayList<Integer> getBottomSensor() {
		return bottomSensor;
	}

	public void setBottomSensor(ArrayList<Integer> bottomSensor) {
		this.bottomSensor = bottomSensor;
	}

	@Override
	public String toString() {
		return "PvzPerception [leftSensor=" + leftSensor + ", topSensor=" + topSensor + ", rightSensor=" + rightSensor
				+ ", bottomSensor=" + bottomSensor + "]";
	}
	
}
