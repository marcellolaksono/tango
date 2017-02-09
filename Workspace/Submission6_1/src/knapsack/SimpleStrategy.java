package knapsack;

import java.util.ArrayList;
import java.util.List;

public class SimpleStrategy implements PackingStrategy {

	@Override
	public List<Item> packItems(int capacity, List<Item> items) {
		// TODO Auto-generated method stub
		List<Item> newitems = new ArrayList<>();
		int Totalx=0;
		for (Item element : items){
				Totalx+=element.getWeight();
				if(Totalx<=capacity)
				{

					newitems.add(element);
				}
				else{
					Totalx-=element.getWeight();
				}
				
			}


		
		return newitems;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		String out= "Simple Strategy";
		return out;
	}

}
