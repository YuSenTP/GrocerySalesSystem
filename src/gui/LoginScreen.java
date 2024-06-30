package gui;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.MainFrame;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

public class LoginScreen extends JPanel{
	private MainFrame main; 
	private JTextField textField;
	private JTextField textField_1;
	private JRadioButton staffSelect , managerSelect;
	private JTextField name; 
	private JPasswordField password; 
	private JButton login; 

	public LoginScreen(MainFrame main){
		this.main = main; 
		
		setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(69, 110, 51, 33);
		add(lblName);
		
		this.name = new JTextField();
		this.name.setBounds(129, 112, 192, 33);
		add(this.name);
		this.name.setColumns(10);
		
		this.login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openPage();
			}
		});
		this.login.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.login.setBounds(294, 272, 116, 42);
		add(this.login);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(40, 170, 80, 33);
		add(lblPassword);
				
		this.password = new JPasswordField();
		this.password.setColumns(10);
		this.password.setBounds(129, 172, 192, 33);
		add(this.password);
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		this.staffSelect = new JRadioButton("Staff");
		this.staffSelect.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.staffSelect.setBounds(57, 39, 127, 25);
		add(this.staffSelect);
		btnGroup.add(this.staffSelect);
		
		this.managerSelect = new JRadioButton("Manager");
		this.managerSelect.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.managerSelect.setBounds(248, 39, 127, 25);
		add(this.managerSelect);
		btnGroup.add(this.managerSelect);
		
		JCheckBox chckbxShowPassword = new JCheckBox("Show Password");
		chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxShowPassword.isSelected()){
					password.setEchoChar((char)0);
				}
				else {
					password.setEchoChar('*');
				}
			}
		});
		chckbxShowPassword.setBounds(40, 227, 161, 25);
		add(chckbxShowPassword);
		
	}
	
	public void openPage(){
		String n = name.getText();
		String p = new String(password.getPassword());
		boolean validity = main.getController().verifyUser(n, p);
//		if (validity == true){
			if (staffSelect.isSelected()){
				main.showStaffMenu();
		}
			else if (managerSelect.isSelected()) {
				main.showManagerHome();
		}
			else{
	            JOptionPane.showMessageDialog(null, " Login Unsuccessful, Please select Staff or Manager", "Notification", JOptionPane.ERROR_MESSAGE);
			}
		}
//		else {
//            JOptionPane.showMessageDialog(null, " Login Unsuccessful ", "Notification", JOptionPane.ERROR_MESSAGE);
//		}
//	}
	
	
	
}