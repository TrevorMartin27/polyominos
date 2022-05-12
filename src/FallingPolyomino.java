
public class FallingPolyomino extends Polyomino {
	private int x, y, color;
	
	public FallingPolyomino(Polyomino parent) {
		super(parent);
		
		this.x = 0;
		this.y = 0;
		this.color = 1;
	}
	
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public int getColor() { return this.color; }
	
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
	
	public boolean tick(PlayCanvas canvas) {
		boolean will_collide = this.willCollide(canvas);
		
		if(!will_collide) {
			this.y++;
		}

		return will_collide;
	}
}
