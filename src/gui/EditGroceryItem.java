/**
 * 
 */
package gui;

import javax.swing.JPanel;

import controller.MainFrame;
import data.GroceryItem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.border.EmptyBorder;

/**
 * @author Yu Sen
 *
 */
public class EditGroceryItem extends JPanel{
	private MainFrame main;
	private GroceryItem item;
	private JLabel lblEditItem;
	private JPanel middlePanel;
	private JPanel bottomPanel;
	private JLabel picLabel;
	private JButton backButton;
	private JButton saveButton;
	private Image itemPic;
	private JButton btnChangePic;
	private JTextField itemName;
	private JLabel lblPrice;
	private JTextField priceText;
	private JLabel lblQuantity;
	private JTextField quantityText;
	private JLabel lblonlyAddSquare;
	private JButton btnDeleteItem;
	private JCheckBox chckbxOnSale;
	private JSpinner spinner;
	private JLabel lblOff;
	private JLabel lblCategory;
	private JComboBox comboBox;
	private String[] category;
	private JLabel PicFileName;
	private JTextField FileNameText;
	private String selectedFile;
	private boolean picChanged;
	
	
	public EditGroceryItem(MainFrame main, GroceryItem item){ 
		this.main = main;
		this.item = item;
		this.category = this.main.getController().getCategory();
//		System.out.println(this.item.getName());
		
		this.main.setTitle("Joy MiniMart - Edit Item");
		
		this.setBackground(UIManager.getColor("OptionPane.background"));
		this.setLayout(new BorderLayout(0, 0));
		
		//Top Label
		this.lblEditItem = new JLabel("Edit Grocery Item");
		this.lblEditItem.setBackground(UIManager.getColor("Button.background"));
		this.lblEditItem.setPreferredSize(new Dimension(87, 60));
		this.lblEditItem.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.lblEditItem.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(this.lblEditItem, BorderLayout.NORTH);
		
		this.middlePanel = new JPanel();
		add(this.middlePanel, BorderLayout.CENTER);
		this.middlePanel.setLayout(null);
		
		
		//Item Pic
		try {
			BufferedImage tempPic = ImageIO.read(new File(this.item.getPicFile()));
			this.itemPic = tempPic.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.picLabel = new JLabel(new ImageIcon(this.itemPic));
		this.picLabel.setBackground(Color.WHITE);
		this.picLabel.setBounds(0, 0, 300, 300);
		this.middlePanel.add(this.picLabel);
		
		this.btnChangePic = new JButton("Change Picture");
		this.btnChangePic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePic();
			}
		});
		this.btnChangePic.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.btnChangePic.setBounds(134, 333, 166, 36);
		this.middlePanel.add(this.btnChangePic);
		
		this.itemName = new JTextField(this.item.getName());
		this.itemName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(itemName.getText().contentEquals("Enter Name")){
					itemName.setText("");
				}
			}
		});
		this.itemName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(itemName.getText().contentEquals("")){
					itemName.setText("Enter Name");
				}
			}
		});
		this.itemName.setHorizontalAlignment(SwingConstants.CENTER);
		this.itemName.setFont(new Font("Tahoma", Font.PLAIN, 35));
		this.itemName.setBounds(325, 0, 390, 63);
		this.itemName.setColumns(10);
		this.middlePanel.add(this.itemName);
		
		this.lblPrice = new JLabel("Price:");
		this.lblPrice.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.lblPrice.setBounds(360, 106, 65, 26);
		this.middlePanel.add(this.lblPrice);
		
		this.priceText = new JTextField("$" + this.item.getPrice().toString());
		this.priceText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(priceText.getText().contentEquals("Enter Price")){
					priceText.setText("");
				}
			}
		});
		this.priceText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(priceText.getText().contentEquals("")){
					priceText.setText("Enter Price");
				}
			}
		});
		this.priceText.setHorizontalAlignment(SwingConstants.CENTER);
		this.priceText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.priceText.setBounds(437, 104, 156, 32);
		this.priceText.setColumns(10);
		this.middlePanel.add(this.priceText);
		
		this.lblQuantity = new JLabel("Quantity:");
		this.lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.lblQuantity.setBounds(325, 163, 100, 26);
		this.middlePanel.add(this.lblQuantity);
		
		this.quantityText = new JTextField(Integer.toString(this.item.getQuantity()));
		this.quantityText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(quantityText.getText().contentEquals("Enter Quantity")){
					quantityText.setText("");
				}
			}
		});
		this.quantityText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(quantityText.getText().contentEquals("")){
					quantityText.setText("Enter Quantity");
				}
			}
		});
		this.quantityText.setHorizontalAlignment(SwingConstants.CENTER);
		this.quantityText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.quantityText.setColumns(10);
		this.quantityText.setBounds(437, 161, 156, 32);
		this.middlePanel.add(this.quantityText);
		
		this.lblonlyAddSquare = new JLabel("*Only Add Square Picture");
		this.lblonlyAddSquare.setForeground(new Color(255, 0, 51));
		this.lblonlyAddSquare.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.lblonlyAddSquare.setBounds(135, 313, 165, 16);
		this.middlePanel.add(this.lblonlyAddSquare);
		
		this.btnDeleteItem = new JButton("Delete Item");
		this.btnDeleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteItem();
			}
		});
		this.btnDeleteItem.setForeground(new Color(255, 0, 51));
		this.btnDeleteItem.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.btnDeleteItem.setBounds(598, 332, 128, 39);
		this.middlePanel.add(this.btnDeleteItem);
				
		this.chckbxOnSale = new JCheckBox("On Sale");
		this.chckbxOnSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxOnSale.isSelected()){
					spinner.setEnabled(true);
				}
				else if (chckbxOnSale.isSelected() == false){
					spinner.setEnabled(false);
				}
			}
		});
		this.chckbxOnSale.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.chckbxOnSale.setBounds(325, 220, 100, 25);
		this.middlePanel.add(this.chckbxOnSale);
		
		this.spinner = new JSpinner();
		this.spinner.setModel(new SpinnerNumberModel(1, 1, 100, 1)); // set limit for spinner min 1 max 100
		this.spinner.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.spinner.setBounds(437, 221, 50, 26);
		this.middlePanel.add(this.spinner);
		
		if (this.item.getOnSale()){
			this.chckbxOnSale.setSelected(true);
			this.spinner.setEnabled(true);
			this.spinner.setValue(this.item.getPercentOff()*100);
		}
		else{
			this.chckbxOnSale.setSelected(false);
			this.spinner.setEnabled(false);
		}
		
		this.lblOff = new JLabel("% Off");
		this.lblOff.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.lblOff.setBounds(487, 220, 56, 26);
		this.middlePanel.add(this.lblOff);
		
		this.lblCategory = new JLabel("Category:");
		this.lblCategory.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.lblCategory.setBounds(325, 271, 100, 26);
		this.middlePanel.add(this.lblCategory);
		
		// Array Slicing to exclude "All"
		this.comboBox = new JComboBox(Arrays.copyOfRange(this.category, 1, this.category.length));
		this.comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox.getSelectedItem().equals("Add New...")) {
                	JLabel label = new JLabel("Enter new category:");
    				label.setFont(new Font("Tahoma", Font.BOLD, 16));
    				
    				JTextField textField = new JTextField();
                    textField.setFont(new Font("Tahoma", Font.PLAIN, 16));

                    // Customize the input dialog
                    Object[] message = {
                        label, textField
                    };
                    int option = JOptionPane.showConfirmDialog(EditGroceryItem.this, message, "Add New Category", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                    	String newCat = textField.getText();
                    	if (newCat != null && !newCat.trim().isEmpty()) { //If no input
                    		comboBox.insertItemAt(newCat, comboBox.getItemCount() - 2);
                    		comboBox.setSelectedItem(newCat);
                    		main.getController().addCategory(newCat);
                    	} else {
                    		comboBox.setSelectedItem(item.getCategory());
                    	}
                    }
                    else{ // If cancel
                    	comboBox.setSelectedItem(item.getCategory());
                    }
                }
            }
        });
		this.comboBox.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.comboBox.setBounds(437, 273, 128, 26);
		this.comboBox.setFocusable(false);
		this.comboBox.setSelectedItem(this.item.getCategory());
		this.middlePanel.add(this.comboBox);
		
		this.PicFileName = new JLabel("FileName:");
		this.PicFileName.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.PicFileName.setBounds(325, 327, 111, 26);
		this.middlePanel.add(this.PicFileName);
		
		String[] picPath = this.item.getPicFile().split("/");
		String[] picNameL = picPath[picPath.length-1].split("\\.");
		System.out.println(picNameL[0]);
		this.FileNameText = new JTextField(picNameL[0]);
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
				if(FileNameText.getText().contentEquals("")){
					FileNameText.setText("Enter FileName");
				}
			}
		});
		this.FileNameText.setHorizontalAlignment(SwingConstants.CENTER);
		this.FileNameText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.FileNameText.setColumns(10);
		this.FileNameText.setBounds(437, 325, 156, 32);
		this.middlePanel.add(this.FileNameText);
		
		this.bottomPanel = new JPanel();
		this.bottomPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
		this.bottomPanel.setBackground(Color.WHITE);
		this.bottomPanel.setLayout(new BorderLayout(0, 0));
		
		this.backButton = new JButton("Back");
		this.backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
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
        this.saveButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.saveButton.setPreferredSize(new Dimension(140, 40));
		this.bottomPanel.add(this.saveButton, BorderLayout.EAST);
		
		this.add(this.bottomPanel, BorderLayout.SOUTH);
	}
	
	private void back(){
		
		//Name
		boolean nameChanged = !(this.itemName.getText().trim().equals(this.item.getName()));
		
		//Price
		String finalPrice;
	
		if (this.priceText.getText().trim().startsWith("$")){
			finalPrice = priceText.getText().trim().substring(1);
		}
		else{
			finalPrice = priceText.getText().trim();
		}
		boolean priceChanged = !finalPrice.equals(this.item.getPrice().toString());
		
		//Quantity
		boolean quantityChanged = !this.quantityText.getText().trim().equals(String.valueOf(this.item.getQuantity()));
		
		//OnSale
		boolean onSaleChanged = !(this.chckbxOnSale.isSelected() == this.item.getOnSale());
		
		//PercentOff
		boolean percentOffChanged;
		if (this.item.getOnSale()){
			percentOffChanged = !(this.spinner.getValue().toString().equals(String.valueOf(this.item.getPercentOff()*100)));
		}
		else{
			percentOffChanged = false;
		}
		
		//Category
		boolean categoryChanged = !(this.comboBox.getSelectedItem().toString().equals(this.item.getCategory()));
		
		//FileName
		String[] fileNameList = this.item.getPicFile().split("/"); 
		String fileName = fileNameList[fileNameList.length-1];
		String pFinalName = fileName.split("\\.")[0];
		boolean fileNameChanged = !(this.FileNameText.getText().trim().equals(pFinalName));
		
		//ItemPic
		boolean picChanged = this.picChanged;
		
		//Checks if values have been edited
		if (nameChanged || priceChanged || quantityChanged || onSaleChanged || percentOffChanged || categoryChanged || fileNameChanged || picChanged){
			System.out.println("Value Changed");
			
			JLabel label = new JLabel("Save Changes?");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
			
			//Ask User if they want to save changes
			int response = JOptionPane.showConfirmDialog(this, label, "Back", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE );
			
			if (response == JOptionPane.YES_OPTION) {
				save();
			}
			
			else{
				this.main.showManagerMenu();
			}
		}
		else{
			this.main.showManagerMenu();
		}
		
		
	}
	
	private void changePic(){
		JFileChooser fileChooser = new JFileChooser();
		//Only allow jpg
		fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg", "jpeg");
        fileChooser.setFileFilter(filter);
        
		int r = fileChooser.showSaveDialog(this);
		
		if (r == JFileChooser.APPROVE_OPTION){
			this.picChanged = true;
			
			this.selectedFile = fileChooser.getSelectedFile().getAbsolutePath();
//			System.out.println(selectedFile);
			String[] fileNameList = this.selectedFile.split("\\\\"); 
			String fileName = fileNameList[fileNameList.length-1];
			String pFinalName = fileName.split("\\.")[0];
			System.out.println(pFinalName);
			
			try {
				//Allow User to Preview Image
				BufferedImage originalImage = ImageIO.read(new File(selectedFile));
				this.itemPic = originalImage.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
				this.picLabel.setIcon(new ImageIcon(this.itemPic));
				this.FileNameText.setText(pFinalName);

			} catch (IOException e) { //If error occurs -- prevents app from crashing
				e.printStackTrace();
				JLabel label = new JLabel("An Error Occured! Try Again!");
				label.setFont(new Font("Tahoma", Font.BOLD, 14));
				JOptionPane.showMessageDialog(this, label, "Error", JOptionPane.ERROR_MESSAGE);
			}		
		}
	}
	
	private void save(){
		
		// Check if all text box is filled
		if (this.FileNameText.getText().trim().contentEquals("") || this.itemName.getText().trim().contentEquals("Enter Name") || this.priceText.getText().trim().contentEquals("Enter Price") || this.quantityText.getText().trim().contentEquals("Enter Quantity")){
			System.out.println("Error");
			JLabel label = new JLabel("Missing Values! Try Again!");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
			JOptionPane.showMessageDialog(this, label, "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//Checking for "$" in price
		String finalPrice;
		if (this.priceText.getText().trim().startsWith("$")){
			finalPrice = priceText.getText().trim().substring(1);
			
		}
		else{
			finalPrice = priceText.getText().trim();
		}
		try{
			//Check if price entered is more than 0
			if (Double.valueOf(finalPrice) < 0){
				JLabel label = new JLabel("Error! Price must be positive!");
				label.setFont(new Font("Tahoma", Font.BOLD, 14));
				JOptionPane.showMessageDialog(this, label, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}catch (Exception e){
//			e.printStackTrace();
			JLabel label = new JLabel("Error! Price must be numeric!");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
			JOptionPane.showMessageDialog(this, label, "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//Check Quantity
		try{
			if (Integer.valueOf(this.quantityText.getText().trim()) < 0){
				JLabel label = new JLabel("Error! Quantity must be positive!");
				label.setFont(new Font("Tahoma", Font.BOLD, 14));
				JOptionPane.showMessageDialog(this, label, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}catch (Exception e){
			JLabel label = new JLabel("Error! Quantity must be numeric!");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
			JOptionPane.showMessageDialog(this, label, "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
			
		//Check Image - if pass save item
		String savePath;
		try{
			String fileName = this.FileNameText.getText().trim() + ".jpg";
			String folder = ".\\img";
			String finalPath = "./img/" + fileName; //filepath saved in object attribute
			File destinationFile = new File(folder, fileName);
			
			if (this.selectedFile != null){ // change pic
				System.out.println("ChangePic");
				
				//Checks is file name exists. Prevents saving if exists.
				if (destinationFile.exists()){ 
					System.out.println("File Exits");
					JLabel label = new JLabel("PicFile name exists! Try another one!");
					label.setFont(new Font("Tahoma", Font.BOLD, 14));
					JOptionPane.showMessageDialog(this, label, "Error", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				BufferedImage originalImage = ImageIO.read(new File(this.selectedFile));
				
				//creates img file if doesn't exist
				if (!destinationFile.getParentFile().exists()) {
					destinationFile.getParentFile().mkdirs();
				}
				
				//Write PicFile
				ImageIO.write(originalImage, "jpg", destinationFile);
				savePath = finalPath;
			}
			else if (!this.item.getPicFile().equals(finalPath)){ //Change PicName
				System.out.println("ChangePicName");
				
				//Checks is file name exists. Prevents saving if exists.
				if (destinationFile.exists()){ 
					System.out.println("File Exits");
					JLabel label = new JLabel("PicFile name exists! Try another one!");
					label.setFont(new Font("Tahoma", Font.BOLD, 14));
					JOptionPane.showMessageDialog(this, label, "Error", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				File oldName = new File(this.item.getPicFile());
				File newName = new File(finalPath);
				oldName.renameTo(newName);
				savePath = finalPath;
				
			}
			else{ //No Change in Pic
				savePath = this.item.getPicFile();
			}
			
			System.out.println(savePath);

			//Saving
			System.out.println("Saving Item...");
			this.main.getController().editGroceryItem(this.item, this.itemName.getText().trim(), finalPrice, this.quantityText.getText().trim(), savePath, this.chckbxOnSale.isSelected(), this.spinner.getValue().toString(), this.comboBox.getSelectedItem().toString());

			//Success Message
			JLabel label = new JLabel("Item Saved!");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
			JOptionPane.showMessageDialog(this, label, "Update Item", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Item Saved successfully");

			//Redirect to Menu
			this.main.showManagerMenu();

		}catch (Exception e) { //If created Item failed
			e.printStackTrace();
			JLabel label = new JLabel("Invalid Inputs! Try Again!");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
			JOptionPane.showMessageDialog(this, label, "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	//Delete Grocery Item
	private void deleteItem(){
		JLabel clabel = new JLabel("Confirm Delete?");
		clabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		//Asking for confirmation
		int response = JOptionPane.showConfirmDialog(this, clabel, "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE );
		
		if (response == JOptionPane.YES_OPTION) {
			this.main.getController().deleteGroceryItem(this.item);
			
			JLabel label = new JLabel("Item Deleted Successfully");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
			JOptionPane.showMessageDialog(this, label, "Delete Item", JOptionPane.INFORMATION_MESSAGE);
			this.main.showManagerMenu();
		}

		
		

	}
}
