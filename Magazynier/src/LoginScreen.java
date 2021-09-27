import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import java.awt.Window.Type;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class LoginScreen {

	private JFrame frame;
	private int x,y;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen window = new LoginScreen();
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
	public LoginScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
	
		frame.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x=e.getX();
				y=e.getY();
			}
		});
		frame.getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				frame.setLocation(frame.getX()+e.getX()-x, frame.getY()+e.getY()-y);
				
			}
		});
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		
		JButton btnNewButton = new JButton("X");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setForeground(Color.BLACK);
				btnNewButton.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setForeground(Color.WHITE);
				btnNewButton.setBackground(Color.DARK_GRAY);
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusPainted(false);
		
		JButton btnNewButton_1 = new JButton("---");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setForeground(Color.BLACK);
				btnNewButton_1.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setForeground(Color.WHITE);
				btnNewButton_1.setBackground(Color.DARK_GRAY);
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setExtendedState(JFrame.ICONIFIED);
			}
		});
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(Color.WHITE);
		
		JLabel lblHaso = new JLabel("Has\u0142o");
		lblHaso.setForeground(Color.WHITE);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBackground(Color.DARK_GRAY);
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setFocusPainted(false);
		Image Bimage = new ImageIcon(this.getClass().getResource("/login-button-png-18034.png")).getImage();
		btnNewButton_2.setIcon(new ImageIcon(Bimage.getScaledInstance(170, 80, 0)));
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login log = new Login();
				try {
					if(log.isZalog(textField.getText().toString(),passwordField.getText().toString())) {
						log.setLogin(textField.getText().toString());
						mainScreen.mainOkno();
						frame.dispose();
					}else {
						System.out.println("Nie zalogowano");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(603)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(327)
					.addComponent(lblNewLabel)
					.addContainerGap(365, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(328)
					.addComponent(lblHaso, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(353, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(194)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(passwordField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
						.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
					.addGap(233))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(248)
					.addComponent(btnNewButton_2)
					.addContainerGap(267, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(68)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
					.addGap(27)
					.addComponent(lblHaso)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(btnNewButton_2)
					.addGap(26))
		);
		frame.getContentPane().setLayout(groupLayout);
		frame.setBounds(100, 100, 717, 371);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		
	}
}
