package data;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "price", "quantity", "picFile", "onSale", "percentOff","onSalePrice", "category"}) //To change the save order to json
public class GroceryItem {

	private String name;
	private BigDecimal price;
	private int quantity;
	private String picFile;
	private boolean onSale;
	private double percentOff; // how many percent off
	private BigDecimal onSalePrice;
	private String category;
	
	//DO NOT Delete jackson needs this...
	public GroceryItem(){
		
	}
	
	public GroceryItem(String name, String price, String quantity, String picFile, boolean onSale, double percentOff, String category) { 
		this.price = new BigDecimal(price); // Took in as a string as the double will truncate the .00 to .0 -- Nvm lol
		this.price = this.price.setScale(2, BigDecimal.ROUND_HALF_UP); //Rounds and set to 2dp
		this.name = name;
		this.quantity = Integer.valueOf(quantity);
		this.picFile = picFile;
		this.onSale = onSale;
		if (onSale){
			this.percentOff = percentOff;

			BigDecimal salePercent = new BigDecimal(1 - this.percentOff);
			this.onSalePrice = this.price.multiply(salePercent); 
			this.onSalePrice = this.onSalePrice.setScale(2, BigDecimal.ROUND_HALF_UP); //Rounds and set to 2dp
		}
		else{
			this.percentOff = 0;
			this.onSalePrice = BigDecimal.ZERO;
		}
		this.category = category;
		
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

	public BigDecimal getOnSalePrice() {
		return onSalePrice;
	}

	public void setOnSalePrice(BigDecimal onSalePrice) {
		this.onSalePrice = onSalePrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
