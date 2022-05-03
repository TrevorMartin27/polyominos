import java.awt.Container;

enum Pages {
	Title,
	Play,
	Settings
};

public class Navigator {
	private Container container;
	
	private IntroPanel title;
	private PlayPanel play;
	private SettingsPanel settings;
	
	public Navigator(Container container, Config config) {
		this.container = container;
		
		this.title = new IntroPanel(this);
		this.play = new PlayPanel(this, config);
		this.settings = new SettingsPanel(this, config);
	}
	
	public void set_page(Pages page) {
		this.container.removeAll();
		
		switch(page) {
		case Title:
			// System.out.println("Switching to Title");
			this.container.add(this.title);
			break;
		case Play:
			// System.out.println("Switching to Play");
			this.container.add(this.play);
			break;
		case Settings:
			// System.out.println("Switching to Settings");
			this.container.add(this.settings);
			break;
		}
		
		/**
		 * For some reason, some braindead developed decided that adding a component to a container shouldn't cause a redraw
		 * This appears to be the only way of forcing a redraw (forcing it to re-calculate and re-render the layouts).
		 * 
		 * This code is terrible but it works.
		*/
		this.container.validate();
		this.container.repaint();
	}
}
