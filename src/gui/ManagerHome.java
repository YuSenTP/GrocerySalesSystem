/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.MainFrame;

/**
 * @author Yu Sen
 *
 */
public class ManagerHome extends JPanel{
	private MainFrame main;
	private JPanel gridPanel;
	private String[] buttonNames = {"Grocery Menu", "Sales", "Inventory", "Edit Category", "Edit Profile", "Manage Accounts"};
	private JLabel nameLabel;
	private JButton button;
	private JLabel frameName;
	private JPanel bottomPanel;
	private JButton logOutButton;
	private Image backgroundImage;
	
	public ManagerHome(MainFrame main){
		this.main = main;
		this.setLayout(new BorderLayout(0, 0));
        this.setBackground(UIManager.getColor("OptionPane.background"));
        
        this.backgroundImage = new ImageIcon("./img/fabric_2.png").getImage();

		this.main.setTitle("Joy MiniMart - Manager Home");
		
		//GridLayout Panel
		this.gridPanel = new JPanel(new GridLayout(0, 3, 30, 30)); // rows, cols, hgap, vgap
        this.gridPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // top, left, bottom, right
        this.gridPanel.setOpaque(false);
        
        //Func Buttons
        for (int i = 0; i < buttonNames.length; i++){
        	this.button = new JButton();
        	this.button.setLayout(new BorderLayout());
        	this.button.setPreferredSize(new Dimension(213, 123));
//        	this.button.setBackground(Color.WHITE);
        	this.button.setActionCommand(buttonNames[i]);
        	this.button.addActionListener(new ActionListener(){
        		@Override
        		public void actionPerformed(ActionEvent e){
        			String buttonName = e.getActionCommand();
        			showNextPanel(buttonName);
        		}
        	});
        	
        	this.nameLabel = new JLabel(buttonNames[i]);
        	this.nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        	this.nameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        	this.button.add(nameLabel, BorderLayout.CENTER);
        	this.gridPanel.add(this.button);
        	
        }
        this.add(this.gridPanel);
        
        //Frame Name
        this.frameName = new JLabel("Manager Home");
        this.frameName.setBackground(UIManager.getColor("Button.background"));
        this.frameName.setPreferredSize(new Dimension(87, 60));
        this.frameName.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.frameName.setHorizontalAlignment(SwingConstants.CENTER);
        this.frameName.setVerticalAlignment(SwingConstants.CENTER);
//        this.frameName.setOpaque(false);
        this.add(this.frameName, BorderLayout.NORTH);
        
        //Bottom Panel
        this.bottomPanel = new JPanel();
        this.bottomPanel.setLayout(new BorderLayout(0, 0));
        this.bottomPanel.setBorder(new EmptyBorder(0, 20, 10, 0));
        this.bottomPanel.setOpaque(false);
        this.add(this.bottomPanel, BorderLayout.SOUTH);
        
        //Logout Button
        this.logOutButton = new JButton("Logout");
        this.logOutButton.setPreferredSize(new Dimension(90, 40));
        this.logOutButton.setFont(new Font("Tahoma", Font.BOLD, 15));
//        this.logOutButton.setBackground(Color.WHITE);
        this.logOutButton.addActionListener(new ActionListener(){
    		@Override
    		public void actionPerformed(ActionEvent e){
    			logOut();
    		}
    	});
        bottomPanel.add(this.logOutButton, BorderLayout.WEST);
        
	}
	
	//Display Manager actions
	private void showNextPanel(String name){
		if (name == "Grocery Menu"){
			System.out.println("Grocery Menu");
			this.main.showManagerMenu();
		}
		else if (name == "Sales"){
			System.out.println("Sales");
			this.main.showSales();
		}
		else if (name == "Inventory"){
			this.main.showManagerInventory();
			System.out.println("Inventory");
		}
		else if (name == "Edit Category"){
			this.main.showEditCategory();
			System.out.println("Edit Category");
		}
		else if (name == "Edit Profile"){
			this.main.showEditOwnProfile();
			System.out.println("Edit Profile");
		}
		else if (name == "Manage Accounts"){
			this.main.showManageAccounts();
		}
	}
	
	//LogOut
	private void logOut(){
		System.out.println("LogOut");
//	    JOptionPane.showMessageDialog(this, "Logged Out Successfully", "Notification", JOptionPane.INFORMATION_MESSAGE);
		JLabel label = new JLabel("Logged Out Successfully");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
        JOptionPane.showMessageDialog(this, label, "Notification", JOptionPane.INFORMATION_MESSAGE); // LogOut Message
		this.main.showLoginScreen();
	}
	
	//For BackGround Image
    @Override
    protected void paintComponent(Graphics g) { // Over rides the command
        super.paintComponent(g); //calls super class to execute default painting
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // add my own painting
    }

}
