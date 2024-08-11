package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import controller.MainFrame;
import data.User;

public class EditAccount extends JPanel {
    private MainFrame main;
    private User user;
    private JLabel lblEditAccount;
    private JPanel middlePanel;
    private JPanel bottomPanel;
    private JLabel picLabel;
    private JButton backButton;
    private JButton saveButton;
    private JButton btnChangePic;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JRadioButton staffRadioButton;
    private JRadioButton managerRadioButton; 	
    private ButtonGroup roleButtonGroup;
    private JLabel lblName;
    private JLabel lblPassword;
    private JLabel PicFileName;
    private JTextField FileNameText;
    private JCheckBox chckbxShowPassword;
    private String selectedFile;
    private boolean picChanged;
	private JButton btnDeleteAccount;

    public EditAccount(MainFrame main, User user) {
        this.main = main;
        this.user = user;

        this.main.setTitle("Joy MiniMart - Edit Account");

        this.setBackground(UIManager.getColor("OptionPane.background"));
        this.setLayout(new BorderLayout(0, 0));

        // Top Label
        this.lblEditAccount = new JLabel("Edit Account");
        this.lblEditAccount.setPreferredSize(new Dimension(87, 60));
        this.lblEditAccount.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.lblEditAccount.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(this.lblEditAccount, BorderLayout.NORTH);

        // Middle Panel
        this.middlePanel = new JPanel();
        this.middlePanel.setLayout(null);
        this.add(this.middlePanel, BorderLayout.CENTER);

        // Profile Picture
        try {
            BufferedImage tempPic = ImageIO.read(new File(this.user.getPicFile()));
            Image profilePic = tempPic.getScaledInstance(280, 280, Image.SCALE_SMOOTH);
            this.picLabel = new JLabel(new ImageIcon(profilePic));
        } catch (IOException e) {
            this.picLabel = new JLabel("No Image");
            e.printStackTrace();
        }
        this.picLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.picLabel.setBounds(48, 20, 280, 280);
        this.middlePanel.add(this.picLabel);

        // Change Picture Button
        this.btnChangePic = new JButton("Change Picture");
        this.btnChangePic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changePic();
            }
        });
        this.btnChangePic.setFont(new Font("Tahoma", Font.BOLD, 16));
        this.btnChangePic.setBounds(163, 337, 165, 31);
        this.middlePanel.add(this.btnChangePic);

        // Name Field
        this.lblName = new JLabel("Name:");
        this.lblName.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.lblName.setBounds(377, 98, 100, 30);
        this.middlePanel.add(this.lblName);

        this.nameField = new JTextField(this.user.getName());
        this.nameField.setHorizontalAlignment(SwingConstants.LEFT);
        this.nameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        this.nameField.setBounds(377, 134, 185, 32);
        this.middlePanel.add(this.nameField);

        // Role Selection
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

        if (this.user.getRole().equals("Staff")) {
            this.staffRadioButton.setSelected(true);
        } else {
            this.managerRadioButton.setSelected(true);
        }

        // Password TextField
        this.lblPassword = new JLabel("Password:");
        this.lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.lblPassword.setBounds(377, 180, 135, 30);
        this.middlePanel.add(this.lblPassword);

        this.passwordField = new JPasswordField(this.user.getPassword());
        this.passwordField.setHorizontalAlignment(SwingConstants.LEFT);
        this.passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        this.passwordField.setBounds(377, 216, 185, 32);
        this.middlePanel.add(this.passwordField);

        this.chckbxShowPassword = new JCheckBox("Show Password");
        this.chckbxShowPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.chckbxShowPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chckbxShowPassword.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('\u2022');
                }
            }
        });
        
        this.btnDeleteAccount = new JButton("Delete Account");
        this.btnDeleteAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteAccount();
            }
        });
        this.btnDeleteAccount.setForeground(new Color(255, 0, 51));
        this.btnDeleteAccount.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.btnDeleteAccount.setBounds(574, 336, 151, 32);
        this.middlePanel.add(this.btnDeleteAccount);
        
        this.chckbxShowPassword.setBounds(377, 253, 161, 25);
        this.chckbxShowPassword.setOpaque(false);
        this.middlePanel.add(this.chckbxShowPassword);

        // File Name
        this.PicFileName = new JLabel("File Name:");
        this.PicFileName.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.PicFileName.setBounds(377, 299, 124, 30);
        this.middlePanel.add(this.PicFileName);

        String[] picPath = this.user.getPicFile().split("/");
        String[] picNameL = picPath[picPath.length - 1].split("\\.");
        this.FileNameText = new JTextField(picNameL[0]);
        this.FileNameText.setHorizontalAlignment(SwingConstants.CENTER);
        this.FileNameText.setFont(new Font("Tahoma", Font.PLAIN, 20));
        this.FileNameText.setBounds(377, 335, 185, 32);
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

        this.saveButton = new JButton("Save");
        this.saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        this.saveButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        this.saveButton.setPreferredSize(new Dimension(100, 40));
        this.bottomPanel.add(this.saveButton, BorderLayout.EAST);

        this.add(this.bottomPanel, BorderLayout.SOUTH);
    }

    private void deleteAccount() {
    	JLabel label1 = new JLabel("Confirm Delete?");
        label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        int response = JOptionPane.showConfirmDialog(this, label1, "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            this.main.getController().deleteUser(this.user);
            
            JLabel label = new JLabel("Account Deleted Successfully!");
            label.setFont(new Font("Tahoma", Font.BOLD, 14));
            JOptionPane.showMessageDialog(this, label, "Delete Account", JOptionPane.INFORMATION_MESSAGE);
            this.main.showManageAccounts();
        }
    }

	private void back() {
        this.main.showManageAccounts();
    }

    private void changePic() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg", "jpeg");
        fileChooser.setFileFilter(filter);

        int r = fileChooser.showOpenDialog(this);

        if (r == JFileChooser.APPROVE_OPTION) {
            this.picChanged = true;
            this.selectedFile = fileChooser.getSelectedFile().getAbsolutePath();
            String[] fileNameList = this.selectedFile.split("\\\\");
            String fileName = fileNameList[fileNameList.length - 1];
            String pFinalName = fileName.split("\\.")[0];
            this.FileNameText.setText(pFinalName);

            try {
                BufferedImage originalImage = ImageIO.read(new File(selectedFile));
                Image scaledImage = originalImage.getScaledInstance(280, 280, Image.SCALE_SMOOTH);
                this.picLabel.setIcon(new ImageIcon(scaledImage));
            } catch (IOException e) {
                e.printStackTrace();
                JLabel label = new JLabel("An Error Occurred! Try Again!");
                label.setFont(new Font("Tahoma", Font.BOLD, 14));
                JOptionPane.showMessageDialog(this, label, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void save() {
        // save changes
        String newName = this.nameField.getText().trim();
        String newPassword = new String(this.passwordField.getPassword()).trim();
        String newRole = this.staffRadioButton.isSelected() ? "Staff" : "Manager";
        String newFileName = this.FileNameText.getText().trim() + ".jpg";

        if (newName.isEmpty() || newPassword.isEmpty()) {
            JLabel label = new JLabel("Name and password cannot be empty!");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
            JOptionPane.showMessageDialog(this, label, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (this.main.getController().accountExists(newName, newRole)){
            JLabel label = new JLabel("Account Exists! Try another name!");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
        	JOptionPane.showMessageDialog(this, label, "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }

        try {
            String newPicFile = "./img/" + newFileName;
            File destinationFile = new File("./img", newFileName);

            if (this.picChanged) {
                if (destinationFile.exists()) {
                	JLabel label = new JLabel("File name exists! Try another one!");
        			label.setFont(new Font("Tahoma", Font.BOLD, 14));
                    JOptionPane.showMessageDialog(this, label, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                BufferedImage originalImage = ImageIO.read(new File(this.selectedFile));
                ImageIO.write(originalImage, "jpg", destinationFile);
            } else if (!this.user.getPicFile().equals(newPicFile)) {
                File oldFile = new File(this.user.getPicFile());
                oldFile.renameTo(destinationFile);
            }

            // Update user information
            this.user.setName(newName);
            this.user.setPassword(newPassword);
            this.user.setRole(newRole);
            this.user.setPicFile(newPicFile);

            // Save changes to data storage
            this.main.getController().saveAll();

            JLabel label = new JLabel("Account updated successfully!");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
            JOptionPane.showMessageDialog(this, label, "Success", JOptionPane.INFORMATION_MESSAGE);
            this.main.showManageAccounts();
        } catch (IOException e) {
            e.printStackTrace();
            JLabel label = new JLabel("Error saving changes!");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
            JOptionPane.showMessageDialog(this, label, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}