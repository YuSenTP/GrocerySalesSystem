/**
 * 
 */
package controller;

import java.math.BigDecimal;

import data.DataStorage;
import data.GroceryItem;
import data.Order;
import data.User;

/**
 * @author Yu Sen
 *
 */
public class Controller {
	public DataStorage ds; 
	  
	public Controller(){
		 this.ds = new DataStorage();
	}
	
	//Get orders
	public Order[] getOrders(){
		return ds.getOrders();
	}
	
	//Get Users
	public User[] getUsers(){
		return ds.getUsers();
	}
	
	//Get Inventory
	public GroceryItem[] getInventory(){
		return ds.getInventory();
	}
	
	//Write data to file
	public void saveAll(){
		this.ds.writeFile();
	}
	
	//Get Category
	public String[] getCategory(){
		return this.ds.getCategory();
	}
	
	//Add category
	public void addCategory(String category){
		this.ds.addCategory(category);
	}
	
	//Manager create item
	public void createGroceryItem(String itemName, String price, String quantity, String picFile, boolean onSale, String percentOff, String category) { 
		GroceryItem temp = new GroceryItem(itemName, price, quantity, picFile, onSale, Double.valueOf(percentOff) / 100, category);
		this.ds.createGroceryItem(temp);
	 }
	
	//Manager edit item
	public void editGroceryItem(GroceryItem item, String name, String price, String quantity, String picFile, boolean onSale, String percentOff, String category){
		this.ds.editGroceryItem(item, name, price, quantity, picFile, onSale, percentOff, category);
	}
	
	//Manager delete item
	public void deleteGroceryItem(GroceryItem item) { 
		this.ds.deleteGroceryItem(item);
	 }
	
	//Manager total order cost
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
	
	// Staff total sales
	public BigDecimal getCurrentStaffTotalSales() {
	    User currentUser = this.getCurrentUser();
	    if (currentUser == null || !currentUser.getRole().equals("Staff")) {
	        return BigDecimal.ZERO;
	    }

	    Order[] staffOrders = this.getCurrentStaffOrders();
	    BigDecimal totalSales = BigDecimal.ZERO;

	    for (int i = 0; i < staffOrders.length; i++) {
	        Order order = staffOrders[i];
	        totalSales = totalSales.add(order.getTotalCost());
	    }

	    return totalSales;
	}
	
	
	//Staff edit order
	public void editOrder(String choice, GroceryItem item) { 
		GroceryItem[] temp = this.ds.getCurrentOrder().getGroceryItems();
		
		boolean itemPresent = false;
		int index = -1;
		for (int i = 0; i < temp.length; i++){
			if(temp[i].getName().equals(item.getName())){
				itemPresent = true;
				index = i;
			}
		}
		
		
		if (choice == "add"){ // Item object is from inventory (IGNORE && item.getQuantity() > 0)
			
			item.setQuantity(item.getQuantity() - 1);// minus 1 from inventory
			
			System.out.println("Item Name:" + item.getName());
			System.out.println("No. of Item in Inventory: " + item.getQuantity());
			System.out.println("Item Price: " + item.getPrice());
			
			
			if (itemPresent == false){
				GroceryItem toAdd = new GroceryItem(item.getName(), item.getPrice().toString(), "1", item.getPicFile(), item.getOnSale(), item.getPercentOff(),item.getCategory()); // n, p, q = 1, pic
				this.ds.getCurrentOrder().addGroceryItem(toAdd);
				System.out.println("Success");
			}
			else{
				//add 1 to the grocery item
				temp[index].setQuantity(temp[index].getQuantity()+1);
			}
		}
		else if (choice == "delete"){
			
			temp[index].setQuantity(temp[index].getQuantity()-1); // remove quantity by 1
			
			item.setQuantity(item.getQuantity()+1);;// add 1 to inventory
			
			System.out.println("Item Name:" + item.getName());
			System.out.println("No. of Item in Inventory: " + item.getQuantity());
			System.out.println("Item Price: " + item.getPrice());
			
//			System.out.println("Quantity" + temp.get(index).getQuantity());
			if (temp[index].getQuantity() == 0){
				this.ds.getCurrentOrder().deleteGroceryItem(item);
			}
		}
		
		System.out.println("Order size: " + this.ds.getCurrentOrder().getGroceryItems().length);
	 }
	
	//Get Current staff order
	public Order[] getCurrentStaffOrders() {
	    User currentUser = this.getCurrentUser();
	    System.out.println("Current user: " + (currentUser != null ? currentUser.getName() : "null"));
	    if (currentUser != null) {
	        System.out.println("Current user role: " + currentUser.getRole());
	    }
	    if (currentUser != null && currentUser.getRole().equals("Staff")) {
	        Order[] orders = this.ds.getStaffOrders(currentUser.getName());
	        System.out.println("Number of orders for staff: " + orders.length);
	        return orders;
	    }
	    return new Order[0];
	}
	
