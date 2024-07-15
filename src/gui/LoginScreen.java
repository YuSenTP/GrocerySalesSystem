package gui;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import controller.MainFrame;
import data.Manager;
import data.Staff;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginScreen extends JPanel{
	private MainFrame main; 
	private JRadioButton staffSelect , managerSelect;
	private JTextField textName; 
	private JPasswordField textPassword; 
	private JButton login;
	private JLabel lblName;
	private JLabel lblPassword;
	private ButtonGroup btnGroup; 
	private JLabel lblLogin;
	private JLabel lblwrong;
	private JCheckBox chckbxShowPassword;
	private Image backgroundImage;

	public LoginScreen(MainFrame main){
		this.main = main; 
		this.main.setTitle("Joy MiniMart - Login");
		
		this.setLayout(null);
		
		this.backgroundImage = new ImageIcon("./img/loginbk2.jpg").getImage();
		
		this.lblName = new JLabel("User Name:");
		this.lblName.setFont(new Font("Tahoma", Font.BOLD, 17));
		this.lblName.setBounds(442, 177, 125, 33);
		this.add(this.lblName);
		
		this.textName = new JTextField();
		this.textName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.textName.setBounds(442, 210, 192, 33);
		add(this.textName);
		this.textName.setColumns(10);
		
		this.login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openPage();
			}
		});
		this.login.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.login.setBounds(442, 367, 192, 42);
		this.add(this.login);
		
		this.lblPassword = new JLabel("Password:");
		this.lblPassword.setFont(new Font("Tahoma", Font.BOLD, 17));
		this.lblPassword.setBounds(442, 246, 99, 33);
		this.add(this.lblPassword);
				
		this.textPassword = new JPasswordField();
		this.textPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.textPassword.setColumns(10);
		this.textPassword.setBounds(442, 278, 192, 33);
		this.textPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("Login");
					openPage();
				}
			}
		});
		this.add(this.textPassword);
		
		this.btnGroup = new ButtonGroup();
		
		this.staffSelect = new JRadioButton("Staff");
		this.staffSelect.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.staffSelect.setBounds(415, 143, 92, 25);
		this.staffSelect.setOpaque(false);
		this.add(this.staffSelect);
		this.btnGroup.add(this.staffSelect);
		
		this.managerSelect = new JRadioButton("Manager");
		this.managerSelect.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.managerSelect.setBounds(547, 143, 134, 25);
		this.managerSelect.setOpaque(false);
		this.add(this.managerSelect);
		this.btnGroup.add(this.managerSelect);
		
		this.staffSelect.setSelected(true); //pre-select Staff radiobutton
		
		this.chckbxShowPassword = new JCheckBox("Show Password");
		this.chckbxShowPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxShowPassword.isSelected()){
					textPassword.setEchoChar((char)0);
				}
				else {
					textPassword.setEchoChar('*');
				}
			}
		});
		this.chckbxShowPassword.setBounds(442, 333, 161, 25);
		this.chckbxShowPassword.setOpaque(false);
		this.add(this.chckbxShowPassword);
		
		this.lblLogin = new JLabel("Joy MiniMart");
		this.lblLogin.setFont(new Font("Tahoma", Font.BOLD, 47));
		this.lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblLogin.setBounds(363, 43, 363, 106);
		add(this.lblLogin);
		
		this.lblwrong = new JLabel("*Incorrect Username or Password");
		this.lblwrong.setVisible(false);
		this.lblwrong.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.lblwrong.setForeground(new Color(255, 0, 0));
		this.lblwrong.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblwrong.setBounds(415, 414, 264, 25);
		add(this.lblwrong);
		
		


	}
	
	public void openPage(){
		boolean testing = true;
		String n = textName.getText();
		String p = new String(textPassword.getPassword());
		boolean validity;
		if (staffSelect.isSelected()){
			validity = this.main.getController().verifyUser(n, p, "staff");
			if (validity || testing){
				this.main.showStaffMenu();
			}
			else{
				this.lblwrong.setVisible(true);
			}
		}
		else if (managerSelect.isSelected()) {
			validity = this.main.getController().verifyUser(n, p, "manager");
			if (validity || testing){
				this.main.showManagerHome();;
			}
			else{
				this.lblwrong.setVisible(true);
			}
		}	
			
	}
	
	
	
    @Override
    protected void paintComponent(Graphics g) { // Over rides the command
        super.paintComponent(g); //calls super class to execute default painting
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // add my own painting
    }
}