import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

// MR Z. REQUIRES SHINY OBJECTS

public class Cloud {

	int x;
	int y;

	int speed;
	int move;
	int floomp;
	boolean floop;
	Random rand = new Random();

	int type;

	Cloud(int x, int y, int type) {

		this.x = x;
		this.y = y;

		this.type = type;

		speed = 1;
		move = 0;
		floomp = rand.nextInt(36);
		if (rand.nextInt(2) % 2 == 0) {
			floop = true;
		} else {
			floop = false;
		}

	}

	void update() {

		// move across screen
		if (move < 2) {
			x -= speed;
			move++;
		} else {
			move = 0;
		}

		if (x < -120) {
			x = 1050;
		}

		// move up and down
		if (floop) {
			if (floomp < 72) {
				if (floomp % 8 == 0) {
					y++;
				}
				floomp++;
			} else {
				floop = false;
			}
		} else {
			if (floomp > 0) {
				if (floomp % 8 == 0) {
					y--;
				}
				floomp--;
			} else {
				floop = true;
			}
		}

	}

	void draw(Graphics g, Color color) {

		g.setColor(color);

		if (type == 1) {

			g.fillOval(x, y, 90, 90);
			g.fillOval(x + 50, y + 25, 75, 60);
			g.fillOval(x - 30, y + 10, 60, 50);
			g.fillOval(x + 65, y + 5, 40, 40);
			g.fillOval(x - 15, y + 45, 90, 50);

		} else if (type == 2) {

			g.fillOval(x, y, 90, 70);
			g.fillOval(x + 60, y + 20, 60, 40);
			g.fillOval(x + 50, y, 50, 40);
			g.fillOval(x - 20, y + 10, 50, 30);
			g.fillOval(x - 10, y + 30, 40, 30);

		} else {

			g.fillOval(x, y, 100, 80);
			g.fillOval(x + 60, y + 20, 70, 50);
			g.fillOval(x + 55, y + 5, 50, 30);
			g.fillOval(x - 30, y, 80, 50);
			g.fillOval(x - 20, y + 30, 50, 40);

		}

	}

}
