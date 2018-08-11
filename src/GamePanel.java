import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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
	
	ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	
	PineTree pineTree1;

	GamePanel(int width, int height) {
		
		final int WIDTH = width;
		final int HEIGHT = height;

		timer = new Timer(100 / 60, this);

		currentState = GAME_STATE; // change

		b = new Balloon(100, 325, 40, 50);
		
		level = 1;
		
		
		pineTree1 = new PineTree(WIDTH, HEIGHT - 450, 300, 450);
		
		obstacles.add(pineTree1);

	}

	void gameStart() {
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) { // timer
		repaint();
		if (currentState == GAME_STATE) {
			updateGameState();
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
		updateObstacles();
		b.update();
		checkPop();

	}
	
	void updateObstacles() {
		
		for (int i = 0; i < obstacles.size(); i++) {
			Obstacle o = obstacles.get(i);
			o.update();
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

		if (e.getKeyCode() == KeyEvent.VK_L) {
			System.out.println("lane: " + b.lane);
		}

		if (!b.movingLanes) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (b.lane != 2) {
					b.lane++;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (b.lane != 0) {
					b.lane--;
				}
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
