import javax.swing.JFrame;

public class WeatherBalloon {

	JFrame frame;
	GamePanel panel;

	static final int WIDTH = 1000;
	static final int HEIGHT = 700;

	WeatherBalloon() {

		frame = new JFrame();
		panel = new GamePanel();
		
	}
	
	public static void main(String[] args) {
		WeatherBalloon WB = new WeatherBalloon();
		WB.setup();
	}

	void setup() {
		
		frame.add(panel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addMouseListener(panel);
		frame.addKeyListener(panel);
		frame.setResizable(false);
		
		panel.gameStart();
		
	}
	
}
