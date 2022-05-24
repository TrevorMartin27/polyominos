import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

public class Polyominos {
	public static final String save_file_path = "high_score.txt";
	
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
		this.navigator.setPage(Pages.Title);
		
		this.window.setVisible(true);
	}
	
	public static int getSavedHighScore() {
		int score = 0;
		
		try {
			File file = new File(Polyominos.save_file_path);
			Scanner reader = new Scanner(file);
			
			score = reader.nextInt();
			
			reader.close();
		} catch(FileNotFoundException e) { /* ... */ }
		
		return score;
	}
	
	public static void setSavedHighScore(int score) {
		try {
			FileWriter file = new FileWriter(Polyominos.save_file_path, false);
			file.write("" + score);
			file.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Polyominos();
	}
}
