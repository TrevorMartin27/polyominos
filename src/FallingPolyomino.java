
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
	
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public void setColor(int color) { this.color = color; }
	
	public boolean willCollide(PlayCanvas canvas) {
		return false;
	}
	
	public boolean tick(PlayCanvas canvas) {
		boolean will_collide = this.willCollide(canvas);
		
		this.y++;
		
		if(this.y > 20) {
			this.y -= 25;
		}

		return will_collide;
	}
}
