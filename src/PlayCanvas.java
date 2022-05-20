import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PlayCanvas extends JPanel {
	private final int CELL_SIZE = 16,
		CELL_SPACING = 1,
		BOARD_HEIGHT = 20,
		BOARD_WIDTH = 10;
	
	private int[][] board;
	private int current_score = 0, max_score = 0;
	
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
	
	public void paintComponent(Graphics g, FallingPolyomino falling) {
		super.paintComponent(g);
		
		final Color[] colors = {
			Color.WHITE,
			// NOTE: To add a color, update FallingPolyomino
			Color.RED,
			Color.ORANGE,
			Color.GREEN,
			Color.BLUE,
			Color.CYAN,
			Color.DARK_GRAY,
			Color.GREEN
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
						if((x - 1 >= falling.getX()) && (x - 1 < falling.getX() + falling.getWidth()) &&
							(y - 1 >= falling.getY()) && (y - 1 < falling.getY() + falling.getHeight())) {
							int local_x = x - falling.getX() - 1, local_y = y - falling.getY() - 1;

							if(falling.getCell(local_x, local_y)) {
								curr = falling.getColor();
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
		
		int sidebar_start = (BOARD_WIDTH + 2) * (CELL_SIZE + CELL_SPACING);
		
		g.setColor(Color.WHITE);
		g.fill3DRect(sidebar_start, 0,
			150, (BOARD_HEIGHT + 2) * (CELL_SIZE + CELL_SPACING),
			false);
		
		g.drawString("High Score:    " + this.max_score, sidebar_start, 15);
		g.drawString("Current Score: " + this.current_score, sidebar_start, 30);
	}

	public void clear() {
		for(int y = 0; y < BOARD_HEIGHT; y++) {
			for(int x = 0; x < BOARD_WIDTH; x++) {
				this.board[y][x] = 0;
			}
		}
		
		this.max_score = Math.max(this.current_score, this.max_score);
		this.current_score = 0;
	}
	
	public void setCurrentScore(int score) {
		this.current_score = score;
	}
	
	public int getCurrentScore() {
		return this.current_score;
	}
	
	public void incCurrentScore(int score) {
		this.setCurrentScore(this.getCurrentScore() + score);
	}
	
	public void setHighScore(int score) {
		this.max_score = score;
	}
}
