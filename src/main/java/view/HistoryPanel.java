package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import controller.Controller;
import models.Action;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.Font;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import javax.swing.ImageIcon;

public class HistoryPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField searchBar;
	private JTable historyTable;
	private int selectedRow;
	private Boolean filtered;	//to check is a filter is ongoing
	JComboBox sortList;
	Controller controller;
	ArrayList<Action> data;
	String iconPath = System.getProperty("user.dir")+"\\src\\main\\resources\\icons\\";
	int iconWidth = 15;
	int iconHeight = 15;

	
	/**
	 * Create the panel.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public HistoryPanel() throws ClassNotFoundException, SQLException {
		
		setBounds(0,0,1000,500);
		setBackground(new Color(213, 234, 255));
		
		
 
		
		String columns[]= {"Action","Material","Quantity","Description","From","To","Date"};
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		dtm.setColumnIdentifiers(columns);
		String hint = "Search...";
				setLayout(null);
		
				
				searchBar = new JTextField();
				searchBar.setBounds(60, 60, 295, 30);
				searchBar.setForeground(Color.GRAY);	//setting hint text 
				searchBar.setText(hint);
				searchBar.setFont(new Font("Arial", Font.PLAIN, 14));
				add(searchBar);
				searchBar.setColumns(10);
				searchBar.setMargin(new Insets(0,5,0,5));
				//searchBar.setBorder(new EmptyBorder(5, 5, 5, 5));
				searchBar.setBorder(new LineBorder(new Color(0, 0, 160), 1, true));	//added border apply it to OTHERS
				//trigger search function through db
				searchBar.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						
						String searchBy = searchBar.getText();
						
						try {
							data = controller.search(searchBy);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						showData(data);
					}
				});
				
				
				
				// Add a FocusListener to show/hide search the hint
				searchBar.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchBar.getText().equals(hint)) {
                	searchBar.setText("");
                	searchBar.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchBar.getText().isEmpty()) {
                	searchBar.setForeground(Color.GRAY);
                	searchBar.setText(hint);
                }
            }
        });
				
		String[] filters = {"Date","Action", "Material"};
		filtered = false;

		sortList = new JComboBox(filters);
		sortList.setFont(new Font("Arial", Font.PLAIN, 14));
		sortList.setBounds(870, 60, 130, 30);
		sortList.setSelectedIndex(0);
		sortList.setBackground(Color.WHITE);
		
		//handle sorting table
		sortList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller = new Controller();
				try {
					data = controller.getAll(sortList.getSelectedItem().toString());
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                showData(data);   
			}
		});
		add(sortList);
		
		historyTable = new JTable();
		historyTable.setFont(new Font("Arial", Font.PLAIN, 14));
		
		historyTable.setModel(dtm);
		
		TableColumnModel cm = historyTable.getColumnModel();
		cm.getColumn(0).setPreferredWidth(50);
		cm.getColumn(1).setPreferredWidth(125);
		cm.getColumn(2).setPreferredWidth(10);
		cm.getColumn(3).setPreferredWidth(200);
		cm.getColumn(4).setPreferredWidth(85);
		cm.getColumn(5).setPreferredWidth(85);
		cm.getColumn(6).setPreferredWidth(50);
		
		historyTable.setRowHeight(20);

		
		historyTable.setFillsViewportHeight(true);
		historyTable.setBounds(30, 90, 940, 350);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 120, 940, 402);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 160), 1, true));
		add(scrollPane);
		scrollPane.setViewportView(historyTable);
		
					
		historyTable.getTableHeader().setBackground(new Color(0, 128, 255));	//change header color
		historyTable.getTableHeader().setForeground(Color.WHITE);
		scrollPane.getVerticalScrollBar().setBackground(new Color(0, 128, 255));
				
		//listener to get index of row selection
		historyTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				selectedRow = historyTable.rowAtPoint(evt.getPoint());
				System.out.println((String) historyTable.getValueAt(selectedRow,0)); 
			}
		});
		historyTable.setDefaultEditor(Object.class, null);
		
		
		// style changes to scroll bar remove from comments after done
		/*scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI()
	    {   
			@Override
		    protected JButton createDecreaseButton(int orientation) {
		        JButton button = super.createDecreaseButton(orientation);
		        button.setBackground(new Color(213, 234, 255));
		        return button;
		    }

		    @Override
		    protected JButton createIncreaseButton(int orientation) {
		        JButton button = super.createIncreaseButton(orientation);
		        button.setBackground(new Color(213, 234, 255));
		        return button;
		    }
	        @Override 
	        protected void configureScrollBarColors(){
	        	this.thumbColor = new Color(213, 234, 255);

	        }
	    });
	    */
	
		//initial data fill through event trigger
		JButton deleteButton = new JButton("Delete");
		ImageIcon deleteIcon = new ImageIcon(iconPath+"\\trash-2-xxl.png");
		ImageIcon resizedDeleteIcon = new ImageIcon(deleteIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
		deleteButton.setIcon(resizedDeleteIcon);
		deleteButton.setFont(new Font("Arial", Font.PLAIN, 14));
		deleteButton.setMargin(new Insets(0,5,0,5));
		deleteButton.setBorder(new EmptyBorder(5, 5, 5, 5));
		deleteButton.setFocusPainted(false);
		deleteButton.setBounds(750, 60, 100, 30);
		deleteButton.setBackground(new Color(220, 20, 60));
		deleteButton.setForeground(new Color(255, 255, 255));
		add(deleteButton);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int[] selectedRows = historyTable.getSelectedRows();
				if (selectedRows.length != 0) {
					
					//popup window to confirm
					int choice = JOptionPane.showConfirmDialog(
			                null,
			                "Are you sure you want to delete?",
			                "Confirm delete",
			                JOptionPane.YES_NO_OPTION
			        );
					
					if (choice == JOptionPane.YES_OPTION) {
						
						try {
							deleteAction(data, selectedRows);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
			}
		});
		
		//CALL DELETE FUNCTION ON 'DELETE' KEYBOARD KEY PRESS
		/*addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int[] selectedRows = historyTable.getSelectedRows();
				
				if (e.getKeyCode() == KeyEvent.VK_DELETE && selectedRows.length != 0) {
					//popup window to confirm
					int choice = JOptionPane.showConfirmDialog(
			                null,
			                "Are you sure you want to delete?",
			                "Confirm delete",
			                JOptionPane.YES_NO_OPTION
			        );
					
					if (choice == JOptionPane.YES_OPTION) {
						
						try {
							deleteAction(data, selectedRows);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				
			}
		});*/
		
		//handle clear selection
		historyTable.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
			}
		});
		
		
		ActionEvent event = new ActionEvent(sortList, ActionEvent.ACTION_PERFORMED, "Selection");
		for (ActionListener listener : sortList.getActionListeners()) {
            listener.actionPerformed(event);
        }
				
				
	}
	
	//FUNCTIONs
	
	public void showData(ArrayList<Action> data) {
		resetTable(historyTable);
		for(Action action : data) {
			String[] a = {action.getActionType(),
                    action.getMaterial().getName(),
                    Integer.toString(action.getMaterial().getQuantity()),
                    action.getDescription(),
                    action.getFrom(),
                    action.getTo(),
                    action.getDate()};
		((DefaultTableModel) historyTable.getModel()).addRow(a);
		}
	}

	public void resetTable(JTable table) {
		((DefaultTableModel) table.getModel()).setRowCount(0);
	}
	
	public void deleteAction(ArrayList<Action> data, int[] selectedRows) throws ClassNotFoundException, SQLException {
		ArrayList<String> ids = new ArrayList<String>();
		for(int row : selectedRows) {
			ids.add(data.get(row).getIdAction());
		}
		controller.deleteAction(ids);
		
		//show data again
		ActionEvent event = new ActionEvent(sortList, ActionEvent.ACTION_PERFORMED, "Selection");
		for (ActionListener listener : sortList.getActionListeners()) {
            listener.actionPerformed(event);
        }
	}
}
