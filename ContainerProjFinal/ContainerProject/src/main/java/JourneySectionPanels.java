import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class JourneySectionPanels {

	private JPanel journeySearch;
	private JPanel viewJourneys;
	private ArrayList<Journey> wJourneys;
	private JPanel info;
	private JFrame failedReg;
	
	public JPanel getJourneySearch() {
		return journeySearch;
	}

	public JPanel getViewJourneys() {
		return viewJourneys;
	}

	public JPanel getinfo() {
		return info;
	}
	public JourneySectionPanels(final Database database, final CompanyMain companymain) {
		
		
		journeySearch = new JPanel();
		journeySearch.setPreferredSize(new Dimension(800, 600));
		journeySearch.setLayout(new FlowLayout(BoxLayout.Y_AXIS));
	//	journeySearch.setLayout(new BoxLayout(journeySearch, BoxLayout.X_AXIS));
	
		
		viewJourneys = new JPanel(new BorderLayout());
		viewJourneys.setPreferredSize(new Dimension(800, 600));
		
		info= new  JPanel(new BorderLayout());
		info.setPreferredSize(new Dimension(800, 600));
		info.setLayout(new FlowLayout(BoxLayout.Y_AXIS));
		
		info.add(new JLabel("What's the Origin:"));
		final JTextField origin = new JTextField();
		origin.setPreferredSize(new Dimension(100, 25));
		info.add(origin);

		info.add(new JLabel("Where is this going?:"));
		final JTextField destination = new JTextField();
		destination.setPreferredSize(new Dimension(100, 25));
		info.add(destination);

		info.add(new JLabel("What's it carrying?"));
		final JTextField load = new JTextField();
		load.setPreferredSize(new Dimension(100, 25));
		info.add(load);

		info.add(new JLabel("Which company are you registering under?  *note, company must already exist under clients*"));
		final JTextField comp = new JTextField();
		comp.setPreferredSize(new Dimension(100, 25));
		info.add(comp);
	
		
		JButton register = new JButton("make journey");
		register.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			
				if ((database.search(comp.getText()).size() == 0) || (comp.getText().isBlank()) ) {
						failreg();		
					}
				else if ((comp.getText().isBlank())&& load.getText().isBlank()) {
					noempty();
				}
				else {
					database.createJourney(origin.getText(), destination.getText(), load.getText(), comp.getText()	);
					companymain.getCl().show(companymain.getCards(), "journeySearch");
					comp.setText("");
					destination.setText("");
					load.setText("");
					origin.setText("");
				}
		
			
			}
		});
		info.add(register);
		
		JButton jmaker = new JButton("Create a journey here");
		journeySearch.add(jmaker);
		
		jmaker.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				companymain.getCl().show(companymain.getCards(), "info");
			}
		});

		journeySearch.add(new JLabel("Search for a journey here"));
		final JTextField search = new JTextField();
		search.setPreferredSize(new Dimension(100, 25));
		journeySearch.add(search);
		
		JButton searchButton = new JButton("Search");
		journeySearch.add(searchButton);
		
		searchButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String keyword = search.getText();
				ArrayList<Journey> result = new ArrayList<Journey>();
				result.addAll(database.findUsingLoop(keyword));
				wJourneys = result;
				displayJourneys(companymain, database);
			}
		});
		
		JButton showAll = new JButton("Show All");
		journeySearch.add(showAll);
		showAll.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				wJourneys = database.getJourney();
				displayJourneys(companymain, database);
			}

		});
		
	}
	

	
	public void displayJourneys(CompanyMain companymain, Database database) {
		viewJourneys.removeAll();
		
	
		DefaultTableModel tableModel = new DefaultTableModel();
		JTable table = new JTable(tableModel);
		String[] columnNames = {
				"ID",
                "Origin",
                "Destination",
                "cur. Location",
                "Container ID's"
                };
		
		for (String s : columnNames) {
			tableModel.addColumn(s);
		}
		for (Journey j : wJourneys) {
			ArrayList<String> containerids = new ArrayList<String>();
			for (Container c : j.getContainerList()) {
				containerids.add(c.getContainerId());
			}
			
			tableModel.insertRow(0, new Object[] {j.getId(),j.getOrigin(),j.getDestination(),j.getCurrentLocation(), containerids});
		}
		viewJourneys.add(new JScrollPane(table), BorderLayout.NORTH);
		companymain.getCl().show(companymain.getCards(), "viewJourneys");
	}
	
	public void failreg() {

		failedReg = new JFrame("Failed to register");

		JLabel lbl2 = new JLabel(" Your company is non-existant, check the client list to find an existing company");

		JButton b2 = new JButton("Okkie");
		b2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				failedReg.dispose();
			}
		});
		failedReg.add(lbl2, BorderLayout.NORTH);
		failedReg.add(b2, BorderLayout.SOUTH);
		failedReg.pack();
		failedReg.setVisible(true);
	}
	
	public void noempty() {

		failedReg = new JFrame("Failed to register");

		JLabel lbl2 = new JLabel(" You're Missing Infornmation");

		JButton b2 = new JButton("Okkie");
		b2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				failedReg.dispose();
			}
		});
		failedReg.add(lbl2, BorderLayout.NORTH);
		failedReg.add(b2, BorderLayout.SOUTH);
		failedReg.pack();
		failedReg.setVisible(true);
	}

	
}
