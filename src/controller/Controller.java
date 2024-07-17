/**
 * 
 */
package controller;

import java.math.BigDecimal;
import java.util.Vector;

import data.DataStorage;
import data.GroceryItem;
import data.Main;
import data.Manager;
import data.Order;
import data.Staff;
import data.User;

/**
 * @author Yu Sen
 *
 */
public class Controller {
	public DataStorage ds; //private or public?
	
	public Controller(){
		 this.ds = new DataStorage();
	}
	
	public Order[] getOrders(){
		return ds.getOrders();
	}
	
	public GroceryItem[] getInventory(){
		return ds.getInventory();
	}
	
	public void saveAll(){
		this.ds.writeFile();
	}
	
	public String[] getCategory(){
		return this.ds.getCategory();
	}
	
	public void addCategory(String category){
		this.ds.addCategory(category);
	}
	
	//Manager
	public void createGroceryItem(String itemName, String price, String quantity, String picFile, boolean onSale, String percentOff, String category) { 
		GroceryItem temp = new GroceryItem(itemName, price, quantity, picFile, onSale, Double.valueOf(percentOff) / 100, category);
//		this.getInventory().add(temp);
		this.ds.createGroceryItem(temp);
	 }
	//Manager
	public void editGroceryItemPrice(GroceryItem item, String price) { 
//		int index = this.getInventory().indexOf(item); //IMPORTANT TO CHANGE This does not adhere to MVC, can only edit in data storage
//		GroceryItem temp = this.getInventory().get(index);
//		temp.setPrice(new BigDecimal(price));
		this.ds.editGroceryItemPrice(item, price);
	 }
	//Manager
	public void editGroceryItemName(GroceryItem item, String name) { 
//		int index = this.getInventory().indexOf(item);
//		GroceryItem temp = this.getInventory().get(index);
//		temp.setName(name);
		this.ds.editGroceryItemName(item, name);
	 }
	//Manager
	public void editGroceryItemQuantity(GroceryItem item, String quantity) { 
//		int index = this.getInventory().indexOf(item);
//		GroceryItem temp = this.getInventory().get(index);
//		temp.setQuantity(Integer.valueOf(quantity));
		this.ds.editGroceryItemQuantity(item, quantity);
	 }
	//Manager
	public void editGroceryItemSale(GroceryItem item, boolean onSale, String percentOff) { 
//		int index = this.getInventory().indexOf(item);
//		GroceryItem temp = this.getInventory().get(index);
//		if (onSale){
//			temp.setOnSale(onSale);
//			temp.setPercentOff(Double.valueOf(percentOff)/100);
//		}
//		else{
//			temp.setOnSale(onSale);
//			temp.setPercentOff(0);
//		}
		this.ds.editGroceryItemSale(item, onSale, percentOff);
	 }
	//Manager
	public void editGroceryItemCategory(GroceryItem item, String category) { 
//		int index = this.getInventory().indexOf(item);
//		GroceryItem temp = this.getInventory().get(index);
////		System.out.println(category);
//		temp.setCategory(category);
		this.ds.editGroceryItemCategory(item, category);
	}
	
	//Manager
	public void deleteGroceryItem(GroceryItem item) { 
//		this.getInventory().remove(item);
		this.ds.deleteGroceryItem(item);
	 }
	//Manager
	public BigDecimal getTotalOrderCost(){
		Order[] tempOrders = this.getOrders();
		BigDecimal totalSales = BigDecimal.ZERO;
		
		if (tempOrders.length == 0){
			return BigDecimal.ZERO;
		}
		else{
			for(int i = 0; i < tempOrders.length; i++){
				Order order = tempOrders[i];
				totalSales = totalSales.add(order.getTotalCost());
//				System.out.println(totalSales);
			}
			return totalSales;
		}
	}
	
