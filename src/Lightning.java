
public class Lightning extends Weather {
	
	Lightning(int amount, int x, int y, int width, int height) {
		
		this.amount = amount;
		
		text = "lightning";
		this.setText("lightning");
		
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
