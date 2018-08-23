
public class Bridge extends Obstacle {

	Bridge(int x, int y, int width, int height) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		lightning = false;

		speed = 3;

	}
	
	void update() {
		
		super.update();
		
	}
	
}
