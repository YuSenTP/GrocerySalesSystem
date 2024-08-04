/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.MainFrame;
import data.GroceryItem;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;

/**
 * @author Yu Sen
 *
 */
public class ManagerMenu extends JPanel{
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
	private JButton backButton;
	private JButton createItemButton;
	private GroceryItem currentItem;
	private JPanel pricePanel;
	private JComboBox comboBox;
	private JLabel lblView;
	private String[] category;
	
	private static String categorySele = "All";

	
	public ManagerMenu(MainFrame main){
		this.main = main;
		this.main.setTitle("Joy MiniMart - Manager Menu");
		this.inventory = this.main.getController().getInventory();
		this.category = this.main.getController().getCategory();
		
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout(0, 0));
		
		//Creates gridlayout
        this.gridPanel = new JPanel(new GridLayout(0, 3, 10, 10));// rows, cols, hgap, vgap
        this.gridPanel.setBorder(new EmptyBorder(0, 10, 10, 10));// top, left, bottom, right
        this.gridPanel.setBackground(UIManager.getColor("OptionPane.background"));
        
        //Loops through inventory
        for (int i = 0; i < this.inventory.length; i++) {
        	this.currentItem = this.inventory[i];
        	
        	//Checks if item's category is selected
        	if (ManagerMenu.categorySele.equals("All") != true){
        		if (this.currentItem.getCategory().equals(ManagerMenu.categorySele) != true){
        			continue;
        		}
        	}
        	
        	//Creates itemPanel
        	this.itemPanel = new JPanel(new BorderLayout());
        	
        	//Creates itemButton
            this.itemButton = new JButton();
//            this.itemButton.setFocusable(false); //CAN USE
            this.itemButton.setLayout(new BorderLayout());
            this.itemButton.setBackground(Color.WHITE);
    		this.itemButton.setPreferredSize(new Dimension(213, 143));          
            
            //Item Action
    		this.itemButton.setActionCommand(this.currentItem.getName()); // not need just nice to have
    		this.itemButton.putClientProperty("object", this.currentItem); //associate each button to each GroceryItem object
        	this.itemButton.addActionListener(new ActionListener(){
        		@Override
        		public void actionPerformed(ActionEvent e){
        			//Get the source of the action
        			JButton sourceBtn = (JButton) e.getSource();
        			GroceryItem object = (GroceryItem) sourceBtn.getClientProperty("object");
        			String buttonName = e.getActionCommand();
        			showItemScreen(buttonName, object);
        		}
        	});

            //Item Pic
            this.itemPic = new ImageIcon(this.currentItem.getPicFile());
            Image img = this.itemPic.getImage() ;  
    		Image newimg = img.getScaledInstance( 90, 90,  java.awt.Image.SCALE_SMOOTH ) ;  
    		this.itemPic = new ImageIcon(newimg);
    		this.itemButton.setIcon(this.itemPic);
            
        	//Item Name
            this.nameLabel = new JLabel(this.currentItem.getName());
            this.nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.nameLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
            this.nameLabel.setOpaque(false);
            this.itemButton.add(this.nameLabel, BorderLayout.NORTH);
   
            //Item Price
            this.pricePanel = new JPanel();
            this.pricePanel.setOpaque(false);
            
            //Original Price
            this.priceLabel = new JLabel("$" + this.currentItem.getPrice().toString());
            this.priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.priceLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
            
            // If On Sale
            if (this.currentItem.getOnSale()){
            	//Sets original price with strike through and grayed
            	Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) this.priceLabel.getFont().getAttributes(); // HashMap Something like a dictonary in python where data are stored as key value pair
            	attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON); // Add new attribute to HashMap
            	this.priceLabel.setFont(new Font(attributes));
            	this.priceLabel.setForeground(Color.GRAY);

            	this.pricePanel.add(this.priceLabel);
            	
            	//Sale Price
            	JLabel saleLabel = new JLabel("$" + this.currentItem.getOnSalePrice());
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

            this.gridPanel.add(this.itemPanel);
            
            
        }
        
        //Scroll Pane
        this.scrollPane = new JScrollPane(gridPanel);
        this.scrollPane.setBorder(new EmptyBorder(0, 2, 2, 2));
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        this.add(this.scrollPane);
        
        //Top Panel
        this.topPanel = new JPanel();
        this.topPanel.setPreferredSize(new Dimension(10, 70));
        this.topPanel.setLayout(null);

        //Grocery Item Label
        this.lblGroceryItems = new JLabel("Grocery Items");
        this.lblGroceryItems.setBounds(275, 5, 200, 60);
        this.lblGroceryItems.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.lblGroceryItems.setPreferredSize(new Dimension(200, 60));
        this.lblGroceryItems.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.lblGroceryItems.setHorizontalAlignment(SwingConstants.CENTER);
        this.topPanel.add(this.lblGroceryItems);
        
        
        //Combobox to select category
        this.comboBox = new JComboBox(Arrays.copyOfRange(this.category, 0, this.category.length - 1));
        this.comboBox.setBounds(588, 24, 111, 25);
        this.comboBox.setSelectedItem(ManagerMenu.categorySele);
        this.comboBox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		ManagerMenu.categorySele = comboBox.getSelectedItem().toString();
        		System.out.println(comboBox.getSelectedItem().toString());
        		main.showManagerMenu();
        	}
        });;
        
        //View label
        this.lblView = new JLabel("View:");
        this.lblView.setBounds(529, 25, 47, 21);
        this.lblView.setFont(new Font("Tahoma", Font.BOLD, 17));
        this.topPanel.add(this.lblView);
        this.comboBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.comboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.comboBox.setFocusable(false);
        this.topPanel.add(this.comboBox);
        
        this.add(this.topPanel, BorderLayout.NORTH);
        
        //Bottom Panel
        this.bottomPanel = new JPanel();
        this.bottomPanel.setBackground(Color.WHITE);  
        this.bottomPanel.setLayout(new BorderLayout(0, 0));
        this.bottomPanel.setBorder(new EmptyBorder(10, 20, 10, 20)); //Used to create white space

        //Back button
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

        //Create Item button
        this.createItemButton = new JButton("Create Item");
        this.createItemButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		createItem();
        	}
        });
        this.createItemButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.createItemButton.setPreferredSize(new Dimension(140, 40));
        this.bottomPanel.add(this.createItemButton, BorderLayout.EAST);
        
        this.add(this.bottomPanel, BorderLayout.SOUTH);   

	}
    
	//Show Item Details
	private void showItemScreen(String name, GroceryItem item){
		System.out.println(name);
		System.out.println(item.getCategory());
		this.main.showEditGroceryItem(item);
	}
	
	//Back to Manger Home
	private void back(){
		ManagerMenu.categorySele = "All";
		this.main.showManagerHome();
	}
	
	//Show Create item page
	private void createItem(){
		this.main.showCreateItem();
	}
}
