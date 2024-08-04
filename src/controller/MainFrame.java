/**
 * 
 */
package controller;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import data.GroceryItem;
import data.Order;
import data.User;
import gui.AddAccount;
import gui.CreateGroceryItem;
import gui.EditAccount;
import gui.EditCategory;
import gui.EditGroceryItem;
import gui.EditOwnProfile;
import gui.LoginScreen;
import gui.ManageAccounts;
import gui.ManagerHome;
import gui.ManagerInventory;
import gui.ManagerMenu;
import gui.ManagerSales;
import gui.StaffCart;
import gui.ManagerOrderDetails;
import gui.StaffMenu;
import gui.StaffOrderDetails;
import gui.StaffSales;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Yu Sen & Gabriel
 * 1x MainFrame
 * 1x Controller
 * 1x DataStorage
 * 5x Entities
 * 10x GUI/Panel
 *
 */
public class MainFrame extends JFrame {
	private CardLayout card;
	private Controller cont;
	
	public MainFrame(){
		this.setTitle("Joy MiniMart");
		this.setSize(750, 550);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Image icon = Toolkit.getDefaultToolkit().getImage("./img/ShoppingCart.png");
		this.setIconImage(icon);
		setResizable(false);
		
		// Instantiation of controller and card
		this.cont = new Controller();
		this.card = new CardLayout();
		getContentPane().setLayout(this.card);
		
		// Add first method (e.g. showLoginScreen)
		showLoginScreen();

		// When program closes
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (cont.getCurrentOrder().getGroceryItems().length > 0){
//					System.out.println("NOT allowed to close");
					JLabel label = new JLabel("Items in cart! Confirm Exit?");
					label.setFont(new Font("Tahoma", Font.BOLD, 14));
//					JOptionPane.showMessageDialog(MainFrame.this, label, "Error" ,JOptionPane.ERROR_MESSAGE);
					int response = JOptionPane.showConfirmDialog(MainFrame.this, label, "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE );
					
					if (response == JOptionPane.YES_OPTION) {
						cont.clearCurrentOrder();
						cont.saveAll();
						dispose();
					}
				}
				else{
					cont.saveAll();
					dispose();
				}
			}
		});
		
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
//				| UnsupportedLookAndFeelException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		this.setVisible(true);
		
	}
		
	public Controller getController(){
		return this.cont;
	}
	
	public void showLoginScreen(){ //P1
		LoginScreen p1 = new LoginScreen(this);
		getContentPane().add(p1, "LoginScreen");
		this.card.show(this.getContentPane(), "LoginScreen");
	}
	
	public void showManagerHome(){ //P2
		ManagerHome p2 = new ManagerHome(this);
		getContentPane().add(p2, "ManagerHome");
		this.card.show(this.getContentPane(), "ManagerHome");
	}
	
	public void showManagerMenu(){ //P3
		ManagerMenu p3 = new ManagerMenu(this);
		getContentPane().add(p3, "ManagerMenu");
		this.card.show(this.getContentPane(), "ManagerMenu");
	}
	
	public void showEditGroceryItem(GroceryItem item){ //P4
		EditGroceryItem p4 = new EditGroceryItem(this, item);
		getContentPane().add(p4, "EditItem");
		this.card.show(this.getContentPane(), "EditItem");
	}
	
	public void showCreateItem(){ //P6
		CreateGroceryItem p6 = new CreateGroceryItem(this);
		getContentPane().add(p6, "CreateItem");
		this.card.show(this.getContentPane(), "CreateItem");
	}
	
	public void showSales(){ //P7
		ManagerSales p7 = new ManagerSales(this);
		getContentPane().add(p7, "Sales");
		this.card.show(this.getContentPane(), "Sales");
	}
	
	public void showOrderDetails(Order order){  //P8
		ManagerOrderDetails p8 = new ManagerOrderDetails(this, order);
		getContentPane().add(p8, "OrderDetails");
		this.card.show(this.getContentPane(), "OrderDetails");
	}
	
	public void showStaffMenu() { //P5
		StaffMenu p5 = new StaffMenu(this);
		getContentPane().add(p5, "StaffMenu");
		this.card.show(this.getContentPane(), "StaffMenu");
	}
	
	public void showStaffCart(){ //P9
		StaffCart p9 = new StaffCart(this);
		getContentPane().add(p9, "StaffCart");
		this.card.show(this.getContentPane(), "StaffCart");
	}
	
	public void showManagerInventory(){ //P10
		ManagerInventory p10 = new ManagerInventory(this);
		getContentPane().add(p10, "StaffCart");
		this.card.show(this.getContentPane(), "StaffCart");
	}
	
	public void showEditCategory(){
		EditCategory p11 = new EditCategory(this); //P11
		getContentPane().add(p11, "EditCategory");
		this.card.show(this.getContentPane(), "EditCategory");
	}
	
	public void showStaffSales(){
		StaffSales p12 = new StaffSales(this); //P12
		getContentPane().add(p12, "StaffSales");
		this.card.show(this.getContentPane(), "StaffSales");
	}
	
	public void showManageAccounts(){
		ManageAccounts p13 = new ManageAccounts(this); //P13
		getContentPane().add(p13, "ManageAccounts");
		this.card.show(this.getContentPane(), "ManageAccounts");
	}
	
	public void showAddAccount(){
		AddAccount p14 = new AddAccount(this); //P14
		getContentPane().add(p14, "AddAccount");
		this.card.show(this.getContentPane(), "AddAccount");
	}
	
	public void showStaffOrderDetails(Order order){
		StaffOrderDetails p15 = new StaffOrderDetails(this, order);
		getContentPane().add(p15, "staffOrderDetails");
		this.card.show(this.getContentPane(), "staffOrderDetails");
	}
	
	public void showEditAccount(User user){
		EditAccount p16 = new EditAccount(this, user);
		getContentPane().add(p16, "staffOrderDetails");
		this.card.show(this.getContentPane(), "staffOrderDetails");
	}
	
	public void showEditOwnProfile(){
		EditOwnProfile p17 = new EditOwnProfile(this);
		getContentPane().add(p17, "staffOrderDetails");
		this.card.show(this.getContentPane(), "staffOrderDetails");
	}
	
	public static void main(String[] args){
		MainFrame gui = new MainFrame();
	}

}
