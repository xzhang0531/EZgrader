package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	protected JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 793, 521);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEzGrader = new JLabel("EZ Grader");
		lblEzGrader.setFont(new Font("Kohinoor Telugu", Font.BOLD | Font.ITALIC, 24));
		lblEzGrader.setBounds(310, 136, 194, 65);
		frame.getContentPane().add(lblEzGrader);
		
		JLabel lblNewLabel = new JLabel("UserName :");
		lblNewLabel.setFont(new Font("Lao Sangam MN", Font.PLAIN, 16));
		lblNewLabel.setBounds(176, 210, 111, 33);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(310, 214, 167, 29);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("PassWord :");
		lblPassword.setFont(new Font("Lao Sangam MN", Font.PLAIN, 16));
		lblPassword.setBounds(176, 276, 111, 33);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(310, 277, 167, 33);
		frame.getContentPane().add(passwordField);
		JLabel lblInvalidUsernameOr = new JLabel("Invalid username or password, Enter again!");
		lblInvalidUsernameOr.setVisible(false);
		lblInvalidUsernameOr.setBounds(233, 321, 285, 29);
		frame.getContentPane().add(lblInvalidUsernameOr);
		
		JButton btnNewButton = new JButton("Sign In");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password=new String(passwordField.getPassword());
				if (textField.getText().equals("cpk") && password.equals("cs591")) {
					frame.dispose();
					Welcome frame2 = new Welcome();
					frame2.frame.setVisible(true);
				} else {
					lblInvalidUsernameOr.setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(317, 362, 131, 44);
		frame.getContentPane().add(btnNewButton);
		

		
		
		ImageIcon image = new ImageIcon("./image/BU.jpg");
		JLabel lblNewLabel_1 = new JLabel(image);
		
		lblNewLabel_1.setBounds(230, 30, image.getIconWidth(), image.getIconHeight());
		frame.getContentPane().add(lblNewLabel_1);
		

	}
}
