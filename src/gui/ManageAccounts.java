package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

import javax.swing.JButton;

public class ManageAccounts extends JPanel {
    private MainFrame main;
    private JLabel lblManageAccounts;
    private JPanel gridPanel;
    private JPanel bottomPanel;
    private JButton backButton;
    private JButton addAccountButton; 
    private JScrollPane scrollPane;
    private User[] users;
    private JButton userButton;
    private JLabel userName;
    private JLabel userRole;

    public ManageAccounts(MainFrame main) {
        this.main = main;
        this.main.setTitle("Joy MiniMart - Manage Accounts");
        this.users = this.main.getController().getUsers();

        this.setBackground(UIManager.getColor("OptionPane.background"));
        this.setLayout(new BorderLayout(0, 0));

        // Top Label
        this.lblManageAccounts = new JLabel("Manage Accounts");
        this.lblManageAccounts.setBackground(UIManager.getColor("Button.background"));
        this.lblManageAccounts.setPreferredSize(new Dimension(87, 60));
        this.lblManageAccounts.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.lblManageAccounts.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(this.lblManageAccounts, BorderLayout.NORTH);

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
            User currentUser = this.users[i];

            // User buttons
            this.userButton = new JButton();
            this.userButton.setPreferredSize(new Dimension(0, 70));
            this.userButton.setBackground(Color.WHITE);
            this.userButton.setLayout(new BorderLayout());

            // User Action
            this.userButton.setActionCommand(String.valueOf(currentUser.getUserID()));
            this.userButton.putClientProperty("object", currentUser);
            this.userButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton sourceBtn = (JButton) e.getSource();
                    User user = (User) sourceBtn.getClientProperty("object");
                    editAccount(user);
                }
            });

            // User Name and ID
            this.userName = new JLabel(currentUser.getName() + " (ID: " + currentUser.getUserID() + ")");
            this.userName.setHorizontalAlignment(SwingConstants.CENTER);
            this.userName.setFont(new Font("Tahoma", Font.BOLD, 16));
            this.userName.setBorder(new EmptyBorder(0, 10, 0, 0));
            this.userButton.add(this.userName, BorderLayout.WEST);

            // User Role
            this.userRole = new JLabel("Role: " + currentUser.getRole());
            this.userRole.setHorizontalAlignment(SwingConstants.CENTER);
            this.userRole.setFont(new Font("Tahoma", Font.BOLD, 16));
            this.userRole.setBorder(new EmptyBorder(0, 0, 0, 10));
            this.userButton.add(this.userRole, BorderLayout.EAST);

            this.gridPanel.add(userButton, gbc);
            gbc.gridy++;
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
        
        //Back Nutton
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

    private void back() {
        this.main.showManagerHome();
    }

    private void editAccount(User user) {
        System.out.println("Show details for user: " + user.getName());
    }
}