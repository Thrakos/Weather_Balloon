import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	
	GamePanel() {
		
		timer = new Timer(100 / 60, this);
		
		currentState = GAME_STATE; //change
		
		b = new Balloon(100, 325, 40, 50);
		
	}
	
	void gameStart() {
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {   //timer
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
		b.draw(g);
		
	}
	
	void updateGameState() {
		
		b.update();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("click");
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
				
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (b.lane != 2) {
				b.lane++;
			}
			System.out.println("lane" + b.lane);
			System.out.println("prevLane" + b.prevLane);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (b.lane != 0) {
				b.lane--;
			}
			System.out.println("lane" + b.lane);
			System.out.println("prevLane" + b.prevLane);
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
