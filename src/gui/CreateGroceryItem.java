/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
import javafx.scene.layout.Border;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private Image itemPic;
	private JButton btnChangePic;
	private JTextField itemName;
	private JLabel lblPrice;
	private JTextField priceText;
	private JLabel lblQuantity;
	private JTextField quantityText;
	private JLabel lblonlyAddSquare;
	private JButton btnDeleteItem;
	private String filePath;
	private File selectedFile;
	
	
	public CreateGroceryItem(MainFrame main){
		this.main = main;
		
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
		
		this.middlePanel = new JPanel();
		this.add(this.middlePanel, BorderLayout.CENTER);
		this.middlePanel.setLayout(null);

		
		//Item Pic
//		try {
//			BufferedImage tempPic = ImageIO.read(new File(this.item.getPicFile()));
//			this.itemPic = tempPic.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		this.picLabel = new JLabel("ItemPic Shown Here");
		this.picLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.picLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.picLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.picLabel.setBackground(Color.WHITE);
		this.picLabel.setBounds(0, 0, 300, 300);
		this.middlePanel.add(this.picLabel);
		
		this.btnChangePic = new JButton("Add Picture");
		this.btnChangePic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPic();
			}
		});
		this.btnChangePic.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.btnChangePic.setBounds(134, 333, 166, 36);
		this.middlePanel.add(this.btnChangePic);
		
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
		
		this.lblPrice = new JLabel("Price:");
		this.lblPrice.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.lblPrice.setBounds(360, 106, 65, 26);
		this.middlePanel.add(this.lblPrice);
		
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
		
		this.lblQuantity = new JLabel("Quantity:");
		this.lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.lblQuantity.setBounds(325, 169, 100, 26);
		this.middlePanel.add(this.lblQuantity);
		
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
		this.quantityText.setBounds(437, 169, 156, 32);
		this.middlePanel.add(this.quantityText);
		
		this.lblonlyAddSquare = new JLabel("*Only Add Square Picture");
		this.lblonlyAddSquare.setForeground(new Color(255, 0, 51));
		this.lblonlyAddSquare.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.lblonlyAddSquare.setBounds(135, 313, 165, 16);
		this.middlePanel.add(this.lblonlyAddSquare);
		
//		this.btnDeleteItem = new JButton("Delete Item");
//		this.btnDeleteItem.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
////				deleteItem();
//			}
//		});
//		this.btnDeleteItem.setForeground(new Color(255, 0, 51));
//		this.btnDeleteItem.setFont(new Font("Tahoma", Font.BOLD, 15));
//		this.btnDeleteItem.setBounds(585, 330, 128, 39);
//		this.middlePanel.add(this.btnDeleteItem);
		
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
//		this.saveButton.setEnabled(false);
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
	
	private void addPic(){
		
		JFileChooser fileChooser = new JFileChooser();
		//Only allow jpg
		fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg", "jpeg");
        fileChooser.setFileFilter(filter);
        
		int r = fileChooser.showSaveDialog(this);
		
		if (r == JFileChooser.APPROVE_OPTION){
			this.selectedFile = fileChooser.getSelectedFile();
			this.filePath = fileChooser.getSelectedFile().getAbsolutePath();
////			System.out.println(selectedFile);
//			String[] fileNameList = selectedFile.split("\\\\"); 
//			String fileName = fileNameList[fileNameList.length-1];
////			System.out.println(fileName);
//			
//			String folder = ".\\img";
////	        System.out.println(folder);
	        
			
			try {
				this.picLabel.setText("");
				ImageIcon imageIcon = new ImageIcon(ImageIO.read(selectedFile).getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH));
				this.picLabel.setIcon(imageIcon);
//				BufferedImage originalImage = ImageIO.read(new File(selectedFile));
//				File destinationFile = new File(folder, fileName);
//				
//	            if (!destinationFile.getParentFile().exists()) {
//	            	destinationFile.getParentFile().mkdirs();
//	            }
//	            
//				ImageIO.write(originalImage, "jpg", destinationFile);
////				this.item.setPicFile("./img/" + fileName);
//				
//				JLabel label = new JLabel("ItemPic Updated!");
//				label.setFont(new Font("Tahoma", Font.BOLD, 14));
//				JOptionPane.showMessageDialog(this, label, "Update Item", JOptionPane.INFORMATION_MESSAGE);
////				this.main.showEditGroceryItem(this.item);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void save(){
		if (this.filePath == null || this.itemName.getText().contentEquals("Enter Name") || this.priceText.getText().contentEquals("Enter Price") || this.quantityText.getText().contentEquals("Enter Quantity")){
			System.out.println("Error");
		}
		else{
			System.out.println("Success");
			String[] fileNameList = this.filePath.split("\\\\"); 
			String fileName = fileNameList[fileNameList.length-1];
//			System.out.println(fileName);
//			
			String folder = ".\\img";
//	        System.out.println(folder);
			try{
				BufferedImage originalImage = ImageIO.read(new File(this.filePath));
				File destinationFile = new File(folder, fileName);
				
				if (!destinationFile.getParentFile().exists()) {
	            	destinationFile.getParentFile().mkdirs();
	            }
//	            
				ImageIO.write(originalImage, "jpg", destinationFile);
				this.filePath = "./img/" + fileName;
				
				this.main.getController().createGroceryItem(this.itemName.getText(), this.priceText.getText(), this.quantityText.getText(), this.filePath);
				
				JLabel label = new JLabel("Item Created!");
				label.setFont(new Font("Tahoma", Font.BOLD, 14));
				JOptionPane.showMessageDialog(this, label, "Update Item", JOptionPane.INFORMATION_MESSAGE);
				this.main.showManagerMenu();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
	}
}
