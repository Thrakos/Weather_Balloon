import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bridge extends Obstacle {

	Bridge(int x, int y, int width, int height) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		lightning = false;

		speed = 3;

		collisionBox = new Rectangle(x, y, width, height - 250);

	}

	void update() {

		super.update();

		collisionBox.setBounds(x, y, width, height - ((height / 3) + 50));

	}

	void draw(Graphics g) {

		super.draw(g);

		if (isAlive) {

			int[] xPoints = { x - 50, x, x };
			int[] yPoints = { y + height, y + height, y + 225 };

			g.setColor(new Color(255, 15, 15));
			g.fillPolygon(xPoints, yPoints, 3);
			g.fillRect(x, y + 225, width, 50);
			g.fillRect(x, y + 250, 100, height - 250);

		}

	}

	void drawTwo(Graphics g) {

		if (isAlive) {
			
			int[] xPoints = { x + width, x + width + 50, x + width };
			int[] yPoints = { y + height, y + height, y + 225 };

			g.setColor(new Color(255, 15, 15));
			g.fillPolygon(xPoints, yPoints, 3);
			g.fillRect(x + (width - 100), y + 250, 100, height - 250);

		}
		
		// soijd

	}

}
