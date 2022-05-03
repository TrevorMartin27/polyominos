import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PlayPanel extends JPanel {
	private Config config;
	private Navigator navigator;
	
	private int[][] board;
	
	private final int CELL_SIZE = 16, SPACING = 1;
	
	public PlayPanel(Navigator nav, Config config) {
		this.config = config;
		this.navigator = nav;
		
		this.board = new int[20 + 2][10 + 2];
		for(int i = 0; i < 20+2; i++) {
			this.board[i][0] = 1;
			this.board[i][10 + 1] = 1;
		}
		
		for(int i = 1; i < 10+1; i++) {
			this.board[0][i] = 1;
			this.board[20 + 1][i] = 1;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		final Color[] colors = {
			Color.WHITE,
			Color.GRAY,
			Color.RED,
			Color.ORANGE,
			Color.GREEN,
			Color.PINK
		};
		
		for(int y = 0; y < this.board.length; y++) {
			for(int x = 0; x < this.board[y].length; x++) {
				int curr = this.board[y][x];
				
				g.setColor(colors[curr]);
				
				g.fill3DRect(
					x * (CELL_SIZE + SPACING),
					y * (CELL_SIZE + SPACING),
					CELL_SIZE, CELL_SIZE, curr != 0);
			}
		}
	}
}
