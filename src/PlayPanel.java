import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PlayPanel extends JPanel {
	private Config config;
	private Navigator navigator;
	
	private PlayCanvas canvas;
	
	private FallingPolyomino falling;

	public PlayPanel(Navigator nav, Config config) {
		this.config = config;
		this.navigator = nav;
		
		this.setBackground(Color.WHITE);

		this.canvas = new PlayCanvas();

		this.falling = new FallingPolyomino(
			this.config.getPolyominos().get(1)
		);
	}

	public void setFalling(Polyomino polyomino) {
		this.falling = new FallingPolyomino(polyomino);
	}

	private boolean checkLineClear() {
		int width = this.canvas.getWidth(),
			height = this.canvas.getHeight();

		boolean full = true;
		for(int i = 0; i < width; i++) {
			if(this.canvas.getCell(height - 1, i) == 0) {
				full = false;
				break;
			}
		}

		return full;
	}

	private void clearLine() {
		int width = this.canvas.getWidth(),
			height = this.canvas.getHeight();

		boolean bottom_full = this.checkLineClear();
		if(!bottom_full) {
			return;
		}

		for(int y = height - 1; y > 0; y--) {
			for(int x = 0; x < width; x++) {
				int cell = this.canvas.getCell(x, y);
				this.canvas.setCell(x, y - 1, cell);
			}
		}
		for(int i = 0; i < width; i++) {
			this.canvas.setCell(i, 0, 0);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		this.canvas.paintComponent(g, falling);
	}
}