	public void changePicPath(String path, GroceryItem item){
//		int index = this.ds.getInventory().indexOf(item);
//		this.ds.getInventory().get(index).setPicFile(path);
		this.ds.changePicPath(path, item);
	}
	
	public void editOrder(String choice, GroceryItem item) { 
		Vector<GroceryItem> temp = this.ds.getCurrentOrder().getGroceryItems();
		
		System.out.println(item.getName());
		System.out.println(item.getQuantity());
		System.out.println(item.getPrice());
		
		boolean itemPresent = false;
		int index = -1;
		for (int i = 0; i < temp.size(); i++){
			if(temp.get(i).getName().equals(item.getName())){
				itemPresent = true;
				index = i;
			}
		}
		
		//logic error, when staff logout I need to add items back to the inventory. TO Solve, Remove logout when there is items in the order
		
		if (choice == "add"){ // Item object is from inventory (IGNORE && item.getQuantity() > 0)
//			System.out.println(item);
//			this.order.addGroceryItem(item);
			
			item.setQuantity(item.getQuantity() - 1);// minus 1 from inventory
			
			
			if (itemPresent == false){
				GroceryItem toAdd = new GroceryItem(item.getName(), item.getPrice(), 1, item.getPicFile()); // n, p, q = 1, pic
				this.ds.getCurrentOrder().addGroceryItem(toAdd);
				System.out.println("Success");
			}
			else{
				//add 1 to the grocery item
				temp.get(index).setQuantity(temp.get(index).getQuantity()+1);
			}

		}
		else if (choice == "delete"){
			
			temp.get(index).setQuantity(temp.get(index).getQuantity()-1); // remove quantity by 1
			
			
//			this.order.deleteGroceryItem(item);
			item.setQuantity(item.getQuantity()+1);;// add 1 to inventory
			
//			System.out.println("Quantity" + temp.get(index).getQuantity());
			if (temp.get(index).getQuantity() == 0){
				this.ds.getCurrentOrder().deleteGroceryItem(item);
			}
		}
		
		System.out.println("Order size: " + this.ds.getCurrentOrder().getGroceryItems().size());
	 }
	
//	public void addGroceryItem(GroceryItem item) { 
//		Vector<GroceryItem> temp = this.ds.getCurrentOrder().getGroceryItems();
//		boolean itemPresent = false;
//		int index = 0;
//		for (int i = 0; i < temp.size(); i++){
//			if(temp.get(i).getName() == item.getName()){
//				itemPresent = true;
//				index = i;
//			}
//		}
//		if (itemPresent == false){
//			temp.add(item.copy());
//		}
//		else{
//			//add 1 to the grocery item
//			temp.get(index).addQuantity(); 
//		}
//		
//	 } 
	
	
	public boolean verifyUser(String n, String pwd, String role) {
		if (role == "staff"){
			Staff[] staffs = this.ds.getStaffs();
			for (Staff staff: staffs){
				if (staff.getName().equals(n) && staff.getPassword().equals(pwd)){
					return true;
				}
				else
					return false;
			}
		}
		
		else if (role == "manager"){
			Manager[] managers = this.ds.getManagers();
			for (Manager manager: managers){
				if (manager.getName().equals(n) && manager.getPassword().equals(pwd)){
					return true;
				}
				else
					return false;
			}
		}
		
		return false;
		
	}

	public Order getCurrentOrder() {
		// TODO Auto-generated method stub
        return this.ds.getCurrentOrder();
	}

	public int generateOrderNumber() {
		// TODO Auto-generated method stub
        return ds.getOrders().length + 1;
	}

	public void confirmOrder() {
		// TODO Auto-generated method stub
		 Order currentOrder = this.ds.getCurrentOrder();
	        if (!currentOrder.getGroceryItems().isEmpty()) {
//	            this.ds.getOrders().add(currentOrder);
	        	this.ds.addOrder(currentOrder);
	            this.ds.setCurrentOrder(new Order());
	        }
	}


}
