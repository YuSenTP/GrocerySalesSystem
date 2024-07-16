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
	private JPanel pricePanel;
	private JComboBox comboBox;
	private JLabel lblFiller;
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
		
        this.gridPanel = new JPanel(new GridLayout(0, 3, 10, 10));// rows, cols, hgap, vgap
        this.gridPanel.setBorder(new EmptyBorder(0, 10, 10, 10));// top, left, bottom, right
        this.gridPanel.setBackground(UIManager.getColor("OptionPane.background"));
        
        for (int i = 0; i < this.inventory.size(); i++) {
        	this.currentItem = this.inventory.elementAt(i);
        	
        	if (ManagerMenu.categorySele.equals("All") != true){
        		if (this.currentItem.getCategory().equals(ManagerMenu.categorySele) != true){
        			continue;
        		}
        	}
        	
        	this.itemPanel = new JPanel(new BorderLayout());
        	

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
            this.nameLabel.setOpaque(false);
            this.itemButton.add(this.nameLabel, BorderLayout.NORTH);
   
            //Item Price
            
            this.pricePanel = new JPanel();
            this.pricePanel.setOpaque(false);
            
            this.priceLabel = new JLabel("$" + this.currentItem.getPrice().toString());
            this.priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.priceLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
            
            // If On Sale
            if (this.currentItem.getOnSale()){
            	Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) this.priceLabel.getFont().getAttributes(); // HashMap Something like a dictonary in python where data are stored as key value pair
            	attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON); // Add new attribute to HashMap
            	this.priceLabel.setFont(new Font(attributes));
            	this.priceLabel.setForeground(Color.GRAY);

            	this.pricePanel.add(this.priceLabel);

            	BigDecimal salePercent = new BigDecimal(1 - this.currentItem.getPercentOff());
            	BigDecimal salePrice = this.currentItem.getPrice().multiply(salePercent);
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
        this.topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        this.lblFiller = new JLabel("");
        this.lblFiller.setPreferredSize(new Dimension(140, 22));
        this.topPanel.add(this.lblFiller);

        this.lblGroceryItems = new JLabel("Grocery Items");
        this.lblGroceryItems.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.lblGroceryItems.setPreferredSize(new Dimension(200, 60));
        this.lblGroceryItems.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.lblGroceryItems.setHorizontalAlignment(SwingConstants.CENTER);
        this.topPanel.add(this.lblGroceryItems);
        
        

        this.comboBox = new JComboBox(this.category);
        this.comboBox.setSelectedItem(ManagerMenu.categorySele);
        this.comboBox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		ManagerMenu.categorySele = comboBox.getSelectedItem().toString();
        		System.out.println(comboBox.getSelectedItem().toString());
        		main.showManagerMenu();
        	}
        });;
        
        this.lblView = new JLabel("View:");
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
		System.out.println(item.getCategory());
		this.main.showEditGroceryItem(item);
	}
	
	private void back(){
		ManagerMenu.categorySele = "All";
		this.main.showManagerHome();
	}
	
	private void createItem(){
		this.main.showCreateItem();
	}
}
