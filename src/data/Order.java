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

	public Order() { 
		this.groceryItems= new Vector<GroceryItem>();
		this.totalCost = BigDecimal.ZERO;
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

}
