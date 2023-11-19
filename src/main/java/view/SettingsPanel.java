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
		setLayout(null);
		
		JButton generateReportButton = new JButton("Generate Report");
		generateReportButton.setFocusPainted(false);
		generateReportButton.setFont(new Font("Arial", Font.PLAIN, 14));
		generateReportButton.setBackground(new Color(0, 128, 255));
		generateReportButton.setForeground(new Color(255, 255, 255));
		generateReportButton.setBounds(30, 30, 200, 30);
		generateReportButton.setMargin(new Insets(0,5,0,5));
		generateReportButton.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(generateReportButton);
		
		JButton resetDataButton = new JButton("Reset Data");
		resetDataButton.setFocusPainted(false);
		resetDataButton.setFont(new Font("Arial", Font.PLAIN, 14));
		resetDataButton.setForeground(new Color(255, 255, 255));
		resetDataButton.setBackground(new Color(220, 20, 60));
		resetDataButton.setMargin(new Insets(0,5,0,5));
		resetDataButton.setBorder(new EmptyBorder(5, 5, 5, 5));
		resetDataButton.setBounds(30, 90, 200, 30);
		add(resetDataButton);
		
		JLabel resetWarningLabel = new JLabel("(Reseting data is irriversible)");
		resetWarningLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		resetWarningLabel.setForeground(new Color(220, 20, 60));
		resetWarningLabel.setBounds(240, 90, 230, 30);
		add(resetWarningLabel);
		
		JLabel reportPathLabel = new JLabel("LOCATION :   ./report/report.docx");
		reportPathLabel.setBackground(new Color(255, 255, 255));
		reportPathLabel.setForeground(new Color(0, 0, 0));
		reportPathLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		reportPathLabel.setBounds(240, 30, 230, 30);
		
		add(reportPathLabel);
		
		JButton goToPath = new JButton(">");
		goToPath.setForeground(Color.WHITE);
		goToPath.setFont(new Font("Arial", Font.PLAIN, 14));
		goToPath.setFocusPainted(false);
		goToPath.setBackground(new Color(0, 0, 160));
		goToPath.setBounds(460, 30, 30, 30);
		goToPath.setMargin(new Insets(0,5,0,5));
		goToPath.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(goToPath);
	}
}
