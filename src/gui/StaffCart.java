package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.MainFrame;
import data.GroceryItem;
import data.Order;

import java.util.Vector;

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


    public StaffCart(MainFrame main) {
        this.main = main;
        this.main.setTitle("Joy MiniMart - StaffCart");

        this.setBackground(UIManager.getColor("OptionPane.background"));
        this.setLayout(new BorderLayout(0, 0));

        // Top Label
        this.lblEditItem = new JLabel("Staff Cart");
        this.lblEditItem.setBackground(UIManager.getColor("Button.background"));
        this.lblEditItem.setPreferredSize(new Dimension(87, 60));
        this.lblEditItem.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.lblEditItem.setHorizontalAlignment(SwingConstants.CENTER);
        add(this.lblEditItem, BorderLayout.NORTH);

        // Middle Panel (Scrollable)
        this.itemsPanel = new JPanel();
        this.itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS)); //box layout instead of grid layout
        this.itemsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.scrollPane = new JScrollPane(itemsPanel);
//        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.scrollPane.setBorder(new EmptyBorder(0, 2, 2, 2));
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        this.add(this.scrollPane, BorderLayout.CENTER);

        // Bottom Panel
        this.bottomPanel = new JPanel();
        this.bottomPanel.setBackground(Color.WHITE);  
        this.bottomPanel.setLayout(new BorderLayout(0, 0));

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

    public void updateCartItems() {
        itemsPanel.removeAll();
        Order currentOrder = main.getController().getCurrentOrder();
        GroceryItem[] cartItems = currentOrder.getGroceryItems();

        for (int i = 0; i < cartItems.length; i++) {
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
            itemPanel.add(picLabel, BorderLayout.WEST);

            // Item Info Panel
            JPanel infoPanel = new JPanel(new GridLayout(2, 1));
            JLabel nameLabel = new JLabel(currentItem.getName());
            nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
            infoPanel.add(nameLabel);

            JLabel priceLabel = new JLabel("$		" + currentItem.getPrice());
            infoPanel.add(priceLabel);
            itemPanel.add(infoPanel, BorderLayout.CENTER);

            // Quantity Panel
            JPanel quantityPanel = new JPanel(new BorderLayout(5, 0));
            JButton decreaseButton = new JButton("-");
            JLabel quantityLabel = new JLabel(String.valueOf(currentItem.getQuantity()));
            JButton increaseButton = new JButton("+");
            
            boolean available = main.getController().checkInventoryAvaiblility(currentItem);
            increaseButton.setEnabled(available);
            

            decreaseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateItemQuantity(currentItem, currentItem.getQuantity() - 1, "delete", decreaseButton);
                }
            });

            increaseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateItemQuantity(currentItem, currentItem.getQuantity() + 1, "add", increaseButton);
                }
            });
            
            quantityPanel.add(decreaseButton, BorderLayout.WEST);
            quantityPanel.add(quantityLabel, BorderLayout.CENTER);
            quantityPanel.add(increaseButton, BorderLayout.EAST);

            itemPanel.add(quantityPanel, BorderLayout.EAST);

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
//    		 		boolean noMore = main.getController().cartUpdateInventory("add", item);
//    		 		if (noMore){
//    		 			System.out.println("Yes" + button.isEnabled());
//    		 			button.setEnabled(false);
//    		 		}
    		 	}
    		 	else{
    		 		main.getController().cartUpdateInventory("delete", item);
    		 	}
    	        main.getController().getCurrentOrder().calculateTotalCost();
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
        Order currentOrder = main.getController().getCurrentOrder();
        BigDecimal totalCost = currentOrder.calculateTotalCost();
        totalLabel.setText("Total: $" + currentOrder.getTotalCost());
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