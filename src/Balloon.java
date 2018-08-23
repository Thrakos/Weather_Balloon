import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Balloon {

	int lane;
	int prevLane;
	int laneChange;
	boolean movingLanes;

	boolean isAlive;

	int x;
	int y;
	int width;
	int height;

	int upDown;
	boolean down;

	Rectangle collisionBox;

	Balloon(int x, int y, int width, int height) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		upDown = 0;
		down = false;

		lane = 1;
		prevLane = 1;

		collisionBox = new Rectangle(x, y, width, height);

		isAlive = true;

	}

	void update() {

		if (prevLane != lane) {
			movingLanes = true;
		}

		if (!movingLanes) {
			if (upDown < 45 && !down) {
				if (upDown % 3 == 0) {
					y++;
				}
				upDown++;
				if (upDown == 45) {
					down = true;
				}
			} else if (upDown > 0 && down) {
				if (upDown % 3 == 0) {
					y--;
				}
				upDown--;
				if (upDown == 0) {
					down = false;
				}
			}
		} else {
			if (prevLane > lane) {
				if (laneChange < 175 / 3) {
					y += 3;
					laneChange++;
				} else {
					movingLanes = false;
					laneChange = 0;
				}
			} else if (prevLane < lane) {
				if (laneChange > -175 / 3) {
					y -= 3;
					laneChange--;
				} else {
					movingLanes = false;
					laneChange = 0;
				}
			}
		}

		collisionBox.setBounds(x, y, width, height);

		if (!movingLanes) {
			prevLane = lane;
		}

	}

	void draw(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		if (isAlive) {
			g2.setStroke(new BasicStroke(1));
			g.setColor(Color.WHITE);
			g.fillOval(x, y, width, height);
			g.drawArc((x + (width / 2)) - 5, y + height, 10, 40, 90, 180);
			g.drawArc((x + (width / 2)) - 5, y + (height * 2) - 8, 10, 40, 90, -180);
		}

	}

}
