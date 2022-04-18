package frsf.cidisi.faia.search.pvz;

import java.util.ArrayList;

import frsf.cidisi.faia.state.EnvironmentState;

public class PvzEnvironmentState extends EnvironmentState{
	
	private int[][] garden;
    private int[] agentPosition;
    private int agentSuns;
	

    /**
     * This method is used to setup the initial real world.
     */
    @Override
    public void initState() {

        // Sets all cells as empty
        for (int row = 0; row < garden.length; row++) {
            for (int col = 0; col < garden.length; col++) {
                garden[row][col] = 0; //0 is the number corresponding to an empty cell
            }
        }

        /* Sets a cell at the back with a zombie. */
        
        //IMPORTANT. This needs randomizing
        garden[8][4] = 5;
      

        this.setAgentPosition(new int[]{0, 2});
        
      //IMPORTANT. This needs randomizing
        this.setAgentSuns(15);
    }

    /**
     * String representation of the real world state.
     */
    
    
    public PvzEnvironmentState(int[][] m) {
        garden = m;
    }

    public PvzEnvironmentState() {
        garden = new int[5][9];
        this.initState();
    }
    
    
    @Override
    public String toString() {
        String str = "";

        return str;
    }
    
	
public ArrayList<Integer> getTopColumn(int row, int col) {
		
		ArrayList<Integer> topColumn = new ArrayList(); //Creating new arrayList to return data
		
		if (row==0) {
			return topColumn; // if I'm on the first row and I want to perceive the environment towards the north I won't see a damn thing, thus I will return an Empty arraylist
		}
		
        for (int i = row; i>=0; i--) {   //I perceive the environment on a column northwards until I hit the garden top wall
   
        	topColumn.add(garden[i][col]); //I add to my return array each element I see
        	if(garden[i][col]!=0) { //As the rules of the game state it, the plant perception can only reach the first object it sees on any direction, be it a zombie or a sunflower, therefore, if I hit something, after adding it, I will stop perceiving beyond
        		
        		while(topColumn.size()<row) { //the length of the arraylist to return needs to be the same length as the row I'm being fed as a parameter
        			topColumn.add(-1);//and as everything beyond the first obstacle is unknown, i need to set it to -1, as per our own game definitions
        		}
        	
        		return topColumn;   
        }
 
        return topColumn; 
        }
		return topColumn;
        
        
        
    }

public ArrayList<Integer> getRightRow(int row, int col) {
		
		ArrayList<Integer> rightRow = new ArrayList(); 
		
		if (col==8) {
			return rightRow; 
		}
		
        for (int i = col; i<=8; i++) { 
   
        	rightRow.add(garden[row][i]); 
        	if(garden[row][i]!=0) { 
        		
        		while(rightRow.size()<(8-col)) {  //as im perceiving to the right, the length of what I'm returning will have to be 8 which is the maximum size according to the garden length - current row position
        			rightRow.add(-1);
        		}
        	
        		return rightRow;   
        }
 
        return rightRow; 
        }
		return rightRow;
       
    }





public ArrayList<Integer> getLeftRow(int row, int col) {
	
	ArrayList<Integer> leftRow = new ArrayList(); 
	
	if (col==8) {
		return leftRow; 
	}
	
    for (int i = col; i>=0; i--) { 

    	leftRow.add(garden[row][i]); 
    	if(garden[row][i]!=0) { 
    		
    		while(leftRow.size()<col) { 
    			leftRow.add(-1);
    		}
    	
    		return leftRow;   
    }

    return leftRow; 
    }
	return leftRow;
    
}

    

public ArrayList<Integer> getBottomColumn(int row, int col) {
		
		ArrayList<Integer> bottomColumn = new ArrayList(); 
		
		if (row==4) {
			return bottomColumn; 
		}
		
        for (int i = row; i<=4; i++) { 
   
        	bottomColumn.add(garden[i][col]); 
        	if(garden[i][col]!=0) { 
        		
        		while(bottomColumn.size()<(4-row)) { 
        			bottomColumn.add(-1);
        		}
        	
        		return bottomColumn;   
        }
 
        return bottomColumn; 
        }
		return bottomColumn;
        
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
}
