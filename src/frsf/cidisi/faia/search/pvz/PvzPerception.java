package frsf.cidisi.faia.search.pvz;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class PvzPerception extends Perception{
	
	public static int UNKNOWN_PERCEPTION = -1;
    public static int EMPTY_PERCEPTION = 0;
    public static int ZOMBIE_TYPE1_PERCEPTION = 1;
    public static int ZOMBIE_TYPE2_PERCEPTION = 2;
    public static int ZOMBIE_TYPE3_PERCEPTION = 5;
    public static int ZOMBIE_TYPE4_PERCEPTION = 3;
    public static int ZOMBIE_TYPE5_PERCEPTION = 2;
    public static int ZOMBIE_TYPE6_PERCEPTION = 4;
    public static int SUNFLOWER_PERCEPTION = 10;
    
    private int leftSensor;
    private int topSensor;
    private int rightSensor;
    private int bottomSensor;
    
    public PvzPerception() {
    	
    }
    
    public PvzPerception(Agent agent, Environment environment) {
        super(agent, environment);
    }
	

	@Override
	public void initPerception(Agent agent, Environment environment) {
		// TODO Auto-generated method stub
		
	}
	
	public int getLeftSensor() {
		return leftSensor;
	}

	public void setLeftSensor(int leftSensor) {
		this.leftSensor = leftSensor;
	}

	public int getTopSensor() {
		return topSensor;
	}

	public void setTopSensor(int topSensor) {
		this.topSensor = topSensor;
	}

	public int getRightSensor() {
		return rightSensor;
	}

	public void setRightSensor(int rightSensor) {
		this.rightSensor = rightSensor;
	}

	public int getBottomSensor() {
		return bottomSensor;
	}

	public void setBottomSensor(int bottomSensor) {
		this.bottomSensor = bottomSensor;
	}

	@Override
	public String toString() {
		return "PvzPerception [leftSensor=" + leftSensor + ", topSensor=" + topSensor + ", rightSensor=" + rightSensor
				+ ", bottomSensor=" + bottomSensor + "]";
	}
	
}
