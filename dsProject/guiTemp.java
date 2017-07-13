package mainPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JTextPane;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

public class guiTemp {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiTemp window = new guiTemp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public guiTemp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 709, 383);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PRODUCT MANAGEMENT SYSTEM");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(181, 11, 370, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(33, 49, 624, 48);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnDisplayAllProducts = new JButton("Display All Products");
		btnDisplayAllProducts.setBounds(31, 5, 139, 37);
		panel.add(btnDisplayAllProducts);
		
		JButton btnSearchProduct = new JButton("Search Product");
		btnSearchProduct.setBounds(201, 5, 139, 37);
		panel.add(btnSearchProduct);
		
		JButton btnAddQuantity = new JButton("Add Quantity");
		btnAddQuantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnAddQuantity.setBounds(371, 5, 97, 37);
		panel.add(btnAddQuantity);
		
		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.setBounds(499, 5, 91, 37);
		panel.add(btnAddProduct);
		btnSearchProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDisplayAllProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//ParentPanel.removeAll();
				
			}
		});
		
		JPanel ParentPanel = new JPanel();
		ParentPanel.setBounds(33, 103, 624, 196);
		frame.getContentPane().add(ParentPanel);
		
		ParentPanel.setLayout(new CardLayout(0, 0));
		
		JPanel DaisplayAllPanel = new JPanel();
		ParentPanel.add(DaisplayAllPanel, "name_205808524651537");
		DaisplayAllPanel.setLayout(null);
		
		JLabel lblProductContentWill = new JLabel("Product Content Will Come Here");
		lblProductContentWill.setBounds(10, 11, 169, 14);
		DaisplayAllPanel.add(lblProductContentWill);
		
		JPanel SearchPanel = new JPanel();
		ParentPanel.add(SearchPanel, "name_205792399119266");
		SearchPanel.setLayout(null);
		
		JLabel lblEnterProductId = new JLabel("Enter Product ID");
		lblEnterProductId.setBounds(10, 11, 87, 14);
		SearchPanel.add(lblEnterProductId);
		
		textField = new JTextField();
		textField.setBounds(107, 8, 86, 20);
		SearchPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblProductDetails = new JLabel("Product Details");
		lblProductDetails.setBounds(20, 43, 173, 55);
		SearchPanel.add(lblProductDetails);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(241, 7, 89, 23);
		SearchPanel.add(btnSearch);
		
		JPanel QuantityPanel = new JPanel();
		ParentPanel.add(QuantityPanel, "name_205868865306001");
		QuantityPanel.setLayout(null);
		
		JLabel lblSelectProduct = new JLabel("Select Product : ");
		lblSelectProduct.setBounds(10, 11, 86, 14);
		QuantityPanel.add(lblSelectProduct);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(106, 8, 147, 20);
		QuantityPanel.add(comboBox);
		
		JLabel lblAddQuantity = new JLabel("Add Quantity : ");
		lblAddQuantity.setBounds(10, 56, 86, 14);
		QuantityPanel.add(lblAddQuantity);
		
		textField_1 = new JTextField();
		textField_1.setBounds(106, 53, 147, 20);
		QuantityPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel AddProductPanel = new JPanel();
		ParentPanel.add(AddProductPanel, "name_205889323377891");
		AddProductPanel.setLayout(null);
		
		JLabel lblProductId = new JLabel("Product Id");
		lblProductId.setBounds(10, 26, 71, 22);
		AddProductPanel.add(lblProductId);
		
		textField_2 = new JTextField();
		textField_2.setBounds(111, 23, 128, 20);
		AddProductPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setBounds(10, 74, 71, 14);
		AddProductPanel.add(lblProductName);
		
		textField_3 = new JTextField();
		textField_3.setBounds(111, 66, 128, 20);
		AddProductPanel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblProductQuantity = new JLabel("Product Quantity");
		lblProductQuantity.setBounds(10, 114, 104, 14);
		AddProductPanel.add(lblProductQuantity);
		
		textField_4 = new JTextField();
		textField_4.setBounds(111, 109, 128, 20);
		AddProductPanel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblProductPrice = new JLabel("Product Price");
		lblProductPrice.setBounds(10, 154, 80, 14);
		AddProductPanel.add(lblProductPrice);
		
		textField_5 = new JTextField();
		textField_5.setBounds(111, 152, 132, 20);
		AddProductPanel.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnAddProduct_1 = new JButton("Add Product");
		btnAddProduct_1.setBounds(356, 150, 128, 23);
		AddProductPanel.add(btnAddProduct_1);
	}
}
