/**
 * 
 */
package controller;

import java.awt.CardLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import data.GroceryItem;
import data.Order;
import gui.CreateGroceryItem;
import gui.EditGroceryItem;
import gui.LoginScreen;
import gui.ManagerHome;
import gui.ManagerMenu;
import gui.ManagerSales;
import gui.ManagerOrderDetails;
import gui.StaffMenu;

/**
 * @author Yu Sen
 *
 */
public class MainFrame extends JFrame {
	private CardLayout card;
	private Controller cont;
	
	public MainFrame(){
		this.setTitle("Joy MiniMart");
		this.setSize(750, 550);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image icon = Toolkit.getDefaultToolkit().getImage("./img/ShoppingCart.png");
		this.setIconImage(icon);
//		setResizable(false);
		
		// Instantiation of controller and card
		this.cont = new Controller();
		this.card = new CardLayout();
		this.setLayout(this.card);
		
		// Add first method (e.g. showLoginScreen)
		showLoginScreen();
//		showManagerHome();
//		showSales();
		
		this.setVisible(true);
		
	}
	
	public Controller getController(){
		return this.cont;
	}
	
	public void showLoginScreen(){ 
		LoginScreen p1 = new LoginScreen(this);
		this.add(p1, "LoginScreen");
		this.card.show(this.getContentPane(), "LoginScreen");//p1
		
	}
	
	public void showManagerHome(){
		ManagerHome p2 = new ManagerHome(this);
		this.add(p2, "ManagerHome");
		this.card.show(this.getContentPane(), "ManagerHome");
	}
	
	public void showManagerMenu(){
		ManagerMenu p3 = new ManagerMenu(this);
		this.add(p3, "ManagerMenu");
		this.card.show(this.getContentPane(), "ManagerMenu");
	}
	
	public void showEditGroceryItem(GroceryItem item){ //does this follow MVC?
		EditGroceryItem p4 = new EditGroceryItem(this, item);
		this.add(p4, "EditItem");
		this.card.show(this.getContentPane(), "EditItem");
	}
	
	public void showCreateItem(){
		CreateGroceryItem p6 = new CreateGroceryItem(this);
		this.add(p6, "CreateItem");
		this.card.show(this.getContentPane(), "CreateItem");
	}
	
	public void showSales(){
		ManagerSales p7 = new ManagerSales(this);
		this.add(p7, "Sales");
		this.card.show(this.getContentPane(), "Sales");
	}
	
	public void showOrderDetails(Order order){ //does this follow MVC?
		ManagerOrderDetails p8 = new ManagerOrderDetails(this, order);
		this.add(p8, "OrderDetails");
		this.card.show(this.getContentPane(), "OrderDetails");
	}
	
	public static void main(String[] args){
		MainFrame gui = new MainFrame();
	}

	public void showStaffMenu() {
		StaffMenu p5 = new StaffMenu(this);
		this.add(p5, "StaffMenu");
		this.card.show(this.getContentPane(), "StaffMenu");
		// TODO Auto-generated method stub
		
	}

}
