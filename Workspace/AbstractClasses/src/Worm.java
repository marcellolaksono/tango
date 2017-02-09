
public class Worm extends Animal {
	
	public Worm() {
		super("");
	}

	protected String getNoise() {
		throw new UnsupportedOperationException("Worms don't make noise");
	}

}
