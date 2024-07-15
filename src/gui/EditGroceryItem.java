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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

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
	
	
	public EditGroceryItem(MainFrame main, GroceryItem item){ 
		this.main = main;
		this.item = item;
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
		this.priceText.setHorizontalAlignment(SwingConstants.CENTER);
		this.priceText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.priceText.setBounds(437, 104, 156, 32);
		this.priceText.setColumns(10);
		this.middlePanel.add(this.priceText);
		
		this.lblQuantity = new JLabel("Quantity:");
		this.lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.lblQuantity.setBounds(325, 169, 100, 26);
		this.middlePanel.add(this.lblQuantity);
		
		this.quantityText = new JTextField(Integer.toString(this.item.getQuantity()));
		this.quantityText.setHorizontalAlignment(SwingConstants.CENTER);
		this.quantityText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.quantityText.setColumns(10);
		this.quantityText.setBounds(437, 169, 156, 32);
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
		this.btnDeleteItem.setBounds(585, 330, 128, 39);
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
		this.chckbxOnSale.setBounds(325, 236, 100, 25);
		this.middlePanel.add(this.chckbxOnSale);
		
		this.spinner = new JSpinner();
		this.spinner.setModel(new SpinnerNumberModel(1, 1, 100, 1)); // set limit for spinner min 1 max 100
		this.spinner.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.spinner.setBounds(437, 237, 50, 26);
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
		this.lblOff.setBounds(487, 236, 56, 26);
		this.middlePanel.add(this.lblOff);
		
		this.bottomPanel = new JPanel();
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
		this.main.showManagerMenu();
	}
	
	private void changePic(){
		JFileChooser fileChooser = new JFileChooser();
		//Only allow jpg
		fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg", "jpeg");
        fileChooser.setFileFilter(filter);
        
		int r = fileChooser.showSaveDialog(this);
		
		if (r == JFileChooser.APPROVE_OPTION){
			
			String selectedFile = fileChooser.getSelectedFile().getAbsolutePath();
//			System.out.println(selectedFile);
			String[] fileNameList = selectedFile.split("\\\\"); 
			String fileName = fileNameList[fileNameList.length-1];
//			System.out.println(fileName);
			
			String folder = ".\\img";
//	        System.out.println(folder);
	        
			
			try {
				BufferedImage originalImage = ImageIO.read(new File(selectedFile));
				File destinationFile = new File(folder, fileName);
				
	            if (!destinationFile.getParentFile().exists()) {
	            	destinationFile.getParentFile().mkdirs();
	            }
	            
				ImageIO.write(originalImage, "jpg", destinationFile);
//				this.item.setPicFile("./img/" + fileName); // TO CHANGE should not be able to edit?
				this.main.getController().changePicPath("./img/" + fileName, this.item);
				
				JLabel label = new JLabel("ItemPic Updated!");
				label.setFont(new Font("Tahoma", Font.BOLD, 14));
				JOptionPane.showMessageDialog(this, label, "Update Item", JOptionPane.INFORMATION_MESSAGE);
				this.main.showEditGroceryItem(this.item);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	}
	
	private void save(){
//		System.out.println(this.item.getName());
/*		this.main.getController().setItemName(this.item, this.itemName.getText());
		this.main.getController().setItemPrice(this.item, this.priceText.getText());
		this.main.getController().setItemQuantity(this.item, this.quantityText.getText());*/
		/*
		try{
			this.item.setName(this.itemName.getText());
			if (this.priceText.getText().startsWith("$")){
				this.item.setPrice(new BigDecimal(this.priceText.getText().substring(1)));
				
			}
			else{
				this.item.setPrice(new BigDecimal(this.priceText.getText()));
			}
			this.item.setQuantity(Integer.valueOf(this.quantityText.getText()));
			JLabel label = new JLabel("Item Updated Successfully");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
			JOptionPane.showMessageDialog(this, label, "Update Item", JOptionPane.INFORMATION_MESSAGE);
			this.main.showManagerMenu();
		}catch (Exception e) {
//			e.printStackTrace();
			JLabel label = new JLabel("Error! Enter Correct Values!");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
			JOptionPane.showMessageDialog(this, label, "Save", JOptionPane.WARNING_MESSAGE);
		}*/
		try{
			this.main.getController().editGroceryItemName(this.item, this.itemName.getText());;
			if (this.priceText.getText().startsWith("$")){
				this.main.getController().editGroceryItemPrice(this.item, this.priceText.getText().substring(1));
				
			}
			else{
				this.main.getController().editGroceryItemPrice(this.item, this.priceText.getText());
			}
			this.main.getController().editGroceryItemQuantity(this.item, this.quantityText.getText());
			this.main.getController().editGroceryItemSale(this.item, this.chckbxOnSale.isSelected(), this.spinner.getValue().toString());
			JLabel label = new JLabel("Item Updated Successfully");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
			JOptionPane.showMessageDialog(this, label, "Update Item", JOptionPane.INFORMATION_MESSAGE);
			this.main.showManagerMenu();
		}catch (Exception e) {
//			e.printStackTrace();
			JLabel label = new JLabel("Error! Enter Correct Values!");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
			JOptionPane.showMessageDialog(this, label, "Save", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	private void deleteItem(){
		this.main.getController().deleteGroceryItem(this.item);
		JLabel label = new JLabel("Item Deleted Successfully");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		JOptionPane.showMessageDialog(this, label, "Delete Item", JOptionPane.INFORMATION_MESSAGE);
		this.main.showManagerMenu();
	}
}
