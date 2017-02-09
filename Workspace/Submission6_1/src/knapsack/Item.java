package knapsack;

public class Item {



	private int weight;
	private int value;
	private int id;
	
	
	private static int NEXT_NUMBER = 1000;
	
	public Item (int weight, int value){
		this.weight=weight;
		this.value=value;
		this.id=NEXT_NUMBER++;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Item [weight=" + weight + ", value=" + value + ", ID=" + id + "]";
	}

	public int getWeight() {
		return weight;
	}


	public int getValue() {
		return value;
	}


	public int getId() {
		return id;
	}
}
