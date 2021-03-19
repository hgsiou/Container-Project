import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CompanyLoginFrame {
	
	private String name = "anna";
	private String pass = "1";
	
	public CompanyLoginFrame(final Database database, final JFrame main) {
		final JFrame company = new JFrame("Company Login");

		JLabel login = new JLabel("  Login:");

		JPanel usernamePanel = new JPanel();
		JLabel userLbl = new JLabel("Username: ");
		final JTextField username = new JTextField("anna");
		username.setPreferredSize(new Dimension(100, 25));
		usernamePanel.add(userLbl, BorderLayout.LINE_START);
		usernamePanel.add(username, BorderLayout.LINE_END);
		
		JPanel passwordPanel = new JPanel();
		JLabel passLbl = new JLabel("Password: ");
		final JPasswordField password = new JPasswordField("1");
		password.setPreferredSize(new Dimension(100, 25));
		passwordPanel.add(passLbl, BorderLayout.LINE_START);
		passwordPanel.add(password, BorderLayout.LINE_END);		
		
		JButton exit = new JButton("exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				company.dispose();
				main.setVisible(true);
			
			}	
					});
		

		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String passtext = new String(password.getPassword());
				if (username.getText().equals(name) && passtext.equals(pass)) {
					new CompanyMain(database, main);
					company.dispose();
				}
				else {
					final JFrame failedLogin = new JFrame("Failed to login");
					
					JLabel lbl = new JLabel(" hint: username: anna password: 1");
				
					
					
					JButton b = new JButton("OK");
					b.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							failedLogin.dispose();
						}
						
					});
					failedLogin.add(lbl, BorderLayout.NORTH);
					failedLogin.add(b, BorderLayout.SOUTH);
					failedLogin.pack();
					failedLogin.setVisible(true);
				}
				
			
			}
		});
		
		JPanel loginField = new JPanel();
		JPanel option= new JPanel();
		company.add(login, BorderLayout.WEST);
		option.add(confirm, BorderLayout.WEST);
		option.add(exit,BorderLayout.EAST);
		company.add(loginField, BorderLayout.CENTER);
		loginField.add(usernamePanel, BorderLayout.PAGE_START);
		loginField.add(passwordPanel, BorderLayout.PAGE_END);
		company.add(option, BorderLayout.PAGE_END);
		company.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		company.pack();
		company.setVisible(true);
	}
}
