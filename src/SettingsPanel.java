import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SettingsPanel extends JPanel implements ActionListener {
	private Config config;
	private Navigator navigator;
	
	public SettingsPanel(Navigator nav, Config config) {
		this.navigator = nav;
		this.config = config;
		
		this.setBackground(Color.WHITE);
		
		JPanel column = new JPanel();
		column.setLayout(new BoxLayout(column, BoxLayout.PAGE_AXIS));
		column.setBackground(this.getBackground());
		
		JLabel title = new JLabel("Settings");
		title.setFont(new Font("Serf", Font.BOLD, 20));
		
		JButton back = new JButton("Back");
		
		column.add(title);
		column.add(back);
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(Box.createHorizontalGlue());
		this.add(column);
		this.add(Box.createHorizontalGlue());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
