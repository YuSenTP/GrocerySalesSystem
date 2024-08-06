package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.MainFrame;
import data.GroceryItem;
import data.Order;

public class ManagerOrderDetails extends JPanel {
    private MainFrame main;
    private Order order;
    private JLabel lblOrderNum;
    private JPanel bottomPanel;
    private JButton backButton;
    private JPanel middlePanel;
    private JScrollPane scrollPane;
    private JPanel boxPanel;
    private ImageIcon itemPic;
	private JLabel totalCostLabel;
	private JLabel cashierLabel;

    public ManagerOrderDetails(MainFrame main, Order order) {
        this.main = main;
        this.order = order;
        
        this.main.setTitle("Joy MiniMart - Order Details");
        
        this.setBackground(UIManager.getColor("OptionPane.background"));
        this.setLayout(new BorderLayout(0, 0));
        this.setPreferredSize(new Dimension(400, 500));
        
        // Top Label
        this.lblOrderNum = new JLabel("Order " + order.getOrderID());
        this.lblOrderNum.setBackground(UIManager.getColor("Button.background"));
        this.lblOrderNum.setPreferredSize(new Dimension(87, 60));
        this.lblOrderNum.setHorizontalAlignment(SwingConstants.CENTER);
        this.lblOrderNum.setFont(new Font("Tahoma", Font.BOLD, 23));
        this.add(this.lblOrderNum, BorderLayout.NORTH);
        
        // Bottom Panel
        this.bottomPanel = new JPanel();
        this.bottomPanel.setBackground(Color.WHITE);
        this.bottomPanel.setLayout(new BorderLayout(0, 0));
        this.bottomPanel.setBorder(new EmptyBorder(10, 20, 10, 20)); //Used to create white space
        
        //Back Button
        this.backButton = new JButton("Back");
        this.backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.backButton.setPreferredSize(new Dimension(100, 40));
        this.backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                back();
            }
        });
        this.bottomPanel.add(this.backButton, BorderLayout.WEST);
        
        //Cashier Name
        this.cashierLabel = new JLabel("Cashier " + order.getStaffName());
        this.cashierLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        this.cashierLabel.setBorder(new EmptyBorder(0, 60, 0, 0)); //t l b r
        this.cashierLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.bottomPanel.add(this.cashierLabel, BorderLayout.CENTER);
        
        // Total Label
        this.totalCostLabel = new JLabel("Total Cost: $" + order.getTotalCost());
        this.totalCostLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        this.totalCostLabel.setBorder(new EmptyBorder(0, 0, 0, 10)); //t l b r
        this.totalCostLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.bottomPanel.add(this.totalCostLabel, BorderLayout.EAST);
        
        this.add(this.bottomPanel, BorderLayout.SOUTH);
        
        // Middle Panel
        this.middlePanel = new JPanel(new BorderLayout());
        this.middlePanel.setBackground(UIManager.getColor("Button.background"));
        this.add(this.middlePanel, BorderLayout.CENTER);
        
        this.boxPanel = new JPanel();
        this.boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        
        GroceryItem[] temp = this.order.getGroceryItems();
        for(int i = 0; i < temp.length; i++) {
            GroceryItem currentItem = temp[i];
            JPanel itemPanel = new JPanel(new BorderLayout(10, 0));
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

            // Item Pic
            JLabel picLabel = new JLabel();
            this.itemPic = new ImageIcon(currentItem.getPicFile());
            Image img = this.itemPic.getImage();
            Image newimg = img.getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH);
            this.itemPic = new ImageIcon(newimg);
            picLabel.setIcon(this.itemPic);
            itemPanel.add(picLabel, BorderLayout.WEST);

            // Item Info Panel
            JPanel infoPanel = new JPanel(new GridLayout(3, 1));
            JLabel nameLabel = new JLabel(currentItem.getName());
            nameLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
            infoPanel.add(nameLabel);

            
            //Price Panel starts from left and zero h&v gap
            JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            pricePanel.setBorder(new EmptyBorder(9, 0, 0, 0));
            JLabel priceheader = new JLabel("Price:");
            priceheader.setFont(new Font("Tahoma", Font.BOLD, 14));
            pricePanel.add(priceheader);
            
            //Price Label
            JLabel priceLabel = new JLabel(" $" + currentItem.getPrice());
            priceLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
            // If On Sale
            if (currentItem.getOnSale()){
            	//Sets regular price to gray and strike through
            	Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) priceLabel.getFont().getAttributes(); // HashMap Something like a dictonary in python where data are stored as key value pair
            	attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON); // Add new attribute to HashMap
            	priceLabel.setFont(new Font(attributes));
            	priceLabel.setForeground(Color.GRAY);
            	pricePanel.add(priceLabel);
            	
            	//OnSale Price Label
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
            
            //Quantity Label
            JLabel quantityLabel = new JLabel("Quantity: " + currentItem.getQuantity());
            quantityLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
            infoPanel.add(quantityLabel);

            itemPanel.add(infoPanel, BorderLayout.CENTER);

            this.boxPanel.add(itemPanel);
            this.boxPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some space between items
        }
        
        // Scroll Pane
        this.scrollPane = new JScrollPane(this.boxPanel);
        this.scrollPane.setBorder(new EmptyBorder(0, 2, 2, 2));
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        this.middlePanel.add(this.scrollPane, BorderLayout.CENTER);
    }
    
    private void back() {
        this.main.showSales();
    }
}