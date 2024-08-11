package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.math.BigDecimal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import controller.MainFrame;
import data.GroceryItem;
import data.Order;

import java.util.Map;
import java.util.Vector;
//ADD STUFF - WHEN CART IS EMPTY AND CLEAR CART BUTTON IS PRESSED, DIALOGUE OPENS THAT CART IS ALREADY CLEARED. 
public class StaffCart extends JPanel {
    private MainFrame main;
    private JLabel lblEditItem;
    private JPanel middlePanel;
    private JPanel bottomPanel;
    private JButton backButton; 
    private JButton confirmButton;
    private JScrollPane scrollPane;
    private JPanel itemsPanel;
    private JLabel totalLabel;
	private ImageIcon itemPic;
	private JButton clearCartButton;
	private JButton resetAllButton;


    public StaffCart(MainFrame main) {
        this.main = main;
        this.main.setTitle("Joy MiniMart - StaffCart");

        this.setBackground(UIManager.getColor("OptionPane.background"));
        this.setLayout(new BorderLayout(0, 0));

        /// Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setBackground(UIManager.getColor("Button.background"));
        topPanel.setPreferredSize(new Dimension(getWidth(), 70)); // Increased height to 70
        topPanel.setLayout(null); // Set to null layout for absolute positioning

        // Top label
        this.lblEditItem = new JLabel("Staff Cart");
        this.lblEditItem.setHorizontalAlignment(SwingConstants.CENTER);
        this.lblEditItem.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.lblEditItem.setBounds(275, 5, 200, 60); // Set specific bounds
        topPanel.add(this.lblEditItem);

        // Clear cart button
        this.clearCartButton = new JButton("Clear Cart");
        this.clearCartButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        this.clearCartButton.setBounds(588, 30, 111, 30); // Set specific bounds
        this.clearCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearCart();
            }
        });
        topPanel.add(this.clearCartButton);
        
        // Reset All button
        this.resetAllButton = new JButton("Reset All");
        this.resetAllButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        this.resetAllButton.setBounds(10, 30, 111, 30); // Same size and vertical position as Clear Cart button
        this.resetAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetAllItems();
            }
        });
        topPanel.add(this.resetAllButton);

        add(topPanel, BorderLayout.NORTH);
        
        // Middle Panel (Scrollable)
        this.itemsPanel = new JPanel();
        this.itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS)); //box layout instead of grid layout
        this.itemsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.scrollPane = new JScrollPane(itemsPanel);
        this.scrollPane.setBorder(new EmptyBorder(0, 2, 2, 2));
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        this.add(this.scrollPane, BorderLayout.CENTER);

        // Bottom Panel
        this.bottomPanel = new JPanel();
        this.bottomPanel.setBackground(Color.WHITE);  
        this.bottomPanel.setLayout(new BorderLayout(0, 0));
        this.bottomPanel.setBorder(new EmptyBorder(10, 20, 10, 20)); //Used to create white space

        this.backButton = new JButton("Back");
        this.backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.backButton.setPreferredSize(new Dimension(100, 40));
        this.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.showStaffMenu();
            }
        });
        this.bottomPanel.add(this.backButton, BorderLayout.WEST);
        
        // Move the totalLabel to the left of the confirmButton

        this.confirmButton = new JButton("Confirm Order");
        this.confirmButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.confirmButton.setPreferredSize(new Dimension(150, 40));
        this.confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmOrder();
            }
        });
        this.bottomPanel.add(this.confirmButton, BorderLayout.EAST);

        this.totalLabel = new JLabel("Total: $0.00");
        this.totalLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        this.totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.bottomPanel.add(this.totalLabel, BorderLayout.CENTER);

        this.add(this.bottomPanel, BorderLayout.SOUTH);

        updateCartItems();
    }
    
    private void clearCart() {
        // Check if the cart is empty
        if (main.getController().getCurrentOrder().getGroceryItems().length == 0) {
            JOptionPane.showMessageDialog(this, "The cart is already empty.", "Cart Empty", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear the cart?", "Clear Cart", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
            	main.getController().clearCurrentOrder();
                updateCartItems();
                updateTotalLabel();
                JOptionPane.showMessageDialog(this, "Cart has been cleared.", "Cart Cleared", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    private void resetAllItems() {
        Order currentOrder = main.getController().getCurrentOrder();
        GroceryItem[] cartItems = currentOrder.getGroceryItems();
        
        if (cartItems.length == 0) {
            JOptionPane.showMessageDialog(this, "The cart is empty. Nothing to reset.", "Cart Empty", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        boolean alreadyResetted = true;
        for (int i = 0; i < cartItems.length; i++) {
            if (cartItems[i].getQuantity() > 1) {
            	alreadyResetted = false;
                break;
            }
        }

        if (alreadyResetted) {
            JOptionPane.showMessageDialog(this, "All items are already set to 1.", "Already Reset", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to reset all items to quantity 1?", "Reset All Items", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            for (GroceryItem item : cartItems) {
                int currentQuantity = item.getQuantity();
                if (currentQuantity > 1) {
                    // Remove the excess quantity from the cart
                    for (int i = currentQuantity; i > 1; i--) {
                        main.getController().cartUpdateInventory("delete", item);
                    }
                    // Set the quantity to 1
                    item.setQuantity(1);
                }
            }
            main.getController().calculateTotalCost();
            updateCartItems();
            updateTotalLabel();
            JOptionPane.showMessageDialog(this, "All items have been reset to quantity 1.", "Reset Complete", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void updateCartItems() {
        itemsPanel.removeAll();
        Order currentOrder = main.getController().getCurrentOrder();
        GroceryItem[] cartItems = currentOrder.getGroceryItems();


        for (int i = 0; i < cartItems.length; i++) {
            /*Structure for each item
             * itemPanel
             * 	- picLabel
             * 	- infoPanel
             * 		- name
             * 		- pricePanel
             * 			- priceLabel
             * 			- onSalePrice (if onsale)
             * 	- controlPanel
             * 		- quantityPanel
             * 			- decreaseButton
             * 			- quantityLabel
             * 			- increaseButton
             * 		- resetButton
             * 		- deleteItemButton
             */
            GroceryItem currentItem = cartItems[i];
            
            JPanel itemPanel = new JPanel(new BorderLayout(10, 0));
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100)); // Set fixed height

            // Item Pic
            JLabel picLabel = new JLabel();
            this.itemPic = new ImageIcon(currentItem.getPicFile());
            Image img = this.itemPic.getImage();
            Image newimg = img.getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH);
            this.itemPic = new ImageIcon(newimg);
            picLabel.setIcon(this.itemPic);
            picLabel.setBorder(new EmptyBorder(0,5,0,5));
            itemPanel.add(picLabel, BorderLayout.WEST);

            // Item Info Panel
            JPanel infoPanel = new JPanel(new GridLayout(2, 1));
            infoPanel.setBorder(new EmptyBorder(0,15,0,25));
            
            //Name
            JLabel nameLabel = new JLabel(currentItem.getName());
            nameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
            nameLabel.setVerticalAlignment(SwingConstants.CENTER);
            infoPanel.add(nameLabel);

            //Price Panel  -- starts from left and zero h&v gap
            JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

            //Price
            JLabel priceLabel = new JLabel("$" + currentItem.getPrice());
            priceLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
            
            // If On Sale
            if (currentItem.getOnSale()){
            	Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) priceLabel.getFont().getAttributes(); // HashMap Something like a dictonary in python where data are stored as key value pair
            	attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON); // Add new attribute to HashMap
            	priceLabel.setFont(new Font(attributes));
            	priceLabel.setForeground(Color.GRAY);
            	pricePanel.add(priceLabel);

            	JLabel saleLabel = new JLabel(" $" + currentItem.getOnSalePrice());
            	saleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            	saleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
            	saleLabel.setForeground(Color.RED);
            	pricePanel.add(saleLabel);
            }
            else{
            	pricePanel.add(priceLabel);
            }
            
            infoPanel.add(pricePanel);

            itemPanel.add(infoPanel, BorderLayout.CENTER);
            
            
            //Quantity
            JLabel quantityLabel = new JLabel(String.valueOf(currentItem.getQuantity()));
            quantityLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
            quantityLabel.setBackground(Color.WHITE);
            quantityLabel.setOpaque(true);
            quantityLabel.setBorder(new MatteBorder(0, 1, 0, 1, Color.black));
            quantityLabel.setPreferredSize(new Dimension(50, 50));
            quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
            
            //Minus Button
            JButton decreaseButton = new JButton("-");
            decreaseButton.setBackground(Color.WHITE);
            decreaseButton.setFont(new Font("Tahoma", Font.BOLD, 18));
            decreaseButton.setBorder(BorderFactory.createLineBorder(Color.black, 0));
            decreaseButton.setPreferredSize(new Dimension(50, 50));
            decreaseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateItemQuantity(currentItem, currentItem.getQuantity() - 1, "delete", decreaseButton);
                }
            });
            
            //Plus Button
            boolean available = main.getController().checkInventoryAvaiblility(currentItem);
            JButton increaseButton = new JButton("+");
            increaseButton.setBackground(Color.WHITE);
            increaseButton.setFont(new Font("Tahoma", Font.BOLD, 18));
            increaseButton.setBorder(BorderFactory.createLineBorder(Color.black, 0));
            increaseButton.setEnabled(available);
            increaseButton.setPreferredSize(new Dimension(50, 50));
            increaseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateItemQuantity(currentItem, currentItem.getQuantity() + 1, "add", increaseButton);
                }
            });
            
            //Add Mouse listener for max quantity        
            increaseButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (!increaseButton.isEnabled()) {
                        JOptionPane.showMessageDialog(StaffCart.this, "Max Quantity Reached", " Notification", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
            
            //Delete all item Button
            ImageIcon dIcon = new ImageIcon("./img/Trash.png");
            Image dimg = dIcon.getImage();
            Image newdimg = dimg.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
            JButton deleteItemButton = new JButton(new ImageIcon(newdimg));
            deleteItemButton.setBackground(Color.WHITE);
            deleteItemButton.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            deleteItemButton.setPreferredSize(new Dimension(50, 50));
            deleteItemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deleteItem(currentItem);
                }
            });

            // Reset Button
            ImageIcon rIcon = new ImageIcon("./img/ResetG.png");
            Image rimg = rIcon.getImage();
            Image newrimg = rimg.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
            JButton resetButton = new JButton(new ImageIcon(newrimg));
            resetButton.setBorder(BorderFactory.createLineBorder(new Color(50, 205, 50), 1));
            resetButton.setBackground(Color.WHITE);
            resetButton.setPreferredSize(new Dimension(50, 50));
            resetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    resetItemQuantity(currentItem);
                }
            });

            //Quantity Panel - holds Plus button, Quantity Label and Minus button
            JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            quantityPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            quantityPanel.add(decreaseButton);
            quantityPanel.add(quantityLabel);
            quantityPanel.add(increaseButton);
            
            //Control Panel Holds Quantity Panel, reset button and delete button
            JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
            controlPanel.setBorder(new EmptyBorder(25, 0, 25, 0));
            controlPanel.add(quantityPanel);
            controlPanel.add(resetButton);
            controlPanel.add(deleteItemButton);
            
            
            itemPanel.add(controlPanel, BorderLayout.EAST);

            itemsPanel.add(itemPanel);
            itemsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some space between the items

        }

        itemsPanel.revalidate();
        itemsPanel.repaint();

        updateTotalLabel();
    }

    //This GroceryItem is object of Current Order, Not inventory
    private void updateItemQuantity(GroceryItem item, int newQuantity, String choice, JButton button) { //if quantity more than 0, adjust quantity. If lesser, delete the item from cart
    	 if (newQuantity > 0) {
    	        item.setQuantity(newQuantity);
    		 	if (choice.equals("add")){
    		 		main.getController().cartUpdateInventory("add", item);
    		 	}
    		 	else{
    		 		main.getController().cartUpdateInventory("delete", item);
    		 	}
    	        main.getController().calculateTotalCost();
    	        updateCartItems();
    	        updateTotalLabel();
    	        System.out.println("Quantity Adjusted");
    	    } else {
    	    	main.getController().cartUpdateInventory("delete", item);
    	        main.getController().getCurrentOrder().deleteGroceryItem(item);
    	        updateCartItems();
    	        updateTotalLabel();
    	    }
    }

    private void updateTotalLabel() {
        BigDecimal totalCost = main.getController().calculateTotalCost();
        totalLabel.setText("Total: $" + totalCost);
    }
    
    private void deleteItem(GroceryItem item) {
        int result = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this item?", 
            "Delete Item", 
            JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
        	for (int i = 0; i < item.getQuantity(); i++) {
                main.getController().cartUpdateInventory("delete", item);
            }
            main.getController().getCurrentOrder().deleteGroceryItem(item);
            updateCartItems();
            updateTotalLabel();
        }
    }
    
    private void resetItemQuantity(GroceryItem item) {
        int currentQuantity = item.getQuantity();
        if (currentQuantity == 1){
            JOptionPane.showMessageDialog(this, "Item is already set to quantity 1", "Notification", JOptionPane.INFORMATION_MESSAGE);
        }
        if (currentQuantity > 1) {
            // Remove the excess quantity from the cart
        	 int result = JOptionPane.showConfirmDialog(this, 
        	            "Are you sure you want to reset this item to quantity 1?", 
        	            "Reset Item", 
        	            JOptionPane.YES_NO_OPTION);
        	 if (result == JOptionPane.YES_OPTION) {
            for (int i = currentQuantity; i > 1; i--) {
                main.getController().cartUpdateInventory("delete", item);
                item.setQuantity(1);
            }
        	 }
            // Set the quantity to 1
            main.getController().calculateTotalCost();
            updateCartItems();
            updateTotalLabel();
        }
    }

    private void confirmOrder() {
        Order currentOrder = main.getController().getCurrentOrder();
        if (currentOrder.getGroceryItems().length == 0) {
            JOptionPane.showMessageDialog(this, "Cart is empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int result = JOptionPane.showConfirmDialog(this, "Confirm order?", "Order Confirmation", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
        	int orderNumber = main.getController().generateOrderNumber();
            currentOrder.setOrderID(orderNumber);
            main.getController().confirmOrder();
            JOptionPane.showMessageDialog(this, "Order confirmed!", "Success", JOptionPane.INFORMATION_MESSAGE);
            main.showStaffMenu();
        }
    }
}