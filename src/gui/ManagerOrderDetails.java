/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.MainFrame;
import data.GroceryItem;
import data.Order;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.SwingConstants;

/**
 * @author Yu Sen
 *
 */
public class ManagerOrderDetails extends JPanel{
	private MainFrame main;
	private Order order;
	private JLabel lblOrderNum;
	private JPanel bottomPanel;
	private JButton backButton;
	private JPanel middlePanel;
	private JScrollPane scrollPane;
	private JPanel boxPanel;
	private ImageIcon itemPic;


	public ManagerOrderDetails(MainFrame main, Order order){
		this.main = main;
		this.order = order;
//		System.out.println(this.item.getName());
		
		this.main.setTitle("Joy MiniMart - Order Details");
		
		this.setBackground(UIManager.getColor("OptionPane.background"));
		this.setLayout(new BorderLayout(0, 0));
		
		//Top Label
		this.lblOrderNum = new JLabel("Order " + order.getOrderID());
		this.lblOrderNum.setBackground(UIManager.getColor("Button.background"));
		this.lblOrderNum.setPreferredSize(new Dimension(87, 60));
		this.lblOrderNum.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblOrderNum.setFont(new Font("Tahoma", Font.BOLD, 23));
		this.add(this.lblOrderNum, BorderLayout.NORTH);
		
		//Bottom Panel
		this.bottomPanel = new JPanel();
		this.bottomPanel.setBackground(Color.WHITE);
		this.bottomPanel.setLayout(new BorderLayout(0, 0));
		
		this.backButton = new JButton("Back");
		this.backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.backButton.setPreferredSize(new Dimension(100, 40));
		this.backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		this.bottomPanel.add(this.backButton, BorderLayout.WEST);
		
		this.add(this.bottomPanel, BorderLayout.SOUTH);
		
		this.middlePanel = new JPanel();
		this.middlePanel.setBackground(UIManager.getColor("Button.background"));
        
        this.add(this.middlePanel, BorderLayout.CENTER);
        
		this.boxPanel = new JPanel();
		this.boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
		
		
		Vector<GroceryItem> temp = this.order.getGroceryItems();
		for(int i = 0; i < temp.size(); i++){
			GroceryItem currentItem = temp.elementAt(i);
			System.out.println(currentItem.getPicFile());
			JPanel itemPanel = new JPanel(new BorderLayout());
			JLabel picLabel = new JLabel();
			
			
			//Item Pic
            this.itemPic = new ImageIcon(currentItem.getPicFile());
            Image img = this.itemPic.getImage() ;  
    		Image newimg = img.getScaledInstance( 90, 90,  java.awt.Image.SCALE_SMOOTH ) ;  
    		this.itemPic = new ImageIcon(newimg);
    		picLabel.setIcon(this.itemPic);
    		
    		itemPanel.add(picLabel, BorderLayout.EAST);
    		
    		this.boxPanel.add(itemPanel);
		}
        
	    //Scroll Pane
        this.scrollPane = new JScrollPane(this.boxPanel);
        this.scrollPane.setBorder(new EmptyBorder(0, 2, 2, 2));
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        this.middlePanel.add(this.scrollPane, BorderLayout.CENTER);
        
        


	}
	
	private void back(){
		this.main.showSales();
	}
}
