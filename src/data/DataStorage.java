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
	private Vector<Order> staffOrders;
	private Vector<User> users;
	private Order currentOrder;
	private User currentUser;
	private Vector<String> category;
	
	public DataStorage(){
		this.inventory = new Vector<GroceryItem>();
		this.orders = new Vector<Order>();
		this.users = new Vector<User>();
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
	
	public User[] getUsers(){
		return this.users.toArray(new User[this.users.size()]);
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
	
	public Order[] getStaffOrders(String staffName) {
	    // Initialize staffOrders if it's null
	    if (this.staffOrders == null) {
	        this.staffOrders = new Vector<Order>();
	    }
	    
	    // Clear previous contents
	    this.staffOrders.clear();
	    
	    for (int i = 0; i < this.orders.size(); i++) {
	        Order order = this.orders.get(i);
	        if (order.getStaffName() != null && order.getStaffName().equals(staffName)) {
	            staffOrders.add(order);
	        }
	    }
	    return staffOrders.toArray(new Order[staffOrders.size()]);
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
			
			BigDecimal salePercent = new BigDecimal(1 - item.getPercentOff());
			BigDecimal onSalePrice = item.getPrice().multiply(salePercent); 
			onSalePrice = onSalePrice.setScale(2, BigDecimal.ROUND_HALF_UP); //Rounds and set to 2dp
			item.setOnSalePrice(onSalePrice);
		}
		else{
			item.setOnSale(onSale);
			item.setPercentOff(0);
			item.setOnSalePrice(BigDecimal.ZERO);
		}
		
		//Category
		item.setCategory(category);
	}
	
	
	public void deleteGroceryItem(GroceryItem item) { 
		File picFile = new File(item.getPicFile());
		picFile.delete();
		this.inventory.remove(item);
	 }
	
	public void editCategory(int originalIndex, int endIndex, String Name){
		String originalCat = this.category.get(originalIndex + 1);
		if (originalIndex == endIndex){
			this.category.set(originalIndex + 1, Name); //Account for "All" at index 0
		}
		else{
			this.category.remove(originalIndex + 1);
			this.category.add(endIndex + 1, Name);
		}
		
		for (GroceryItem g: this.inventory){
//			System.out.println(c);
			if (g.getCategory().equals(originalCat)){
				g.setCategory(Name);
			}
		}
	}
	
	public void newCategory(int cIndex, String name) {
		this.category.add(cIndex + 1, name);
		
	}
	
	public void deleteCategory(String name){
		this.category.removeElement(name);
	}
	
	public boolean categoryInUse(String name) {
		for (GroceryItem g: this.inventory){
//			System.out.println(c);
			if (g.getCategory().equals(name)){
				return true;
			}
		}
		return false;
	}
	
	public void reassignCategory(String originalCategory, String selectedCat) {
		for (GroceryItem g: this.inventory){
//			System.out.println(c);
			if (g.getCategory().equals(originalCategory)){
				g.setCategory(selectedCat);
			}
		}
		
	}
	
	public void setCurrentOrder(Order order) {
        this.currentOrder = order;
	}
	
	public void clearCurrentOrderItems(){
		this.currentOrder.setGroceryItems(new Vector<GroceryItem>());
	}

	public void storeUser(User u){
		this.users.add(u); 
		
		for (int i=0; i<users.size(); i++)
		{
			User temp = users.get(i);
		}
	}
	
	 public void deleteUser(User user) {
	        File picFile = new File(user.getPicFile());
	        picFile.delete();
	        this.users.remove(user);
	    }
	
	public User getCurrentUser(){
		return this.currentUser;
	}
	
	
	public void setCurrentUser(User user) {
	    this.currentUser = user;
	}
	
	
	public void readFile(){ // Method to read from JSON file
		ObjectMapper objectMapper = new ObjectMapper();
		
		try{
			this.users = objectMapper.readValue(
	                 new File("./src/database/user.json"),
	                 new TypeReference<Vector<User>>(){}
	         );
	        
	         this.inventory = objectMapper.readValue(
	                 new File("./src/database/inventory.json"),
	                 new TypeReference<Vector<GroceryItem>>(){}
	         );
	         
	         this.orders = objectMapper.readValue(
	                 new File("./src/database/order.json"),
	                 new TypeReference<Vector<Order>>(){}
	         );
	         
	         String temp = "", read = "";
	         BufferedReader in = new BufferedReader(new FileReader("./src/database/category.txt"));
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
    	ObjectMapper objectMapper = new ObjectMapper(); 
    	try {
    		//! TO-CHANGE filePath
    		objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("./src/database/inventory.json"), this.inventory);
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("./src/database/user.json"), this.users);
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("./src/database/order.json"), this.orders);
			
			BufferedWriter out = new BufferedWriter(new FileWriter("src/database/category.txt"));
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



}
