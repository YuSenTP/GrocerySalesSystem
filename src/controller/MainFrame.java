/**
 * 
 */
package controller;

import java.awt.CardLayout;

import javax.swing.JFrame;

/**
 * @author Yu Sen
 *
 */
public class MainFrame extends JFrame {
	private CardLayout card;
	private Controller cont;
	
	public MainFrame(){
		this.setTitle("Joy MiniMart");
		this.setSize(750, 550);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Instantiation of controller and card
		this.cont = new Controller();
		this.card = new CardLayout();
		this.setLayout(this.card);
		
		// Add first method (e.g. showLoginScreen)
		
		this.setVisible(true);
		
	}
	
	public Controller getController(){
		return this.cont;
	}
	
	public void showLoginScreen(){
		
	}
	
	public static void main(String[] args){
		MainFrame gui = new MainFrame();
	}

}
