package data;

import java.math.BigDecimal;
import java.util.Vector;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({"orderID", "totalCost", "groceryItems", "staffName"}) //To change the save order to json
public class Order {

	private Vector<GroceryItem> groceryItems;
	private BigDecimal totalCost;
	private int orderID;
	private String staffName; 


	
////	private static int counter =  + 1;
//	private static int counter;
	
	public Order() { 
		this.groceryItems= new Vector<GroceryItem>();
		this.totalCost = BigDecimal.ZERO;
//		this.orderID = counter++;
	 }
	
	public String getStaffName() {
	    return staffName;
	}

	public void setStaffName(String staffName) {
	    this.staffName = staffName;
	}

	public GroceryItem[] getGroceryItems() {
	 	 return this.groceryItems.toArray(new GroceryItem[this.groceryItems.size()]); 
	}
	
	public void addGroceryItem(GroceryItem item){
		this.groceryItems.add(item);
	}
	
	public void deleteGroceryItem(GroceryItem item){
		for (int i = 0; i < this.groceryItems.size(); i++){
			if (this.groceryItems.get(i).getName().equals(item.getName())){
				this.groceryItems.remove(i);
			}
		}
		
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

//	public BigDecimal calculateTotalCost() { //Gab to change --> total cost to be calculated in controller and passed to this order via settotalcost (setter)
//		totalCost = BigDecimal.ZERO; //!
//		for(int i = 0; i < groceryItems.size(); i++){
//			BigDecimal groceryCost = groceryItems.get(i).getPrice().multiply(BigDecimal.valueOf(groceryItems.get(i).getQuantity()));
//			totalCost = totalCost.add(groceryCost);
////			totalCost = totalCost.add(groceryItems.get(i).getPrice() * BigDecimal.valueOf(groceryItems.get(i).getQuantity())); // BigDecimal is Immutable
//		}
//		return totalCost;
//	 }

//	public void deleteGroceryItem(GroceryItem item) { //check this
////		groceryItems.get(index)
//		for (int i = 0; i < groceryItems.size(); i++){
//			if(groceryItems.get(i).getName() == item.getName()){
//				groceryItems.get(i).subtractQuantity();
//			}
//		}
//
//	 }

//	public void addGroceryItem(GroceryItem item) { 
//		boolean itemPresent = false;
//		int index = 0;
//		for (int i = 0; i < groceryItems.size(); i++){
//			if(groceryItems.get(i).getName() == item.getName()){
//				itemPresent = true;
//				index = i;
//			}
//		}
//		if (itemPresent == false){
//			groceryItems.add(item.copy());
//		}
//		else{
//			//add 1 to the grocery item
//			groceryItems.get(index).addQuantity(); 
//		}
//		
//	 } 

}
