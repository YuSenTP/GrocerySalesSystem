package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.MainFrame;
import data.Order;
import data.User;

public class AddAccount extends JPanel {
    private MainFrame main;
    private JLabel lblAddAccount;
    private JPanel middlePanel;
    private JPanel bottomPanel;
    private JLabel picLabel;
    private JButton backButton;
    private JButton addAccountButton;
    private JButton btnChangePic;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JRadioButton staffRadioButton;
    private JRadioButton managerRadioButton;
    private ButtonGroup roleButtonGroup;
    private JLabel lblName;
    private JLabel lblPassword;
    private JLabel lblPicture;
    private String filePath;
    private File selectedFile;
    private JLabel PicFileName;
    private JTextField FileNameText;
    private JLabel label_1;
	private JCheckBox chckbxShowPassword;

    public AddAccount(MainFrame main) {
        this.main = main;
        this.main.setTitle("Joy MiniMart - Add Account");

        this.setBackground(UIManager.getColor("OptionPane.background"));
        this.setLayout(new BorderLayout(0, 0));

        // Top Label
        this.lblAddAccount = new JLabel("Add New Account");
        this.lblAddAccount.setPreferredSize(new Dimension(87, 60));
        this.lblAddAccount.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.lblAddAccount.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(this.lblAddAccount, BorderLayout.NORTH);
        
        //Middle Panel
        this.middlePanel = new JPanel();
        this.add(this.middlePanel, BorderLayout.CENTER);
        this.middlePanel.setLayout(null);

        // Profile Pic label
        this.picLabel = new JLabel("Profile Picture");
        this.picLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.picLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        this.picLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.picLabel.setBounds(48, 20, 280, 280);
        this.middlePanel.add(this.picLabel);

        this.btnChangePic = new JButton("Upload Picture");
        this.btnChangePic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPic();
            }
        });
        this.btnChangePic.setFont(new Font("Tahoma", Font.BOLD, 16));
        this.btnChangePic.setBounds(163, 337, 165, 30);
        this.middlePanel.add(this.btnChangePic);

        // Add Name 
        this.lblName = new JLabel("Name:");
        this.lblName.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.lblName.setBounds(377, 98, 100, 30);
        this.middlePanel.add(this.lblName);

        this.nameField = new JTextField();
        this.nameField.setHorizontalAlignment(SwingConstants.LEFT);
        this.nameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        this.nameField.setBounds(377, 134, 200, 32);
        this.middlePanel.add(this.nameField);
        
     // Add Role Selection
        JLabel lblRole = new JLabel("Role:");
        lblRole.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblRole.setBounds(377, 20, 100, 30);
        this.middlePanel.add(lblRole);

        this.staffRadioButton = new JRadioButton("Staff");
        this.staffRadioButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        this.staffRadioButton.setBounds(377, 59, 100, 30);
        this.middlePanel.add(this.staffRadioButton);

        this.managerRadioButton = new JRadioButton("Manager");
        this.managerRadioButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        this.managerRadioButton.setBounds(477, 59, 100, 30);
        this.middlePanel.add(this.managerRadioButton);

        this.roleButtonGroup = new ButtonGroup();
        this.roleButtonGroup.add(this.staffRadioButton);
        this.roleButtonGroup.add(this.managerRadioButton);

        // Set Staff as default selection
        this.staffRadioButton.setSelected(true);

        // Add Password
        this.lblPassword = new JLabel("Password:");
        this.lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.lblPassword.setBounds(377, 180, 135, 30);
        this.middlePanel.add(this.lblPassword);

        this.passwordField = new JPasswordField();
        this.passwordField.setHorizontalAlignment(SwingConstants.LEFT);
        this.passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        this.passwordField.setBounds(377, 216, 200, 32);
        this.middlePanel.add(this.passwordField);
        
        
        this.chckbxShowPassword = new JCheckBox("Show Password");
		this.chckbxShowPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxShowPassword.isSelected()){
					passwordField.setEchoChar((char)0);
				}
				else {
					passwordField.setEchoChar('\u2022');
				}
			}
		});
		this.chckbxShowPassword.setBounds(377, 253, 161, 25);
		this.chckbxShowPassword.setOpaque(false);
		this.middlePanel.add(this.chckbxShowPassword);

        // File Name
        this.PicFileName = new JLabel("File Name:");
        this.PicFileName.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.PicFileName.setBounds(377, 299, 124, 30);
        this.middlePanel.add(this.PicFileName);

        this.FileNameText = new JTextField("No Pic Selected");
		this.FileNameText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(FileNameText.getText().contentEquals("Enter FileName")){
					FileNameText.setText("");
				}
			}
		});
		this.FileNameText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(FileNameText.getText().contentEquals("") && selectedFile != null){
					FileNameText.setText("Enter FileName");
				}
				else if (FileNameText.getText().contentEquals("") && selectedFile == null){
					FileNameText.setText("No Pic Selected");
				}
			}
		});
		this.FileNameText.setHorizontalAlignment(SwingConstants.CENTER);
		this.FileNameText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.FileNameText.setColumns(10);
		this.FileNameText.setBounds(377, 335, 200, 32);
		this.middlePanel.add(this.FileNameText);
		
		this.label_1 = new JLabel("*Only Add Square Picture");
		this.label_1.setForeground(new Color(255, 0, 51));
		this.label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.label_1.setBounds(163, 313, 165, 16);
		this.middlePanel.add(this.label_1);
        // Bottom Panel 
        this.bottomPanel = new JPanel();
        this.bottomPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        this.bottomPanel.setBackground(Color.WHITE);
        this.bottomPanel.setLayout(new BorderLayout(0, 0));

        this.backButton = new JButton("Back");
        this.backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        this.backButton.setPreferredSize(new Dimension(100, 40));
        this.backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                back();
            }
        });
        this.bottomPanel.add(this.backButton, BorderLayout.WEST);

        this.addAccountButton = new JButton("Save");
        this.addAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addAccount();
            }
        });
        this.addAccountButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        this.addAccountButton.setPreferredSize(new Dimension(100, 40));
        this.bottomPanel.add(this.addAccountButton, BorderLayout.EAST);

        this.add(this.bottomPanel, BorderLayout.SOUTH);
    }

    private void back() {
        this.main.showManageAccounts();
    }

    //Add picture
    private void addPic() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg", "jpeg");
        fileChooser.setFileFilter(filter);

        int r = fileChooser.showOpenDialog(this);

        if (r == JFileChooser.APPROVE_OPTION) {
            this.selectedFile = fileChooser.getSelectedFile();
            this.filePath = fileChooser.getSelectedFile().getAbsolutePath();
            
            String[] fileNameList = this.filePath.split("\\\\"); 
			String fileName = fileNameList[fileNameList.length-1];
			String[] temp = fileName.split("\\."); 
			this.FileNameText.setText(temp[temp.length-2]); //take from back incase there is "." in front
            
//            String fileName = selectedFile.getName();
//            this.FileNameText.setText(fileName);

            try {
                ImageIcon imageIcon = new ImageIcon(ImageIO.read(selectedFile).getScaledInstance(280, 280, Image.SCALE_SMOOTH));
                this.picLabel.setIcon(imageIcon);
                this.picLabel.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addAccount() {
//        User currentUser = main.getController().getCurrentUser();
        String n = this.nameField.getText().trim();
        String pwd = new String(this.passwordField.getText()).trim();
        String r = "";
        
        String fileName = this.FileNameText.getText().trim() + ".jpg";
		String folder = ".\\img";
		String pic = "./img/" + fileName; //filepath saved in object attribute
		File destinationFile = new File(folder, fileName);
		
		//creates img file if doesn't exist
		if (!destinationFile.getParentFile().exists()) {
			destinationFile.getParentFile().mkdirs();
		}
		
		//Checks is file name exists. Prevents saving if exists.
		if (destinationFile.exists()){ 
			System.out.println("File Exits");
			JLabel label = new JLabel("PicFile name exists! Try another one!");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
			JOptionPane.showMessageDialog(this, label, "Error", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

        if (n.isEmpty() || pwd.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Name and password are required!", "Error", JOptionPane.ERROR_MESSAGE);
            JLabel label = new JLabel("Name and password are required!");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
        	JOptionPane.showMessageDialog(this, label, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (this.filePath == null) {
//            JOptionPane.showMessageDialog(this, "Please select a profile picture!", "Error", JOptionPane.ERROR_MESSAGE);
            JLabel label = new JLabel("Please select a profile picture!");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
        	JOptionPane.showMessageDialog(this, label, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (staffRadioButton.isSelected()){
        	r = staffRadioButton.getText();
        }
        else if (managerRadioButton.isSelected()){
        	r = managerRadioButton.getText();
        }
        
        if (this.main.getController().accountExists(n, r)){
        	JLabel label = new JLabel("Account Exists! Try another name!");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
        	JOptionPane.showMessageDialog(this, label, "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        
        try {
            BufferedImage originalImage = ImageIO.read(new File(this.filePath));
            ImageIO.write(originalImage, "jpg", destinationFile);
            
            int id = main.getController().generateUserID();
            main.getController().addUser(n, pwd, r, pic, id);

            JLabel label = new JLabel("Account created successfully!");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
            JOptionPane.showMessageDialog(this, label, "Success", JOptionPane.INFORMATION_MESSAGE);
            this.main.showManageAccounts();
        } catch (IOException e) {
            e.printStackTrace();
            JLabel label = new JLabel("Error saving image file!");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
            JOptionPane.showMessageDialog(this, label, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}