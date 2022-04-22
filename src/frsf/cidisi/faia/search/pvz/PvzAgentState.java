package frsf.cidisi.faia.search.pvz;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

import java.util.Iterator;
import java.util.Random;

public class PvzAgentState extends SearchBasedAgentState {

    private int[] position;
    private int suns;
    private int zombies; //Represents the total number of zombies that will be part of the game
    private int zombiesAlive; //Represents the number of zombies that are currently alive
    private int[][] garden;


    public PvzAgentState(int row, int col, int suns, int zombies, int[][] g, int zombiesAlive){
        this.garden = g;
        this.position = new int[] {row, col};
        this.suns = suns;
        this.zombies = zombies;
        this.zombiesAlive = zombiesAlive;
    }

    public PvzAgentState(){
        this.garden = new int[PvzEnvironmentState.MATRIX_ROW_LENGTH][PvzEnvironmentState.MATRIX_COLUMN_LENGTH];
        position = new int[2];
        suns=0;
        zombies=0;
        this.initState();
    }

    @Override
    public void initState(){
    	//The starting position, suns and zombies are assigned in the agent constructor since the data is provided by the environment.
    	
    	// Sets all cells as unknown
        for (int row = 0; row < PvzEnvironmentState.MATRIX_ROW_LENGTH; row++) {
            for (int col = 0; col < PvzEnvironmentState.MATRIX_COLUMN_LENGTH; col++) {
                garden[row][col] = PvzPerception.UNKNOWN_PERCEPTION; //Fill the array as unknown.
            }
        }
    }

	@Override
	public boolean equals(Object obj) {
        if (!(obj instanceof PvzAgentState))
            return false;

        int[][] gardenObj = ((PvzAgentState) obj).getGarden();
        int[] positionObj = ((PvzAgentState) obj).getPosition();
        int sunsObj = ((PvzAgentState) obj).getSuns();
        int zombiesObj = ((PvzAgentState) obj).getZombies();
        int zombiesAliveObj = ((PvzAgentState) obj).getZombiesAlive();

        for (int row = 0; row < PvzEnvironmentState.MATRIX_ROW_LENGTH; row++) {
            for (int col = 0; col < PvzEnvironmentState.MATRIX_COLUMN_LENGTH; col++) {
                if (garden[row][col] != gardenObj[row][col]) {
                    return false;
                }
            }
        }

        if (position[0] != positionObj[0] || position[1] != positionObj[1]) {
            return false;
        }
        
        if (suns!=sunsObj || zombies!=zombiesObj || zombiesAlive!=zombiesAliveObj) {
        	return false;
        }
        
        return true;
    }

	@Override
	public SearchBasedAgentState clone() {
        int[][] newGarden = new int[PvzEnvironmentState.MATRIX_ROW_LENGTH][PvzEnvironmentState.MATRIX_COLUMN_LENGTH];

        for (int row = 0; row < PvzEnvironmentState.MATRIX_ROW_LENGTH; row++) {
            for (int col = 0; col < PvzEnvironmentState.MATRIX_COLUMN_LENGTH; col++) {
                newGarden[row][col] = garden[row][col];
            }
        }

        int[] newPosition = new int[2];
        newPosition[0] = position[0];
        newPosition[1] = position[1];
        int newSuns = suns;
        int newZombies = zombies;
        int newZombiesAlive = zombiesAlive;

        PvzAgentState newState = new PvzAgentState(newPosition[0], newPosition[1], newSuns, newZombies, newGarden, newZombiesAlive);

        return newState;
    }

	@Override
	public void updateState(Perception p) {
		PvzPerception perception = (PvzPerception) p;
		
		int row = this.getRowPosition();
		int col = this.getColumnPosition();
		
		//The garden is updated upwards.
		for(int i=0; i<perception.getTopSensor().size(); i++) {
			garden[row-i][col] = perception.getTopSensor().get(i);
		}
		
		//The garden is updated downwards.
		for(int i=0; i<perception.getBottomSensor().size(); i++) {
			garden[row+i][col] = perception.getBottomSensor().get(i);
		}
		
		//The garden is updated to the right.
		for(int i=0; i<perception.getRightSensor().size(); i++) {
			garden[row][col+i] = perception.getRightSensor().get(i);
		}
		
		//The garden is updated to the left.
		for(int i=0; i<perception.getLeftSensor().size(); i++) {
			garden[row][col-i] = perception.getLeftSensor().get(i);
		}
		
		this.setZombiesAlive(perception.getZombiesAlive());
	}

	public int[] getPosition() {
		return position;
	}

	public void setPosition(int[] position) {
		this.position = position;
	}

	public int getSuns() {
		return suns;
	}

	public void setSuns(int suns) {
		this.suns = suns;
	}

	public int getZombies() {
		return zombies;
	}

	public void setZombies(int zombies) {
		this.zombies = zombies;
	}

	public int getZombiesAlive() {
		return zombiesAlive;
	}

	public void setZombiesAlive(int zombiesAlive) {
		this.zombiesAlive = zombiesAlive;
	}

	public int[][] getGarden() {
		return garden;
	}

	public void setGarden(int[][] garden) {
		this.garden = garden;
	}

	@Override
	public String toString() {
		String str = "";
		
		str = str + " Position=\"(" + getRowPosition() + "," + "" + getColumnPosition() + ")\"\n";
		str = str + " Suns=\"" + suns + "\"\n";
		str = str + " RemainingZombies=\"" + zombies + "\"\n";
		str = str + " ZombiesAlive=\"" + zombiesAlive + "\"\n";
		str = str + " Garden= \n";
		
        for (int row = 0; row < PvzEnvironmentState.MATRIX_ROW_LENGTH; row++) {
            str = str + "[";
            for (int col = 0; col < PvzEnvironmentState.MATRIX_COLUMN_LENGTH; col++) {
            	if (garden[row][col] == PvzPerception.UNKNOWN_PERCEPTION) {
                    str = str + "? ";
                }
            	else {
                	if (garden[row][col] == PvzPerception.EMPTY_PERCEPTION) {
                		 str = str + "* ";
                	}
                	else
                		str = str + garden[row][col] + " "; 
                }
            }
            str = str + "]\n";
        }
		
		return str;
	}

	public boolean isAgentAlive() {
		return this.suns>0;
	}

	public boolean isNoMoreZombies() {
		return this.zombiesAlive==0;
	}
	
	public boolean isZombie(int perception) {
		return (perception>=PvzPerception.ZOMBIE_TYPE3_PERCEPTION && perception<=PvzPerception.ZOMBIE_TYPE1_PERCEPTION);
	}

	public int getRowPosition() {
		return this.position[0];
	}

	public int getColumnPosition() {
		return this.position[1];
	}

	public void setRowPosition(int row) {
		this.position[0] = row;
	}
	
	public void setColumnPosition(int col) {
		this.position[1] = col;
	}

	public void setGardenPosition(int row, int col, int perception) {
		this.garden[row][col] = perception;
	}

	public int getGardenPosition(int row, int col) {
		return this.garden[row][col];
	}

	public void decreaseZombies() {
		this.zombies--;
		this.zombiesAlive--;
	}
    
}
