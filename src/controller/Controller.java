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
