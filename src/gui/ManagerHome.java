/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
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
	
	public ManagerHome(MainFrame main){
		this.main = main;
		this.setBackground(Color.WHITE);
		main.setTitle("Joy MiniMart - Manager Home");
		
		JPanel gridPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        gridPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        setLayout(new BorderLayout(0, 0));
        setBackground(UIManager.getColor("OptionPane.background"));
        
        String[] buttons = {"Menu", "Sales", "Inventory", "Add Account", "Edit Profile", "Delete Account"};
        
        for (int i = 0; i < buttons.length; i++){
        	JButton button = new JButton();
        	button.setLayout(new BorderLayout());
        	button.setPreferredSize(new Dimension(213, 123));
        	
        	JLabel nameLabel = new JLabel(buttons[i]);
        	nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        	nameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        	button.add(nameLabel, BorderLayout.CENTER);
        	gridPanel.add(button);
        	
        }
        this.add(gridPanel);
        
        JLabel lblNewLabel = new JLabel("Manager Home");
        lblNewLabel.setBackground(UIManager.getColor("Button.background"));
        lblNewLabel.setPreferredSize(new Dimension(87, 70));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblNewLabel, BorderLayout.NORTH);
        
        JPanel panel = new JPanel();
        this.add(panel, BorderLayout.SOUTH);
        panel.setLayout(new BorderLayout(0, 0));
        
        JButton btnNewButton = new JButton("Logout");
        btnNewButton.setPreferredSize(new Dimension(90, 35));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        panel.add(btnNewButton, BorderLayout.WEST);
        
	}

}
