import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PlayPanel extends Scene {
	private final int TICKS_PER_SECOND = 2;
	
	private Config config;
	
	private PlayCanvas canvas;
	
	private Timer ticker = null;
	
	private FallingPolyomino[] falling;

	public PlayPanel(Navigator nav, Config config) {
		super(nav);
		
		this.config = config;
		
		this.setBackground(Color.WHITE);

		this.canvas = new PlayCanvas();
		
		int[] offset = new int[this.canvas.getWidth()];
		
		this.falling = new FallingPolyomino[25];
		for(int i = 0; i < this.falling.length; i++) {
			int type_index = i % this.config.getPolyominos().size();
			
			FallingPolyomino curr = new FallingPolyomino(
				this.config.getPolyominos().get(type_index)
			);
			
			int x = (int)(Math.random() * this.canvas.getWidth());
			if(x + curr.getWidth() > this.canvas.getWidth())
				continue;
			
			int y = offset[x];
			for(int j = 0; j < curr.getWidth(); j++) {
				if((j + x) >= offset.length) {
					break;
				}
				
				if(offset[j + x] > y) {
					y = offset[j + x];
				}
			}
			if(y > (this.canvas.getHeight() + 3)) {
				continue;
			}
			
			for(int j = x; j < x + curr.getWidth(); j++) {
				if(j >= offset.length) {
					break;
				}
				
				offset[j] = y + curr.getHeight();
			}

			curr.setColor((type_index % 4) + 1);
			curr.setX(x);
			curr.setY(y);
			
			this.falling[i] = curr;
		}
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
	
	private void startTicker() {
		this.stopTicker();
		this.ticker = new Timer(1000 / TICKS_PER_SECOND, e -> {
			this.tick();
		});
		this.ticker.start();
	}
	
	private void stopTicker() {
		if(this.ticker != null) {
			this.ticker.stop();
			this.ticker = null;
		}
	}
	
	private void startGame() {
		this.startTicker();
	}
	
	private void stopGame() {
		this.stopTicker();
	}
	
	private void tick() {
		for(FallingPolyomino f : this.falling) {
			if(f == null)
				continue;
			
			f.tick(this.canvas);
		}

		this.repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		this.canvas.paintComponent(g, this.falling);
	}

	@Override
	public void onNavigateTo() {
		this.startGame();
	}
}
