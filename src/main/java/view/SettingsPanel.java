package view;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public SettingsPanel() {
		setBounds(0,0,1000,500);
		setBackground(new Color(213, 234, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 200, 250, 0};
		gridBagLayout.rowHeights = new int[]{30, 30, 30, 30, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton generateReportButton = new JButton("Generate Report");
		generateReportButton.setFocusPainted(false);
		generateReportButton.setFont(new Font("Arial", Font.PLAIN, 14));
		generateReportButton.setBackground(new Color(0, 128, 255));
		generateReportButton.setForeground(new Color(255, 255, 255));
		generateReportButton.setMargin(new Insets(0,5,0,5));
		generateReportButton.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_generateReportButton = new GridBagConstraints();
		gbc_generateReportButton.fill = GridBagConstraints.BOTH;
		gbc_generateReportButton.insets = new Insets(0, 0, 5, 5);
		gbc_generateReportButton.gridx = 1;
		gbc_generateReportButton.gridy = 1;
		add(generateReportButton, gbc_generateReportButton);
		
		JButton goToPath = new JButton(">");
		goToPath.setForeground(Color.WHITE);
		goToPath.setFont(new Font("Arial", Font.PLAIN, 14));
		goToPath.setFocusPainted(false);
		goToPath.setBackground(new Color(0, 0, 160));
		goToPath.setMargin(new Insets(0,5,0,5));
		goToPath.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_goToPath = new GridBagConstraints();
		gbc_goToPath.anchor = GridBagConstraints.EAST;
		gbc_goToPath.fill = GridBagConstraints.VERTICAL;
		gbc_goToPath.insets = new Insets(0, 0, 5, 0);
		gbc_goToPath.gridx = 2;
		gbc_goToPath.gridy = 1;
		add(goToPath, gbc_goToPath);
		
		JLabel reportPathLabel = new JLabel("LOCATION :   ./report/report.docx");
		reportPathLabel.setBackground(new Color(255, 255, 255));
		reportPathLabel.setForeground(new Color(0, 0, 0));
		reportPathLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		
		GridBagConstraints gbc_reportPathLabel = new GridBagConstraints();
		gbc_reportPathLabel.fill = GridBagConstraints.BOTH;
		gbc_reportPathLabel.insets = new Insets(0, 0, 5, 0);
		gbc_reportPathLabel.gridx = 2;
		gbc_reportPathLabel.gridy = 1;
		add(reportPathLabel, gbc_reportPathLabel);
		
		JButton resetDataButton = new JButton("Reset Data");
		resetDataButton.setFocusPainted(false);
		resetDataButton.setFont(new Font("Arial", Font.PLAIN, 14));
		resetDataButton.setForeground(new Color(255, 255, 255));
		resetDataButton.setBackground(new Color(220, 20, 60));
		resetDataButton.setMargin(new Insets(0,5,0,5));
		resetDataButton.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_resetDataButton = new GridBagConstraints();
		gbc_resetDataButton.fill = GridBagConstraints.BOTH;
		gbc_resetDataButton.insets = new Insets(0, 0, 0, 5);
		gbc_resetDataButton.gridx = 1;
		gbc_resetDataButton.gridy = 3;
		add(resetDataButton, gbc_resetDataButton);
		
		JLabel resetWarningLabel = new JLabel("(Reseting data is irriversible)");
		resetWarningLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		resetWarningLabel.setForeground(new Color(220, 20, 60));
		GridBagConstraints gbc_resetWarningLabel = new GridBagConstraints();
		gbc_resetWarningLabel.fill = GridBagConstraints.BOTH;
		gbc_resetWarningLabel.gridx = 2;
		gbc_resetWarningLabel.gridy = 3;
		add(resetWarningLabel, gbc_resetWarningLabel);
	}
}
