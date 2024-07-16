package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.MainFrame;
import data.GroceryItem;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class StaffMenu extends JPanel {
    private MainFrame main;
    private Vector<GroceryItem> inventory;
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

    public StaffMenu(MainFrame main) {
        this.main = main;
        this.main.setTitle("Joy MiniMart - Staff Menu");
        this.inventory = this.main.getController().getInventory();

        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout(0, 0));

        this.gridPanel = new JPanel(new GridLayout(0, 3, 10, 10)); // rows, cols, hgap, vgap
        this.gridPanel.setBorder(new EmptyBorder(0, 10, 10, 10)); // top, left, bottom, right
        this.gridPanel.setBackground(UIManager.getColor("OptionPane.background"));

        for (int i = 0; i < this.inventory.size(); i++) {
            GroceryItem currentItem = this.inventory.elementAt(i);
            
            if (currentItem.getQuantity() == 0){
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
            this.priceLabel = new JLabel("$" + currentItem.getPrice().toString());
            this.priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.priceLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
            this.itemButton.add(this.priceLabel, BorderLayout.SOUTH);

            this.itemPanel.add(this.itemButton, BorderLayout.CENTER);

            // Add to Cart Button
            JButton addToCartButton = new JButton("Add to Cart");
            addToCartButton.putClientProperty("object", currentItem);
            addToCartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showQuantityControls(addToCartButton, currentItem);
                }
            });
            this.itemPanel.add(addToCartButton, BorderLayout.SOUTH);

            this.gridPanel.add(this.itemPanel);
        }

        // Scroll Pane
        this.scrollPane = new JScrollPane(gridPanel);
        this.scrollPane.setBorder(new EmptyBorder(0, 2, 2, 2));
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        this.add(this.scrollPane);

        // Top Panel
        this.topPanel = new JPanel();
        this.lblGroceryItems = new JLabel("Grocery Items");
        this.lblGroceryItems.setPreferredSize(new Dimension(200, 60));
        this.lblGroceryItems.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.lblGroceryItems.setHorizontalAlignment(SwingConstants.CENTER);
        this.topPanel.add(this.lblGroceryItems);
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
        JOptionPane.showMessageDialog(this, "Logged Out Successfully", "Notification", JOptionPane.INFORMATION_MESSAGE);
        this.main.showLoginScreen();
    }

    private void showQuantityControls(JButton addToCartButton, GroceryItem currentItem) {
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
        this.main.getController().editOrder("add", currentItem);
        
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
                	if (main.getController().ds.getCurrentOrder().getGroceryItems().size() == 0){
                    	logoutButton.setVisible(true);
                    }
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
            	int quantity = Integer.parseInt(quantityField.getText());
            	if (object.getQuantity()>0){
                    quantity++;
                    quantityField.setText(String.valueOf(quantity));
                    main.getController().editOrder("add", object);
            	}
            	else if (object.getQuantity() == 0){
                	increaseButton.setEnabled(false);
            	}
                

            }
        });
        

        this.logoutButton.setVisible(false);


        quantityPanel.add(decreaseButton, BorderLayout.WEST);
        quantityPanel.add(quantityField, BorderLayout.CENTER);
        quantityPanel.add(increaseButton, BorderLayout.EAST);
        parentPanel.add(quantityPanel, BorderLayout.SOUTH);

        parentPanel.revalidate();
        parentPanel.repaint();
    }

}
