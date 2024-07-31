package gui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.MainFrame;
import data.GroceryItem;

public class ManagerInventory extends JPanel {
    private MainFrame main;
    private JTable inventoryTable;
    private DefaultTableModel tableColumns;
    private JButton backButton;
    private JScrollPane scrollPane;

    public ManagerInventory(MainFrame main) {
        this.main = main;
        this.main.setTitle("Joy MiniMart - Inventory");
        setLayout(new BorderLayout());

        // Create table columns
        String[] columnNames = {"Item Name", "Price", "Quantity", "Category", "On Sale"};
        tableColumns = new DefaultTableModel(columnNames, 0);
        
        // Create table
        inventoryTable = new JTable(tableColumns);
        this.scrollPane = new JScrollPane(inventoryTable);
        this.scrollPane.setBorder(new EmptyBorder(0, 2, 2, 2));
        this.add(this.scrollPane, BorderLayout.CENTER);
        
        //Adjust Font Size
        Font font = new Font("Tahoma", Font.PLAIN, 19);
        inventoryTable.setFont(font);
        inventoryTable.getTableHeader().setFont(font);
        inventoryTable.setRowHeight(font.getSize() + 10); // Adjust row height for the new font size
        
        
        // Bottom Panel 
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new BorderLayout(0, 0));
        buttonPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        

        // Back Button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.showManagerHome();
            }
        });
        buttonPanel.add(backButton, BorderLayout.WEST);

        this.add(buttonPanel, BorderLayout.SOUTH);

        // Populate table
        updateInventoryTable();
    }

    private void updateInventoryTable() {
        // Clear existing rows
        tableColumns.setRowCount(0);

        // Get updated inventory from controller
        GroceryItem[] inventory = main.getController().getInventory();

        // Populate table with inventory data
        for (int i = 0; i < inventory.length; i++) {
            GroceryItem item = inventory[i];
            String onSaleStatus;
            if (item.getOnSale()) {
                onSaleStatus = "Yes";
            } else {
                onSaleStatus = "No";
            }
            Object[] row = {
                item.getName(),
                "$" + item.getPrice(),
                item.getQuantity(),
                item.getCategory(),
                onSaleStatus
            };
            tableColumns.addRow(row);
        }
    }
}