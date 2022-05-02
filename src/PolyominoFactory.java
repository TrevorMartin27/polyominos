import java.util.ArrayList;

public class PolyominoFactory {
	private class RecursivePolyomino {
		public int size;
		public boolean[][] cells;
		
		public int w_x, w_y;
		
		public RecursivePolyomino(int size) {
			this.size = size;
			
			int side_length = (size * 2) - 1;
			this.cells = new boolean[side_length][side_length];
			
			this.w_x = this.size - 1;
			this.w_y = this.size - 1;
			
			this.cells[this.w_y][this.w_x] = true; 
		}
		
		public RecursivePolyomino(RecursivePolyomino other) {
			this.size = other.size;
			
			int side_length = (this.size * 2) - 1;
			this.cells = new boolean[side_length][side_length];
			for(int y = 0; y < side_length; y++) { // In C++ I wouldn't need to do this
				this.cells[y] = other.cells[y].clone();
			}
			
			this.w_x = other.w_x;
			this.w_y = other.w_y;
		}
		
		@Override
		public String toString() {
			String res = "";
			
			int cnt = 0;
			for(int y = 0; y < this.cells.length; y++) {
				for(int x = 0; x < this.cells[y].length; x++) {
					boolean curr = this.cells[y][x];
					if(curr)
						cnt++;
					res += curr ? (
						(this.w_x == x && this.w_y == y) ? "X" : "#"
					) : " ";
				}
				res += "|\n";
			}
			res += "Count: " + cnt + "\n";

			return res;
		}
	};
	
	public PolyominoFactory() { /* ... */ }
	
	private void recurse(RecursivePolyomino parent, int remaining) {
		/* TODO: This recursive function does NOT generate 'T' shapes, because it can't backtrack. In the future, generate
		 * a buffer at start time (in generatePolyominos) that tracks the history of cell placement, then look through that
		 * buffer for neighbors instead of just the current cell.
		*/
		if(remaining == 0) {
			System.out.println(parent);
			return;
		}
		
		if(remaining < 0) {
			// PANIC
			System.exit(1);
		}
		
		
		final int delta[][] = {
			{  0,  1 },
			{  0, -1 },
			{  1,  0 },
			{ -1, 0 },
		};
		
		for(int[] step : delta) {
			RecursivePolyomino child = new RecursivePolyomino(parent);
			
			child.w_x += step[0];
			child.w_y += step[1];
			if(child.cells[child.w_y][child.w_x]) {
				continue;
			}
			child.cells[child.w_y][child.w_x] = true;
			
			this.recurse(child, remaining - 1);
		}
	}
	
	public ArrayList<Polyomino> generatePolyominos(int size) {
		RecursivePolyomino parent = new RecursivePolyomino(size);
		
		this.recurse(parent, size - 1);
		
		return null;
	}
}
