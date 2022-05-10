import java.awt.Color;

import javax.swing.JPanel;

abstract public class Scene extends JPanel {
	protected Navigator navigator;
	
	public Scene(Navigator nav) {
		this.navigator = nav;
		
		this.setBackground(Color.WHITE);
	}
	
	abstract public void onNavigateTo();
}
