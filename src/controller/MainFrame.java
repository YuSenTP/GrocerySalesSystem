/**
 * 
 */
package controller;

import java.awt.CardLayout;

import javax.swing.JFrame;

import gui.ManagerHome;

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
//		setResizable(false);
		
		// Instantiation of controller and card
		this.cont = new Controller();
		this.card = new CardLayout();
		this.setLayout(this.card);
		
		// Add first method (e.g. showLoginScreen)
		showManagerHome();
		
		this.setVisible(true);
		
	}
	
	public Controller getController(){
		return this.cont;
	}
	
	public void showLoginScreen(){
		
	}
	
	public void showManagerHome(){
		ManagerHome p2 = new ManagerHome(this);
		this.add(p2, "ManagerHome");
		this.card.show(this.getContentPane(), "ManagerHome");
	}
	
	public static void main(String[] args){
		MainFrame gui = new MainFrame();
	}

}
