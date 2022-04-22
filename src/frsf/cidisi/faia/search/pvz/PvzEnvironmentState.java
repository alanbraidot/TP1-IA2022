package frsf.cidisi.faia.search.pvz;

import java.util.ArrayList;
import java.util.Random;

import frsf.cidisi.faia.state.EnvironmentState;

public class PvzEnvironmentState extends EnvironmentState{
	
	public static int MATRIX_ROW_LENGHT = 5;
	public static int MATRIX_COLUMN_LENGHT = 10;
	
	private int[][] garden;
    private int[] agentPosition; //Represents the current position of the agent
    private int agentSuns; //Represents the soles owned by the agent
    private int remainingZombies; //Represents the number of zombies that are in play or remain to appear.
    private boolean houseAttacked; //It represents the state of the house. If true, a zombie came to her
    private ArrayList<Zombie> zombies; //Represents the list of zombies that exist or will exist in the game
    private ArrayList<Sunflower> sunflowers; //Represents the list of sunflowers that exist or will exist in the game
	

    /**
     * This method is used to setup the initial real world.
     */
	@Override
    public void initState() {
    	
		//TODO Uncomment this.agentSuns = new Random().nextInt(20 + 2) + 2)
        this.agentSuns = 15;
        
        //TODO Uncomment this.remainginZombies = new Random().nextInt(20 + 5) + 5)
        this.remainingZombies = 10;
    	
    	// Randomly generate the zombies
    	for(int i=1; i <= remainingZombies; i++) {
    		zombies.add(new Zombie());
    	}

        // Sets all cells as empty
        for (int row = 0; row < PvzEnvironmentState.MATRIX_ROW_LENGHT; row++) {
            for (int col = 0; col < PvzEnvironmentState.MATRIX_COLUMN_LENGHT; col++) {
                garden[row][col] = PvzPerception.EMPTY_PERCEPTION; //Fill the array as unknown.
            }
        }

        this.setAgentPosition(new int[]{1, 2});
    }
    
    public PvzEnvironmentState(int[][] g) {
        garden = g;
    }

    public PvzEnvironmentState() {
        this.garden = new int[PvzEnvironmentState.MATRIX_ROW_LENGHT][PvzEnvironmentState.MATRIX_COLUMN_LENGHT];
        this.zombies = new ArrayList<>();
        this.sunflowers = new ArrayList<>();
        this.houseAttacked = false;
        this.initState();
    }
    
    
    @Override
    public String toString() {
        String str = "";

        return str;
    }
    
	
    public ArrayList<Integer> getTopColumn(int row, int col) {
		
		ArrayList<Integer> topColumn = new ArrayList<Integer>(); //Creating new arrayList to return data
		
		if (row==0) {
			return topColumn; // if I'm on the first row and I want to perceive the environment towards the north I won't see a damn thing, thus I will return an Empty arraylist
		}
		
        for (int i = row; i>=0; i--) {   //I perceive the environment on a column northwards until I hit the garden top wall
        	
        	topColumn.add(garden[i][col]); //I add to my return array each element I see
        	
        	if(garden[i][col] != PvzPerception.EMPTY_PERCEPTION) { //As the rules of the game state it, the plant perception can only reach the first object it sees on any direction, be it a zombie or a sunflower, therefore, if I hit something, after adding it, I will stop perceiving beyond
        		
        		while(topColumn.size()<row) { //the length of the arraylist to return needs to be the same length as the row I'm being fed as a parameter
        			topColumn.add(PvzPerception.UNKNOWN_PERCEPTION); //and as everything beyond the first obstacle is unknown, i need to set it to -1, as per our own game definitions
        		}
        		return topColumn;
        	}
        }
		return topColumn;   
    }

    public ArrayList<Integer> getRightRow(int row, int col) {
		
		ArrayList<Integer> rightRow = new ArrayList<Integer>(); 
		
		if (col == PvzEnvironmentState.MATRIX_COLUMN_LENGHT-1) {
			return rightRow; 
		}
		
        for (int i = col; i<PvzEnvironmentState.MATRIX_COLUMN_LENGHT; i++) { 
   
        	rightRow.add(garden[row][i]);
        	
        	if(garden[row][i] != PvzPerception.EMPTY_PERCEPTION) { 
        		
        		while(rightRow.size()<(PvzEnvironmentState.MATRIX_COLUMN_LENGHT-1-col)) {  //as im perceiving to the right, the length of what I'm returning will have to be 8 which is the maximum size according to the garden length - current row position
        			rightRow.add(PvzPerception.UNKNOWN_PERCEPTION);
        		}
        		return rightRow;
        	}
        }
		return rightRow;
    }

