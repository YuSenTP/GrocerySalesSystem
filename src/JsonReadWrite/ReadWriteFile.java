/**
 * 
 */
package JsonReadWrite;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.*;

/**
 * @author Yu Sen
 *
 */

public class ReadWriteFile {
	public static void readFile(){
   	 ObjectMapper objectMapper = new ObjectMapper();
     try {
         // Read JSON array from file and convert to list of User objects
         Main.managers = objectMapper.readValue(
                 new File("./src/JsonReadWrite/startManager.json"),
                 new TypeReference<Vector<Manager>>(){}
         );
         Main.staffs = objectMapper.readValue(
                 new File("./src/JsonReadWrite/startStaff.json"),
                 new TypeReference<Vector<Staff>>(){}
         );
         
         Main.inventory = objectMapper.readValue(
                 new File("./src/JsonReadWrite/startinventory.json"),
                 new TypeReference<Vector<GroceryItem>>(){}
         );
         
         Main.orders = objectMapper.readValue(
                 new File("./src/JsonReadWrite/startorder.json"),
                 new TypeReference<Vector<Order>>(){}
         );
         // Output the list of User objects
//         for (Manager user : Main.managers) {
//             System.out.println(user.getName());
//         }
         //Loops through Main.staffs Vector and set each object index to staff
         for (Staff staff : Main.staffs) { //set new instance of order to each staff
//             System.out.println(user.getName());
        	 staff.start();
        	 
         } 
     } catch (IOException e) {
         e.printStackTrace();
     }
		
	}
	
	public static void writeFile(){
		for (int i = 0; i < Main.staffs.size(); i++){ //Clear staff order object to null
			Main.staffs.get(i).setOrder(null);
		}
    	ObjectMapper objectMapper = new ObjectMapper(); 
    	try {
    		//! TO-CHANGE filePath
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("./src/JsonReadWrite/inventory.json"), Main.inventory);
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("./src/JsonReadWrite/manager.json"), Main.managers);
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("./src/JsonReadWrite/staff.json"), Main.staffs);
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("./src/JsonReadWrite/order.json"), Main.orders);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
