import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

public class Obstacle {

	int x;
	int y;
	int width;
	int height;

	int speed;
	int slow;
	int moved;

	boolean lightning;

	boolean bam;

	boolean outline;

	boolean isAlive;

	Rectangle collisionBox;

	Obstacle() {

		isAlive = false;

		moved = 0;

	}

	void update() {

		if (isAlive) {
			if (moved % slow == 0) {
				x -= speed;
			}
			moved++;
		}

	}

	void draw(Graphics g) {

		if (isAlive) {
			Graphics2D g2 = (Graphics2D) g;

			if (outline) {
				g.setColor(new Color(255, 255, 0));
				g2.setStroke(new BasicStroke(2));
				g.drawRect(x, y, width, height);
			}
		}

	}

	void lightning() {

	}

}
