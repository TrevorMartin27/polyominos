import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SettingsPanel extends Scene implements ActionListener {
	private Config config;
	
	public SettingsPanel(Navigator nav, Config config) {
		super(nav);

		this.config = config;
		
		JPanel column = new JPanel();
		column.setLayout(new BoxLayout(column, BoxLayout.PAGE_AXIS));
		column.setBackground(this.getBackground());
		
		JLabel title = new JLabel("Settings");
		title.setFont(new Font("Serf", Font.BOLD, 20));
		
		JButton back = new JButton("Back");
		back.addActionListener(this);
		
		column.add(title);
		column.add(back);
		
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
			
			if(id.equals("Back")) {
				this.navigator.setPage(Pages.Title);
			} else {
				System.out.println("Unknown Button Pressed");
			}
		} else {
			System.out.println("Unknown Event Source");
		}
	}

	@Override
	public void onNavigateTo() {
		// TODO Auto-generated method stub
		
	}
}
