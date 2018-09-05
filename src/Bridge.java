import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bridge extends Obstacle {

	int backX;

	Bridge(int x, int y, int width, int height) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		backX = x;
	//	frontX = x;

		lightning = false;

		speed = 3;

		collisionBox = new Rectangle(x, 0, width, 350);

	}

	void update() {

		if (isAlive) {

			super.update();
			
	//		backX -= 2;

			collisionBox.setBounds(x, 0, width, 350);
			
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

		if (isAlive) {

//			int[] xPoints = { x + width, x + width + 50, x + width };
//			int[] yPoints = { y + height, y + height, y + 225 };
//
//			g.setColor(new Color(255, 15, 15));
//			g.fillPolygon(xPoints, yPoints, 3);
//			g.fillRect(x + (width - 100), y + 250, 100, height - 250);
			
	//		int[] bridgeX = { backX, backX - 10, x, x + width };
			int [] bridgeX = {  };
			int[] bridgeY = { y - height, y - height, 700, 700 };
			
			g.setColor(new Color(255, 15, 15));
			g.fillPolygon(bridgeX, bridgeY, 4);
			
		}

	}

}
