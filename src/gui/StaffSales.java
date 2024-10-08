package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.MainFrame;
import data.GroceryItem;
import data.Order;
import data.User;

import javax.swing.JButton;
import javax.swing.JTextPane;

/**
 * @author Yu Sen
 *
 */
public class StaffSales extends JPanel{
	private MainFrame main;
	private JLabel lblEditItem;
	private JPanel bottomPanel;
	private JButton backButton;
	private JScrollPane scrollPane;
	private Order currentOrder;
	private JButton orderButton;
	private JLabel orderNumber;
	private JLabel orderTotal;
	private JLabel totalSales;
	private JPanel gridPanel;
	private Order[] staffOrders;
	
	public StaffSales(MainFrame main){
		this.main = main;
		this.main.setTitle("Joy MiniMart - Staff Sales History");
//		this.orders = this.main.getController().getOrders();
		this.staffOrders = main.getController().getCurrentStaffOrders();
	    User currentUser = main.getController().getCurrentUser();
	    System.out.println("Current user in StaffSales: " + (currentUser != null ? currentUser.getName() : "null"));

		
		
		this.setBackground(UIManager.getColor("OptionPane.background"));
		this.setLayout(new BorderLayout(0, 0));
		//Top Label
		this.lblEditItem = new JLabel("Sales History");
		this.lblEditItem.setBackground(UIManager.getColor("Button.background"));
		this.lblEditItem.setPreferredSize(new Dimension(87, 60));
		this.lblEditItem.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.lblEditItem.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(this.lblEditItem, BorderLayout.NORTH);
		//Middle Panel

		this.gridPanel = new JPanel(new GridBagLayout());
		this.gridPanel.setBorder(new EmptyBorder(0, 5, 5, 5));// top, left, bottom, right
        this.gridPanel.setBackground(UIManager.getColor("OptionPane.background"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
//        System.out.println(this.orders.size());
        
        //for loop to create order buttons
        for (int i = 0; i < this.staffOrders.length; i++) {
        	this.currentOrder = this.staffOrders[i];
        	
//        	this.orderPanel = new JPanel(new BorderLayout());
        	//Button
        	this.orderButton = new JButton();
        	this.orderButton.setPreferredSize(new Dimension(0, 70));
        	this.orderButton.setBackground(Color.WHITE);
        	this.orderButton.setLayout(new BorderLayout());
        	
            //Order Action
    		this.orderButton.setActionCommand(String.valueOf(this.currentOrder.getOrderID()));
    		this.orderButton.putClientProperty("object", this.currentOrder); //associate each button to each order object
        	this.orderButton.addActionListener(new ActionListener(){
        		@Override
        		public void actionPerformed(ActionEvent e){
        			//TO CHANGE
//        			System.out.println(e.getSource());
        			JButton sourceBtn = (JButton) e.getSource(); //get source return button object that is called
        			Order object = (Order) sourceBtn.getClientProperty("object");
//        			System.out.println(object);
        			
        			String buttonName = e.getActionCommand();
        			showOrderScreen(buttonName, object);
//        			main.getController();
        		}
        	});
        	
        	
        	//Order number
        	this.orderNumber = new JLabel("Order " + String.valueOf(this.currentOrder.getOrderID()));
        	this.orderNumber.setHorizontalAlignment(SwingConstants.CENTER);
            this.orderNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
            this.orderNumber.setBorder(new EmptyBorder(0, 10, 0, 0)); //t l b r
            this.orderButton.add(this.orderNumber, BorderLayout.WEST);
            
            
            //Order Total
            this.orderTotal = new JLabel("Total: $" + String.valueOf(this.currentOrder.getTotalCost()));
        	this.orderTotal.setHorizontalAlignment(SwingConstants.CENTER);
            this.orderTotal.setFont(new Font("Tahoma", Font.BOLD, 18));
            this.orderTotal.setBorder(new EmptyBorder(0, 0, 0, 10)); //t l b r
            this.orderButton.add(this.orderTotal, BorderLayout.EAST);
        	
        	
        	this.gridPanel.add(orderButton, gbc);
        	gbc.gridy++;
        }
        //Container to push items up --> Filler Panel
        gbc.weightx = 1; // Extra space allocate to this Panel as weight > 0
        gbc.weighty = 1;
//        gbc.fill = GridBagConstraints.BOTH;
        this.gridPanel.add(new JPanel(), gbc);
        
        
        //Scroll Pane
        this.scrollPane = new JScrollPane(this.gridPanel);
        this.scrollPane.setBorder(new EmptyBorder(0, 2, 2, 2));
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        this.add(this.scrollPane, BorderLayout.CENTER);
		//Bottom Panel
		this.bottomPanel = new JPanel();
        this.bottomPanel.setBackground(Color.WHITE);  
        this.bottomPanel.setLayout(new BorderLayout(0, 0));
        this.bottomPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        //Back Button
        this.backButton = new JButton("Back");
        this.backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.backButton.setPreferredSize(new Dimension(100, 40));
    	this.backButton.addActionListener(new ActionListener(){
    		@Override
    		public void actionPerformed(ActionEvent e){
    			back();
    		}
    	});
        this.bottomPanel.add(this.backButton, BorderLayout.WEST);
        
        //Total Sales Label
        this.totalSales = new JLabel("Total Sales: $" + this.main.getController().getCurrentStaffTotalSales());
        this.totalSales.setHorizontalAlignment(SwingConstants.CENTER);
        this.totalSales.setFont(new Font("Tahoma", Font.BOLD, 18));
        this.totalSales.setBorder(new EmptyBorder(0, 0, 0, 20)); //t l b r
        this.bottomPanel.add(this.totalSales, BorderLayout.EAST);
        
        this.add(bottomPanel, BorderLayout.SOUTH);
        
	}
	
	//Back to Staff Menu
	private void back(){
		this.main.showStaffMenu();
	}
	
	//Show Staff Order
	private void showOrderScreen(String name, Order order){
		System.out.println(name);
		this.main.showStaffOrderDetails(order);
	}
}
