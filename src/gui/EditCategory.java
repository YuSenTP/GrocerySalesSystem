/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.MainFrame;

/**
 * @author Yu Sen
 *
 */
public class EditCategory extends JPanel{
	MainFrame main;
	private JLabel lblEditItem;
	private JPanel bottomPanel;
	private JButton backButton;
	private JButton createButton;
	private JScrollPane scrollPane;
	private String[] category;
	private JPanel middlePanel;
	private JPanel boxPanel;
	
	public EditCategory(MainFrame main){
		this.main = main;
        this.main.setTitle("Joy MiniMart - Edit Category");
        this.category = this.main.getController().getCategory();
        this.category = Arrays.copyOfRange(this.category, 1, this.category.length - 1); //Don't get "ALL" & "Add New"

        this.setBackground(UIManager.getColor("OptionPane.background"));
        this.setLayout(new BorderLayout(0, 0));
        
        // Top Label
        this.lblEditItem = new JLabel("Edit Category");
        this.lblEditItem.setBackground(UIManager.getColor("Button.background"));
        this.lblEditItem.setPreferredSize(new Dimension(87, 60));
        this.lblEditItem.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.lblEditItem.setHorizontalAlignment(SwingConstants.CENTER);
        add(this.lblEditItem, BorderLayout.NORTH);
        
        // Middle Panel (Scrollable)
        this.middlePanel = new JPanel(new BorderLayout());
        this.middlePanel.setBackground(UIManager.getColor("Button.background"));
        this.add(this.middlePanel, BorderLayout.CENTER);
        
        // Box Panel -> Contains All Category Panel
        this.boxPanel = new JPanel();
        this.boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        for (int i = 0; i < this.category.length; i++){
        	String currentCategory = this.category[i];
            JPanel categoryPanel = new JPanel(new BorderLayout(10, 0));
            categoryPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            categoryPanel.setMaximumSize(new Dimension(600, 100));
            categoryPanel.setBackground(Color.WHITE);
            
            //Category Name
            JLabel categoryName = new JLabel(currentCategory);
            categoryName.setFont(new Font("Tahoma", Font.BOLD, 18));
            categoryName.setBorder(new EmptyBorder(0, 20, 0, 20));
            categoryPanel.add(categoryName, BorderLayout.WEST);
            
            //Edit Button
            Icon eIcon = new ImageIcon("./img/Edit.png");
            JButton editButton = new JButton(eIcon);
            editButton.setPreferredSize(new Dimension(50, 50));
            editButton.setBackground(Color.WHITE);
            
            //Edit Action
            editButton.setActionCommand(currentCategory);
            editButton.addActionListener(new ActionListener(){
         		@Override
         		public void actionPerformed(ActionEvent e){
         			String selectedCategory = e.getActionCommand();
         			JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));

         			//Category Name
         			JLabel nameLabel = new JLabel("Name:");
         			nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
         			JTextField nameField = new JTextField(selectedCategory);
         			nameField.setFont(new Font("Tahoma", Font.BOLD, 14));

         			// Category Placing
         			JLabel placingLabel = new JLabel("Placing:");
         			placingLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
         			String[] placing = new String[category.length];

         			int index = 0;
         			//Loading all category
         			for(int i = 0; i < category.length; i++){
         				placing[i] = String.valueOf(i+1);
         				if(category[i].equals(selectedCategory)){
         					index = i;
         				}
         			}

         			JComboBox<String> placingComboBox = new JComboBox<>(placing);
         			placingComboBox.setSelectedIndex(index);
         			placingComboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
         			// Add the components to the panel
         			panel.add(nameLabel);
         			panel.add(nameField);
         			panel.add(placingLabel);
         			panel.add(placingComboBox);

         			int response = JOptionPane.showConfirmDialog(EditCategory.this, panel, "Edit Category", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

         			if (response == JOptionPane.YES_OPTION) { // Edit category name or placing
         				String cName = nameField.getText().trim();
         				int cIndex = placingComboBox.getSelectedIndex();
         				main.getController().editCategory(index, cIndex, cName);
         				main.showEditCategory();
					}
         		}
            });
            
            
            //Delete Button
            Icon dIcon = new ImageIcon("./img/Delete.png");
            JButton deleteButton = new JButton(dIcon);
            deleteButton.setPreferredSize(new Dimension(50, 50));
            deleteButton.setBackground(Color.WHITE);
            
            //Delete Action
            deleteButton.setActionCommand(currentCategory);
            deleteButton.addActionListener(new ActionListener(){
         		@Override
         		public void actionPerformed(ActionEvent e){
         			String originalCategory = e.getActionCommand();
         			
         			boolean inUse = main.getController().categoryInUse(originalCategory);
         			System.out.println(inUse);
         			
         			if (inUse){ // Checks if Category is Used by Grocery Item
         				
         				JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
         				JLabel qLabel = new JLabel("Category In Use! Reassign Items!");
             			qLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
             			
             			String[] newCat = new String[category.length - 1];
             			
             			int i = 0;
             			for (String cat: category){
             				if (cat.equals(originalCategory)){
             					continue;
             				}
             				else{
             					newCat[i] = cat;
             					i++;
             				}
             			}
             			JComboBox<String> catComboBox = new JComboBox<>(newCat);
             			catComboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
             			
             			panel.add(qLabel);
             			panel.add(catComboBox);
             			int response = JOptionPane.showConfirmDialog(EditCategory.this, panel, "Delete Category", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
             			
             			if (response == JOptionPane.YES_OPTION){ // Reassign Category
             				String selectedCat = (String) catComboBox.getSelectedItem();
//             				System.out.println(selectedCat);
             				main.getController().reassignCategory(originalCategory, selectedCat);
             				main.getController().deleteCategory(originalCategory);
             				
             				//Success Message
             				JLabel label = new JLabel("Category Deleted!");
             				label.setFont(new Font("Tahoma", Font.BOLD, 14));
             				JOptionPane.showMessageDialog(EditCategory.this, label, "Edit Category", JOptionPane.INFORMATION_MESSAGE);
             			}
         				
         			}
         			else{ // Category Not in use --> delete category
         				main.getController().deleteCategory(originalCategory);
         				//Success Message
         				JLabel label = new JLabel("Category Deleted!");
         				label.setFont(new Font("Tahoma", Font.BOLD, 14));
         				JOptionPane.showMessageDialog(EditCategory.this, label, "Edit Category", JOptionPane.INFORMATION_MESSAGE);
         			}
         			
         			main.showEditCategory();
         		}
            });
            
            //Button Panel (Edit & Delete)
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(Color.WHITE);
            buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            buttonPanel.add(editButton);
            buttonPanel.add(deleteButton);
            
            categoryPanel.add(buttonPanel, BorderLayout.EAST);
            
            this.boxPanel.add(categoryPanel);
            this.boxPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some space between items
        }
        
        
        // Scroll Panel
        this.scrollPane = new JScrollPane(this.boxPanel);
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

        
        //Create Category Button
        this.createButton = new JButton("Create Category");
        this.createButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.createButton.setPreferredSize(new Dimension(170, 40));
        this.createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                create();
            }
        });
        this.bottomPanel.add(this.createButton, BorderLayout.EAST);

        this.add(this.bottomPanel, BorderLayout.SOUTH);
	}
	
	//GO Home
	private void back(){
		this.main.showManagerHome();
	}

	//Create Category Method
	private void create(){
		//Using GridLayout to design
		JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));

		//Category Name
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		JTextField nameField = new JTextField(10);

		// Category Placing
		JLabel placingLabel = new JLabel("Placing:");
		placingLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		String[] placing = new String[this.category.length+1];

		//Loading all category
		for(int i = 0; i < this.category.length + 1; i++){
			placing[i] = String.valueOf(i+1);
		}

		JComboBox<String> placingComboBox = new JComboBox<>(placing);
		placingComboBox.setSelectedIndex(this.category.length);
		placingComboBox.setFont(new Font("Tahoma", Font.BOLD, 14));

		// Add the components to the panel
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(placingLabel);
		panel.add(placingComboBox);

		//Dialog
		int response = JOptionPane.showConfirmDialog(EditCategory.this, panel, "New Category", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			String cName = nameField.getText().trim();
			int cIndex = placingComboBox.getSelectedIndex();
			main.getController().newCategory(cIndex, cName);
			main.showEditCategory();
		}
	}
}
