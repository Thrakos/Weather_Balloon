import java.awt.Color;
import java.awt.Graphics;

// MR Z. REQUIRES SHINY OBJECTS

public class Cloud {

	int x;
	int y;

	int speed;
	int move;
	int floomp;
	boolean floop;

	int type;

	Color color;

	Color storm = new Color(120, 120, 120);
	Color rain = new Color(150, 150, 150);
	Color sun = new Color(255, 255, 255);

	Cloud(int x, int y, int type) {

		this.x = x;
		this.y = y;

		this.type = type;

		color = sun;

		speed = 1;
		move = 0;
		floomp = 0;
		floop = true;

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
			if (floomp < 40) {
				if (floomp % 4 == 0) {
					y++;
				}
				floomp++;
			} else {
				floop = false;
			}
		} else {
			if (floomp > 0) {
				if (floomp % 4 == 0) {
					y--;
				}
				floomp--;
			} else {
				floop = true;
			}
		}

	}

	void draw(Graphics g) {

		if (type == 1) {

			g.setColor(color);

			g.fillOval(x, y, 90, 90);
			g.fillOval(x + 50, y + 25, 75, 60);
			g.fillOval(x - 30, y + 10, 60, 50);
			g.fillOval(x + 65, y + 5, 40, 40);
			g.fillOval(x - 15, y + 45, 90, 50);

		}

	}

}
