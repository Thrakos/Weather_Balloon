import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

public class PineTree extends Obstacle {

	PineTree(int x, int y, int width, int height) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		lightning = true;

		bam = false;

		speed = 3;

		collisionBox = new Rectangle(x + 30, y, (x + height) - 30, height);

	}

	void update() {

		if (isAlive) {

			super.update();

			collisionBox.setBounds(x + 30, y, (x + height) - 30, height);

		}

	}

	void draw(Graphics g) {

		super.draw(g);

		if (isAlive) {

			g.setColor(new Color(0, 110, 0));
			int[] xPoints = { x + (width / 2), x + width, x };
			int[] yPoints = { y, y + (height / 4) * 3, y + (height / 4) * 3 };
			g.fillPolygon(xPoints, yPoints, 3);
			g.setColor(new Color(150, 85, 0));
			g.fillRect((x + (width) / 2) - ((width / 6) / 2), y + (3 * (height / 4)), width / 6, height / 4);

			Graphics2D g2 = (Graphics2D) g;

			if (bam) {
				g.setColor(new Color(240, 240, 0));
				g2.setStroke(new BasicStroke(10));
				g.drawLine(x + (width / 2), y + (height / 2), (x + (width / 2)) - 50, y - 100);
				g.drawLine((x + (width / 2)) - 50, y - 100, (x + (width / 2)) + 100, y - 90);
				g.drawLine((x + (width / 2)) + 100, y - 90, (x + (width / 2)) + 50, y - 90 - 325);
			}
		}

	}

}
