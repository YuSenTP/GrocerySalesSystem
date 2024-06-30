/**
 * 
 */
package controller;

import java.util.Vector;

import data.DataStorage;
import data.GroceryItem;
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

//	public void setItemPrice(GroceryItem item, String price){
//		int i = this.ds.getInventory().indexOf(item);
//		
//	}
//	
//	public void setItemQuantity(GroceryItem item, String num){
//		
//	}
//	
//	public void setItemName(GroceryItem item, String name){
//		
//	}
	
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
