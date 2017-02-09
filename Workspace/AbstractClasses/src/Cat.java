
public class Cat extends Animal {
	
	/** Is this cat indoor or outdoor? */
	protected boolean indoor;
	
	/** Creates a new Cat with the given name and indoor/outdoor status */
	public Cat(String name, boolean indoor) {
		super(name);
		this.indoor = indoor;
	}

	/** Returns a cat noise */
	protected String getNoise() {
		return "meow";
	}
	
	/** Creates a nice string representation of this object */
	public String toString() {
		if (indoor) {
			return "An indoor cat called " + name;
		} else {
			return "An outdoor cat called " + name;
		}
	}

}
