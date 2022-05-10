import java.awt.Container;

enum Pages {
	Title,
	Play,
	Settings
};

public class Navigator {
	private Container container;
	
	private Scene title, play, settings;
	
	public Navigator(Container container, Config config) {
		this.container = container;
		
		this.title = new IntroPanel(this);
		this.play = new PlayPanel(this, config);
		this.settings = new SettingsPanel(this, config);
	}
	
	public void setPage(Pages page) {
		Scene new_scene = null;
		
		// TODO: Move this into a less ugly structure
		switch(page) {
		case Title:
			new_scene = this.title;
			break;
		case Play:
			new_scene = this.play;
			break;
		case Settings:
			new_scene = this.settings;
			break;
		}
		
		if(new_scene == null) {
			System.out.println("Warning: Unknown Scene");
			return;
		}
		
		this.container.removeAll();
		this.container.add(new_scene);
		
		/**
		 * For some reason, some braindead developed decided that adding a component to a container shouldn't cause a redraw
		 * This appears to be the only way of forcing a redraw (forcing it to re-calculate and re-render the layouts).
		 * 
		 * This code is terrible but it works.
		*/
		this.container.validate();
		this.container.repaint();
		
		new_scene.onNavigateTo();
	}
}
