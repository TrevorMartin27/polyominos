import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IntroPanel extends JPanel implements ActionListener {
	Navigator navigator;
	
	public IntroPanel(Navigator nav) {
		this.navigator = nav;
		
		this.setBackground(Color.WHITE);
		
		JPanel column = new JPanel();
		column.setLayout(new BoxLayout(column, BoxLayout.PAGE_AXIS));
		column.setBackground(this.getBackground());
		
		JLabel title = new JLabel("Polyominos");
		title.setFont(new Font("Serf", Font.BOLD, 30));
		
		JButton play = new JButton("Play");
		play.addActionListener(this);
		JButton settings = new JButton("Settings");
		settings.addActionListener(this);
		
		column.add(title);
		column.add(play);
		column.add(settings);
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(Box.createHorizontalGlue());
		this.add(column);
		this.add(Box.createHorizontalGlue());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton source = (JButton)e.getSource();
			String id = source.getText();
			
			if(id.equals("Settings")) {
				this.navigator.set_page(Pages.Settings);
			} else {
				System.out.println("Unknown Button Pressed");
			}
		} else {
			System.out.println("Unknown Event Source");
		}
	}
}
