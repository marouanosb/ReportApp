package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.Font;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JComboBox;

public class HistoryPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField searchBar;
	private JTable historyTable;
	private int selectedRow;
	private Boolean filtered;	//to check is a filter is ongoing
	
	/**
	 * Create the panel.
	 */
	public HistoryPanel() {
		setBounds(0,0,1000,500);
		setBackground(new Color(213, 234, 255));
		
		String data[][]={{"cassé","briques","20","taho m sma","-","-","12/12/2012"},    
                {"reparation","madriya","5","voila","-","-","12/12/2021"},
                {"deplacement","slouka","30","bach yarabto jcp","magasin A","magasin B", "18/01/2022"}, 
                {"deplacement","bala","3","yahafro","magasin A","magasin B", "18/01/2022"}, 
                {"deplacement","bala","3","rej3ouhoum","magain B","magasin A", "19/01/2022"}};  
 
		
		String columns[]= {"Action","Material","Quantity","Description","From","To","Date"};
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		dtm.setColumnIdentifiers(columns);
		String hint = "Search...";
				setLayout(null);
		
				
				searchBar = new JTextField();
				searchBar.setBounds(30, 30, 295, 27);
				searchBar.setForeground(Color.GRAY);	//setting hint text 
				searchBar.setText(hint);
				searchBar.setFont(new Font("Arial", Font.PLAIN, 14));
				add(searchBar);
				searchBar.setColumns(10);
				searchBar.setMargin(new Insets(0,5,0,5));
				searchBar.setBorder(new EmptyBorder(5, 5, 5, 5));
				
				
				
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

		JComboBox sortList = new JComboBox(filters);
		sortList.setBounds(840, 30, 130, 27);
		sortList.setSelectedIndex(0);
		sortList.setBackground(Color.WHITE);
		
		//handle sorting table
		sortList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                sortData(data);
                showData(data,historyTable);
               
			}
		});
		add(sortList);
		
		historyTable = new JTable();
		historyTable.setModel(dtm);
		showData(data,historyTable);
		historyTable.setFillsViewportHeight(true);
		historyTable.setBounds(30, 90, 940, 350);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 92, 940, 402);
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
	
	}
	
	//FUNCTIONS
	public void showData(String[][] data, JTable table) {
		resetTable(table);
		for(String[] row : data)
		((DefaultTableModel) table.getModel()).addRow(row);
	}
	
	public String[][] sortData(String[][] data){
		String[][] sortedData = null;
		
		return sortedData;
		
	}
	public void resetTable(JTable table) {
		((DefaultTableModel) table.getModel()).setRowCount(0);
	}
}
