package frsf.cidisi.faia.search.pvz;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import java.util.Random;

public class PvzAgentState extends SearchBasedAgentState {

    private int[] position;
    private int suns;
    private int zombies;
    private int[][] garden;


    public PvzAgentState(int row, int col, int suns, int zombies, int[][] g){
        this.garden = g;
        this.position = new int[] {row, col};
        this.suns = suns;
        this.zombies = zombies;
    }

    public PvzAgentState(){
        this.garden = new int[PvzEnvironmentState.MATRIX_ROW_LENGHT][PvzEnvironmentState.MATRIX_COLUMN_LENGHT];
        position = new int[2];
        suns=0;
        zombies=0;
        this.initState();
    }

    @Override
    public void initState(){
        //The number of suns and zombies is randomly generated. Format: ...nextInt(max + min) + min;
    	this.suns = new Random().nextInt(20 + 2) + 2;
    	this.zombies = new Random().nextInt(20 + 5) + 5;
    }

	@Override
	public boolean equals(Object obj) {
        if (!(obj instanceof PvzAgentState))
            return false;

        int[][] gardenObj = ((PvzAgentState) obj).getGarden();
        int[] positionObj = ((PvzAgentState) obj).getPosition();
        int sunsObj = ((PvzAgentState) obj).getSuns();
        int zombiesObj = ((PvzAgentState) obj).getZombies();

        for (int row = 0; row < PvzEnvironmentState.MATRIX_ROW_LENGHT; row++) {
            for (int col = 0; col < PvzEnvironmentState.MATRIX_COLUMN_LENGHT; col++) {
                if (garden[row][col] != gardenObj[row][col]) {
                    return false;
                }
            }
        }

        if (position[0] != positionObj[0] || position[1] != positionObj[1]) {
            return false;
        }
        
        if (suns!=sunsObj || zombies!=zombiesObj) {
        	return false;
        }
        
        return true;
    }

	@Override
	public SearchBasedAgentState clone() {
        int[][] newGarden = new int[PvzEnvironmentState.MATRIX_ROW_LENGHT][PvzEnvironmentState.MATRIX_COLUMN_LENGHT];

        for (int row = 0; row < PvzEnvironmentState.MATRIX_ROW_LENGHT; row++) {
            for (int col = 0; col < PvzEnvironmentState.MATRIX_COLUMN_LENGHT; col++) {
                newGarden[row][col] = garden[row][col];
            }
        }

        int[] newPosition = new int[2];
        newPosition[0] = position[0];
        newPosition[1] = position[1];
        int newSuns = suns;
        int newZombies = zombies;

        PvzAgentState newState = new PvzAgentState(newPosition[0], newPosition[1], newSuns, newZombies, newGarden);

        return newState;
    }

	@Override
	public void updateState(Perception p) {
		// TODO Auto-generated method stub
		
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

	public int[][] getGarden() {
		return garden;
	}

	public void setGarden(int[][] garden) {
		this.garden = garden;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isAgentAlive() {
		return this.suns>0;
	}

	public boolean isNoMoreZombies() {
		return this.zombies==0;
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
    
}
