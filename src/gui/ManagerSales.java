/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import controller.MainFrame;

/**
 * @author Yu Sen
 *
 */
public class ManagerSales extends JPanel{
	private MainFrame main;
	private JLabel lblEditItem;
	private JPanel middlePanel;
	
	public ManagerSales(MainFrame main){
		this.main = main;
		this.main.setTitle("Joy MiniMart - Create Item");
		
		this.setBackground(UIManager.getColor("OptionPane.background"));
		this.setLayout(new BorderLayout(0, 0));
		//Top Label
		this.lblEditItem = new JLabel("Create Grocery Item");
		this.lblEditItem.setBackground(UIManager.getColor("Button.background"));
		this.lblEditItem.setPreferredSize(new Dimension(87, 60));
		this.lblEditItem.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.lblEditItem.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(this.lblEditItem, BorderLayout.NORTH);
		
		this.middlePanel = new JPanel();
		this.middlePanel.setLayout(null);
		this.add(this.middlePanel, BorderLayout.CENTER);
	}

}
