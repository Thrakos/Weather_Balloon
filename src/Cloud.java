import java.awt.Color;
import java.awt.Graphics;

// MR Z. REQUIRES SHINY OBJECTS

public class Cloud {
	
	int x;
	int y;
	
	int speed;
	
	int type;
	
	Color color;
	
	Color storm = new Color(120, 120, 120);
	Color rain = new Color(150, 150, 150);
	Color sun = Color.white;
	
	Cloud(int x, int y, int type) {
		
		this.x = x;
		this.y = y;
		
		this.type = type;
		
		color = sun;
		
		speed = 1;
		
	}
	
	void update() {
		
	}
	
	void draw(Graphics g) {
		
		if (type == 1) {
			
			g.setColor(color);
			
			g.fillOval(x, y, 100, 50);
			g.fillOval(x + 50, y + 25, 75, 75);
			
		}
		
	}

}
