import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PlayCanvas extends JPanel {
	private final int CELL_SIZE = 16,
		CELL_SPACING = 1,
		BOARD_HEIGHT = 20,
		BOARD_WIDTH = 10;
	
	private int[][] board;
	
	public PlayCanvas() {
		// Board is by default all 0s
		this.board = new int[BOARD_HEIGHT][BOARD_WIDTH];
	}
	
	public int getWidth() { return this.BOARD_WIDTH; }
	public int getHeight() { return this.BOARD_HEIGHT; }

	public void setCell(int x, int y, int value) {
		this.board[y][x] = value;
	}
	
	public int getCell(int x, int y) {
		return this.board[y][x];
	}
	
	public void paintComponent(Graphics g, FallingPolyomino[] falling) {
		super.paintComponent(g);
		
		final Color[] colors = {
			Color.WHITE,
			Color.RED,
			Color.ORANGE,
			Color.GREEN,
			Color.PINK
		};
		
		for(int y = 0; y < BOARD_HEIGHT + 2; y++) {
			int ty = y * (CELL_SIZE + CELL_SPACING);
			for(int x = 0; x < BOARD_WIDTH + 2; x++) {
				int tx = x * (CELL_SIZE + CELL_SPACING);
				
				if((x == 0) ||
				   (y == 0) ||
				   (x >= BOARD_WIDTH + 1) ||
				   (y >= BOARD_HEIGHT + 1)) {
					g.setColor(Color.GRAY);
					
					g.fill3DRect(tx, ty,
						CELL_SIZE, CELL_SIZE,
						true
					);
				} else {
					int curr = this.board[y - 1][x - 1];
					
					// Render the falling piece if there
					if(falling != null) {
						for(FallingPolyomino f : falling) {
							if(f == null)
								continue;
							if((x - 1 >= f.getX()) && (x - 1 < f.getX() + f.getWidth()) &&
								(y - 1 >= f.getY()) && (y - 1 < f.getY() + f.getHeight())) {
								int local_x = x - f.getX() - 1, local_y = y - f.getY() - 1;

								if(f.getCell(local_x, local_y)) {
									curr = f.getColor();
									break;
								}
							}
						}
					}

					g.setColor(colors[curr]);
					
					g.fill3DRect(tx, ty,
						CELL_SIZE, CELL_SIZE,
						curr != 0
					);
				}
			}
		}
	}
}
