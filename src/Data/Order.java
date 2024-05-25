package Data;

import java.math.BigDecimal;
import java.util.Vector;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"orderID", "totalCost", "groceryItems"}) //To change the save order to json
public class Order {

	private Vector<GroceryItem> groceryItems;
	private BigDecimal totalCost;
	private int orderID;
	
	private static int counter = Main.orders.size() + 1;
	
	public Order() { 
		this.groceryItems= new Vector();
		this.totalCost = BigDecimal.ZERO;
		this.orderID = counter++;
	 }

	public Vector<GroceryItem> getGroceryItems() {
	 	 return groceryItems; 
	}

	public void setGroceryItems(Vector<GroceryItem> groceryItems) { 
		 this.groceryItems = groceryItems; 
	}
	
	public BigDecimal getTotalCost() {
	 	 return totalCost; 
	}

	public void setTotalCost(BigDecimal totalCost) { 
		 this.totalCost = totalCost; 
	}

	public int getOrderID() {
	 	 return orderID; 
	}

	public void setOrderID(int orderID) { 
		 this.orderID = orderID; 
	}

//	public int getCounter() {
//	 	 return counter; 
//	}

//	public void setCounter(int counter) { 
//		 this.counter = counter; 
//	}

	public BigDecimal calculateTotalCost() { 
		totalCost = BigDecimal.ZERO; //!
		for(int i = 0; i < groceryItems.size(); i++){
			totalCost = totalCost.add(groceryItems.get(i).getPrice()); // BigDecimal is Immutable
		}
		return totalCost;
	 }

	public void deleteGroceryItem(GroceryItem item) { 
		groceryItems.remove(item);
	 }

	public void addGroceryItem(GroceryItem item) { 
		groceryItems.add(item.copy());
	 } 

}
