import java.awt.Container;

enum Pages {
	Title,
	Settings
};

public class Navigator {
	private Container container;
	
	private IntroPanel title;
	private SettingsPanel settings;
	
	public Navigator(Container container, Config config) {
		this.container = container;
		
		this.title = new IntroPanel(this);
		this.settings = new SettingsPanel(this, config);
	}
	
	public void set_page(Pages page) {
		System.out.println("Navigate");
		this.container.removeAll();
		
		switch(page) {
		case Title:
			System.out.println("Switching to title");
			this.container.add(this.title);
			break;
		case Settings:
			System.out.println("Switching to settings");
			this.container.add(this.settings);
			break;
		}
	}
}
