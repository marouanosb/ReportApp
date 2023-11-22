package view;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;

import controller.Controller;
import models.Action;
import models.Material;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ActionsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField materialEdit;
	private JTextField fromEdit;
	private JTextField toEdit;
	private JTextField dateEdit;
	JComboBox actionList;
	JSpinner quantityEdit;
	JEditorPane descriptionEdit;

	/**
	 * Create the panel.
	 */
	public ActionsPanel() {
		setBounds(0,0,1000,500);
		setBackground(new Color(213, 234, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();	//changes disabled field color
	    defaults.put("TextField.disabledBackground", new ColorUIResource(new Color(0, 0, 160)));
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(213, 234, 255));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(30, 30, 30, 30);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		
		JLabel lblNewLabel = new JLabel("Action :");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(30, 10, 230, 30);
		panel.add(lblNewLabel);
		
		actionList = new JComboBox();
		actionList.setModel(new DefaultComboBoxModel(new String[] {"Déplacement", "Réparation", "Cassé"}));
		actionList.setSelectedIndex(0);
		actionList.setBackground(Color.WHITE);
		actionList.setFont(new Font("Arial", Font.PLAIN, 14));
		actionList.setBounds(30, 40, 230, 30);
		panel.add(actionList);
		actionList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(actionList.getSelectedIndex() != 0) {	//if action is not transfer disable from/to
					fromEdit.setText("");
					toEdit.setText("");
					fromEdit.setEnabled(false);
					toEdit.setEnabled(false);
				} else {	//else enable them
					fromEdit.setEnabled(true);
					toEdit.setEnabled(true);
				}
				
			}
		});
		
		materialEdit = new JTextField();
		materialEdit.setMargin(new Insets(0,5,0,5));
		materialEdit.setBorder(new EmptyBorder(5, 5, 5, 5));
		materialEdit.setFont(new Font("Arial", Font.PLAIN, 14));
		materialEdit.setBounds(350, 40, 430, 30);
		panel.add(materialEdit);
		materialEdit.setColumns(10);
		
		quantityEdit = new JSpinner();
		quantityEdit.setModel(new SpinnerNumberModel(Integer.valueOf(1), null, null, Integer.valueOf(1)));
		quantityEdit.setBackground(Color.WHITE);
		quantityEdit.setBorder(new EmptyBorder(5, 5, 5, 5));
		quantityEdit.setFont(new Font("Arial", Font.PLAIN, 14));
		quantityEdit.setBounds(805, 40, 50, 30);
		panel.add(quantityEdit);
		
		JLabel lblNewLabel_1 = new JLabel("Material :");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(350, 10, 230, 30);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Qty :");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(805, 10, 50, 30);
		panel.add(lblNewLabel_2);
		
		JButton saveActionButton = new JButton("Save Action");
		
		saveActionButton.setBackground(new Color(0, 128, 255));
		saveActionButton.setForeground(new Color(255, 255, 255));
		saveActionButton.setMargin(new Insets(0,5,0,5));
		saveActionButton.setBorder(new EmptyBorder(5, 5, 5, 5));
		saveActionButton.setFont(new Font("Arial", Font.PLAIN, 14));
		saveActionButton.setBounds(725, 350, 130, 30);
		panel.add(saveActionButton);
		
		//handles action save into the db
		saveActionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveAction();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		fromEdit = new JTextField();
		fromEdit.setMargin(new Insets(0,5,0,5));
		fromEdit.setBorder(new EmptyBorder(5, 5, 5, 5));
		fromEdit.setFont(new Font("Arial", Font.PLAIN, 14));
		fromEdit.setColumns(10);
		fromEdit.setBounds(30, 105, 230, 30);
		panel.add(fromEdit);
		
		toEdit = new JTextField();
		toEdit.setMargin(new Insets(0,5,0,5));
		toEdit.setBorder(new EmptyBorder(5, 5, 5, 5));
		toEdit.setFont(new Font("Arial", Font.PLAIN, 14));
		toEdit.setColumns(10);
		toEdit.setBounds(290, 105, 230, 30);
		panel.add(toEdit);
		
		JLabel lblNewLabel_3 = new JLabel("From :");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(30, 77, 230, 30);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("To :");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(290, 77, 230, 30);
		panel.add(lblNewLabel_4);
		
		dateEdit = new JTextField();
		dateEdit.setMargin(new Insets(0,5,0,5));
		dateEdit.setBorder(new EmptyBorder(5, 5, 5, 5));
		dateEdit.setFont(new Font("Arial", Font.PLAIN, 14));
		dateEdit.setColumns(10);
		dateEdit.setBounds(625, 110, 230, 30);
		panel.add(dateEdit);
		
		JLabel lblNewLabel_4_1 = new JLabel("Date :");
		lblNewLabel_4_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_4_1.setBounds(625, 81, 230, 30);
		panel.add(lblNewLabel_4_1);
		
		descriptionEdit = new JEditorPane();
		descriptionEdit.setMargin(new Insets(0,5,0,5));
		descriptionEdit.setBorder(new EmptyBorder(5, 5, 5, 5));
		descriptionEdit.setFont(new Font("Arial", Font.PLAIN, 14));
		descriptionEdit.setBounds(30, 180, 825, 100);
		panel.add(descriptionEdit);
		
		JLabel lblNewLabel_3_1 = new JLabel("Description :");
		lblNewLabel_3_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(30, 146, 230, 30);
		panel.add(lblNewLabel_3_1);
	
	}
	
	//FUNCTIONS
	public void setDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");	//maybe add HOUR & JCALENDAR
		dateEdit.setText(LocalDateTime.now().format(formatter));
	}
	
	public void saveAction() throws ClassNotFoundException, SQLException {
		//doTests
		Material material = new Material(materialEdit.getText(), (int) quantityEdit.getValue());
		Action action = new Action(actionList.getSelectedItem().toString(),
									material,
									descriptionEdit.getText(),
									fromEdit.getText(),
									toEdit.getText(),
									dateEdit.getText());
		Controller controller = new Controller();
		controller.insert(action);
	}
	
}
