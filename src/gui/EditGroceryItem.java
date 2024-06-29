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
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

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
	
	//need create a current item variable fuckkkkkkk
	public EditGroceryItem(MainFrame main, GroceryItem item){ //:(( Need change cannot edit item through gui fuckkkkk!!!!
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
		this.btnDeleteItem.setForeground(new Color(255, 0, 51));
		this.btnDeleteItem.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.btnDeleteItem.setBounds(585, 330, 128, 39);
		this.middlePanel.add(this.btnDeleteItem);
		
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
			String[] fileNameList = selectedFile.split("\\\\"); //f this shit
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
				this.item.setPicFile("./img/" + fileName);
				this.main.showEditGroceryItem(this.item);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	}
	
	private void save(){
		
	}
}
