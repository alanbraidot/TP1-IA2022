package frsf.cidisi.faia.search.pvz;

public class Zombie {
	
	private int type; //Represents the type of zombie
	private int lastMovement; //Represents the number of perception cycles it has been without moving
	private int[] position; //Represents the position within the garden matrix. It is null if it has not yet appeared
	
	public Zombie() {
		super();
		this.type = PvzPerception.ZOMBIE_TYPE1_PERCEPTION; //TODO Randomizing
		this.lastMovement = 0;
		this.position = null;
	}

	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getLastMovement() {
		return lastMovement;
	}
	
	public void setLastMovement(int lastMove) {
		this.lastMovement = lastMove;
	}
	
	public void increaseLastMovement() {
		this.lastMovement++;
	}
	
	public int[] getPosition() {
		return position;
	}
	
	public void setPosition(int[] position) {
		this.position = position;
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

}
