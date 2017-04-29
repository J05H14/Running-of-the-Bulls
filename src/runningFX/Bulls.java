package runningFX;

public class Bulls {
	private Coordinate[][] lastSeenAt;
	private Coordinate bull;
	private Coordinate fool;
	private StreetMap map;
	private int numMoves;

	public Bulls(StreetMap map, int numMoves){

		this.map = map;
		this.numMoves = numMoves;
		lastSeenAt = new Coordinate[map.getRows()][map.getCols()];
		fool = map.getFoolLocation();
		
		bull = new Coordinate(1, 1, 'B');
		
		bullMove();
	}

	private void bullMove() {
		
		for(int r = 0; r < map.getRows(); r++){
			for(int c = 0; c < map.getCols(); c++){
				if(lastSeenAt[r][c] == fool){
					
				}
			}
		}
	}
}
