package runningFX;

import java.util.Random;

public class StreetMap {

	private int rows;
	private int cols;
	private Coordinate[][] blocks;
	private Coordinate block;
	private Coordinate foolLocation;
	Random noWalls = new Random(1);
	private double wallPercent;

	public StreetMap(int rows, int cols, double wallPercent) {
		this.rows = rows;
		this.cols = cols;
		this.wallPercent = wallPercent;
		blocks = new Coordinate[rows][cols];

		for(int r = 0; r < blocks.length; r++){
			for(int c = 0; c < blocks[r].length; c++){

				if(r == 0 && c == 1){
					block = new Coordinate(r, c, 'S');
				}
				else if(r == blocks.length - 1 && c == blocks[r].length - 2){
					block = new Coordinate(r, c, 'E');
				}
				else if(r == 0 || r == blocks.length - 1 || c == 0 || c == blocks[r].length - 1){
					block = new Coordinate(r, c, 'W');
				}

				else if(noWalls.nextDouble() <= wallPercent){
					block = new Coordinate(r, c , 'W');
				}
				else{
					block = new Coordinate(r, c , ' ');
				}

				blocks[r][c] = block;
			}
		}
	}
	public char blockValue(int r, int c){
		char value = 0;
		noWalls = new Random();

		if(r == 0 && c == 1){
			value = 'S';
		}
		else if(r == blocks.length - 1 && c == blocks[r].length - 2){
			value = 'E';
		}
		else if(r == 0 || r == blocks.length - 1 || c == 0 || c == blocks[r].length - 1){
			value = 'W';
		}
		else if(noWalls.nextDouble() < wallPercent){
			value = 'W';
		}
		else{
			value = ' ';
		}
		return value;
	}
	
	public boolean isValidMove(int row, int col){
//		if(row < 0 || row >= blocks.length - 2 || col < 0 || col >= blocks.length - 2){
//			return false;
//		}
		if(blocks[row][col].getVal() == 'W'){
			System.out.println(blocks[row][col].getVal());
			return false;
		}
		
		return true;
	}

	public Coordinate[][] getBlocks() {
		return blocks;
	}

	public void setBlocks(Coordinate[][] blocks) {
		this.blocks = blocks;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public Coordinate getBlock() {
		return block;
	}

	public void setBlock(Coordinate block) {
		this.block = block;
	}
	public double getWallPercent() {
		return wallPercent;
	}
	public void setWallPercent(double wallPercent) {
		this.wallPercent = wallPercent;
	}
	public Coordinate getFoolLocation() {
		return foolLocation;
	}
	public void setFoolLocation(int row, int col) {
		this.foolLocation = blocks[row][col];
	}
}
