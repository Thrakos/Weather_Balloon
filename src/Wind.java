
public class Wind extends Weather {
	
	Wind(int amount, int x, int y, int width, int height) {
		
		this.amount = amount;
		
		text = "wind";
		this.setText("wind");
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.setBounds(x, y, width, height);
		
		constant = false;
		
		isAlive = false;
				
	}
	
	void update() {
		
		super.update();
				
		if (isAlive) {
			this.setVisible(true);
		} else {
			this.setVisible(false);
		}
		
	}
	
}
