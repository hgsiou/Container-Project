import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIMain {
	
	static Database database = new Database(); 
	public static void main(String[] args) {
	final JFrame init = new JFrame("Shippy McShipface");
	
	
	JPanel top = new JPanel();
	top.setPreferredSize(new Dimension(250, 50));

	JPanel panel = new JPanel();
 
	panel.setPreferredSize(new Dimension(400,100));
	
	JLabel lbl = new JLabel("Hello, would you like to register or act as a company");
	
	JButton rbutton = new JButton("Register");
	rbutton.setPreferredSize(new Dimension(100, 50));
	rbutton.addActionListener(new ActionListener() {
	
		public void actionPerformed(ActionEvent e) {
			new RegistrationWindow(database, init);
		
		}	
	});
	JButton bCompany = new JButton("Company");
	bCompany.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			new CompanyLoginFrame(database, init);
			init.dispose();
			
		}
		
	});
		

	bCompany.setPreferredSize(new Dimension(100, 50));
	
	
	init.add(panel, BorderLayout.CENTER);
	panel.add(rbutton, BorderLayout.WEST);
	panel.add(bCompany, BorderLayout.LINE_END);
	top.add(lbl, BorderLayout.LINE_START);
	init.add(top, BorderLayout.NORTH);
	init.pack();
	init.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	init.setVisible(true);
	}
}
