package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.Controller;
import models.Action;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
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
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.Font;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
				searchBar.setBounds(60, 60, 295, 27);
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
		sortList.setBounds(870, 60, 130, 27);
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
		historyTable.setModel(dtm);
		
		historyTable.setFillsViewportHeight(true);
		historyTable.setBounds(30, 90, 940, 350);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 120, 940, 402);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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
		ActionEvent event = new ActionEvent(sortList, ActionEvent.ACTION_PERFORMED, "Selection");
		for (ActionListener listener : sortList.getActionListeners()) {
            listener.actionPerformed(event);
        }
				
				
	}
	
	//FUNCTIONS
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
}
