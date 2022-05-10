import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PlayCanvas extends JPanel {
	private final int CELL_SIZE = 16,
		CELL_SPACING = 1,
		BOARD_HEIGHT = 20,
		BOARD_WIDTH = 10;
	private final int TICKS_PER_SECOND = 10;
	
	private int[][] board;
	private Timer ticker = null;
	
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
	
	private void startTicker() {
		this.stopTicker();
		this.ticker = new Timer(1000 / TICKS_PER_SECOND, e -> { this.tick(); });
	}
	
	private void stopTicker() {
		if(this.ticker != null) {
			this.ticker.stop();
			this.ticker = null;
		}
	}
	
	public void startGame() {
		this.startTicker();
	}
	
	public void stopGame() {
		this.stopTicker();
	}
	
	public void tick() {
		System.out.println("TICK");
	}
	
	public void paintComponent(Graphics g, FallingPolyomino falling) {
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
	}
}
