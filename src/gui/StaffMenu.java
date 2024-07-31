package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.MainFrame;
import data.GroceryItem;
import data.Order;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.Vector;

public class StaffMenu extends JPanel {
    private MainFrame main;
    private GroceryItem[] inventory;
    private JPanel gridPanel;
    private JPanel itemPanel;
    private JButton itemButton;
    private ImageIcon itemPic;
    private JLabel nameLabel;
    private JLabel priceLabel;
    private JScrollPane scrollPane;
    private JPanel topPanel;
    private JLabel lblGroceryItems;
    private JPanel bottomPanel;
    private JButton logoutButton;
    private JButton cartButton;
//    private JTextField quantityField;
//    private JLabel quantityField;
//	private JButton decreaseButton;
//	private JButton increaseButton;
//    private boolean flag;
    private GroceryItem cItem;
	private JPanel pricePanel;
	private GroceryItem[] cItems;
	private JComboBox comboBox;
	private JLabel lblView;
	private String[] category;
	
	private static String categorySele = "All";

    public StaffMenu(MainFrame main) {
        this.main = main;
        this.main.setTitle("Joy MiniMart - Staff Menu");
        this.inventory = this.main.getController().getInventory();
		this.category = this.main.getController().getCategory();

        
        this.cItems = this.main.getController().getCurrentOrder().getGroceryItems();

        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout(0, 0));

        this.gridPanel = new JPanel(new GridLayout(0, 3, 10, 10)); // rows, cols, hgap, vgap
        this.gridPanel.setBorder(new EmptyBorder(0, 10, 10, 10)); // top, left, bottom, right
        this.gridPanel.setBackground(UIManager.getColor("OptionPane.background"));
        
        // Top Panel
        this.topPanel = new JPanel();
        this.topPanel.setPreferredSize(new Dimension(10, 70));
        this.topPanel.setLayout(null);

        this.lblGroceryItems = new JLabel("Grocery Items");
        this.lblGroceryItems.setBounds(275, 5, 200, 60);
        this.lblGroceryItems.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.lblGroceryItems.setPreferredSize(new Dimension(200, 60));
        this.lblGroceryItems.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.lblGroceryItems.setHorizontalAlignment(SwingConstants.CENTER);
        this.topPanel.add(this.lblGroceryItems);
        
        

