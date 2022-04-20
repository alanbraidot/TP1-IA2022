package frsf.cidisi.faia.search.pvz;

public class Sunflower {
	
	private int suns; //Represents the number of suns that the sunflower has
	private int[] position; //Represents the position within the garden matrix.
	
	public Sunflower(int[] position) {
		super();
		this.suns = 0;
		this.position = position;
	}

	public int getSuns() {
		return suns;
	}

	public void setSuns(int suns) {
		this.suns = suns;
	}
	
	public void increaseSuns(int suns) {
		this.suns += suns;
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
