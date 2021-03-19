import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.chart.JFreeChart;


public class CompanyMain {
	
	private JPanel cards;
	private CardLayout cl;

	public JPanel getCards() {
		return cards;
	}

	public CardLayout getCl() {
		return cl;
	}

	public CompanyMain(final Database database, final JFrame main) {
		
		final JFrame company = new JFrame("Company Page");
		
		
		//add background
		ImageIcon img = new ImageIcon("img/shippy.png");
		Image image = img.getImage();
		Image newimg = image.getScaledInstance(800, 600,  java.awt.Image.SCALE_SMOOTH);   
		img = new ImageIcon(newimg);
		JLabel logo = new JLabel("");
		logo.setIcon(img);
		
		JPanel pic = new JPanel();
		pic.add(logo);
		main.add(pic, BorderLayout.CENTER);
		
		JPanel options = new JPanel();
		final JPanel menupanel = new JPanel();
		menupanel.setSize(new Dimension(500, 400));
		menupanel.add(pic,BorderLayout.CENTER);
		
		
		final JPanel rest2 = new JPanel();
		rest2.setSize(new Dimension(500, 400));
		rest2.setBackground(Color.RED);
		cards = new JPanel(new CardLayout());
		cards.add(menupanel, "menu");
		cards.add(rest2, "rest2");
		
		JButton menu = new JButton("Logout");
		menu.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		menu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				main.setVisible(true);
				company.dispose();
			}
		});
		
		
		cl = (CardLayout)(cards.getLayout());
		
		JButton clients = new JButton("View Clients");
		clients.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		ClientSectionPanels c = new ClientSectionPanels(database, this);
		cards.add(c.getClientSearch(), "clientSearch");
		cards.add(c.getViewClients(), "viewClients");
		
		
		JButton journeys = new JButton("View Journeys");
		journeys.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		JourneySectionPanels j = new JourneySectionPanels(database, this);
		cards.add(j.getJourneySearch(), "journeySearch");
		cards.add(j.getViewJourneys(), "viewJourneys");
		cards.add(j.getinfo(), "info");
		
		
		JButton containers = new JButton("View Containers");
		containers.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		ContainerSelectionPanels cont = new ContainerSelectionPanels(database, this);
		cards.add(cont.getContainerSearch(), "containerSearch");
		cards.add(cont.getViewContainers(), "viewContainers");
		

		
	
		
		
		// menu button
		
		menu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				cl.show(cards, "menu");
			}
		});
		
		// client button
		
		clients.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				cl.show(cards, "clientSearch");
			}
		});
		
		
		
		// journey button
		
		journeys.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//cl.show(cards, "info");
				
				cl.show(cards,  "journeySearch");
			}
		});
		
		// container button
		
		containers.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				cl.show(cards,  "containerSearch");
			}
		});
		
		
		
		
		options.setLayout(new BoxLayout(options, BoxLayout.X_AXIS));
		company.add(options, BorderLayout.SOUTH);
		company.add(cards, BorderLayout.NORTH);
		cl.show(cards, "menu");
		options.add(clients);
		options.add(journeys);
		options.add(containers);
		options.add(menu);
		
		company.pack();	
	
		company.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		company.setVisible(true);
	}
	
}
