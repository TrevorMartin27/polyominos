
public class Polyomino {
	private int width, height;
	private boolean[][] cells;
	
	public Polyomino(int width, int height) {
		this.width = width;
		this.height = height;
		
		this.cells = new boolean[height][width];
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Polyomino) {
			// TODO: Add equals. Two rotated polyominos must still be equal
		}
		return false;
	}
	
	public void setCell(int x, int y, boolean v) {
		this.cells[y][x] = v;
	}
	
	@Override
	public String toString() {
		String res = "";
		
		res += "Width: " + this.width + " Height: " + this.height + "\n";
		for(int y = 0; y < this.height; y++) {
			for(int x = 0; x < this.width; x++) {
				res += this.cells[y][x] ? "#" : " ";
			}
			res += "|\n";
		}
		res += "----------\n";
		
		return res;
	}
}