        this.comboBox = new JComboBox(Arrays.copyOfRange(this.category, 0, this.category.length - 1));
        this.comboBox.setBounds(588, 24, 111, 25);
        this.comboBox.setSelectedItem(StaffMenu.categorySele);
        this.comboBox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		StaffMenu.categorySele = comboBox.getSelectedItem().toString();
        		System.out.println(comboBox.getSelectedItem().toString());
        		main.showStaffMenu();
        	}
        });;
        
        this.lblView = new JLabel("View:");
        this.lblView.setBounds(529, 25, 47, 21);
        this.lblView.setFont(new Font("Tahoma", Font.BOLD, 17));
        this.topPanel.add(this.lblView);
        this.comboBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.comboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.comboBox.setFocusable(false);
        this.topPanel.add(this.comboBox);
        
        this.add(this.topPanel, BorderLayout.NORTH);
   

        // Bottom Panel
        this.bottomPanel = new JPanel();
        this.bottomPanel.setBackground(Color.WHITE);
        this.bottomPanel.setLayout(new BorderLayout(0, 0));
        
        //Logout
        this.logoutButton = new JButton("Logout");
        this.logoutButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.logoutButton.setPreferredSize(new Dimension(100, 40));
        this.logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	logOut();
            }
        });
        
        this.bottomPanel.add(this.logoutButton, BorderLayout.WEST);

        for (int i = 0; i < this.inventory.length; i++) {
        	GroceryItem currentItem = this.inventory[i];
            
            if (StaffMenu.categorySele.equals("All") != true){
        		if (currentItem.getCategory().equals(StaffMenu.categorySele) != true){
        			continue;
        		}
        	}
            if (currentItem.getQuantity() == 0 && this.cItems.length == 0){ // If item not in cart and no more in inventory then no show
            	continue;
            }

            this.itemPanel = new JPanel(new BorderLayout());

            this.itemButton = new JButton();
            this.itemButton.setLayout(new BorderLayout());
            this.itemButton.setBackground(Color.WHITE);
            this.itemButton.setPreferredSize(new Dimension(213, 143));

            // Item Action
//            this.itemButton.setActionCommand(currentItem.getName());
//            this.itemButton.putClientProperty("object", currentItem); // associate each button to each GroceryItem object
//            this.itemButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    int quantity = Integer.valueOf(quantityField.getText());
//                    // Handle item button action
//                }
//            });

            // Item Pic
            this.itemPic = new ImageIcon(currentItem.getPicFile());
            Image img = this.itemPic.getImage();
            Image newimg = img.getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH);
            this.itemPic = new ImageIcon(newimg);
            this.itemButton.setIcon(this.itemPic);

            // Item Name
            this.nameLabel = new JLabel(currentItem.getName());
            this.nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.nameLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
            this.itemButton.add(this.nameLabel, BorderLayout.NORTH);

            // Item Price
            
            this.pricePanel = new JPanel();
            this.pricePanel.setOpaque(false);
            
            this.priceLabel = new JLabel("$" + currentItem.getPrice().toString());
            this.priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.priceLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
            
            // If On Sale
            if (currentItem.getOnSale()){
            	Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) this.priceLabel.getFont().getAttributes(); // HashMap Something like a dictonary in python where data are stored as key value pair
            	attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON); // Add new attribute to HashMap
            	this.priceLabel.setFont(new Font(attributes));
            	this.priceLabel.setForeground(Color.GRAY);

            	this.pricePanel.add(this.priceLabel);

            	BigDecimal salePercent = new BigDecimal(1 - currentItem.getPercentOff());
            	BigDecimal salePrice = currentItem.getPrice().multiply(salePercent);
            	salePrice = salePrice.setScale(2, BigDecimal.ROUND_HALF_UP);
            	JLabel saleLabel = new JLabel("$" + salePrice.toString());
            	saleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            	saleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
            	saleLabel.setForeground(Color.RED);
            	this.pricePanel.add(saleLabel);
            }
            else{
            	this.pricePanel.add(this.priceLabel);
            }
            
            
            this.itemButton.add(this.pricePanel, BorderLayout.SOUTH);

            this.itemPanel.add(this.itemButton, BorderLayout.CENTER);

            // Add to Cart Button
            JButton addToCartButton = new JButton("Add to Cart");
            addToCartButton.putClientProperty("object", currentItem);
            addToCartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                	flag = true;
                    showQuantityControls(addToCartButton, currentItem, true);
                }
            });
            this.itemPanel.add(addToCartButton, BorderLayout.SOUTH);

            this.gridPanel.add(this.itemPanel);
//            System.out.println(currentItem);
//            showQuantityControls(addToCartButton, currentItem);
//            System.out.println(cItems.length);
            for (int x = 0; x < this.cItems.length; x++){
//            	System.out.println(cItems[x].getName());
            	if(currentItem.getName().equals(this.cItems[x].getName())){
            		this.cItem = this.cItems[x];
            		showQuantityControls(addToCartButton, currentItem, false);
            		
            	}
            }
            
            
        }

        // Scroll Pane
        this.scrollPane = new JScrollPane(gridPanel);
        this.scrollPane.setBorder(new EmptyBorder(0, 2, 2, 2));
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        this.add(this.scrollPane);

