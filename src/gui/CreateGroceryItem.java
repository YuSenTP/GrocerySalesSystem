/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import controller.MainFrame;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.EmptyBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

/**
 * @author Yu Sen
 *
 */
public class CreateGroceryItem extends JPanel{
	private MainFrame main;
	private JLabel lblEditItem;
	private JPanel middlePanel;
	private JPanel bottomPanel;
	private JLabel picLabel;
	private JButton backButton;
	private JButton saveButton;
	private JButton btnChangePic;
	private JTextField itemName;
	private JLabel lblPrice;
	private JTextField priceText;
	private JLabel lblQuantity;
	private JTextField quantityText;
	private JLabel lblonlyAddSquare;
	private String filePath;
	private File selectedFile;
	private JCheckBox chckbxOnSale;
	private JSpinner spinner;
	private JLabel lblOff;
	private JLabel lblCategory;
	private JComboBox comboBox;
	private String[] category;
	private JLabel PicFileName;
	private JTextField FileNameText;
	
	public CreateGroceryItem(MainFrame main){
		this.main = main;
		this.category = this.main.getController().getCategory();
		
		this.main.setTitle("Joy MiniMart - Create Item");
		
		this.setBackground(UIManager.getColor("OptionPane.background"));
		this.setLayout(new BorderLayout(0, 0));
		
		//Top Label
		this.lblEditItem = new JLabel("Create Grocery Item");
		this.lblEditItem.setBackground(UIManager.getColor("Button.background"));
		this.lblEditItem.setPreferredSize(new Dimension(87, 60));
		this.lblEditItem.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.lblEditItem.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(this.lblEditItem, BorderLayout.NORTH);
		
		//Middle Panel
		this.middlePanel = new JPanel();
		this.middlePanel.setLayout(null);
		this.add(this.middlePanel, BorderLayout.CENTER);
		
		
		//Item Pic
		this.picLabel = new JLabel("ItemPic Shown Here");
		this.picLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.picLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.picLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.picLabel.setBackground(Color.WHITE);
		this.picLabel.setBounds(0, 0, 300, 300);
		this.middlePanel.add(this.picLabel);
		
		//Add Pic Button
		this.btnChangePic = new JButton("Add Picture");
		this.btnChangePic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPic();
			}
		});
		this.btnChangePic.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.btnChangePic.setBounds(134, 333, 166, 36);
		this.middlePanel.add(this.btnChangePic);
		
		//Name TextField
		this.itemName = new JTextField("Enter Name");
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
		
		//Price Label
		this.lblPrice = new JLabel("Price:");
		this.lblPrice.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.lblPrice.setBounds(360, 106, 65, 26);
		this.middlePanel.add(this.lblPrice);
		
		//Price TextField
		this.priceText = new JTextField("Enter Price");
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
		
		//Quantity Label
		this.lblQuantity = new JLabel("Quantity:");
		this.lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.lblQuantity.setBounds(325, 163, 100, 26);
		this.middlePanel.add(this.lblQuantity);
		
		//Quantity TextField
		this.quantityText = new JTextField("Enter Quantity");
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
		
		//Add Square Pic Label
		this.lblonlyAddSquare = new JLabel("*Only Add Square Picture");
		this.lblonlyAddSquare.setForeground(new Color(255, 0, 51));
		this.lblonlyAddSquare.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.lblonlyAddSquare.setBounds(135, 313, 165, 16);
		this.middlePanel.add(this.lblonlyAddSquare);
		
		//OnSale CheckBox
		this.chckbxOnSale = new JCheckBox("On Sale");
		this.chckbxOnSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//If CheckBox is selectes spinner is enabled
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
		
		//Percentage Off Spinner
		this.spinner = new JSpinner();
		this.spinner.setEnabled(false);
		this.spinner.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.spinner.setBounds(437, 221, 50, 26);
		this.middlePanel.add(this.spinner);
		
		//Percentage Off Label
		this.lblOff = new JLabel("% Off");
		this.lblOff.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.lblOff.setBounds(487, 220, 56, 26);
		this.middlePanel.add(this.lblOff);
		
		//Category Label
		this.lblCategory = new JLabel("Category:");
		this.lblCategory.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.lblCategory.setBounds(325, 271, 100, 26);
		this.middlePanel.add(this.lblCategory);
		
		//Category Combo Box
		this.comboBox = new JComboBox(Arrays.copyOfRange(this.category, 1, this.category.length)); //Do not include all
		this.comboBox.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.comboBox.setFocusable(false);
		this.comboBox.setBounds(437, 273, 128, 26);
		this.middlePanel.add(this.comboBox);
		
		//FileName Label
		this.PicFileName = new JLabel("FileName:");
		this.PicFileName.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.PicFileName.setBounds(325, 327, 101, 26);
		this.middlePanel.add(this.PicFileName);
		
		//FileName TextField
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
		this.FileNameText.setBounds(437, 325, 156, 32);
		this.middlePanel.add(this.FileNameText);
		
		//Bottom Panel
		this.bottomPanel = new JPanel();
		this.bottomPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
		this.bottomPanel.setBackground(Color.WHITE);
		this.bottomPanel.setLayout(new BorderLayout(0, 0));
		
		//Back Button
		this.backButton = new JButton("Back");
		this.backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.backButton.setPreferredSize(new Dimension(100, 40));
		this.backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		this.bottomPanel.add(this.backButton, BorderLayout.WEST);
		
		//Save Button
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
	
	//Redirect to Manager Menu
	private void back(){
		this.main.showManagerMenu();
	}
	
	//Add Picture to Item
	private void addPic(){
		JFileChooser fileChooser = new JFileChooser();
		//Only allow jpg
		fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg", "jpeg");
        fileChooser.setFileFilter(filter);
        
        //Show file chooser
		int r = fileChooser.showSaveDialog(this);
		//Check if ok is selected
		if (r == JFileChooser.APPROVE_OPTION){
			this.selectedFile = fileChooser.getSelectedFile();
			this.filePath = fileChooser.getSelectedFile().getAbsolutePath();
			String[] fileNameList = this.filePath.split("\\\\"); 
			String fileName = fileNameList[fileNameList.length-1];
			String[] temp = fileName.split("\\."); 
			this.FileNameText.setText(temp[temp.length-2]); //take from back incase there is "." in front
	        
			//Show selected image in preview
			try {
				this.picLabel.setText("");
				ImageIcon imageIcon = new ImageIcon(ImageIO.read(selectedFile).getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH));
				this.picLabel.setIcon(imageIcon);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Does checks before saving
	private void save(){
		// Check if filled
		if (this.filePath == null || this.FileNameText.getText().trim().contentEquals("") || this.itemName.getText().trim().contentEquals("Enter Name") || this.priceText.getText().trim().contentEquals("Enter Price") || this.quantityText.getText().trim().contentEquals("Enter Quantity")){
			System.out.println("Error");
			JLabel label = new JLabel("Missing Values! Try Again!");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
			JOptionPane.showMessageDialog(this, label, "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//Check Price
		String finalPrice;
		//If Price starts with "$", trim off
		if (this.priceText.getText().trim().startsWith("$")){
			finalPrice = priceText.getText().trim().substring(1);
		}
		else{
			finalPrice = priceText.getText().trim();
		}
		try{
			if (Double.valueOf(finalPrice) < 0){
				JLabel label = new JLabel("Error! Price must be positive!");
				label.setFont(new Font("Tahoma", Font.BOLD, 14));
				JOptionPane.showMessageDialog(this, label, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}catch (Exception e){
//			e.printStackTrace();
			//Price entered in not numeric, thus throws an error
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
			
		//Check Image - if pass create item
		try{
			String fileName = this.FileNameText.getText().trim() + ".jpg";
			String folder = ".\\img";
			String finalPath = "./img/" + fileName; //filepath saved in object attribute
			BufferedImage originalImage = ImageIO.read(new File(this.filePath));
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

			//Saving
			System.out.println("Creating Item...");
			ImageIO.write(originalImage, "jpg", destinationFile);
			this.main.getController().createGroceryItem(this.itemName.getText().trim(), finalPrice, this.quantityText.getText().trim(), finalPath, this.chckbxOnSale.isSelected(), this.spinner.getValue().toString(), this.comboBox.getSelectedItem().toString());

			//Success Message
			JLabel label = new JLabel("Item Created!");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
			JOptionPane.showMessageDialog(this, label, "Create Item", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Item created successfully");

			//Redirect to Menu
			this.main.showManagerMenu();

		}catch (Exception e) { //If created Item failed
			e.printStackTrace();
			JLabel label = new JLabel("Invalid Inputs! Try Again!");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
			JOptionPane.showMessageDialog(this, label, "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
}
