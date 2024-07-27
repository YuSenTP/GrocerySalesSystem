/**
 * 
 */
package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Vector;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Yu Sen
 *
 */

public class DataStorage { //!! TO_CHANGE -- filePaths
	
	private Vector<GroceryItem> inventory;
	private Vector<Order> orders;
	private Vector<Staff> staffs;
	private Vector<Manager> managers;
//	Vector<User> storage = new Vector<User>();
	private Order currentOrder;
//	private String[] category = {"All", "Fruits", "Vegetables", "Meat", "Dairy", "Others"};
	private Vector<String> category;
	
	public DataStorage(){
		this.inventory = new Vector<GroceryItem>();
		this.orders = new Vector<Order>();
		this.staffs = new Vector<Staff>();
		this.managers = new Vector<Manager>();
		this.currentOrder = new Order();
		this.category = new Vector<String>();
		
		this.readFile();
	}
	
	public GroceryItem[] getInventory(){
		return this.inventory.toArray(new GroceryItem[this.inventory.size()]);
	}
	
	public Order[] getOrders(){
		return this.orders.toArray(new Order[this.orders.size()]);
	}
	
	public Staff[] getStaffs(){
		return this.staffs.toArray(new Staff[this.staffs.size()]);
	}
	
	public Manager[] getManagers(){
		return this.managers.toArray(new Manager[this.managers.size()]);
	}
	
	public Order getCurrentOrder(){
		return this.currentOrder;
	}
	
	public void addOrder(Order order){
		this.orders.add(order);
	}
	
	public String[] getCategory() {
		return this.category.toArray(new String[this.category.size()]);
	}

	public void addCategory(String category) {
		this.category.add(this.category.size()-2, category);
	}

	public void createGroceryItem(GroceryItem item){
		this.inventory.add(item);
	}
	
	public void editGroceryItem(GroceryItem item, String name, String price, String quantity, String picFile, boolean onSale, String percentOff, String category){
		//Name
		item.setName(name);
		
		//Price
		BigDecimal tempPrice = new BigDecimal(price);
		tempPrice = tempPrice.setScale(2, BigDecimal.ROUND_HALF_UP); //Rounds and set to 2dp
		item.setPrice(tempPrice);
		
		//Quantity
		item.setQuantity(Integer.valueOf(quantity));
		
		//PicFile
		item.setPicFile(picFile);
		
		//Sale
		if (onSale){
			item.setOnSale(onSale);
			item.setPercentOff(Double.valueOf(percentOff)/100);
		}
		else{
			item.setOnSale(onSale);
			item.setPercentOff(0);
		}
		
		//Category
		item.setCategory(category);
	}
	
//	public void editGroceryItemPrice(GroceryItem item, String price){
////		int index = this.inventory.indexOf(item);
////		GroceryItem temp = this.inventory.get(index);
//		
//		BigDecimal tempPrice = new BigDecimal(price);
//		tempPrice = tempPrice.setScale(2, BigDecimal.ROUND_HALF_UP); //Rounds and set to 2dp
//		item.setPrice(tempPrice);
//		
////		this.price = new BigDecimal(price); // Took in as a string as the double will truncate the .00 to .0 -- Nvm lol
////		this.price = this.price.setScale(2, BigDecimal.ROUND_HALF_UP); //Rounds and set to 2dp
//		
////		item.setPrice(new BigDecimal(price));
//	}
//	
//	public void editGroceryItemName(GroceryItem item, String name) { 
////		int index = this.inventory.indexOf(item);
////		GroceryItem temp = this.inventory.get(index);
//		item.setName(name);
//	 }
//	
//	public void editGroceryItemQuantity(GroceryItem item, String quantity) { 
////		int index = this.inventory.indexOf(item);
////		GroceryItem temp = this.inventory.get(index);
//		item.setQuantity(Integer.valueOf(quantity));
//	 }
//	
//	public void editGroceryItemSale(GroceryItem item, boolean onSale, String percentOff) { 
////		int index = this.inventory.indexOf(item);
////		GroceryItem temp = this.inventory.get(index);
//		if (onSale){
//			item.setOnSale(onSale);
//			item.setPercentOff(Double.valueOf(percentOff)/100);
//		}
//		else{
//			item.setOnSale(onSale);
//			item.setPercentOff(0);
//		}
//	 }
//	
//	public void editGroceryItemCategory(GroceryItem item, String category) { 
////		int index = this.inventory.indexOf(item);
////		GroceryItem temp = this.inventory.get(index);
////		System.out.println(category);
//		item.setCategory(category);
//	}
	