	//Update cart
	public void cartUpdateInventory(String choice, GroceryItem item){
		GroceryItem[] inventoryItems = this.ds.getInventory();
		for(int i = 0; i < inventoryItems.length; i++){
			if (item.getName().equals(inventoryItems[i].getName())){
				if (choice == "add"){
					inventoryItems[i].setQuantity(inventoryItems[i].getQuantity()-1);
				}
				else if (choice == "delete"){
					inventoryItems[i].setQuantity(inventoryItems[i].getQuantity()+1);
				}
			}
		}
	}
	
	//Checks if item available
	public boolean checkInventoryAvaiblility(GroceryItem item){
		GroceryItem[] inventoryItems = this.ds.getInventory();
		for(int i = 0; i < inventoryItems.length; i++){
			if (item.getName().equals(inventoryItems[i].getName())){
				if(inventoryItems[i].getQuantity() == 0){
					return false;
				}
			}
		}
		return true;	
	}
	
	//Clear cart
	public void clearCurrentOrder() {
	    GroceryItem[] items = this.ds.getCurrentOrder().getGroceryItems();
	    for (int i = 0; i < items.length; i++) {
	        GroceryItem item = items[i];
	        for (int j = 0; j < item.getQuantity(); j++) {
	            cartUpdateInventory("delete", item);
	        }
	    }
	    this.ds.clearCurrentOrderItems();
	}
	
		
	//Checks user for login
	public String verifyUser(String n, String pwd) {
	    User[] users = getUsers();
	    for (int i = 0; i < users.length; i++) {
	        User user = users[i];
	        if (user.getName().equals(n) && user.getPassword().equals(pwd)) {
	            return user.getRole(); //role will return only if name, password matches
	        } //returned role will be used to determine if go to Staff or Manager in LoginScreen
	    }
	    return null; //otherwise, return null
	}
	
	//Set current user
	public void setCurrentUserName(String username) {
	    User[] users = getUsers();
	    for (int i = 0; i < users.length; i++) {
	        User user = users[i];
	        if (user.getName().equals(username)) {
	            this.ds.setCurrentUser(user);
	            System.out.println("Current user set: " + user.getName() + ", Role: " + user.getRole());
	            return;
	        }
	    }
	    System.out.println("User not found: " + username);
	}
	
	//Add new user
	public void addUser(String n, String pwd, String r, String pic, int id) {
		User u = new User();
		// setting info
		u.setName(n);
		u.setPassword(pwd);
		// u.setUserID(id);
		u.setRole(r);
		u.setPicFile(pic);
		u.setUserID(id);
		// Store info into datastorage
		this.ds.storeUser(u);
	}
	
	//Deletes User
	public void deleteUser(User user) {
		this.ds.deleteUser(user);
	}
		
	//Generate UserID based on number of users
	public int generateUserID() {
		return ds.getUsers().length + 1;
	}
	
	//Get Current user
	public User getCurrentUser() {
		return this.ds.getCurrentUser();
	}

	//Get current order
	public Order getCurrentOrder() {
        return this.ds.getCurrentOrder();
	}

	//Generate Order Number base on total number of orders
	public int generateOrderNumber() {
        return ds.getOrders().length + 1;
	}
	
	//Calculate total cost
	public BigDecimal calculateTotalCost() {
		BigDecimal totalCost = BigDecimal.ZERO;
		BigDecimal groceryCost;
		GroceryItem[] items = this.ds.getCurrentOrder().getGroceryItems();
		//Loop through all items and add to total cost
		for(int i = 0; i < items.length; i++){
			//If Onsale
			if (items[i].getOnSalePrice() != BigDecimal.ZERO){
				groceryCost = items[i].getOnSalePrice().multiply(BigDecimal.valueOf(items[i].getQuantity()));
			}
			else{
				groceryCost = items[i].getPrice().multiply(BigDecimal.valueOf(items[i].getQuantity()));
			}
			//Add to total cost
			totalCost = totalCost.add(groceryCost);
		}
		totalCost = totalCost.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.ds.getCurrentOrder().setTotalCost(totalCost);
		return totalCost;
	 }
	
	//Saves order
	public void confirmOrder() {
		 Order currentOrder = this.ds.getCurrentOrder();
	        if (currentOrder.getGroceryItems().length != 0) {
//	            this.ds.getOrders().add(currentOrder);
	        	currentOrder.setStaffName(this.getCurrentUser().getName());
	        	this.ds.addOrder(currentOrder);
	            this.ds.setCurrentOrder(new Order());
	        }
	}

	//Edit category
	public void editCategory(int originalIndex, int endIndex, String Name) {
		this.ds.editCategory(originalIndex, endIndex, Name);
		
	}

	//Add category with placing
	public void newCategory(int cIndex, String name) {
		this.ds.newCategory(cIndex, name);
		
	}
	
	//Delete Category
	public void deleteCategory(String name){
		this.ds.deleteCategory(name);
	}

	//Checks if category in use
	public boolean categoryInUse(String name) {
		return this.ds.categoryInUse(name);
	}

	//Reassign category in use
	public void reassignCategory(String originalCategory, String selectedCat) {
		this.ds.reassignCategory(originalCategory, selectedCat);
		
	}

	//Checks if account exists
	public boolean accountExists(String name, String role){
		return this.ds.accountExists(name, role);
	}

}
