import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bridge extends Obstacle {

	int backX;
	int frontX;

	Bridge(int x, int y, int width, int height) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		backX = x - 250;
		frontX = x + 250;

		lightning = false;

		speed = 3;

		collisionBox = new Rectangle(x, 0, width, 450);

	}

	void update() {

		if (isAlive) {

			super.update();

			frontX -= 1;
			frontX -= speed;
			backX += 1;
			backX -= speed;

			collisionBox.setBounds(x, 0, width, 450);

		}

	}

	void draw(Graphics g) {

		super.draw(g);

		if (isAlive) {

//			int[] xPoints = { x - 50, x, x };
//			int[] yPoints = { y + height, y + height, y + 225 };
//
//			g.setColor(new Color(255, 15, 15));
//			g.fillPolygon(xPoints, yPoints, 3);
//			g.fillRect(x, y + 225, width, 50);
//			g.fillRect(x, y + 250, 100, height - 250);

			g.setColor(new Color(230, 0, 0));
			g.fillRect(x, y, width, height);

		}

	}

	void drawTwo(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		if (isAlive) {

//			int[] xPoints = { x + width, x + width + 50, x + width };
//			int[] yPoints = { y + height, y + height, y + 225 };
//
//			g.setColor(new Color(255, 15, 15));
//			g.fillPolygon(xPoints, yPoints, 3);
//			g.fillRect(x + (width - 100), y + 250, 100, height - 250);

			g2.setStroke(new BasicStroke(3));

			g.setColor(new Color(255, 15, 15));

			int[] bridgeX = { backX + (width / 2) + 2, backX + (width / 2) - 2, frontX - (width / 2) - 2,
					frontX + width + (width / 2) };
			int[] bridgeY = { y - height + 100, y - height + 100, 600, 600 };

			// actual bridge
			g.fillPolygon(bridgeX, bridgeY, 4);
			
			// front addition to bridge
			int[] bridgeX2 = { frontX - (width / 2) - 2, frontX + width + (width / 2),
					frontX + width + (width / 2), frontX - (width / 2) - 2 };
			int[] bridgeY2 = { 600, 600, 700, 700 };

			g.fillPolygon(bridgeX2, bridgeY2, 4);

			// g.fillRect(frontX - (width/2) - 1, 600, (width * 2) + 1, 100);

			g.setColor(new Color(255, 20, 20));
			g.fillRect(x, y - 250, 10, 250);
			g.fillRect(x + (width - 10), y - 250, 10, 250);

			// x, y, width, height, start angle, arc angle
			// > is 0, ^ is 90, < is 180, v is 270, arcAngle is relative to startAngle

			// g2.drawArc(x, y, width, height, startAngle, arcAngle);

//			if (x > 500) {
//				g.drawOval(backX, backX, frontX, backX);
//			} else {
//				
//			}

			// g.fillPolygon(bridgeX, bridgeY, 4);

		}

	}

}