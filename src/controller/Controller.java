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
}
