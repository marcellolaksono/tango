
public abstract class Animal {
	/** This animal's name */
	protected String name;
	
	/** Creates a new Animal with the given name */
	public Animal(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the noise that this animal makes.
	 */
	protected abstract String getNoise() throws UnsupportedOperationException;
	
	/**
	 * Makes this animal "speak" -- depends on what noise it makes.
	 */
	public void speak() {
		try {
			System.out.println(name + " says: " + getNoise());
		} catch (UnsupportedOperationException ex) {
			System.out.println("Oops: " + ex.getMessage());
		}
	}

}
