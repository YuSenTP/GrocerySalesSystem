package data;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "price", "quantity", "picFile", "onSale", "percentOff"}) //To change the save order to json
public class GroceryItem {

	private String name;
	private BigDecimal price;
	private int quantity;
	private String picFile;
	private boolean onSale;
	private double percentOff; // how many percent off
	
	public GroceryItem(){
		
	}
	
	public GroceryItem(String name, String price, String quantity, String picFile, boolean onSale, double percentOff) { 
		this.price = new BigDecimal(price); // Took in as a string as the double will truncate the .00 to .0
		this.name = name;
		this.quantity = Integer.valueOf(quantity);
		this.picFile = picFile;
		this.onSale = onSale;
		if (onSale){
			this.percentOff = percentOff;
		}
		else{
			this.percentOff = 0;
		}
		
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
	
	public boolean getOnSale() {
		return onSale;
	}

	public void setOnSale(boolean onSale) {
		this.onSale = onSale;
	}

	public double getPercentOff() {
		return percentOff;
	}

	public void setPercentOff(double percentOff) {
		this.percentOff = percentOff;
	}

//	public void subtractQuantity() { 
//		this.quantity--;
//	 }
//	
//	public void addQuantity() { 
//		this.quantity++;
//	 }

//	public GroceryItem copy() { 
//		return new GroceryItem(this.name, this.price, 1, this.picFile); //start from quantity 1
//	 } 

}
