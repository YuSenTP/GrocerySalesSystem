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
	
	public String[] getCategory() {
		return this.category.toArray(new String[this.category.size()]);
	}

//	public void setCategory(String[] category) { //Not needed
//		this.category = category;
//	}

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
