package view;

import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	String path = System.getProperty("user.dir");
	String iconPath = System.getProperty("user.dir")+"\\resources\\icons\\";
	int iconWidth = 15;
	int iconHeight = 15;

	/**
	 * Create the panel.
	 */
	public SettingsPanel() {
		setBounds(0,0,1000,500);
		setBackground(new Color(213, 234, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 200, 200, 0, 0};
		gridBagLayout.rowHeights = new int[]{30, 0, 30, 30, 0, 0, 0, 30, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton generateReportButton = new JButton("Generate Report");
		
		ImageIcon generateReportIcon = new ImageIcon(iconPath+"\\view-details-xxl.png");
		ImageIcon resizedGenerateReportIcon = new ImageIcon(generateReportIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
		
		JLabel lblNewLabel = new JLabel("Report :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(30, 30, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		add(lblNewLabel, gbc_lblNewLabel);
		generateReportButton.setIcon(resizedGenerateReportIcon);
		
		generateReportButton.setFocusPainted(false);
		generateReportButton.setFont(new Font("Arial", Font.PLAIN, 14));
		generateReportButton.setBackground(new Color(0, 128, 255));
		generateReportButton.setForeground(new Color(255, 255, 255));
		generateReportButton.setMargin(new Insets(30, 30, 0, 0));
		generateReportButton.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_generateReportButton = new GridBagConstraints();
		gbc_generateReportButton.fill = GridBagConstraints.BOTH;
		gbc_generateReportButton.insets = new Insets(10, 30, 5, 5);
		gbc_generateReportButton.gridx = 1;
		gbc_generateReportButton.gridy = 2;
		add(generateReportButton, gbc_generateReportButton);
		
		generateReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					gotoPath(path+"/reports");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JLabel reportPathLabel = new JLabel("LOCATION :   ./reports/report.docx");
		reportPathLabel.setBackground(new Color(255, 255, 255));
		reportPathLabel.setForeground(new Color(0, 0, 0));
		reportPathLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		
		GridBagConstraints gbc_reportPathLabel = new GridBagConstraints();
		gbc_reportPathLabel.fill = GridBagConstraints.BOTH;
		gbc_reportPathLabel.insets = new Insets(10, 30, 5, 5);
		gbc_reportPathLabel.gridx = 2;
		gbc_reportPathLabel.gridy = 2;
		gbc_reportPathLabel.gridwidth = 2;
		add(reportPathLabel, gbc_reportPathLabel);
		
		JButton goToPath = new JButton(">");
		goToPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					gotoPath(path+"/reports");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		goToPath.setForeground(Color.WHITE);
		goToPath.setFont(new Font("Arial", Font.PLAIN, 14));
		goToPath.setFocusPainted(false);
		goToPath.setBackground(new Color(0, 0, 160));
		goToPath.setMargin(new Insets(30, 0, 0, 0));
		goToPath.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_goToPath = new GridBagConstraints();
		gbc_goToPath.anchor = GridBagConstraints.WEST;
		gbc_goToPath.fill = GridBagConstraints.VERTICAL;
		gbc_goToPath.insets = new Insets(10, 0, 5, 0);
		gbc_goToPath.gridx = 4;
		gbc_goToPath.gridy = 2;
		add(goToPath, gbc_goToPath);
		
		JLabel lblNewLabel_2 = new JLabel("________________________________________________________________________________________");
		lblNewLabel_2.setForeground(new Color(0, 0, 160));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(30, 30, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 3;
		gbc_lblNewLabel_2.gridwidth= 4;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Data :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 24));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(10, 30, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 5;
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JButton importDataButton = new JButton("Import Data");
		importDataButton.setToolTipText("Copy external \".db\" file here and change its name to \"bdd.db\"");
		ImageIcon importDataIcon = new ImageIcon(iconPath+"\\download-2-xxl.png");
		ImageIcon resizedImportDataIcon = new ImageIcon(importDataIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
		importDataButton.setIcon(resizedImportDataIcon);
		importDataButton.setMargin(new Insets(30, 30, 0, 0));
		importDataButton.setForeground(Color.WHITE);
		importDataButton.setFont(new Font("Arial", Font.PLAIN, 14));
		importDataButton.setFocusPainted(false);
		importDataButton.setBorder(new EmptyBorder(5, 5, 5, 5));
		importDataButton.setBackground(new Color(30, 144, 255));
		GridBagConstraints gbc_importDataButton = new GridBagConstraints();
		gbc_importDataButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_importDataButton.insets = new Insets(10, 30, 5, 5);
		gbc_importDataButton.gridx = 1;
		gbc_importDataButton.gridy = 6;
		add(importDataButton, gbc_importDataButton);
		
		importDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					gotoPath(path+"/database");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton exportDataButton = new JButton("Export Data");
		ImageIcon exportDataIcon = new ImageIcon(iconPath+"\\upload-3-xxl.png");
		ImageIcon resizedExportDataIcon = new ImageIcon(exportDataIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
		exportDataButton.setIcon(resizedExportDataIcon);
		exportDataButton.setMargin(new Insets(30, 30, 0, 0));
		exportDataButton.setForeground(Color.WHITE);
		exportDataButton.setFont(new Font("Arial", Font.PLAIN, 14));
		exportDataButton.setFocusPainted(false);
		exportDataButton.setBorder(new EmptyBorder(5, 5, 5, 5));
		exportDataButton.setBackground(new Color(30, 144, 255));
		GridBagConstraints gbc_exportDataButton = new GridBagConstraints();
		gbc_exportDataButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_exportDataButton.insets = new Insets(10, 30, 5, 5);
		gbc_exportDataButton.gridx = 2;
		gbc_exportDataButton.gridy = 6;
		add(exportDataButton, gbc_exportDataButton);
		
		exportDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					gotoPath(path+"/exports");
					java.util.Date date = Calendar.getInstance().getTime();   
					String newBddName = "bdd_" + new SimpleDateFormat("dd-MM-yyyy_HH-mm").format(date);
			        // Copy the file
					Files.copy(Paths.get(path+"/database/bdd.db"),
							   Paths.get(path+"/exports/"+newBddName+".db"));
			        System.out.println("Database exported successfully!");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		JButton resetDataButton = new JButton("Reset Data");
		ImageIcon resetDataIcon = new ImageIcon(iconPath+"\\trash-2-xxl.png");
		ImageIcon resizedResetDataIcon = new ImageIcon(resetDataIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
		resetDataButton.setIcon(resizedResetDataIcon);
		resetDataButton.setFocusPainted(false);
		resetDataButton.setFont(new Font("Arial", Font.PLAIN, 14));
		resetDataButton.setForeground(new Color(255, 255, 255));
		resetDataButton.setBackground(new Color(220, 20, 60));
		resetDataButton.setMargin(new Insets(30, 30, 0, 0));
		resetDataButton.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_resetDataButton = new GridBagConstraints();
		gbc_resetDataButton.fill = GridBagConstraints.BOTH;
		gbc_resetDataButton.insets = new Insets(30, 30, 0, 5);
		gbc_resetDataButton.gridx = 1;
		gbc_resetDataButton.gridy = 7;
		add(resetDataButton, gbc_resetDataButton);
		
		resetDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				//popup window to confirm
				int choice = JOptionPane.showConfirmDialog(
		                null,
		                "Are you sure you want to delete?",
		                "Confirm",
		                JOptionPane.YES_NO_OPTION
		        );
				
				if (choice == JOptionPane.YES_OPTION) {
					try {
						controller.deleteAll();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		JLabel resetWarningLabel = new JLabel("(Reseting data is irriversible)");
		resetWarningLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		resetWarningLabel.setForeground(new Color(220, 20, 60));
		GridBagConstraints gbc_resetWarningLabel = new GridBagConstraints();
		gbc_resetWarningLabel.fill = GridBagConstraints.BOTH;
		gbc_resetWarningLabel.insets = new Insets(30, 30, 0, 5);
		gbc_resetWarningLabel.gridx = 2;
		gbc_resetWarningLabel.gridy = 7;
		gbc_resetWarningLabel.gridwidth = 2;
		add(resetWarningLabel, gbc_resetWarningLabel);
	}
	
	
	//FUNCTIONS
	
	//GO TO SPECIFIED PATH
	public void gotoPath(String path) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			Files.createDirectories(Paths.get(path));
		}
		Desktop.getDesktop().open(file);
		
	}
}
