package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.MainFrame;
import data.User;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class ManageAccounts extends JPanel {
    private MainFrame main;
    private JLabel lblManageAccounts;
    private JPanel gridPanel;
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JButton backButton;
    private JButton addAccountButton; 
    private JScrollPane scrollPane;
    private User[] users;
    private User currentUser;
    private JButton userButton;
    private JLabel name;
    private JLabel role;
    private JComboBox<String> roleComboBox;
    private JLabel lblView;
    private String[] roles = {"All", "Staff", "Manager"};
    private static String roleSele = "All";


    public ManageAccounts(MainFrame main) {
        this.main = main;
        this.main.setTitle("Joy MiniMart - Manage Accounts");
        this.users = this.main.getController().getUsers();
        currentUser = this.main.getController().getCurrentUser(); 

        this.setBackground(UIManager.getColor("OptionPane.background"));
        this.setLayout(new BorderLayout(0, 0));
        
        //Top Panel 
        this.topPanel = new JPanel();
        this.topPanel.setLayout(null);
        this.topPanel.setPreferredSize(new Dimension(10, 70));
        
        // Top Label
        this.lblManageAccounts = new JLabel("Manage Accounts");
        this.lblManageAccounts.setBounds(280, 24, 200, 25);
        this.lblManageAccounts.setBackground(UIManager.getColor("Button.background"));
        this.lblManageAccounts.setPreferredSize(new Dimension(87, 60));
        this.lblManageAccounts.setFont(new Font("Tahoma", Font.BOLD, 20));

        this.add(this.lblManageAccounts, BorderLayout.NORTH);
        
     // ComboBox for role selection
        this.roleComboBox = new JComboBox<>(roles);
        this.roleComboBox.setBounds(588, 24, 111, 25);
        this.roleComboBox.setSelectedItem(roleSele);
        this.roleComboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.roleComboBox.setFocusable(false);
        this.roleComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	roleSele = (String) roleComboBox.getSelectedItem();
                main.showManageAccounts();
            }
        });

        // View label
        this.lblView = new JLabel("View:");
        this.lblView.setBounds(529, 25, 47, 21);
        this.lblView.setFont(new Font("Tahoma", Font.BOLD, 17));

        // Add components to the top panel
        this.topPanel.add(this.lblManageAccounts);
        this.topPanel.add(this.lblView);
        this.topPanel.add(this.roleComboBox);

        this.add(this.topPanel, BorderLayout.NORTH);

        // Middle Panel
        this.gridPanel = new JPanel(new GridBagLayout());
        this.gridPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
        this.gridPanel.setBackground(UIManager.getColor("OptionPane.background"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

     // For loop to create user buttons
        for (int i = 0; i < this.users.length; i++) {
            User user = this.users[i];
            
            if (user.getUserID() == currentUser.getUserID()) {
                continue;
            }
            
            // Filter users based on selected role
            if (roleSele.equals("All") || user.getRole().equals(roleSele)) {
           
            // User buttons
            this.userButton = new JButton();
            this.userButton.setPreferredSize(new Dimension(0, 100));
            this.userButton.setBackground(Color.WHITE);
            this.userButton.setLayout(new BorderLayout());

            // User Action
            this.userButton.setActionCommand(String.valueOf(user.getUserID()));
            this.userButton.putClientProperty("object", currentUser);
            this.userButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton sourceBtn = (JButton) e.getSource();
                    User user = (User) sourceBtn.getClientProperty("object");
                    editAccount(user);
                    int userId = Integer.parseInt(sourceBtn.getActionCommand());
                    User selectedUser = findUserById(userId);
                    if (selectedUser != null) {
                        editAccount(selectedUser);
                    }
                }
            });
            
            //User Pic
            JLabel picLabel = new JLabel();
            ImageIcon userPic = new ImageIcon(user.getPicFile());
//            System.out.println("Loading image from: " + user.getPicFile());
            Image img = userPic.getImage();
            Image newimg = img.getScaledInstance(95, 95, java.awt.Image.SCALE_SMOOTH);
            userPic = new ImageIcon(newimg);
            picLabel.setIcon(userPic);
            picLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
            this.userButton.add(picLabel, BorderLayout.WEST);

         // User Info Panel
            JPanel infoPanel = new JPanel(new BorderLayout());
            infoPanel.setOpaque(false);

            // Left panel for Name and ID
            JPanel leftPanel = new JPanel(new GridLayout(2, 1));
            leftPanel.setOpaque(false);

            // User Name and ID
            this.name = new JLabel(user.getName() + " (ID: " + user.getUserID() + ")");
            this.name.setHorizontalAlignment(SwingConstants.LEFT);
            this.name.setFont(new Font("Tahoma", Font.BOLD, 20));
            leftPanel.add(this.name);

            // Empty label for spacing
            leftPanel.add(new JLabel());

            infoPanel.add(leftPanel, BorderLayout.WEST);

            // User Role (on the right)
            this.role = new JLabel("Role: " + user.getRole());
            this.role.setHorizontalAlignment(SwingConstants.RIGHT);
            this.role.setFont(new Font("Tahoma", Font.BOLD, 20));
            infoPanel.add(this.role, BorderLayout.EAST);

            this.userButton.add(infoPanel, BorderLayout.CENTER);

            this.gridPanel.add(userButton, gbc);
            gbc.gridy++;
            
            } 
            
        }

        // Container to push items up --> Filler Panel
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.gridPanel.add(new JPanel(), gbc);

        // Scroll Pane
        this.scrollPane = new JScrollPane(this.gridPanel);
        this.scrollPane.setBorder(new EmptyBorder(0, 2, 2, 2));
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        this.add(this.scrollPane, BorderLayout.CENTER);

        // Bottom Panel
        this.bottomPanel = new JPanel();
        this.bottomPanel.setBackground(Color.WHITE);
        this.bottomPanel.setLayout(new BorderLayout(0, 0));
        this.bottomPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        
        //Back Button
        this.backButton = new JButton("Back");
        this.backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.backButton.setPreferredSize(new Dimension(100, 40));
        this.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                back();
            }
        });
        this.bottomPanel.add(this.backButton, BorderLayout.WEST);
        
        this.addAccountButton = new JButton("Add Account");
        this.addAccountButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.addAccountButton.setPreferredSize(new Dimension(150, 40));
        this.addAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.showAddAccount();
            }
        });
        this.bottomPanel.add(this.addAccountButton, BorderLayout.EAST);

        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private User findUserById(int userId) {
    	for (int i = 0; i < users.length; i++) {
    	    if (users[i].getUserID() == userId) {
    	        return users[i];
    	    }
    	}
    	return null;
	}

	private void back() {
        this.main.showManagerHome();
    }

    private void editAccount(User user) {
        this.main.showEditAccount(user);
    }
}