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
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.MainFrame;
import data.GroceryItem;

/**
 * @author Yu Sen
 *
 */
public class ManagerMenu extends JPanel{
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
	private JButton backButton;
	private JButton createItemButton;
	private GroceryItem currentItem;
//	private String[][] items = {
//            {"Tomato", "$33.48", "./img/tomato.jpg"},
//            {"Lettuce", "$96.76", "./img/lettuce.jpg"},
//            {"Watermelon", "$14.03", "./img/watermelon1.jpg"},
//            {"Apple", "$24.26", "green_apple.png"},
//            {"Orange", "$90.86", "orange.png"},
//            {"Lime", "$98.32", "lime.png"},
//            {"Cucumber", "$8.46", "cucumber.png"},
//            {"Peach", "$14.73", "peach.png"},
//            {"Apple", "$12.24", "red_apple.png"},
//            {"Avocado", "$18.76", "avocado.png"},
//            {"Carrot", "$10.11", "carrot.png"},
//            {"Lemon", "$68.99", "lemon.png"},
//            {"Carrot", "$45.53", "carrot.png"},
//            {"Banana", "$5.83", "banana.png"},
//            {"Maracuya", "$9.46", "maracuya.png"},
//            {"Cherry", "$23.16", "cherry.png"}
//        };

	
	public ManagerMenu(MainFrame main){
		this.main = main;
		this.main.setTitle("Joy MiniMart - Manager Menu");
		this.inventory = this.main.getController().getInventory();
		
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout(0, 0));
		
        this.gridPanel = new JPanel(new GridLayout(0, 3, 10, 10));// rows, cols, hgap, vgap
        this.gridPanel.setBorder(new EmptyBorder(0, 10, 10, 10));// top, left, bottom, right
        this.gridPanel.setBackground(UIManager.getColor("OptionPane.background"));
        
        for (int i = 0; i < this.inventory.size(); i++) {
        	this.currentItem = this.inventory.elementAt(i);
        	
            this.itemPanel = new JPanel(new BorderLayout());
            
            this.itemButton = new JButton();
//            this.itemButton.setFocusable(false); //CAN USE
            this.itemButton.setLayout(new BorderLayout());
            this.itemButton.setBackground(Color.WHITE);
    		this.itemButton.setPreferredSize(new Dimension(213, 143));          
            
            //Item Action
    		this.itemButton.setActionCommand(this.currentItem.getName());
    		this.itemButton.putClientProperty("object", this.currentItem); //associate each button to each GroceryItem object
        	this.itemButton.addActionListener(new ActionListener(){
        		@Override
        		public void actionPerformed(ActionEvent e){
        			//TO CHANGE
//        			System.out.println(e.getSource());
        			JButton sourceBtn = (JButton) e.getSource();
        			GroceryItem object = (GroceryItem) sourceBtn.getClientProperty("object");
//        			System.out.println(object);
        			
        			String buttonName = e.getActionCommand();
        			showItemScreen(buttonName, object);
//        			main.getController();
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
            this.itemButton.add(this.nameLabel, BorderLayout.NORTH);
            
            //Item Price
            this.priceLabel = new JLabel("$" + this.currentItem.getPrice().toString());
            this.priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.priceLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
            this.itemButton.add(this.priceLabel, BorderLayout.SOUTH);
            
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

        this.lblGroceryItems = new JLabel("Grocery Items");
        this.lblGroceryItems.setPreferredSize(new Dimension(200, 60));
        this.lblGroceryItems.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.lblGroceryItems.setHorizontalAlignment(SwingConstants.CENTER);
        this.topPanel.add(this.lblGroceryItems);

//        JComboBox comboBox = new JComboBox();
//        panel_1.add(comboBox);
        
        this.add(this.topPanel, BorderLayout.NORTH);

        //Bottom Panel
        this.bottomPanel = new JPanel();
        this.bottomPanel.setBackground(Color.WHITE);  
        this.bottomPanel.setLayout(new BorderLayout(0, 0));

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
    
	private void showItemScreen(String name, GroceryItem item){
		System.out.println(name);
		this.main.showEditGroceryItem(item);
	}
	
	private void back(){
		this.main.showManagerHome();
	}
	
	private void createItem(){
		this.main.showCreateItem();
	}
	

}
