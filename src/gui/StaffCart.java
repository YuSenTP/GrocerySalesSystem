/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import controller.MainFrame;
import javax.swing.JButton;

/**
 * @author Yu Sen
 *
 */
public class StaffCart extends JPanel{
	private MainFrame main;
	private JLabel lblEditItem;
	private JPanel middlePanel;
	private JPanel bottomPanel;
	private JButton backButton; 
	private JButton confirmButton; 
	
	
	public StaffCart(MainFrame main){
		this.main = main;
		this.main.setTitle("Joy MiniMart - StaffCart");
		
		this.setBackground(UIManager.getColor("OptionPane.background"));
		this.setLayout(new BorderLayout(0, 0));
		//Top Label
		this.lblEditItem = new JLabel("Staff Cart");
		this.lblEditItem.setBackground(UIManager.getColor("Button.background"));
		this.lblEditItem.setPreferredSize(new Dimension(87, 60));
		this.lblEditItem.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.lblEditItem.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(this.lblEditItem, BorderLayout.NORTH);
		
		this.middlePanel = new JPanel();
		this.middlePanel.setLayout(null);
		this.add(this.middlePanel, BorderLayout.CENTER);
		
		this.bottomPanel = new JPanel();
        this.bottomPanel.setBackground(Color.WHITE);  
        this.bottomPanel.setLayout(new BorderLayout(0, 0));
        this.backButton = new JButton("Back");
        this.backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.backButton.setPreferredSize(new Dimension(100, 40));
        this.backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Fuck you", "Notification", JOptionPane.INFORMATION_MESSAGE);
                
            }
        });
        this.bottomPanel.add(this.backButton, BorderLayout.WEST);
        
         

		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}