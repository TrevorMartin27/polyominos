import java.util.ArrayList;
import java.util.LinkedList;

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
		
		public Polyomino toPolyomino() {
			int side_length = this.size * 2 - 1;
			boolean ftop = false, fbottom = false, fleft = false, fright = false;
			int top = 0, bottom = side_length - 1, left = 0, right = side_length - 1;
			
			// Get bounds of the Polyomino (remove translation information)
			for(int i = 0; i < side_length; i++) {
				for(int j = 0; j < side_length; j++) {
					if(!ftop && this.cells[i][j]) {
						top = i;
						ftop = true;
					}
					
					if(!fbottom && this.cells[side_length - i - 1][j]) {
						bottom = side_length - i - 1;
						fbottom = true;
					}
					
					if(!fleft && this.cells[j][i]) {
						left = i;
						fleft = true;
					}
					
					if(!fright && this.cells[j][side_length - i - 1]) {
						right = side_length - i - 1;
						fright = true;
					}
				}
			}
			
			int width = right - left + 1, height = bottom - top + 1;
			Polyomino res = new Polyomino(width, height);
			
			for(int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					res.setCell(x, y, this.cells[y + top][x + left]);
				}
			}
			
			return res;
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
	
	private void recurse(LinkedList<Polyomino> polyominos, RecursivePolyomino parent, int remaining) {
		/* TODO: This recursive function does NOT generate 'T' shapes, because it can't backtrack. In the future, generate
		 * a buffer at start time (in generatePolyominos) that tracks the history of cell placement, then look through that
		 * buffer for neighbors instead of just the current cell.
		*/
		if(remaining == 0) {
			Polyomino new_polyomino = parent.toPolyomino();
			for(Polyomino other : polyominos) {
				if(new_polyomino.equals(other)) {
					return;
				}
			}
			polyominos.add(new_polyomino);
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
			
			this.recurse(polyominos, child, remaining - 1);
		}
	}
	
	public LinkedList<Polyomino> generatePolyominos(int size) {
		RecursivePolyomino parent = new RecursivePolyomino(size);
		
		LinkedList<Polyomino> res = new LinkedList<Polyomino>();
		this.recurse(res, parent, size - 1);

		for(Polyomino poly : res) {
			System.out.println(poly.toString());
		}
		
		return res;
	}
}
