import java.awt.Graphics;
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

	}

}
