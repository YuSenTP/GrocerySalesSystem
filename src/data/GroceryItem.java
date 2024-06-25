package data;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "price", "quantity" }) //To change the save order to json
public class GroceryItem {

	private String name;
	private BigDecimal price;
	private int quantity;
	
	public GroceryItem(){
		
	}
	
	public GroceryItem(String name, double price, int quantity) { 
		this.price = BigDecimal.valueOf(price);
		this.name = name;
		this.quantity = quantity;
	 }
	
	public GroceryItem(String name, BigDecimal price, int quantity) { 
		this.price = price;
		this.name = name;
		this.quantity = quantity;
	 }

	public String getName() {
	 	 return name; 
	}

	public void setName(String name) { 
		 this.name = name; 
	}

	public BigDecimal getPrice() {
	 	 return price; 
	}

	public void setPrice(BigDecimal price) { 
		 this.price = price; 
	}

	public int getQuantity() {
	 	 return quantity; 
	}

	public void setQuantity(int quantity) { 
		 this.quantity = quantity; 
	}

	public void subtractQuantity() { 
		this.quantity--;
	 }
	
	public void addQuantity() { 
		this.quantity++;
	 }

	public GroceryItem copy() { 
		return new GroceryItem(this.name, this.price, 1); //start from quantity 1
	 } 

}
