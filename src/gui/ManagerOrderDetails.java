/**
 * 
 */
package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.UIManager;

import controller.MainFrame;
import data.Order;

/**
 * @author Yu Sen
 *
 */
public class ManagerOrderDetails extends JPanel{
	private MainFrame main;
	private Order order;

	public ManagerOrderDetails(MainFrame main, Order order){
		this.main = main;
		this.order = order;
//		System.out.println(this.item.getName());
		
		this.main.setTitle("Joy MiniMart - Order Details");
		
		this.setBackground(UIManager.getColor("OptionPane.background"));
		this.setLayout(new BorderLayout(0, 0));
	}
}
