package data;

import java.io.IOException;
import java.util.Vector;

import JsonReadWrite.*;


public class Main {
	public static Vector<GroceryItem> inventory = new Vector();
	public static Vector<Order> orders = new Vector();
	public static Vector<Staff> staffs = new Vector();
	public static Vector<Manager> managers = new Vector();
	
	/*
	public static void main(String[] args){
		// TODO Auto-generated method stub
		ReadWriteFile.readFile();
		//LOAD previous order history, staff and manager info, and inventory
//		 Manager manager1 = new Manager("Manager1", "Password");
//		 managers.add(manager1);
		// manager1.createGroceryItem("Milk", 2.99, 5);
		// manager1.createGroceryItem("Bread", 1.99, 5);
		
//		 Staff staff1 = new Staff("staff1", "1234");
//		 Staff staff2 = new Staff("Staff2", "5678");
//		 staffs.add(staff1);
//		 staffs.add(staff2);
		
		//Order1 3milk 1bread
		staffs.get(0).editOrder("add", inventory.get(0)); // -milk
		staffs.get(0).editOrder("add", inventory.get(1)); // -bread
		staffs.get(0).editOrder("add", inventory.get(0)); // -milk
		staffs.get(0).editOrder("add", inventory.get(1)); // -bread
		staffs.get(0).editOrder("add", inventory.get(0)); // -milk
		staffs.get(0).editOrder("delete", inventory.get(1)); // +bread
		staffs.get(0).saveOrder(); // bread = 4, milk = 2
		
		//Order2 2bread
		staffs.get(1).editOrder("add", inventory.get(1)); // -bread
		staffs.get(1).editOrder("add", inventory.get(1)); // -bread
		staffs.get(1).saveOrder(); // bread = 2, milk = 2
		
		//Order3 1milk 1bread
		staffs.get(0).editOrder("add", inventory.get(1)); // -bread
		staffs.get(0).editOrder("add", inventory.get(0)); // -milk
		staffs.get(0).saveOrder(); // bread = 1, milk = 1
		
		managers.get(0).editGroceryItem(inventory.get(0), "Cookies"); // cookies = 1, milk = 1
		managers.get(0).editGroceryItem(inventory.get(0), 2.33); // cookies = 2.33
		
		//Order4 1cookie
		staffs.get(1).editOrder("add", inventory.get(0)); 
		staffs.get(1).editOrder("add", inventory.get(0)); 
		staffs.get(1).saveOrder(); // cookies = 0, milk = 1
		
		managers.get(0).orderDetails(1); //get individual details of orders
		managers.get(0).viewInventory(); // view all items in Inventory
		managers.get(0).saleSummary(); // generic view of orders and total cost
		
//		System.out.println(staffs);
//		System.out.println(managers.get(0).getName());
//		System.out.println(inventory);
		ReadWriteFile.writeFile();//write info into json
		
//		Main.debug();
		
	}
	
	public static void debug(){ // prints all orders
		for (int x = 0; x < orders.size(); x++){
			Order order = orders.get(x);
			System.out.println("Order" + order.getOrderID());
//			System.out.println(order.getGroceryItems());
			for(int i = 0; i < order.getGroceryItems().size(); i++){
				GroceryItem item = order.getGroceryItems().get(i);
				System.out.println(item.getName() + " $" + item.getPrice());
			}
			System.out.println();
		}
	}
	
	*/

}
