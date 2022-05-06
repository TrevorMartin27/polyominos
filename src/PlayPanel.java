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
			this.config.get_polyominos().get(1)
		);
	}

	public void set_falling(Polyomino polyomino) {
		this.falling = new FallingPolyomino(polyomino);
	}

	private boolean check_line_clear() {
		int width = this.canvas.get_width(),
			height = this.canvas.get_height();

		boolean full = true;
		for(int i = 0; i < width; i++) {
			if(this.canvas.get_cell(height - 1, i) == 0) {
				full = false;
				break;
			}
		}

		return full;
	}

	private void clear_line() {
		int width = this.canvas.get_width(),
			height = this.canvas.get_height();

		boolean bottom_full = this.check_line_clear();
		if(!bottom_full) {
			return;
		}

		for(int y = height - 1; y > 0; y--) {
			for(int x = 0; x < width; x++) {
				int cell = this.canvas.get_cell(x, y);
				this.canvas.set_cell(x, y - 1, cell);
			}
		}
		for(int i = 0; i < width; i++) {
			this.canvas.set_cell(i, 0, 0);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		this.canvas.paintComponent(g, falling);
	}
}