    public ArrayList<Integer> getLeftRow(int row, int col) {
	
		ArrayList<Integer> leftRow = new ArrayList<Integer>(); 
		
		if (col == 1) {
			return leftRow; 
		}
		
	    for (int i = col; i>0; i--) { 
	
	    	leftRow.add(garden[row][i]);
	    	
	    	if(garden[row][i] != PvzPerception.EMPTY_PERCEPTION) {
	    		
	    		while(leftRow.size()<col) { 
	    			leftRow.add(PvzPerception.UNKNOWN_PERCEPTION);
	    		}
	    		return leftRow;   
	    	}
	    }
		return leftRow;
	}

    

    public ArrayList<Integer> getBottomColumn(int row, int col) {
		
		ArrayList<Integer> bottomColumn = new ArrayList<Integer>(); 
		
		if (row == PvzEnvironmentState.MATRIX_ROW_LENGHT-1) {
			return bottomColumn; 
		}
		
        for (int i = row; i<PvzEnvironmentState.MATRIX_ROW_LENGHT; i++) { 
   
        	bottomColumn.add(garden[i][col]);
        	
        	if(garden[i][col] != PvzPerception.EMPTY_PERCEPTION) { 
        		
        		while(bottomColumn.size()<(PvzEnvironmentState.MATRIX_ROW_LENGHT-1-row)) { 
        			bottomColumn.add(PvzPerception.UNKNOWN_PERCEPTION);
        		}
        		return bottomColumn;
        	}
        }
		return bottomColumn; 
    }
	
    public void plantSunflower() {
    	this.sunflowers.add(new Sunflower(this.agentPosition));
    	int row = agentPosition[0];
    	int col = agentPosition[1];
    	this.garden[row][col] = PvzPerception.SUNFLOWER_PERCEPTION;
    }
    
    public void killZombie(int row, int col) {
    	this.zombies.removeIf(z -> z.getColumnPosition()==col && z.getRowPosition()==row);
    	this.garden[row][col] = PvzPerception.EMPTY_PERCEPTION;
    }
	
	public int[][] getGarden() {
		return garden;
	}
	
	public void setGarden(int[][] garden) {
		this.garden = garden;
	}
	
	public int[] getAgentPosition() {
		return agentPosition;
	}
	
	public void setAgentPosition(int[] agentPosition) {
		this.agentPosition = agentPosition;
	}
	
	public int getAgentSuns() {
		return agentSuns;
	}
	
	public void setAgentSuns(int agentSuns) {
		this.agentSuns = agentSuns;
	}

	public void setGardenPosition(int row, int col, int perception) {
		this.garden[row][col] = perception;
	}
	
	public int getGardenPosition(int row, int col) {
		return this.garden[row][col];
	}

	public ArrayList<Zombie> getZombies() {
		return zombies;
	}

	public int getRemainingZombies() {
		return remainingZombies;
	}

	public void setRemainingZombies(int remainingZombies) {
		this.remainingZombies = remainingZombies;
	}

	public boolean getHouseAttacked() {
		return houseAttacked;
	}

	public void setHouseAttacked(boolean houseAtacked) {
		this.houseAttacked = houseAtacked;
	}

	public ArrayList<Sunflower> getSunflowers() {
		return sunflowers;
	}

	public void setSunflowers(ArrayList<Sunflower> sunflowers) {
		this.sunflowers = sunflowers;
	}

	public void setZombies(ArrayList<Zombie> zombies) {
		this.zombies = zombies;
	}

	public boolean isZombie(int perception) {
		return (perception >= PvzPerception.ZOMBIE_TYPE3_PERCEPTION && perception <= PvzPerception.ZOMBIE_TYPE1_PERCEPTION);
	}

	public boolean isSunflower(int perception) {
		return (perception >= PvzPerception.SUNFLOWER_PERCEPTION);
	}

	public boolean isAgentPosition(int row, int col) {
		return (this.agentPosition[0] == row && this.agentPosition[1] == col);
	}
}