//        // Top Panel
//        this.topPanel = new JPanel();
//        this.lblGroceryItems = new JLabel("Grocery Items");
//        this.lblGroceryItems.setPreferredSize(new Dimension(200, 60));
//        this.lblGroceryItems.setFont(new Font("Tahoma", Font.BOLD, 20));
//        this.lblGroceryItems.setHorizontalAlignment(SwingConstants.CENTER);
//        this.topPanel.add(this.lblGroceryItems);
//        this.add(this.topPanel, BorderLayout.NORTH);
//
//        // Bottom Panel
//        this.bottomPanel = new JPanel();
//        this.bottomPanel.setBackground(Color.WHITE);
//        this.bottomPanel.setLayout(new BorderLayout(0, 0));
//        
//        //Logout
//        this.logoutButton = new JButton("Logout");
//        this.logoutButton.setFont(new Font("Tahoma", Font.BOLD, 15));
//        this.logoutButton.setPreferredSize(new Dimension(100, 40));
//        this.logoutButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            	logOut();
//            }
//        });
//        
//        this.bottomPanel.add(this.logoutButton, BorderLayout.WEST);

        //Cart
        this.cartButton = new JButton("Cart");
        cartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                main.showStaffCart();
            }
        });
        this.cartButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.cartButton.setPreferredSize(new Dimension(140, 40));
        this.bottomPanel.add(this.cartButton, BorderLayout.EAST);

        this.add(this.bottomPanel, BorderLayout.SOUTH);
    }
    
    private void logOut(){
    	if (this.main.getController().getCurrentOrder().getGroceryItems().length > 0){ //can not use this.cItems as it is not updated... need get latest version
    		JLabel label = new JLabel("Items in cart! Confirm Logout?");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
//			JOptionPane.showMessageDialog(MainFrame.this, label, "Error" ,JOptionPane.ERROR_MESSAGE);
			int response = JOptionPane.showConfirmDialog(this, label, "Confirm Logout", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE );
			
			if (response == JOptionPane.YES_OPTION) {
				this.main.getController().clearCurrentOrder();
		        JOptionPane.showMessageDialog(this, "Logged Out Successfully", "Notification", JOptionPane.INFORMATION_MESSAGE);
		        this.main.showLoginScreen();
			}
    	}
    	else{
    		JOptionPane.showMessageDialog(this, "Logged Out Successfully", "Notification", JOptionPane.INFORMATION_MESSAGE);
	        this.main.showLoginScreen();
    	}

    }

    private void showQuantityControls(JButton addToCartButton, GroceryItem currentItem, boolean flag) {
        JPanel parentPanel = (JPanel) addToCartButton.getParent(); // Parent is itemPanel
        parentPanel.remove(addToCartButton); // itemPanel to remove button
//    	 itemPanel.remove(addToCartButton);
//        GroceryItem currentItem = (GroceryItem) addToCartButton.getClientProperty("object");
        
        

        JPanel quantityPanel = new JPanel(new BorderLayout());
        JButton decreaseButton = new JButton("-");
        decreaseButton.putClientProperty("object", currentItem);
        JButton increaseButton = new JButton("+");
        increaseButton.putClientProperty("object", currentItem);
//        this.quantityField = new JTextField("1", 3);
        JLabel quantityField = new JLabel("1");
        quantityField.setHorizontalAlignment(SwingConstants.CENTER);
        quantityField.setOpaque(true);
        quantityField.setBackground(Color.WHITE);
//        this.quantityField.setBorder(new Border()); TO ADD LATER
        if(flag == true){
        	this.main.getController().editOrder("add", currentItem);
//        	flag = false;
        }
        else{
        	quantityField.setText(String.valueOf(this.cItem.getQuantity()));
        }
        
        
        //this is to set when back from cart
        if (currentItem.getQuantity() == 0){
        	increaseButton.setEnabled(false);
        }
        
        
        //decrease button
        decreaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
    			GroceryItem object = (GroceryItem) decreaseButton.getClientProperty("object");
                int quantity = Integer.valueOf((quantityField.getText()));
                main.getController().editOrder("delete", object);
                if (quantity > 1) {
                    quantity--;
                    quantityField.setText(String.valueOf(quantity));
                    increaseButton.setEnabled(true);
                    
                } else {
//                	if (main.getController().ds.getCurrentOrder().getGroceryItems().length == 0){
//                    	logoutButton.setVisible(true);
//                    }
                	parentPanel.remove(quantityPanel);
                	parentPanel.add(addToCartButton, BorderLayout.SOUTH);
                	parentPanel.revalidate();
                	parentPanel.repaint();
                }
                
            }
        });

      //increase button
        increaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
    			GroceryItem object = (GroceryItem) increaseButton.getClientProperty("object");
    			System.out.println(object.getQuantity());
            	int quantity = Integer.parseInt(quantityField.getText());
            	if (object.getQuantity()>0){
                    quantity++;
                    quantityField.setText(String.valueOf(quantity));
                    main.getController().editOrder("add", object);
                    
                    if (object.getQuantity() == 0){
                    	increaseButton.setEnabled(false);
                    }
            	}
//            	else if (object.getQuantity() == 0){
//                	increaseButton.setEnabled(false);
//            	}
                

            }
        });
        

//        this.logoutButton.setVisible(false);


        quantityPanel.add(decreaseButton, BorderLayout.WEST);
        quantityPanel.add(quantityField, BorderLayout.CENTER);
        quantityPanel.add(increaseButton, BorderLayout.EAST);
        parentPanel.add(quantityPanel, BorderLayout.SOUTH);

        parentPanel.revalidate();
        parentPanel.repaint();
    }

}