	public void deleteGroceryItem(GroceryItem item) { 
		File picFile = new File(item.getPicFile());
		picFile.delete();
		this.inventory.remove(item);
	 }
	
//	public void changePicPath(String path, GroceryItem item){
////		int index = this.inventory.indexOf(item);
////		this.inventory.get(index).setPicFile(path);
//		item.setPicFile(path);
//	}
	
	
	public void readFile(){ // Method to read from JSON file
		ObjectMapper objectMapper = new ObjectMapper();
		
		try{
			this.managers = objectMapper.readValue(
	                 new File("./src/JsonReadWrite/startManager.json"),
	                 new TypeReference<Vector<Manager>>(){}
	         );
			
			this.staffs = objectMapper.readValue(
	                 new File("./src/JsonReadWrite/startStaff.json"),
	                 new TypeReference<Vector<Staff>>(){}
	         );
	        
	         this.inventory = objectMapper.readValue(
	                 new File("./src/JsonReadWrite/startinventory.json"),
	                 new TypeReference<Vector<GroceryItem>>(){}
	         );
	         
	         this.orders = objectMapper.readValue(
	                 new File("./src/JsonReadWrite/order.json"),
	                 new TypeReference<Vector<Order>>(){}
	         );

	         //Loops through staffs Vector and set each object index to staff
//	         for (Staff staff : this.staffs) { //set new instance of order to each staff -- current state of order: null
////	             System.out.println(user.getName());
//	        	 staff.start();
	        	 
//	         } 
	         
	         String temp = "", read = "";
	         BufferedReader in = new BufferedReader(new FileReader("./src/JsonReadWrite/category.txt"));
	         while ((read = in.readLine()) != null){
	        	 temp += read;
	         }
	         in.close();
	         String[] cc = temp.split(";");
	         for (int i = 0; i < cc.length; i++){
	        	 this.category.add(cc[i]);
	         }
	         
			
		}catch (IOException e) {
	         e.printStackTrace();
	     }
	}
	
	public void writeFile(){  //Method to save to JSON file
//		for (int i = 0; i < this.staffs.size(); i++){ //Clear staff order object to null
//			this.staffs.get(i).setOrder(null);
//		}
    	ObjectMapper objectMapper = new ObjectMapper(); 
    	try {
    		//! TO-CHANGE filePath
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("./src/JsonReadWrite/inventory.json"), this.inventory);
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("./src/JsonReadWrite/manager.json"), this.managers);
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("./src/JsonReadWrite/staff.json"), this.staffs);
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("./src/JsonReadWrite/order.json"), this.orders);
			
			BufferedWriter out = new BufferedWriter(new FileWriter("src/JsonReadWrite/category.txt"));
			for (int i = 0; i < this.category.size() - 1; i++){
				out.write(this.category.get(i) + ";");
				out.newLine();
			}
			out.write(this.category.get(this.category.size() - 1));
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public void setCurrentOrder(Order order) {
		// TODO Auto-generated method stub
        this.currentOrder = order;
	}
	
	public void clearCurrentOrderItems(){
		this.currentOrder.setGroceryItems(new Vector<GroceryItem>());
	}

//	public User getUser(String n) {
//		for (int i = 0 ; i <storage.size(); i++)
//		{
//			User temp = storage.get(i);
//			if (temp.getName().equals(n)){
//				return temp;
//			}
//		}
//		return null;
//	}

}
