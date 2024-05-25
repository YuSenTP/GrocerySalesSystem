package Data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"userID", "name", "password", "order"}) //To change the save order to json
public class Staff extends User {

	private Order order;
	
	public Staff(){
//		super();
//		this.order = new Order();
	}
	
	public Staff(String name, String password) { 
		super(name, password);
		this.order = new Order();
	 }

	public Order getOrder() {
	 	 return order; 
	}

	public void setOrder(Order order) { 
		 this.order = order; 
	}

	public void saveOrder() { 
		Main.orders.add(this.order);
		this.order = new Order();
	 }


	public void editOrder(String choice, GroceryItem item) { 
		if (choice == "add" && item.getQuantity() > 0){
//			System.out.println(item);
			this.order.addGroceryItem(item);
			item.subtractQuantity();
		}
		else if (choice == "delete"){
			this.order.deleteGroceryItem(item);
			item.addQuantity();
		}
	 } 
	
	public void start(){
		this.order = new Order();
	}

}
