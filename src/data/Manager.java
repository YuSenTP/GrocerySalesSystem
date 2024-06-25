package data;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "userID", "name", "password" }) //To change the save order to json
public class Manager extends User {
	
	public Manager(){
//		super();
	}
	public Manager(String name, String password) { 
		super(name, password);
	 }
	
	public void createGroceryItem(String itemName, double price, int quantity) { 
		GroceryItem temp = new GroceryItem(itemName, price, quantity);
		Main.inventory.add(temp);
	 }

	public void editGroceryItem(GroceryItem item, double price) { 
		int index = Main.inventory.indexOf(item);
		GroceryItem temp = Main.inventory.get(index);
		BigDecimal price1 = BigDecimal.valueOf(price);
		temp.setPrice(price1);
	 }
	
	public void editGroceryItem(GroceryItem item, String name) { 
		int index = Main.inventory.indexOf(item);
		GroceryItem temp = Main.inventory.get(index);
		temp.setName(name);
	 }
	
	public void editGroceryItem(GroceryItem item, int quantity) { 
		int index = Main.inventory.indexOf(item);
		GroceryItem temp = Main.inventory.get(index);
		temp.setQuantity(quantity);
	 }
	
	public void deleteGroceryItem(GroceryItem item) { 
		Main.inventory.remove(item);
	 }
	
	public void orderDetails(int orderNum) { 
		// TODO Auto-generated method
//		System.out.println("Name Price Quantity\n" + item.getName() + " $" + item.getPrice() + " " + item.getQuantity());
//		System.out.println();
		
		if (orderNum > Main.orders.size() || orderNum == 0){
			System.out.println("Invalid OrderNum!(ItemDetails)");
		}
		else{
			Order order = Main.orders.get(orderNum - 1);
			System.out.println("Order" + order.getOrderID());
			for(int i = 0; i < order.getGroceryItems().size(); i++){
				GroceryItem item = order.getGroceryItems().get(i);
				System.out.println(item.getName() + " $" + item.getPrice() + " " + item.getQuantity());
			}
			System.out.println("Total Cost: $" + order.calculateTotalCost());
			System.out.println();
		}
		
		
	 }
	
	public void viewInventory(){
		System.out.println("Inventory");
		System.out.println("Items | Cost | Quantity");
		for (int x = 0; x < Main.inventory.size(); x++){
			GroceryItem item = Main.inventory.get(x);
			System.out.println(item.getName() + " $" + item.getPrice() + " " + item.getQuantity());
		}
		System.out.println();
	}




	public void saleSummary() { // show orders and each other total + total number of orders and total sales
		BigDecimal totalSales = BigDecimal.ZERO;;
		if (Main.orders.isEmpty()){
			System.out.println("No Orders Recorded");
		}
		else {
			System.out.println("Sale Summary:");
			for (int i = 0; i < Main.orders.size(); i++){
				Order order = Main.orders.get(i);
				System.out.println("Order " + order.getOrderID() + "   Cost $" + order.calculateTotalCost());
//				System.out.println();
				totalSales = totalSales.add(order.getTotalCost());
			}
			System.out.println("Total Orders: " + Main.orders.size());
			System.out.println("Total Sales: $" + totalSales);
		}
	}

}
