/**
 * 
 */
package controller;

import java.math.BigDecimal;
import java.util.Vector;

import data.DataStorage;
import data.GroceryItem;
import data.Main;
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
	
	public Vector<GroceryItem> getInventory(){
		return ds.getInventory();
	}
	

	public void createGroceryItem(String itemName, String price, String quantity, String picFile) { 
		GroceryItem temp = new GroceryItem(itemName, price, quantity, picFile);
		this.getInventory().add(temp);
	 }

	public void editGroceryItemPrice(GroceryItem item, String price) { 
		int index = this.getInventory().indexOf(item);
		GroceryItem temp = this.getInventory().get(index);
		temp.setPrice(new BigDecimal(price));
	 }
	
	public void editGroceryItemName(GroceryItem item, String name) { 
		int index = this.getInventory().indexOf(item);
		GroceryItem temp = this.getInventory().get(index);
		temp.setName(name);
	 }
	
	public void editGroceryItemQuantity(GroceryItem item, String quantity) { 
		int index = this.getInventory().indexOf(item);
		GroceryItem temp = this.getInventory().get(index);
		temp.setQuantity(Integer.valueOf(quantity));
	 }
	
	public void deleteGroceryItem(GroceryItem item) { 
		this.getInventory().remove(item);
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
