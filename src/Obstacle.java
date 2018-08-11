import java.awt.Graphics;
import java.awt.Rectangle;

public class Obstacle {
	
	int x;
	int y;
	int width;
	int height;
	
	int speed;
	
	boolean lightning;
	
	boolean isAlive;
	
	Rectangle collisionBox;
	
	Obstacle() {
		
		collisionBox = new Rectangle(x, y, width, height);
		
		isAlive = false;
		
	}
	
	void update() {
		
	}
	
	void draw(Graphics g) {
		
	}
	
}
