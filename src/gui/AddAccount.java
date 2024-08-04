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
    private JTextField passwordField;
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

    public AddAccount(MainFrame main) {
        this.main = main;
        this.main.setTitle("Joy MiniMart - Add Account");

        this.setBackground(UIManager.getColor("OptionPane.background"));
        this.setLayout(new BorderLayout(0, 0));

        // Top Label
        this.lblAddAccount = new JLabel("Add New Account");
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
        this.picLabel.setBounds(50, 20, 200, 200);
        this.middlePanel.add(this.picLabel);

        this.btnChangePic = new JButton("Upload Picture");
        this.btnChangePic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPic();
            }
        });
        this.btnChangePic.setFont(new Font("Tahoma", Font.BOLD, 14));
        this.btnChangePic.setBounds(50, 230, 200, 30);
        this.middlePanel.add(this.btnChangePic);

        // Add Name 
        this.lblName = new JLabel("Name:");
        this.lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
        this.lblName.setBounds(300, 90, 100, 30);
        this.middlePanel.add(this.lblName);

        this.nameField = new JTextField();
        this.nameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.nameField.setBounds(300, 120, 200, 30);
        this.middlePanel.add(this.nameField);
        
     // Add Role Selection
        JLabel lblRole = new JLabel("Role:");
        lblRole.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblRole.setBounds(300, 20, 100, 30);
        this.middlePanel.add(lblRole);

        this.staffRadioButton = new JRadioButton("Staff");
        this.staffRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.staffRadioButton.setBounds(300, 50, 100, 30);
        this.middlePanel.add(this.staffRadioButton);

        this.managerRadioButton = new JRadioButton("Manager");
        this.managerRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.managerRadioButton.setBounds(400, 50, 100, 30);
        this.middlePanel.add(this.managerRadioButton);

        this.roleButtonGroup = new ButtonGroup();
        this.roleButtonGroup.add(this.staffRadioButton);
        this.roleButtonGroup.add(this.managerRadioButton);

        // Set Staff as default selection
        this.staffRadioButton.setSelected(true);

        // Add Password
        this.lblPassword = new JLabel("Password:");
        this.lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
        this.lblPassword.setBounds(300, 160, 100, 30);
        this.middlePanel.add(this.lblPassword);

        this.passwordField = new JPasswordField();
        this.passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.passwordField.setBounds(300, 190, 200, 30);
        this.middlePanel.add(this.passwordField);

        // File Name
        this.PicFileName = new JLabel("File Name:");
        this.PicFileName.setFont(new Font("Tahoma", Font.BOLD, 16));
        this.PicFileName.setBounds(300, 230, 100, 30);
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
		this.FileNameText.setBounds(300, 260, 200, 30);
		this.middlePanel.add(this.FileNameText);
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

        this.addAccountButton = new JButton("Add Account");
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
            String fileName = selectedFile.getName();
            this.FileNameText.setText(fileName);

            try {
                ImageIcon imageIcon = new ImageIcon(ImageIO.read(selectedFile).getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                this.picLabel.setIcon(imageIcon);
                this.picLabel.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addAccount() {
        User currentUser = main.getController().getCurrentUser();
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
            JOptionPane.showMessageDialog(this, "Name and password are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (this.filePath == null) {
            JOptionPane.showMessageDialog(this, "Please select a profile picture!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (staffRadioButton.isSelected()){
        	r = staffRadioButton.getText();
        }
        else if (managerRadioButton.isSelected()){
        	r = managerRadioButton.getText();
        }
        
        try {
            BufferedImage originalImage = ImageIO.read(new File(this.filePath));
            ImageIO.write(originalImage, "jpg", destinationFile);
            
            int id = main.getController().generateUserID();
            main.getController().addUser(n, pwd, r, pic, id);

            JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            this.main.showManageAccounts();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving image file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}