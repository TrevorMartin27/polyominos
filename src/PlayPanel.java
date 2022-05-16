import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

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

		this.newFalling();
	}

	public void setFalling(Polyomino polyomino, int color) {
		this.falling = new FallingPolyomino(polyomino, color);
	}

	public void newFalling() {
		LinkedList<Polyomino> polyominos = this.config.getPolyominos();
		int polyomino_count = polyominos.size();

		int index = (int)(Math.random() * polyomino_count);
		int color = (index % 7) + 1;

		// O(n), but I don't care
		this.setFalling(polyominos.get(index), color);
	}

	private int checkLineClear() {
		int width = this.canvas.getWidth(),
			height = this.canvas.getHeight();

		for(int y = 0; y < height; y++) {
			boolean full = true;
			for(int x = 0; x < width; x++) {
				if(this.canvas.getCell(x, y) == 0) {
					full = false;
					continue;
				}
			}
			if(full) {
				return y;
			}
		}

		return -1;
	}

	private void clearLine(int line) {
		int width = this.canvas.getWidth();

		for(int y = line; y > 0; y--) {
			for(int x = 0; x < width; x++) {
				int cell = this.canvas.getCell(x, y - 1);
				this.canvas.setCell(x, y, cell);
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
		boolean landed = this.falling.tick(this.canvas);

		this.repaint();

		if(landed) {
			this.newFalling();

			int clear_cnt = 0, line;
			while((line = this.checkLineClear()) > 0) {
				clear_cnt++;
				this.clearLine(line);
			}
			if(clear_cnt > 0) {
				System.out.println("CLEAR: " + clear_cnt);
			}
		}
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
			FallingPolyomino rotated = this.falling.rotate();
			if(rotated.willCollide(canvas)) {
				break;
			}
			
			System.out.println("UP");

			this.falling = rotated;
			this.repaint();
			break;
		case KeyEvent.VK_DOWN:
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
