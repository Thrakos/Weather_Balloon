
public class Sun extends Weather {

	Sun(int amount, int x, int y, int width, int height) {
		
		this.amount = amount;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.setBounds(x, y, width, height);
		
		text = "sun";
		
		// I don't know what this variable does
		constant = false;
		
		isAlive = false;
		
	}
	
	void update() {
		
		super.update();
		
		if (isAlive) {
			// do stuff
		}
		
	}
	
}
