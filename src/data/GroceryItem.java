package data;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "price", "quantity", "picFile"}) //To change the save order to json
public class GroceryItem {

	private String name;
	private BigDecimal price;
	private int quantity;
	private String picFile;
	
	public GroceryItem(){
		
	}
	
	public GroceryItem(String name, double price, int quantity, String picFile) { 
		this.price = BigDecimal.valueOf(price);
		this.name = name;
		this.quantity = quantity;
		this.picFile = picFile;
	 }
	
	public GroceryItem(String name, BigDecimal price, int quantity, String picFile) { 
		this.price = price;
		this.name = name;
		this.quantity = quantity;
		this.picFile = picFile;
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

	public String getPicFile() {
		return picFile;
	}

	public void setPicFile(String picFile) {
		this.picFile = picFile;
	}

	public void subtractQuantity() { 
		this.quantity--;
	 }
	
	public void addQuantity() { 
		this.quantity++;
	 }

	public GroceryItem copy() { 
		return new GroceryItem(this.name, this.price, 1, this.picFile); //start from quantity 1
	 } 

}
