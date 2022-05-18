public class FallingPolyomino extends Polyomino {
	private int x, y, color;

	public FallingPolyomino(Polyomino parent, int color) {
		super(parent);

		this.color = color;
	}

	public FallingPolyomino(Polyomino parent) {
		super(parent);
		
		this.x = 0;
		this.y = 0;
		this.color = 1;
	}
	
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public int getColor() { return this.color; }
	
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public void setColor(int color) { this.color = color; }
	
	public FallingPolyomino rotate() {
		Polyomino rotated = super.rotate();
		
		FallingPolyomino res = new FallingPolyomino(rotated);
		res.setX(this.x);
		res.setY(this.y);
		res.setColor(this.color);
		
		return res;
	}
	
	public boolean willCollide(PlayCanvas canvas) {
		if(this.y + this.getHeight() >= 20) {
			return true;
		}
		for(int lx = 0; lx < this.getWidth(); lx++) {
			for(int ly = this.getHeight() - 1; ly >= 0; ly--) {
				if(this.getCell(lx, ly)) {
					if(canvas.getCell(lx + this.x, ly + this.y + 1) != 0) {
						return true;
					}
					continue;
				}
			}
		}
		
		return false;
	}
	
	public boolean isColliding(PlayCanvas canvas) {
		if((this.x + this.getWidth()) > 10) { // Make constant
			return true;
		}

		for(int y = 0; y < this.getHeight(); y++) {
			for(int x = 0; x < this.getWidth(); x++) {
				if(
					this.getCell(x, y) &&
					canvas.getCell(x + this.x, y + this.y) != 0
				) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean willCollideHorizontal(PlayCanvas canvas, int delta) {
		if(delta == 0) return false;

		int lx = delta > 0 ? this.getWidth() - 1 : 0;
		for(int y = 0; y < this.getHeight(); y++) {
			if(this.getCell(lx, y)) {
				int gx = lx + this.x + delta;
				if((gx < 0) || (gx >= 10)) { // TODO: Make 10 constant
					return true;
				}

				if(canvas.getCell(gx, this.y + y) != 0) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean moveHorizontal(PlayCanvas canvas, int delta) {
		if(!this.willCollideHorizontal(canvas, delta)) {
			this.x += delta;

			return true;
		}
		return false;
	}

	public void writeToBoard(PlayCanvas canvas) {
		for(int y = 0; y < this.getHeight(); y++) {
			int gy = this.y + y;
			for(int x = 0; x < this.getWidth(); x++) {
				int gx = this.x + x;

				if(this.getCell(x, y)) {
					canvas.setCell(gx, gy, this.getColor());
				}
			}
		}
	}

	public boolean tick(PlayCanvas canvas) {
		boolean will_collide = this.willCollide(canvas);
		
		if(!will_collide) {
			this.y++;
		} else {
			this.writeToBoard(canvas);
		}

		return will_collide;
	}
}
