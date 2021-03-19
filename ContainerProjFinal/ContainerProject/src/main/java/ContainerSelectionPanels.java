import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ContainerSelectionPanels {

	private JPanel containerSearch;
	private JPanel viewContainers;
	private ArrayList<Journey> wJourneys;
	private ArrayList<Container> wContainers;
	private String keyword;

	


	public JPanel getContainerSearch() {
		return containerSearch;
	}

	public JPanel getViewContainers() {
		return viewContainers;
	}

	public ContainerSelectionPanels(final Database database, final CompanyMain companymain) {
		
		containerSearch = new JPanel();
		containerSearch.setPreferredSize(new Dimension(600, 400));
		
		viewContainers = new JPanel(new BorderLayout());
		viewContainers.setPreferredSize(new Dimension(600, 400));
		

		
		// Search through containers
		JLabel activecontainer = new JLabel("Search Container");
		containerSearch.add(activecontainer);
		final JTextField searchActiveContainer = new JTextField();
		searchActiveContainer.setPreferredSize(new Dimension(100, 25));
		containerSearch.add(searchActiveContainer);
		
		JButton search2 = new JButton("Search");
		containerSearch.add(search2);
		search2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				keyword = searchActiveContainer.getText();
				wContainers = database.findContainer(keyword);
				displayContainers(database, companymain);
			}
		});
		
		
		
		JButton showAll = new JButton("Show All");
		containerSearch.add(showAll);
		showAll.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				wContainers = database.getAllContainers();
				displayContainers(database, companymain);
			}
		});
		
	}
	

	
	
	public void displayContainers(Database database, CompanyMain companymain) {
		
		viewContainers.removeAll();
		
		DefaultTableModel tableModel = new DefaultTableModel();
		JTable table = new JTable(tableModel);
		String[] columnNames = {
				"Container ID",
                "company",
                "content",
                " Location"
            
                };
		
		for (String s : columnNames) {
			tableModel.addColumn(s);
		}
		for (Container c : wContainers) {
			
				tableModel.insertRow(0, new Object[] {c.getContainerId(), c.getCompany(), c.getContent(), c.getCurrentLocation()});
			
		}
		viewContainers.add(new JScrollPane(table), BorderLayout.SOUTH);
		companymain.getCl().show(companymain.getCards(), "viewContainers");
	}
}
