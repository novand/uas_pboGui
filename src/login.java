import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JPasswordField;


public class login {

	private JFrame frame;
	private JTextField txUsername;
	private JLabel lbError;
	private JPasswordField txPassword;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
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
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//calling DatabaseQuery class
				DatabaseQuery db = new DatabaseQuery();
				//connect to db
				db.connect();
				//get input from user
				String input_username = txUsername.getText();
				String input_password = txPassword.getText();
				//check user in db, return true is exist, else false
				boolean check = db.checkUser(input_username, input_password);
				if(check){
					String userLevel = db.getUserLevel();
					lbError.setText("OK");
				}
				else{
					lbError.setText("User dan password tidak ditemukan");
				}
					
				db.close();
				//coba3 coba = new coba3();
				//coba.setVisible(true);
				//frame.setVisible(false);
			}
		});
		btnLogin.setBounds(184, 141, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		txUsername = new JTextField();
		txUsername.setBounds(148, 56, 125, 20);
		frame.getContentPane().add(txUsername);
		txUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(75, 59, 63, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(75, 91, 63, 14);
		frame.getContentPane().add(lblPassword);
		
		lbError = new JLabel("");
		lbError.setForeground(Color.RED);
		lbError.setBounds(148, 175, 240, 14);
		frame.getContentPane().add(lbError);
		
		txPassword = new JPasswordField();
		txPassword.setBounds(148, 88, 125, 20);
		frame.getContentPane().add(txPassword);
	}
}
