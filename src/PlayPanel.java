import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

public class PlayPanel extends Scene implements KeyListener {
	private final int TICKS_PER_SECOND = 2;
	
	private Config config;
	
	private PlayCanvas canvas;
	
	private Timer ticker = null;
	
	private FallingPolyomino falling;

	public PlayPanel(Navigator nav, Config config) {
		super(nav);
		
		this.config = config;
		
		this.setFocusable(true);
		this.addKeyListener(this);

		this.canvas = new PlayCanvas();
		this.canvas.setFocusable(true);
		this.canvas.addKeyListener(this);

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
		this.falling.tick(this.canvas);

		this.repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		this.canvas.paintComponent(g, falling);
	}

	@Override
	public void onNavigateTo() {
		this.startGame();

		this.requestFocusInWindow();
	}

	@Override
	public void keyTyped(KeyEvent e) { /* ... */ }

	@Override
	public void keyPressed(KeyEvent e) {
		int key_code = e.getKeyCode();

		switch(key_code) {
		case KeyEvent.VK_UP:
			System.out.println("UP");

			this.repaint();
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("DOWN");

			// NOTE: I may want to do something different
			// Is it better to do an instant down?

			this.tick();
			break;
		case KeyEvent.VK_RIGHT:
			this.falling.moveHorizontal(this.canvas, 1);

			this.repaint();
			break;
		case KeyEvent.VK_LEFT:
			this.falling.moveHorizontal(this.canvas, -1);

			this.repaint();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) { /* ... */ }
}
