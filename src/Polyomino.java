
public class Polyomino {
	private int width, height;
	private boolean[][] cells;
	
	public Polyomino(int width, int height) {
		this.width = width;
		this.height = height;
		
		this.cells = new boolean[height][width];
	}
	
	public Polyomino rotate() {
		Polyomino res = new Polyomino(this.height, this.width);

		for(int y = 0; y < this.height; y++) {
			for(int x = 0; x < this.width; x++) {
				int nx = y,
					ny = this.width - 1 - x;
				res.setCell(nx, ny, this.getCell(x, y));
			}
		}

		return res;
	}

	public boolean shallowEquals(Polyomino other) {
		if(this.width != other.width || this.height != other.height) {
			return false;
		}

		for(int y = 0; y < this.height; y++) {
			for(int x = 0; x < this.width; x++) {
				if(this.getCell(x, y) != other.getCell(x, y)) {
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public boolean equals(Object other) {
		if(other instanceof Polyomino) {
			// This is extremely inefficient, but it shouldn't be called often
			if(this.shallowEquals((Polyomino)other)) {
				return true;
			}

			Polyomino rot = this.rotate();
			for(int i = 1; i < 4; i++) {
				if(rot.shallowEquals((Polyomino)other)) {
					return true;
				}
				if(i < 3) {
					rot = rot.rotate();
				}
			}
		}
		return false;
	}
	
	public boolean getCell(int x, int y) {
		return this.cells[y][x];
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
		res += "-".repeat(this.width);
		
		return res;
	}
}
