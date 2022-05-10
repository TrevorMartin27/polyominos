import java.util.LinkedList;

public class Config {
	private PolyominoFactory factory;

	private int polyomino_size = -1;
	private LinkedList<Polyomino> polyominos;

	public Config() {
		this.factory = new PolyominoFactory();

		this.setPolyominoSize(4);
	}

	public void setPolyominoSize(int size) {
		if(size != this.polyomino_size) {
			this.polyomino_size = size;

			this.polyominos = this.factory.generatePolyominos(size);
		}
	}

	public int getPolyominoSize() {
		return this.polyomino_size;
	}

	public LinkedList<Polyomino> getPolyominos() {
		return this.polyominos;
	}
}
