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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, MouseListener, KeyListener {

	Timer timer;

	final int LEVEL_SELECT = 0;
	final int GAME_STATE = 1;

	final int WIDTH = 1000;
	final int HEIGHT = 700;

	final int GROUND_HEIGHT = 40;

	ArrayList<Building> buildings = new ArrayList<Building>();
	
	int currentState;

	Balloon b;

	final int LANE_ONE = 175;
	final int LANE_TWO = 350;
	final int LANE_THREE = 525;

	int level;

	int pixelsMoved;
	
	
	// BG COLOR

	Color sky = new Color(171, 224, 255);
	Color storm = new Color(93, 143, 195);
	Color snow = new Color(213, 255, 255);
	Color rain = new Color(79, 113, 160);

	Color currentColor = sky;

	Color targetColor = sky;
	
	int cloud = 0;

	private int rChange = 0;
	private int gChange = 0;
	private int bChange = 0;

	final int BG_CHANGE_FRAMES = 30;

	//CLOUDS
	
	Cloud c1 = new Cloud(100, 20, 1);
	Cloud c2 = new Cloud(500, 80, 2);
	Cloud c3 = new Cloud(800, 40, 3);
	
	ArrayList<Cloud> clouds = new ArrayList<Cloud>();
	
	//CLOUD COLORS
	
	Color cloudStorm = new Color(120, 120, 120);
	Color cloudRain = new Color(150, 150, 150);
	Color cloudSun = new Color(255, 255, 255);
	
	Color cloudCol = cloudSun;
	
	// WEATHERS
	
	ArrayList<Weather> weather = new ArrayList<Weather>();

	Wind wind;
	JButton windUp;
	JButton windDown;
	int windPix = 0;
	boolean useWind = false;
	int returnWind = 0;

	Lightning lightning;
	Obstacle nullLight = new Obstacle();
	Obstacle lightninged = nullLight;
	boolean useLightning = false;
	int lightningPix = 0;
	
	Sun sun;
	boolean useSun = false;
	
	// OBSTACLES

	ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	ArrayList<Obstacle> obstaclesTwo = new ArrayList<Obstacle>();

	PineTree pineTree1;
	PineTree pineTree2;
	Bridge bridge1;
	Bridge bridge2;

	GamePanel(int width, int height) {
		
		timer = new Timer(1000 / 60, this);

		currentState = GAME_STATE; // change

		b = new Balloon(100, 325, 40, 50);

		level = 1;
		
		//CLOUDS
		
		clouds.add(c1);
		clouds.add(c2);
		clouds.add(c3);

		//WEATHERS
		
		wind = new Wind(2, 200, height - 60, 100, 40);
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

		lightning = new Lightning(2, 310, height - 60, 120, 40);
		lightning.isAlive = true;
		
		sun = new Sun(10, 440, height - 60, 100, 40);
		sun.isAlive = true;

		weather.add(wind);
		weather.add(lightning);
		weather.add(sun);

		pineTree1 = new PineTree(WIDTH, HEIGHT - 450, 200, 450);
		pineTree2 = new PineTree(WIDTH, HEIGHT - 450, 200, 450);
		bridge1 = new Bridge(WIDTH + 224, HEIGHT - 230, 100, 230);
		bridge2 = new Bridge(WIDTH + 224, HEIGHT - 230, 100, 230);

		obstacles.add(pineTree1);
		obstacles.add(pineTree2);
		obstacles.add(bridge1);
		obstacles.add(bridge2);
		
		obstaclesTwo.add(bridge1);
		obstaclesTwo.add(bridge2);

		for (int i = 0; i < weather.size(); i++) {
			Weather w = weather.get(i);
			w.addActionListener(this);
		}

		this.setLayout(null);
		this.add(wind);
		this.add(windUp);
		this.add(windDown);
		this.add(sun);

		this.add(lightning);

	}

	void gameStart() {
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//update
		if (e.getSource() == timer) {
			repaint();
			if (currentState == GAME_STATE) {
				updateGameState();
			}
		}

		//wind
		if (e.getSource() == wind) {
			if (!b.movingLanes && wind.amount > 0 && windPix == 0) {
				if (!useWind) {
					windUp.setVisible(true);
					windDown.setVisible(true);
					useWind = true;

					useLightning = false;
					for (int i = 0; i < obstacles.size(); i++) {
						Obstacle w = obstacles.get(i);
						if (w.lightning) {
							w.outline = false;
						}
					}

					updateColor(currentColor, sky, BG_CHANGE_FRAMES);

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

		
		//lightning!
		if (e.getSource() == lightning) {

			if (lightning.amount > 0) {
				if (!useLightning) {
					useLightning = true;

					updateColor(currentColor, storm, BG_CHANGE_FRAMES);

					for (int i = 0; i < obstacles.size(); i++) {
						Obstacle w = obstacles.get(i);
						if (w.lightning) {
							w.outline = true;
						}
					}
				} else {
					useLightning = false;

					updateColor(currentColor, sky, BG_CHANGE_FRAMES);

					for (int i = 0; i < obstacles.size(); i++) {
						Obstacle w = obstacles.get(i);
						if (w.lightning) {
							w.outline = false;
						}
					}
				}
			}
		}
		
		//Sun!
		if (e.getSource() == sun) {
			
			if (sun.amount > 0) {
//				
//				if (false) {
//					// SO CONFUZZLED
//				}
				
			}
			
		}

	}
	

	public void paintComponent(Graphics g) {
		if (currentState == GAME_STATE) {
			drawGameState(g);
		}
	}

	void drawGameState(Graphics g) {

		updateBg(currentColor, targetColor);

		g.setColor(currentColor);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(Color.gray);
		g.fillRect(0, HEIGHT - GROUND_HEIGHT, 1000, GROUND_HEIGHT);

		if (pixelsMoved % 60 == 0) {
			Building building = new Building();
			buildings.add(building);
		}
		
		//draw sun and clouds and whatnot
		
		g.setColor(new Color(155, 220, 20, 255));
		g.fillOval(812, 62, 100, 100);
		
		g.setColor(new Color(255, 220, 20, 150));
		g.fillOval(800, 50, 125, 125);
		
		for (int i = 0; i < clouds.size(); i++) {
			Cloud w = clouds.get(i);
			w.draw(g, cloudCol);
		}

		//stop drawing sun and clouds and whatnot
		//look at me commenting! Look at me GO!

		for (int i = 0; i < buildings.size(); i++) {
			Building w = buildings.get(i);
			w.draw(g);
		}

		for (int i = 0; i < obstacles.size(); i++) {
			Obstacle o = obstacles.get(i);
			o.draw(g);
		}

		b.draw(g);
		
		for (int i = 0; i < obstaclesTwo.size(); i++) {			
			Obstacle f = obstaclesTwo.get(i);
			f.drawTwo(g);
		}

	}

	void updateColor(Color c, Color d, int steps) {

		if (steps > 0) {

			rChange = (d.getRed() - c.getRed()) / steps;
			gChange = (d.getGreen() - c.getGreen()) / steps;
			bChange = (d.getBlue() - c.getBlue()) / steps;

		}

		targetColor = d;

	}

	void updateBg(Color c, Color d) {

		int newR = 0;
		int newB = 0;
		int newG = 0;

		if (Math.abs(c.getRed() - d.getRed()) >= Math.abs(rChange)) {
			newR = c.getRed() + rChange;
		} else {
			newR = d.getRed();
		}

		if (Math.abs(c.getGreen() - d.getGreen()) >= Math.abs(gChange)) {
			newG = c.getGreen() + gChange;
		} else {
			newG = d.getGreen();
		}

		if (Math.abs(c.getBlue() - d.getBlue()) >= Math.abs(bChange)) {
			newB = c.getBlue() + bChange;
		} else {
			newB = d.getBlue();
		}

		currentColor = new Color(newR, newG, newB);

	}

	void updateGameState() {

		if (level == 1) {
			levelOne();
		}

		if (returnWind > 1 || returnWind < -1) {
			returnWind = returnWind / 2;
			b.lane += (returnWind);
		} else if (returnWind != 0) {
			if (windPix == 350) {
				b.lane += (-returnWind);
				windPix = 0;
				returnWind = 0;
			} else {
				windPix++;
			}
		}

		if (lightninged == nullLight) {
			for (int i = 0; i < obstacles.size(); i++) {
				Obstacle w = obstacles.get(i);
				if (w.bam) {
					lightningPix++;
					lightninged = w;
				}
			}
		} else {
			if (lightningPix < 10) {
				lightningPix++;
			} else {

				lightningPix = 0;
				lightninged.isAlive = false;
				lightninged.x = -500;
				obstacles.remove(lightninged);
				useLightning = false;
				lightninged = nullLight;
				
				for (int i = 0; i < obstacles.size(); i++) {
					Obstacle w = obstacles.get(i);
					if (w.lightning) {
						w.outline = false;
					}
				}

			}
		}

		for (Cloud w : clouds) {
			w.update();
		}
		
		for (int i = 0; i < buildings.size(); i++) {
			Building w = buildings.get(i);
			if (w.x > -100) {
				w.update();
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

//		if (pixelsMoved == 100) {
//			pineTree1.isAlive = true;
//		}
//
		if (pixelsMoved == 600) {
			pineTree2.isAlive = true;
		}
		
		if (pixelsMoved == 100) {
			bridge1.isAlive = true;
		}
		
		if (pixelsMoved == 900) {
			pineTree1.isAlive = true;
		}
		
		if (pixelsMoved == 1200) {
			bridge2.isAlive = true;
		}
		
		if (pixelsMoved == 1700 && b.isAlive) {
			JOptionPane.showMessageDialog(null, "YOU BEAT THE EASIEST PUZZLE!");
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

		if (useLightning) {
			for (int i = 0; i < obstacles.size(); i++) {
				Obstacle w = obstacles.get(i);
				if (w.lightning) {
					if (e.getX() > w.x && e.getX() < (w.x + w.width)) {
						if (e.getY() > w.y && e.getY() < (w.y + w.height)) {
							w.bam = true;
							lightning.amount--;
							updateColor(currentColor, sky, BG_CHANGE_FRAMES);
						}
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
