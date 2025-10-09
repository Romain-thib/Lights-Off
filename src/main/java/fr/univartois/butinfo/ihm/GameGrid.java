package fr.univartois.butinfo.ihm;

public class GameGrid {
	private boolean[][] lights = new boolean[5][5];
	
	public void init() {
		for(int i=0; i < lights.length; i++) {
			for (int y = 0; y < lights[0].length; y++) {
				lights[i][y] = false;
				
			}
		}
	}
	
	public void switchAt(int row, int column) {
		switchSingle(row, column);
		switchSingle(row, column-1);
		switchSingle(row, column+1);
		switchSingle(row-1, column);
		switchSingle(row+1, column);
	}
	
	public void switchSingle(int row, int column) {
		if((row >= 0 && row < lights.length) && (column >= 0 && column < lights[row].length)) {
			lights[row][column] = !lights[row][column];
		}
	}
	
	public boolean isOn(int row, int column) {
		return lights[row][column];
	}
	
	public boolean isOff() {
		for(int i=0; i < lights.length; i++) {
			for (int y = 0; y < lights[0].length; y++) {
				if (lights[i][y] == true) {
					return false;
				}
			}
		}
		return true;
	}
}
