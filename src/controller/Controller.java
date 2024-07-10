/**
 * 
 */
package controller;

import java.math.BigDecimal;
import java.util.Vector;

import data.DataStorage;
import data.GroceryItem;
import data.Main;
import data.Order;
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
	
	public Vector<Order> getOrders(){
		return ds.getOrders();
	}
	
	public Vector<GroceryItem> getInventory(){
		return ds.getInventory();
	}
	
	//Manager
	public void createGroceryItem(String itemName, String price, String quantity, String picFile) { 
		GroceryItem temp = new GroceryItem(itemName, price, quantity, picFile);
		this.getInventory().add(temp);
	 }
	//Manager
	public void editGroceryItemPrice(GroceryItem item, String price) { 
		int index = this.getInventory().indexOf(item);
		GroceryItem temp = this.getInventory().get(index);
		temp.setPrice(new BigDecimal(price));
	 }
	//Manager
	public void editGroceryItemName(GroceryItem item, String name) { 
		int index = this.getInventory().indexOf(item);
		GroceryItem temp = this.getInventory().get(index);
		temp.setName(name);
	 }
	//Manager
	public void editGroceryItemQuantity(GroceryItem item, String quantity) { 
		int index = this.getInventory().indexOf(item);
		GroceryItem temp = this.getInventory().get(index);
		temp.setQuantity(Integer.valueOf(quantity));
	 }
	//Manager
	public void deleteGroceryItem(GroceryItem item) { 
		this.getInventory().remove(item);
	 }
	//Manager
	public BigDecimal getTotalOrderCost(){
		Vector<Order> tempOrders = this.getOrders();
		BigDecimal totalSales = BigDecimal.ZERO;
		
		if (tempOrders.isEmpty()){
			return BigDecimal.ZERO;
		}
		else{
			for(int i = 0; i < tempOrders.size(); i++){
				Order order = tempOrders.get(i);
				totalSales = totalSales.add(order.getTotalCost());
//				System.out.println(totalSales);
			}
			return totalSales;
		}
	}
	
	public void editOrder(String choice, GroceryItem item) { 
		Vector<GroceryItem> temp = this.ds.getCurrentOrder().getGroceryItems();
		
		if (choice == "add" && item.getQuantity() > 0){
//			System.out.println(item);
//			this.order.addGroceryItem(item);
			
			
			item.subtractQuantity(); // minus 1 from inventory
		}
		else if (choice == "delete"){
//			this.order.deleteGroceryItem(item);
			item.addQuantity();// add 1 to inventory
		}
	 }
	
	public boolean verifyUser(String n, String pwd) {
		String real = pwd;
		String cc = "";
		User t = ds.getUser(n);
		if (t!=null)
		{
			cc = t.getPassword().toString();
			if (real.equals(cc))
				return true;
			else 
				return false;		
		}
		else 
			return false;
	}
}
