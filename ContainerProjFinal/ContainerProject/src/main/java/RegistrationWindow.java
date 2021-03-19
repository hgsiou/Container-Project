import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegistrationWindow {

	private JFrame registered;
	private JFrame failedReg;

	public RegistrationWindow(final Database database, final JFrame main) {

		final JFrame clientfield = new JFrame("Register Page");

		JLabel login = new JLabel("Register");

		JPanel info = new JPanel();
		info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
	
		info.add(new JLabel("Insert your company/username:"));
		final JTextField user = new JTextField();
		user.setPreferredSize(new Dimension(100, 25));
		info.add(user);

		info.add(new JLabel("Insert your reference person:"));
		final JTextField ref = new JTextField();
		ref.setPreferredSize(new Dimension(100, 25));
		info.add(ref);

		info.add(new JLabel("Insert your e-mail:"));
		final JTextField mail = new JTextField();
		mail.setPreferredSize(new Dimension(100, 25));
		info.add(mail);

		info.add(new JLabel("Insert your address:"));
		final JTextField address = new JTextField();
		address.setPreferredSize(new Dimension(100, 25));
		info.add(address);

		info.add(new JLabel("Insert your password:"));
		final JPasswordField passwordReg = new JPasswordField();
		passwordReg.setPreferredSize(new Dimension(100, 25));
		info.add(passwordReg);

		JPanel choices = new JPanel();
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		//		main.setVisible(true);
				clientfield.dispose();
			}
		});

		JButton register = new JButton("Register");
		register.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String password = new String(passwordReg.getPassword());
				if ((database.search(user.getText()).size() == 0) && (!user.getText().isBlank()) ) {

						database.createClient(user.getText(), address.getText(), mail.getText(), ref.getText(),
								password);
						

						registered = new JFrame("Registered");

						JLabel lbl = new JLabel(" Client Sucessfully Registered");

						JButton b = new JButton("woohoo");
						b.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								mail.setText("");
								user.setText("");
								ref.setText("");
								address.setText("");
								passwordReg.setText("");
								registered.dispose();
							}
						});
						registered.add(lbl, BorderLayout.NORTH);
						registered.add(b, BorderLayout.SOUTH);
						registered.pack();
						registered.setVisible(true);
					}
				else if (user.getText().isBlank()) {
					noempty();
				}
				else {
					failreg();
				}
			}
		});

		

		clientfield.add(login, BorderLayout.NORTH);
		clientfield.add(choices, BorderLayout.SOUTH);
		clientfield.add(info, BorderLayout.CENTER);
		choices.add(register);
		choices.add(exit);
		clientfield.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		clientfield.pack();
		clientfield.setVisible(true);
	}

	

	public void failreg() {

		failedReg = new JFrame("Failed to register");

		JLabel lbl2 = new JLabel(" Sorry, someone already has that username");

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

		JLabel lbl2 = new JLabel(" You can't register nothing");

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
