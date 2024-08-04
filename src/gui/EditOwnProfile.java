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

public class EditOwnProfile extends JPanel {
    private MainFrame main;
    private User currentUser;
    private JLabel lblEditProfile;
    private JPanel middlePanel;
    private JPanel bottomPanel;
    private JLabel picLabel;
    private JButton backButton;
    private JButton saveButton;
    private JButton btnChangePic;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JLabel lblName;
    private JLabel lblPassword;
    private JLabel PicFileName;
    private JTextField FileNameText;
    private JCheckBox chckbxShowPassword;
    private String selectedFile;
    private boolean picChanged;

    public EditOwnProfile(MainFrame main) {
        this.main = main;
        this.currentUser = main.getController().getCurrentUser();

        if (this.currentUser == null || !this.currentUser.getRole().equals("Manager")) {
            JOptionPane.showMessageDialog(this, "Access denied. Manager profile not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.main.setTitle("Joy MiniMart - Edit Own Profile");

        this.setBackground(UIManager.getColor("OptionPane.background"));
        this.setLayout(new BorderLayout(0, 0));

        // Top Label
        this.lblEditProfile = new JLabel("Edit Profile - " + this.currentUser.getName());
        this.lblEditProfile.setPreferredSize(new Dimension(87, 60));
        this.lblEditProfile.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.lblEditProfile.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(this.lblEditProfile, BorderLayout.NORTH);

        // Middle Panel
        this.middlePanel = new JPanel();
        this.middlePanel.setLayout(null);
        this.add(this.middlePanel, BorderLayout.CENTER);

        // Profile Picture
        try {
            BufferedImage tempPic = ImageIO.read(new File(this.currentUser.getPicFile()));
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
        this.btnChangePic.setBounds(163, 337, 165, 30);
        this.middlePanel.add(this.btnChangePic);

        // Name Field
        this.lblName = new JLabel("Name:");
        this.lblName.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.lblName.setBounds(377, 98, 100, 30);
        this.middlePanel.add(this.lblName);

        this.nameField = new JTextField(this.currentUser.getName());
        this.nameField.setHorizontalAlignment(SwingConstants.LEFT);
        this.nameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        this.nameField.setBounds(377, 134, 200, 32);
        this.middlePanel.add(this.nameField);

        // Password Field
        this.lblPassword = new JLabel("Password:");
        this.lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.lblPassword.setBounds(377, 180, 135, 30);
        this.middlePanel.add(this.lblPassword);

        this.passwordField = new JPasswordField(this.currentUser.getPassword());
        this.passwordField.setHorizontalAlignment(SwingConstants.LEFT);
        this.passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        this.passwordField.setBounds(377, 216, 200, 32);
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
        this.chckbxShowPassword.setBounds(377, 253, 161, 25);
        this.chckbxShowPassword.setOpaque(false);
        this.middlePanel.add(this.chckbxShowPassword);

        // File Name
        this.PicFileName = new JLabel("File Name:");
        this.PicFileName.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.PicFileName.setBounds(377, 299, 124, 30);
        this.middlePanel.add(this.PicFileName);

        String[] picPath = this.currentUser.getPicFile().split("/");
        String[] picNameL = picPath[picPath.length - 1].split("\\.");
        this.FileNameText = new JTextField(picNameL[0]);
        this.FileNameText.setHorizontalAlignment(SwingConstants.CENTER);
        this.FileNameText.setFont(new Font("Tahoma", Font.PLAIN, 20));
        this.FileNameText.setBounds(377, 335, 200, 32);
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

    private void back() {
        this.main.showManagerHome();
    }

    private void changePic() {
        // Implement picture change logic similar to EditAccount
    }

    private void save() {
        String newName = this.nameField.getText().trim();
        String newPassword = new String(this.passwordField.getPassword()).trim();
        String newFileName = this.FileNameText.getText().trim() + ".jpg";

        if (newName.isEmpty() || newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name and password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String newPicFile = "./img/" + newFileName;
            File destinationFile = new File("./img", newFileName);

            if (this.picChanged) {
                if (destinationFile.exists()) {
                    JOptionPane.showMessageDialog(this, "File name already exists! Try another one!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                BufferedImage originalImage = ImageIO.read(new File(this.selectedFile));
                ImageIO.write(originalImage, "jpg", destinationFile);
            } else if (!this.currentUser.getPicFile().equals(newPicFile)) {
                File oldFile = new File(this.currentUser.getPicFile());
                oldFile.renameTo(destinationFile);
            }

            // Update user information
            this.currentUser.setName(newName);
            this.currentUser.setPassword(newPassword);
            this.currentUser.setPicFile(newPicFile);

            // Save changes to data storage
            this.main.getController().saveAll();

            JOptionPane.showMessageDialog(this, "Profile updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            this.main.showManagerHome();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving changes!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}