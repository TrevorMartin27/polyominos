import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PlayPanel extends Scene {
	private final int TICKS_PER_SECOND = 2;
	
	private Config config;
	
	private PlayCanvas canvas;
	
	private Timer ticker = null;
	
	private FallingPolyomino falling;

	public PlayPanel(Navigator nav, Config config) {
		super(nav);
		
		this.config = config;
		
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
		System.out.println("TICK");
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		this.canvas.paintComponent(g, falling);
	}

	@Override
	public void onNavigateTo() {
		this.startGame();
	}
}
