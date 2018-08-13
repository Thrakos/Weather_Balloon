import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, MouseListener, KeyListener {

	Timer timer;

	final int LEVEL_SELECT = 0;
	final int GAME_STATE = 1;

	int currentState;

	Balloon b;

	final int LANE_ONE = 175;
	final int LANE_TWO = 350;
	final int LANE_THREE = 525;

	int level;

	int pixelsMoved;

	ArrayList<Weather> weather = new ArrayList<Weather>();

	Wind wind;
	JButton windUp;
	JButton windDown;
	int windPix = 0;
	boolean useWind = false;
	int returnWind = 0;

	ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

	PineTree pineTree1;

	GamePanel(int width, int height) {

		final int WIDTH = width;
		final int HEIGHT = height;

		timer = new Timer(100 / 60, this);

		currentState = GAME_STATE; // change

		b = new Balloon(100, 325, 40, 50);

		level = 1;

		wind = new Wind(10, 200, height - 60, 100, 40);
		wind.isAlive = true;

		windUp = new JButton();
		windUp.addActionListener(this);
		windUp.setBounds((WIDTH / 2) - 25, 20, 75, 40);
		windUp.setText("up");
		windUp.setVisible(false);
		windDown = new JButton();
		windDown.addActionListener(this);
		windDown.setBounds((WIDTH / 2) - 25, HEIGHT - 100, 75, 40);
		windDown.setText("down");
		windDown.setVisible(false);

		weather.add(wind);

		pineTree1 = new PineTree(WIDTH, HEIGHT - 450, 200, 450);

		obstacles.add(pineTree1);

		for (int i = 0; i < weather.size(); i++) {
			Weather w = weather.get(i);
			w.addActionListener(this);
		}

		this.setLayout(null);
		this.add(wind);
		this.add(windUp);
		this.add(windDown);

	}

	void gameStart() {
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) { // timer

		if (e.getSource() == timer) {
			repaint();
			if (currentState == GAME_STATE) {
				updateGameState();
			}
		}

		if (e.getSource() == wind) {
			if (!b.movingLanes && wind.amount > 0 && windPix == 0) {
				if (!useWind) {
					windUp.setVisible(true);
					windDown.setVisible(true);
					useWind = true;
				} else {
					windUp.setVisible(false);
					windDown.setVisible(false);
					useWind = false;
				}
			}
		}
		if (e.getSource() == windUp) {
			if (useWind) {
				returnWind = 2;
				useWind = false;
				windUp.setVisible(false);
				windDown.setVisible(false);
				wind.amount--;
			}
		}
		if (e.getSource() == windDown) {
			if (useWind) {
				returnWind = -2;
				useWind = false;
				windUp.setVisible(false);
				windDown.setVisible(false);
				wind.amount--;
			}
		}

	}

	public void paintComponent(Graphics g) {
		if (currentState == GAME_STATE) {
			drawGameState(g);
		}
	}

	void drawGameState(Graphics g) {

		g.setColor(new Color(171, 224, 255));
		g.fillRect(0, 0, 1000, 700);
		for (int i = 0; i < obstacles.size(); i++) {
			Obstacle o = obstacles.get(i);
			o.draw(g);
		}
		b.draw(g);

	}

	void updateGameState() {

		if (level == 1) {
			levelOne();
		}
		
		if (returnWind > 1 || returnWind < -1) {
			returnWind = returnWind/2;
			b.lane += (returnWind);
		} else if (returnWind != 0) {
			if (windPix == 1750) {
				b.lane += (-returnWind);
				windPix = 0;
				returnWind = 0;
			} else {
				windPix++;
			}
		}
		
		updateObstacles();
		b.update();
		checkPop();

	}

	void updateObstacles() {

		for (int i = 0; i < obstacles.size(); i++) {
			Obstacle o = obstacles.get(i);
			o.update();
		}

		for (int i = 0; i < weather.size(); i++) {
			Weather w = weather.get(i);
			w.update();
			w.setText(w.text + " (" + w.amount + ")");
		}

	}

	void checkPop() {

		for (int i = 0; i < obstacles.size(); i++) {
			Obstacle o = obstacles.get(i);
			if (b.collisionBox.intersects(o.collisionBox)) {
				b.isAlive = false;
			}
		}

	}

	void levelOne() {

		if (pixelsMoved == 1000) {
			pineTree1.isAlive = true;
		}

		pixelsMoved++;

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
