import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PineTree extends Obstacle {

	PineTree(int x, int y, int width, int height) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		lightning = true;

		speed = 1;

	}

	void update() {
		
		if (isAlive) {
			x -= speed;
			collisionBox.setBounds(x, y, width, height);
		}

	}

	void draw(Graphics g) {

		if (isAlive) {
			g.setColor(new Color(0, 110, 0));
			g.fillRect(x, y, width, height - (height / 4));
			g.setColor(new Color(150, 85, 0));
			g.fillRect((x + (width)/2) - ((width/6)/2), y + (3*(height/4)), width/6, height / 4);
		}
	}

}
