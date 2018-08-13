
public class Bridge extends Obstacle {

	Bridge(int x, int y, int width, int height) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		lightning = false;

		speed = 1;
		slow = 3;

	}
	
}
