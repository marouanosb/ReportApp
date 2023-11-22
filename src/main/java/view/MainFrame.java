package view;

import controller.Controller;
import models.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Container;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static ColoredBorderButton historyButton;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					
					//defaut startp-up on historyPanel
					Controller controller = new Controller();
					try {
						controller.createDB();
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					historyButton.doClick();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 500);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel menuPanel = new JPanel();
		JPanel changingPanel = new JPanel();
		changingPanel.setBackground(new Color(213, 234, 255));
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, menuPanel, changingPanel);
		menuPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		//border to separate buttons
		EmptyBorder border= new EmptyBorder(5, 5, 5, 5);
		
		historyButton = new ColoredBorderButton("History");
		historyButton.setBorder(border);
		historyButton.setBackground(new Color(0, 128, 255));
		historyButton.setForeground(new Color(255, 255, 255));
		historyButton.setFont(new Font("Arial", Font.PLAIN, 14));
		historyButton.setFocusPainted(false);
		//historyButton.setFocusable(false);
		menuPanel.add(historyButton);
		
		ColoredBorderButton actionsButton = new ColoredBorderButton("Actions");
		actionsButton.setBorder(border);
		actionsButton.setForeground(new Color(255, 255, 255));
		actionsButton.setBackground(new Color(0, 128, 255));
		actionsButton.setFont(new Font("Arial", Font.PLAIN, 14));
		actionsButton.setFocusPainted(false);
		//actionsButton.setFocusable(false);
		menuPanel.add(actionsButton);
		
		ColoredBorderButton settingsButton = new ColoredBorderButton("Settings");
		settingsButton.setBorder(border);
		settingsButton.setForeground(new Color(255, 255, 255));
		settingsButton.setBackground(new Color(0, 128, 255));
		settingsButton.setFont(new Font("Arial", Font.PLAIN, 14));
		settingsButton.setFocusPainted(false);
		//settingsButton.setFocusable(false);
		menuPanel.add(settingsButton);
		
		contentPane.add(splitPane);
		splitPane.setDividerLocation(50);
		splitPane.setDividerSize(1);
		splitPane.setEnabled(false);	//disable the function of resizing divider
		
		
		// Set the frame to fullscreen
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(false);
		
		setTitle("ReportApp");
		
		// Button functions
		historyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//add panel changes
				HistoryPanel historyPanel = null;
				try {
					historyPanel = new HistoryPanel();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				splitPane.setBottomComponent(historyPanel);
				splitPane.setDividerLocation(50);
			}
			
		});
		
		actionsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//add panel changes
				ActionsPanel actionsPanel = new ActionsPanel();
				splitPane.setBottomComponent(actionsPanel);
				actionsPanel.setDate();
				splitPane.setDividerLocation(50);
			}
			
		});
		
		settingsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//add panel changes
				SettingsPanel settingsPanel = new SettingsPanel();
				splitPane.setBottomComponent(settingsPanel);
				splitPane.setDividerLocation(50);
				
			}
			
		});
		
		
		//adds for all buttons a call to the method to change their colors
		for(ColoredBorderButton button : getAllButtons(menuPanel)) {
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					changeButtonsColor(button, menuPanel);
					
				}
				
			});
		}

	}
	
			//get all buttons inside the frame
			public static List<ColoredBorderButton> getAllButtons(Container container) {
		        List<ColoredBorderButton> buttons = new ArrayList<>();
		        getAllButtonsRecursive(container, buttons);
		        return buttons;
		    }
			
			//go through the component hierrarchy
			private static void getAllButtonsRecursive(Container container, List<ColoredBorderButton> buttons) {
		        Component[] components = container.getComponents();

		        for (Component component : components) {
		            if (component instanceof ColoredBorderButton) {
		                buttons.add((ColoredBorderButton) component);
		            } else if (component instanceof Container) {
		                getAllButtonsRecursive((Container) component, buttons);
		            }
		        }
		    }
			
			//method called for changing the buttons color when clicked
			public void changeButtonsColor(ColoredBorderButton currentButton, Container container) {
				for(ColoredBorderButton button : getAllButtons(container)) {
					if(button == currentButton) {
						button.addBottomBorder(new Color(0, 0, 160), 5);
					}else {
						button.setBackground(new Color(0, 128, 255));
						button.removeBottomBorder();	//else remove border from other buttons
					}
					
					
				}
			}
	

}
