/**
 * 
 */
package data;

import java.io.File;
import java.io.IOException;
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
	
	public DataStorage(){
		this.inventory = new Vector<GroceryItem>();
		this.orders = new Vector<Order>();
		this.staffs = new Vector<Staff>();
		this.managers = new Vector<Manager>();
		this.currentOrder = new Order();
		
		this.readFile();
	}
	
	public Vector<GroceryItem> getInventory(){
		return this.inventory;
	}
	
	public Vector<Order> getOrders(){
		return this.orders;
	}
	
	public Vector<Staff> getStaffs(){
		return this.staffs;
	}
	
	public Vector<Manager> getManagers(){
		return this.managers;
	}
	
	public Order getCurrentOrder(){
		return this.currentOrder;
	}
	
	public void addItem(){
		
	}
	
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
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public void setCurrentOrder(Order order) {
		// TODO Auto-generated method stub
        this.currentOrder = order;
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
