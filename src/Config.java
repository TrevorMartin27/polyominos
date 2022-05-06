import java.util.LinkedList;

public class Config {
	private PolyominoFactory factory;

	private int polyomino_size = -1;
	private LinkedList<Polyomino> polyominos;

	public Config() {
		this.factory = new PolyominoFactory();

		this.set_polyomino_size(4);
	}

	public void set_polyomino_size(int size) {
		if(size != this.polyomino_size) {
			this.polyomino_size = size;

			this.polyominos = this.factory.generatePolyominos(size);
		}
	}

	public int get_polyomino_size() {
		return this.polyomino_size;
	}

	public LinkedList<Polyomino> get_polyominos() {
		return this.polyominos;
	}
}
