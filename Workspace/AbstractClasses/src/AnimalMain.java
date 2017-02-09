
public class AnimalMain {

	public static void main(String[] args) {
		Animal cat = new Cat("Fluffy", true);
		Animal cat2 = new Cat("Scarface Claw", false);
		
		// System.out.println(cat);
		// System.out.println(cat2);
		
		cat.speak();
		cat2.speak();
		
		Worm w = new Worm();
		w.speak();
	}

}
