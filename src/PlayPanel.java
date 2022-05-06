import java.awt.Graphics;

import javax.swing.JPanel;

public class PlayPanel extends JPanel {
	private Config config;
	private Navigator navigator;
	
	private PlayCanvas canvas;
	
	public PlayPanel(Navigator nav, Config config) {
		this.config = config;
		this.navigator = nav;
		
		this.canvas = new PlayCanvas();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		this.canvas.paintComponent(g);
	}
}
