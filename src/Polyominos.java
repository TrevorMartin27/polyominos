import javax.swing.JFrame;

public class Polyominos {
	private Config config;
	
	private JFrame window;
	
	private Navigator navigator;
	
	public Polyominos() {
		this.config = new Config();
		
		this.window = new JFrame("Polyominos");
		this.window.setBounds(100, 100, 192*3, 108*4);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setResizable(false);
		this.window.setVisible(false);
		
		this.navigator = new Navigator(
			this.window.getContentPane(),
			this.config
		);
		this.navigator.set_page(Pages.Title);
		
		this.window.setVisible(true);
		
		PolyominoFactory factory = new PolyominoFactory();
		factory.generatePolyominos(4);
	}
	
	public static void main(String[] args) {
		new Polyominos();
	}
}
