package frsf.cidisi.faia.search.pvz;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

import frsf.cidisi.faia.state.EnvironmentState;

public class PvzEnvironmentState extends EnvironmentState{
	
	public static int MATRIX_ROW_LENGTH = 5;
	public static int MATRIX_COLUMN_LENGTH = 10; //Column 0 is used to represent the house.
	
	private int[][] garden;
    private int[] agentPosition; //Represents the current position of the agent
    private int agentSuns; //Represents the soles owned by the agent
    private int remainingZombies; //Represents the number of zombies that are in play or remain to appear.
    private int zombiesAlive; //Represents the number of zombies that are currently alive
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
    	this.zombiesAlive = 0;
    	
    	// Randomly generate the zombies
    	for(int i=1; i <= remainingZombies; i++) {
    		zombies.add(new Zombie());
    	}

        // Sets all cells as empty
        for (int row = 0; row < PvzEnvironmentState.MATRIX_ROW_LENGTH; row++) {
            for (int col = 0; col < PvzEnvironmentState.MATRIX_COLUMN_LENGTH; col++) {
                garden[row][col] = PvzPerception.EMPTY_PERCEPTION; //Fill the array as unknown.
            }
        }

        this.setAgentPosition(new int[]{2, 1});
    }
    
    public PvzEnvironmentState(int[][] g) {
        garden = g;
    }

    public PvzEnvironmentState() {
        this.garden = new int[PvzEnvironmentState.MATRIX_ROW_LENGTH][PvzEnvironmentState.MATRIX_COLUMN_LENGTH];
        this.zombies = new ArrayList<>();
        this.sunflowers = new ArrayList<>();
        this.houseAttacked = false;
        this.initState();
    }
    
    
    @Override
    public String toString() {
		String str = "";
		
		str = str + " AgentPosition=\"(" + getAgentPosition()[0] + "," + "" + getAgentPosition()[1] + ")\"\n";
		str = str + " AgentSuns=\"" + agentSuns + "\"\n";
		str = str + " RemainingZombies=\"" + remainingZombies + "\"\n";
		str = str + " ZombiesAlive=\"" + zombiesAlive + "\"\n";
		str = str + " HouseAttacked=\"" + houseAttacked + "\"\n";
		str = str + " Garden= \n";
		
        for (int row = 0; row < PvzEnvironmentState.MATRIX_ROW_LENGTH; row++) {
            str = str + "[";
            for (int col = 0; col < PvzEnvironmentState.MATRIX_COLUMN_LENGTH; col++) {
            	if (garden[row][col] == PvzPerception.EMPTY_PERCEPTION) {
                	str = str + " * ";
                }
            	else {
            		if(garden[row][col] >= PvzPerception.SUNFLOWER_PERCEPTION && garden[row][col] < 10) {
            			str = str + " " + garden[row][col] + " ";
            		}
            		else
            			str = str + garden[row][col] + " ";
            	}
            }
            str = str + "]\n";
        }
		
		return str;
    }
    
	
    public ArrayList<Integer> getTopColumn(int row, int col) {
		
		ArrayList<Integer> topColumn = new ArrayList<Integer>(); //Creating new arrayList to return data
		
		if (row==0) {
			return topColumn; // if I'm on the first row and I want to perceive the environment towards the north I won't see a damn thing, thus I will return an Empty arraylist
		}
		
        for (int i=(row-1); i>=0; i--) {   //I perceive the environment on a column northwards until I hit the garden top wall
        	
        	topColumn.add(garden[i][col]); //I add to my return array each element I see
        	
        	if(garden[i][col] != PvzPerception.EMPTY_PERCEPTION) { //As the rules of the game state it, the plant perception can only reach the first object it sees on any direction, be it a zombie or a sunflower, therefore, if I hit something, after adding it, I will stop perceiving beyond
        		return topColumn;
        	}
        }
		return topColumn;   
    }

    public ArrayList<Integer> getRightRow(int row, int col) {
		
		ArrayList<Integer> rightRow = new ArrayList<Integer>(); 
		
		if (col == PvzEnvironmentState.MATRIX_COLUMN_LENGTH-1) {
			return rightRow; 
		}
		
		rightRow.add(garden[row][col]);
		
        for (int i = (col+1); i<PvzEnvironmentState.MATRIX_COLUMN_LENGTH; i++) { 
   
        	rightRow.add(garden[row][i]);
        	
        	if(garden[row][i] != PvzPerception.EMPTY_PERCEPTION) { 
        		
        		while(rightRow.size()<(PvzEnvironmentState.MATRIX_COLUMN_LENGTH-col)) {  //as im perceiving to the right, the length of what I'm returning will have to be 8 which is the maximum size according to the garden length - current row position
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
		
	    for (int i = (col-1); i>0; i--) { 
	
	    	leftRow.add(garden[row][i]);
	    	
	    	if(garden[row][i] != PvzPerception.EMPTY_PERCEPTION) {
	    		return leftRow;   
	    	}
	    }
		return leftRow;
	}

    

    public ArrayList<Integer> getBottomColumn(int row, int col) {
		
		ArrayList<Integer> bottomColumn = new ArrayList<Integer>(); 
		
		if (row == PvzEnvironmentState.MATRIX_ROW_LENGTH-1) {
			return bottomColumn; 
		}
		
        for (int i = (row+1); i<PvzEnvironmentState.MATRIX_ROW_LENGTH; i++) { 
   
        	bottomColumn.add(garden[i][col]);
        	
        	if(garden[i][col] != PvzPerception.EMPTY_PERCEPTION) { 
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
    	this.zombies.removeIf(z -> z.getPosition()!=null && z.getColumnPosition()==col && z.getRowPosition()==row);
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
	
	public int getZombiesAlive() {
		return zombiesAlive;
	}

	public void setZombiesAlive(int zombiesAlive) {
		this.zombiesAlive = zombiesAlive;
	}
	
	public void calculateZombiesAlive() {
		this.zombiesAlive = this.zombies.stream().filter(z -> z.getPosition() != null).collect(Collectors.toList()).size();
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
	
	public boolean isRemainingZombies() { //GoalTest for game finishing
		return this.remainingZombies>0;
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

	public void decreaseRemainingZombies() {
		this.remainingZombies--;
		this.zombiesAlive--;
	}

	public void harvestSunflower(int row, int col) {
		this.sunflowers.stream().filter(s -> s.getColumnPosition() == col && s.getRowPosition() == row).collect(Collectors.toList()).get(0).setSuns(0);
		garden[row][col] = PvzPerception.SUNFLOWER_PERCEPTION; 
	}
}
