
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
		return false;
	}
	
	public boolean tick(PlayCanvas canvas) {
		boolean will_collide = this.willCollide(canvas);
		
		return will_collide;
	}
}
