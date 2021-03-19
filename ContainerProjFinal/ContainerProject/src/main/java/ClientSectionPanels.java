import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//this is where the client infornmation can be viewed
public class ClientSectionPanels {

	private JPanel clientSearch;
	private JPanel viewClients;
	private ArrayList<client> regClients;
	private JFrame init;
	
	public ClientSectionPanels(final Database database, final CompanyMain companymain) {
		
		
		clientSearch = new JPanel();
		clientSearch.setPreferredSize(new Dimension(600, 400));
		
		viewClients = new JPanel();
		viewClients.setPreferredSize(new Dimension(600, 400));
		
		//allows company to register for a client
		JButton rbutton = new JButton("Register");
		rbutton.setPreferredSize(new Dimension(100, 50));
		rbutton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new RegistrationWindow(database, init);
			
			}	
		});
		clientSearch.add(rbutton);
		
		
		
		//allows company to search for a client
		final JTextField search = new JTextField();
		search.setPreferredSize(new Dimension(100, 25));
		clientSearch.add(search);
		
		JButton searchButton = new JButton("Search");
		clientSearch.add(searchButton);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = search.getText();
				ArrayList<client> result = new ArrayList<client>();
				result.addAll(database.search(keyword));
				regClients = result;
				displayclients(companymain);
			}
		});
		
		
		//shows all clients in database 
		JButton showAll = new JButton("Show All");
		clientSearch.add(showAll);
		showAll.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				regClients = database.getClients();
				displayclients(companymain);
			}
		});

	}
	
	// display the clients with a table
	
	public void displayclients(CompanyMain companymain) {
		
		viewClients.removeAll();
		DefaultTableModel tableModel = new DefaultTableModel();
		JTable table = new JTable(tableModel);
		String[] columnNames = {
				"Company",
                "Name",
                "mail",
                "address"
                };
		
		for (String s : columnNames) {
			tableModel.addColumn(s);
		}
		
		for (client c : regClients) {
			tableModel.insertRow(0, new Object[] {c.getCompany(),c.getName(),c.getEmail(),c.getAddress()});
		}
		viewClients.add(new JScrollPane(table), BorderLayout.CENTER);
		companymain.getCl().show(companymain.getCards(), "viewClients");
	}
	
	
	public JPanel getViewClients() {
		return viewClients;
	}

	public JPanel getClientSearch() {
		return clientSearch;
	}
}
