/**
 * 
 */
package controller;

import java.util.Vector;

import data.DataStorage;
import data.GroceryItem;

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
}
