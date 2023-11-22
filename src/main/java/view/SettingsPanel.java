package view;

import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public SettingsPanel() {
		setBounds(0,0,1000,500);
		setBackground(new Color(213, 234, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 200, 250, 0, 0};
		gridBagLayout.rowHeights = new int[]{30, 30, 30, 30, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton generateReportButton = new JButton("Generate Report");
		generateReportButton.setFocusPainted(false);
		generateReportButton.setFont(new Font("Arial", Font.PLAIN, 14));
		generateReportButton.setBackground(new Color(0, 128, 255));
		generateReportButton.setForeground(new Color(255, 255, 255));
		generateReportButton.setMargin(new Insets(30, 30, 0, 0));
		generateReportButton.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_generateReportButton = new GridBagConstraints();
		gbc_generateReportButton.fill = GridBagConstraints.BOTH;
		gbc_generateReportButton.insets = new Insets(30, 30, 5, 5);
		gbc_generateReportButton.gridx = 1;
		gbc_generateReportButton.gridy = 1;
		add(generateReportButton, gbc_generateReportButton);
		
		JLabel reportPathLabel = new JLabel("LOCATION :   ./report/report.docx");
		reportPathLabel.setBackground(new Color(255, 255, 255));
		reportPathLabel.setForeground(new Color(0, 0, 0));
		reportPathLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		
		GridBagConstraints gbc_reportPathLabel = new GridBagConstraints();
		gbc_reportPathLabel.fill = GridBagConstraints.BOTH;
		gbc_reportPathLabel.insets = new Insets(30, 30, 5, 5);
		gbc_reportPathLabel.gridx = 2;
		gbc_reportPathLabel.gridy = 1;
		add(reportPathLabel, gbc_reportPathLabel);
		
		JButton goToPath = new JButton(">");
		goToPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//String path = "C://Program Files//";
					String path = System.getProperty("user.dir")+"/reports";
					gotoPath(path);
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
		gbc_goToPath.anchor = GridBagConstraints.EAST;
		gbc_goToPath.fill = GridBagConstraints.VERTICAL;
		gbc_goToPath.insets = new Insets(30, 0, 0, 0);
		gbc_goToPath.gridx = 3;
		gbc_goToPath.gridy = 1;
		add(goToPath, gbc_goToPath);
		
		JButton resetDataButton = new JButton("Reset Data");
		
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
		gbc_resetDataButton.gridy = 3;
		add(resetDataButton, gbc_resetDataButton);
		
		resetDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				try {
					controller.deleteAll();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
		gbc_resetWarningLabel.gridy = 3;
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
	
	//GENERATE REPORT FUNCTION
	/*private void imprimer() throws IOException, InvalidFormatException {
			
			XWPFDocument doc = new XWPFDocument(OPCPackage.open("./src/milocsample.docx"));
			for (XWPFParagraph p : doc.getParagraphs()) {
			    List<XWPFRun> runs = p.getRuns();
			    if (runs != null) {
			        for (XWPFRun r : runs) {
			            String text = r.getText(0);
			            if (text != null) {
			            	text = text.replace("typeEdit", typeEdit.getText());
				            text = text.replace("classeEdit", classeEdit.getText());
				            text = text.replace("numEnregistrementEdit", numEnregistrementEdit.getText());
				            text = text.replace("metrageEdit", metrageEdit.getText());
				            text = text.replace("prixEdit", prixEdit.getText());
				            text = text.replace("marqueEdit", marqueEdit.getText());
				            text = text.replace("immatriculationEdit", immatriculationEdit.getText());
				            text = text.replace("metragePrecisEdit", metragePrecisEdit.getText());
				            text = text.replace("garantieEdit", garantieEdit.getText());
				            text = text.replace("passeportEdit", passeportEdit.getText());
				            text = text.replace("nomEdit", nomEdit.getText());
				            text = text.replace("dateNaissanceEdit", dateFormat.format(dateNaissanceEdit.getDate()));
				            text = text.replace("lieuNaissanceEdit", lieuNaissanceEdit.getText());
				            text = text.replace("phoneEdit", phoneEdit.getText());
				            text = text.replace("adresseEdit", adresseEdit.getText());
				            text = text.replace("dureeEdit", dureeEdit.getText());
				            text = text.replace("permisEdit", permisEdit.getText());
				            text = text.replace("datePermisEdit", dateFormat.format(datePermisEdit.getDate()));
				            text = text.replace("datePriseEdit", dateFormat.format(datePriseEdit.getDate()));
				            text = text.replace("heurePriseEdit", heurePriseEdit.getText());
				            text = text.replace("dateRemiseEdit", dateFormat.format(dateRemiseEdit.getDate()));
				            text = text.replace("lieuPermisEdit", lieuPermisEdit.getText());
			                r.setText(text, 0);
			            }
			        }
			    }
			}
			for (XWPFTable tbl : doc.getTables()) {
			   for (XWPFTableRow row : tbl.getRows()) {
			      for (XWPFTableCell cell : row.getTableCells()) {
			         for (XWPFParagraph p : cell.getParagraphs()) {
			            for (XWPFRun r : p.getRuns()) {
			              String text = r.getText(0);
			              if (text != null) {
			            	  text = text.replace("typeEdit", typeEdit.getText());
				              text = text.replace("classeEdit", classeEdit.getText());
				              text = text.replace("numEnregistrementEdit", numEnregistrementEdit.getText());
				              text = text.replace("metrageEdit", metrageEdit.getText());
				              text = text.replace("prixEdit", prixEdit.getText());
				              text = text.replace("marqueEdit", marqueEdit.getText());
				              text = text.replace("immatriculationEdit", immatriculationEdit.getText());
				              text = text.replace("metragePrecisEdit", metragePrecisEdit.getText());
				              text = text.replace("garantieEdit", garantieEdit.getText());
				              text = text.replace("passeportEdit", passeportEdit.getText());
				              text = text.replace("nomEdit", nomEdit.getText());
				              text = text.replace("dateNaissanceEdit", dateFormat.format(dateNaissanceEdit.getDate()));
				              text = text.replace("lieuNaissanceEdit", lieuNaissanceEdit.getText());
				              text = text.replace("phoneEdit", phoneEdit.getText());
				              text = text.replace("adresseEdit", adresseEdit.getText());
				              text = text.replace("dureeEdit", dureeEdit.getText());
				              text = text.replace("permisEdit", permisEdit.getText());
				              text = text.replace("datePermisEdit", dateFormat.format(datePermisEdit.getDate()));
				              text = text.replace("datePriseEdit", dateFormat.format(datePriseEdit.getDate()));
				              text = text.replace("heurePriseEdit", heurePriseEdit.getText());
				              text = text.replace("dateRemiseEdit", dateFormat.format(dateRemiseEdit.getDate()));
				              text = text.replace("lieuPermisEdit", lieuPermisEdit.getText());
				              r.setText(text, 0); 
			              }
			            }
			         }
			      }
			   }
			}
			File outfile = new File("./src/output.docx");
			outfile.getParentFile().mkdirs();
			doc.write(new FileOutputStream(outfile));
			
			return;
		}
	*/
}
