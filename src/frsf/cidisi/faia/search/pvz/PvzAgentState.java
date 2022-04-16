package frsf.cidisi.faia.search.pvz;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

public class PvzAgentState extends SearchBasedAgentState {

    private int[] position;
    private int suns;
    private int zombies;
    private int[][] garden;
    private int[] initialPosition;


    public PvzAgentState(int row, int col, int suns, int zombies, int[][] g){
        this.garden = g;
        this.position = new int[] {row, col};
        this.initialPosition = new int[2];
        this.initialPosition[0] = row;
        this.initialPosition[1] = col;
        this.suns = suns;
        this.zombies = zombies;
    }

    public PvzAgentState(){
        this.garden = new int[5][9];
        position = new int[2];
        this.initState();
    }

    @Override
    public void initState(){
        
    }

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SearchBasedAgentState clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateState(Perception p) {
		// TODO Auto-generated method stub
		
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

    
}
