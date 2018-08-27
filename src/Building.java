import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class Building {

	final int STORY_HEIGHT = 100;

	int x;
	int y;

	int speed;

	int stories;

	Color color;
	Random rand;

	boolean top;
	
	int topType;

	Building() {

		y = 700 - 40;
		x = 1000;

		speed = 2;

		rand = new Random();

		stories = rand.nextInt(5) + 1;

		top = rand.nextBoolean();

		int col = rand.nextInt(5);
		col *= 10;

		color = new Color(110 + col, 110 + col, 110 + col);
		
		if (stories == 1) {
			topType = 2;
		} else {
			topType = rand.nextInt(3);
		}

	}

	void update() {
		
		x -= speed;

	}

	void draw(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;

		g.setColor(color);
				
		for (int i = 0; i < stories; i++) {

			g.fillRect(x, y - (STORY_HEIGHT * (i + 1)), 100, STORY_HEIGHT);

		}

		if (top) {

			if (topType == 0) {
				g2.setStroke(new BasicStroke(5));
				g.drawLine(x + 50, y - (stories * STORY_HEIGHT), x + 50, y - (stories * STORY_HEIGHT) - 75);
			} else if (topType == 1) {
				g.fillOval(x, y - (stories * STORY_HEIGHT) - 50, 100, 100);
			} else if (topType == 2) {
				int[] xPoints = { x, x + 100, x + 50 };
				int[] yPoints = { y - (stories * STORY_HEIGHT), y - (stories * STORY_HEIGHT),
						y - (stories * STORY_HEIGHT) - 50 };
				g.fillPolygon(xPoints, yPoints, 3);
			}

		}

	}

}