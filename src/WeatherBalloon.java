import java.awt.Dimension;

import javax.swing.JFrame;

public class WeatherBalloon {

	JFrame frame;
	GamePanel panel;

	static final int WIDTH = 1000;
	static final int HEIGHT = 700;

	WeatherBalloon() {

		frame = new JFrame();
		panel = new GamePanel(WIDTH, HEIGHT);
		
	}
	
	public static void main(String[] args) {
		WeatherBalloon WB = new WeatherBalloon();
		WB.setup();
	}

	void setup() {
		
		frame.add(panel);		
		frame.getContentPane().setPreferredSize(new Dimension(WIDTH - 20, HEIGHT - 10));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addMouseListener(panel);
		frame.addKeyListener(panel);
		frame.setResizable(false);
		
		panel.gameStart();
		
	}
	
}
