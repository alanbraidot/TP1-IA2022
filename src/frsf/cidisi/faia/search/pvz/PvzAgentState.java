package frsf.cidisi.faia.search.pvz;

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

    
}
