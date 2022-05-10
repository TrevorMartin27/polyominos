
public class FallingPolyomino extends Polyomino {
	private int x, y, color;
	
	public FallingPolyomino(Polyomino parent) {
		super(parent);
		
		this.x = 0;
		this.y = 0;
		this.color = 1;
	}
	
	public int get_x() { return this.x; }
	public int get_y() { return this.y; }
	public int get_color() { return this.color; }
	
	public boolean will_collide(PlayCanvas canvas) {
		return false;
	}
	
	public boolean tick(PlayCanvas canvas) {
		boolean will_collide = this.will_collide(canvas);
		
		return will_collide;
	}
}
